package org.unibl.etf.spider.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.unibl.etf.spider.web.model.SubjectDescriptor;

/**
 * Зрно које се односи на разврставање ликова за предмете на ЕТФ БЛ, по семестрима
 * на разним студијским програмима. Акценат је при могућностима колекција.  
 * @author MV
 * @version 1.0
 */
public class ETFSubjectsCollectionBean implements Serializable{
	private static final long serialVersionUID = -996348876567384206L;
	private HashMap<URI, String> linksMap = new HashMap<>();
	private HashMap<String, HashMap<SubjectDescriptor, URI>> semesterMap = new HashMap<>();
	
	private int count = 0; 
	private String url = "";
	private String clazz = ""; 
	
	private void findLinks(String url, String clazz) throws IOException {
		URIBuilder basic = new URIBuilder(URI.create(url));
		HashMap<URI, String> linksMap = new HashMap<>(); 
		Document doc = Jsoup.connect(url).get();
		Elements elems = doc.getElementsByClass(clazz);
		for(Element elem: elems) {
			Elements rows = elem.getElementsByTag("tr");
			for (Element row : rows) {
				Elements links = row.getElementsByTag("a");
				if(links.size()==0) continue;
				Elements data = row.getElementsByTag("td");
				SubjectDescriptor sd = new SubjectDescriptor();
				for(int i=0; i<data.size(); i++) {
					if(i==2)  sd.setSemester(data.get(i).text());
					if(i==3)  sd.setEcts(data.get(i).text());
					if(i==4)  sd.setFound(data.get(i).text());
					if(i==5)  sd.setType(data.get(i).text());
				}
				URI urn = null;
				for(Element link: links) {
					try {
						String linkHref = link.attr("href");
						String linkText = link.text();
						sd.setHref(linkHref);
						sd.setName(linkText);
						URIBuilder builder = new URIBuilder(URI.create(linkHref));
						if(builder.getScheme()==null) builder.setScheme(basic.getScheme());
						if(builder.getPort()==-1)     builder.setPort(basic.getPort());
						if(builder.getHost()==null)   builder.setHost(basic.getHost());
						URI uri = urn = builder.build();
						linksMap.put(uri, linkText);
						sd.setHref(uri.toString());
					}catch(Exception ex) {
						continue;
					}
				}
				String semesterName = ""; 
				switch(Integer.parseInt(sd.getSemester())) {
					case 1:
						semesterName = "ГОДИНА 1 - СЕМЕСТАР 1"; 
						break;
					case 2: 
						semesterName = "ГОДИНА 1 - СЕМЕСТАР 2"; 
						break;
					case 3: 
						semesterName = "ГОДИНА 2 - СЕМЕСТАР 3"; 
						break;
					case 4: 
						semesterName = "ГОДИНА 2 - СЕМЕСТАР 4"; 
						break;
					case 5: 
						semesterName = "ГОДИНА 3 - СЕМЕСТАР 5"; 
						break;
					case 6: 
						semesterName = "ГОДИНА 3 - СЕМЕСТАР 6"; 
						break;
					case 7: 
						semesterName = "ГОДИНА 4 - СЕМЕСТАР 7"; 
						break;
					case 8: 
						semesterName = "ГОДИНА 4 - СЕМЕСТАР 8"; 
						break;
				}
				
				if(!semesterMap.containsKey(semesterName)) 
					semesterMap.put(semesterName, new HashMap<>()); 
				semesterMap.get(semesterName).put(sd, urn);
				count++; 
			}
		}
		this.linksMap = linksMap;
	}
	
	public  void readLinks(HttpServletRequest request){
		try {
			String linxPage = request.getParameter("url");
			String clazz = request.getParameter("clazz"); 
			if(linxPage==null) linxPage = "";
			if(clazz==null) clazz = ""; 
			linksMap.clear();
			semesterMap.clear();
			this.url = linxPage;
			this.clazz = clazz; 
			this.count = 0;
			findLinks(linxPage, clazz);
		}catch(Exception ex) {
			this.linksMap =  new HashMap<>();
		}
	}
	
	public  void resetLinks(HttpServletRequest request){
		url = "";  
		clazz = "";
		count = 0; 
		this.linksMap.clear();
		this.semesterMap.clear();
	}
	
	public String codeURI() {
		try {
			URI uri = URI.create(url);
			return uri.toASCIIString();
		}catch(Exception ex) {
			return "";
		}
	}
	
	public String codeURI(String url) {
		try {
			URI uri = URI.create(url);
			return uri.toASCIIString();
		}catch(Exception ex) {
			return "";
		}
	}
	
	
	public String escapeURI() {
		try {
			return URLEncoder.encode(url, "UTF-8");
		}catch(Exception ex) {
			return "";
		}
	}
	
	public String escapeURI(URI uri) {
		try {
			return URLEncoder.encode(uri.toString(), "UTF-8");
		}catch(Exception ex) {
			return "";
		}
	}

	public HashMap<URI, String> getLinksMap() {
		return new HashMap<>(linksMap);
	}

	public String getUrl() {
		return url;
	}


	public String getClazz() {
		return clazz;
	}

	public SubjectDescriptor forName(String sdName) {
		for(HashMap<SubjectDescriptor, URI> sdMap: this.semesterMap.values()) 
			for(SubjectDescriptor sd:sdMap.keySet()) 
				if(sd.getName().equals(sdName)) return sd;
		return null; 
	}
	
	public List<String> getSemesters() {
		ArrayList<String> semesters = new ArrayList<>();
		for(String semester: semesterMap.keySet()) semesters.add(semester);
		Collections.sort(semesters);
		return semesters;
	}
	
	public List<URI> getUriForSemester(String semesterNo){ 
		ArrayList<URI> subjects = new ArrayList<>();
		if(!semesterMap.containsKey(semesterNo)) return subjects;
		for(URI subject: semesterMap.get(semesterNo).values()) 
			subjects.add(subject); 
		
		for(int i=0; i<subjects.size()-1; i++) {
			for(int j=i+1; j<subjects.size(); j++) {
				URI subjectA = subjects.get(i); 
				URI subjectB = subjects.get(j);
				String titleA = linksMap.get(subjectA); 
				String titleB = linksMap.get(subjectB);
				if(titleA.compareTo(titleB)>0) {
					subjects.set(i, subjectB);
					subjects.set(j, subjectA);
				}
			}
		}
		return subjects; 
	}
	
	public List<SubjectDescriptor> getDataForSemester(String semesterNo){
		ArrayList<SubjectDescriptor> subjects = new ArrayList<>();
		if(!semesterMap.containsKey(semesterNo)) return subjects;
		for(SubjectDescriptor subject: semesterMap.get(semesterNo).keySet()) 
			subjects.add(subject); 
		
		for(int i=0; i<subjects.size()-1; i++) {
			for(int j=i+1; j<subjects.size(); j++) {
				SubjectDescriptor subjectA = subjects.get(i); 
				SubjectDescriptor subjectB = subjects.get(j);
				String titleA = subjectA.getName(); 
				String titleB = subjectB.getName();
				if(titleA.compareTo(titleB)>0) {
					subjects.set(i, subjectB);
					subjects.set(j, subjectA);
				}
			}
		}
		return subjects; 
	}

	public int getCount() {
		return count;
	}
}
