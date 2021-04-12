package zenit.spider.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Парсирање елемената по класи. 
 * @author MV
 * @version 1.0
 */
public class WebClassParserBean implements Serializable{
	private static final long serialVersionUID = 477486467296801490L;
	private HashMap<URI, String> linksMap = new HashMap<>();
	private String url = "";
	private String clazz = ""; 
	
	private static HashMap<URI, String> findLinks(String url, String clazz) throws IOException {
		URIBuilder basic = new URIBuilder(URI.create(url));
		HashMap<URI, String> linksMap = new HashMap<>(); 
		Document doc = Jsoup.connect(url).get();
		Elements elems = doc.getElementsByClass(clazz);
		for(Element elem: elems) {
			Elements links = elem.getElementsByTag("a");
			for (Element link : links) {
				try {
					String linkHref = link.attr("href");
					String linkText = link.text();
					URIBuilder builder = new URIBuilder(URI.create(linkHref));
					if(builder.getScheme()==null) builder.setScheme(basic.getScheme());
					if(builder.getPort()==-1)     builder.setPort(basic.getPort());
					if(builder.getHost()==null)   builder.setHost(basic.getHost());
					URI uri = builder.build();
					linksMap.put(uri, linkText);
				}catch(Exception ex) {
					continue;
				}
			}
		}
		return linksMap;
	}
	
	public  void readLinks(HttpServletRequest request){
		try {
			String linxPage = request.getParameter("url");
			String clazz = request.getParameter("clazz"); 
			if(linxPage==null) linxPage = "";
			if(clazz==null) clazz = ""; 
			this.url = linxPage;
			this.clazz = clazz; 
			this.linksMap = findLinks(linxPage, clazz);
		}catch(Exception ex) {
			this.linksMap =  new HashMap<>();
		}
	}
	
	public  void resetLinks(HttpServletRequest request){
		url = "";  
		clazz = "";
		this.linksMap.clear();
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
}
