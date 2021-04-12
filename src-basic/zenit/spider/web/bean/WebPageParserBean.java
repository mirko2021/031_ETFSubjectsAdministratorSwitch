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
 * Зрно које се може искористити за листање линкова са неког домена, односно странице.
 * @author MV
 * @version 1.0
 */
public class WebPageParserBean implements Serializable{
	private static final long serialVersionUID = -7409317156480288975L;
	private HashMap<URI, String> linksMap = new HashMap<>();
	private String url = "";
	
	private static HashMap<URI, String> findLinks(String url) throws IOException {
		URIBuilder basic = new URIBuilder(URI.create(url));
		HashMap<URI, String> linksMap = new HashMap<>(); 
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.getElementsByTag("a");
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
		return linksMap;
	}
	
	public  void readLinks(HttpServletRequest request){
		try {
			String linxPage = request.getParameter("url");
			if(linxPage==null) linxPage = ""; 
			this.url = linxPage;
			this.linksMap = findLinks(linxPage);
		}catch(Exception ex) {
			this.linksMap =  new HashMap<>();
		}
	}
	
	public  void resetLinks(HttpServletRequest request){
		url = "";  
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
}
