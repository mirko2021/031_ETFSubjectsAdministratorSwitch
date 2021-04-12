package zenit.collector.bean;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.etfbl.spider.adapter.ETFWebSiteAdapter;
import zenit.data.controller.SubjectController;
import zenit.data.impl.StandardSubject;
import zenit.data.model.Subject;
import zenit.web.listener.ControllerListener;

/**
 * Зрно којим се управља студијским предметима предметима. 
 * @author MV
 * @version 1.0
 */
public class SubjectBean implements Serializable{
	private static final long serialVersionUID = 3617750037754859707L;
	public static final boolean ERROR_REMIX = false;
	
	private transient SubjectController subjectController;
	private ETFWebSiteAdapter adapter = new ETFWebSiteAdapter();
	private boolean initialized; 
	
	public void initialize(HttpSession session) {
		if(initialized) return; 
		subjectController = new SubjectController(ControllerListener.getEdgeController(session));
		initialized = true; 
	}
	
	private boolean success = false; 
	private String message = ""; 
	private UtilBean utilBean = new UtilBean(); 

	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public UtilBean getUtilBean() {
		return utilBean;
	}
	

	public ETFWebSiteAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(ETFWebSiteAdapter adapter) {
		this.adapter = adapter;
	}

	public String getMessage() {
		String message = this.message; 
		this.message = ""; 
		this.success = false; 
		return message;
	}

	public void setMessage(String message) {
		if(message==null) throw new RuntimeException();
		this.message = message;
	}
	
	
	public Subject get(HttpServletRequest request) {
		try {
			String href = request.getParameter("href");
			if(href==null) return null;
			URL url = new URL(href);
			String html = "";
			try(Scanner scanner = new Scanner(url.openStream(), "UTF-8")) {
				while(scanner.hasNextLine()) 
					html += scanner.nextLine();
			}
			StandardSubject subject = new StandardSubject();
			Elements elements = Jsoup.parse(html).outputSettings(new OutputSettings().prettyPrint(false)).getElementsByTag("table");
			for(int i=0; i<elements.size(); i++) {
				Element e = elements.get(i);
				if(i==0) { 
					Elements rows = e.getElementsByTag("tr");
					subject.setSubjectId(rows.get(2).getAllElements().get(1).text());
					subject.setSubjectName(rows.get(0).getAllElements().get(2).text());
					subject.setSubjectState(rows.get(2).getAllElements().get(2).text());
					subject.setSemestar(rows.get(2).getAllElements().get(3).text());
					subject.setEctsValue(rows.get(2).getAllElements().get(5).text());
					subject.setLessonsFound(rows.get(2).getAllElements().get(4).text());
					subject.setLectors(rows.get(3).getAllElements().get(2).text());
				}
				if(i==1) {
					int rowMinus = 0;
					Elements rows = e.getElementsByTag("tr");
					subject.setEdges(rows.get(1).getAllElements().get(1).text());
					subject.setEdgesForm(rows.get(1).getAllElements().get(2).text());
					subject.setSubjectTarget(rows.get(3).getAllElements().get(1).text());
					subject.setSubjectRealisations(rows.get(5).getAllElements().get(1).text());
					subject.setSubjectContent(rows.get(7).getAllElements().get(1).text());
					subject.setMethodLectionTransfer(rows.get(9).getAllElements().get(1).text());
					Elements es = rows.get(11).getAllElements().get(1).getElementsByTag("li");
					ArrayList<String> references = new ArrayList<>();
					for(int j=0; j<es.size(); j++)
						references.add(es.get(j).text());
					subject.setReferences(references);
					subject.getGradulation().setGradulationNotes(rows.get(13).getAllElements().get(1).text());
					Elements firstRow = rows.get(14).getElementsByTag("td");
					Elements secondRow = rows.get(15).getElementsByTag("td");
					if(rows.get(15).getElementsByClass("plavi_naslov").size()!=0) rowMinus=1;
					ArrayList<String> gradeSystItems = new ArrayList<>();
					for(int j=0; j<firstRow.size(); j++) {
						if(j==firstRow.size()-1) continue;
						String text = firstRow.get(j).text(); 
						if(text.trim().length()<=1) {++j; continue;}
						try {
							if(text.contentEquals("Први колоквијум")) {
								subject.getGradulation().setFirstColocvium(Integer.parseInt(firstRow.get(++j).text()));
								continue;
							}
						}catch(Exception ex) {
							continue;
						}
						try {
							if(text.contentEquals("Други колоквијум")) {
								subject.getGradulation().setSecondColocvium(Integer.parseInt(firstRow.get(++j).text()));
								continue;
							}
						}catch(Exception ex) {
							continue;
						}
						try {
							if(text.contentEquals("Лабораторијске вјежбе")) {
								subject.getGradulation().setLaboratoryExercies(Integer.parseInt(firstRow.get(++j).text()));
								continue;
							}
						}catch(Exception ex) {
							continue;
						}
						try {
							if(text.contentEquals("Завршни испит")) {
								subject.getGradulation().setFinalExams(Integer.parseInt(firstRow.get(++j).text()));
								continue;
							}
						}catch(Exception ex) {
							continue;
						}
						gradeSystItems.add(firstRow.get(j).text()+": "+firstRow.get(++j).text());
					}
					
					if(rowMinus==0) 
					for(int j=0; j<secondRow.size(); j++) {
						if(j==secondRow.size()-1) continue;
						String text = secondRow.get(j).text();
						if(text.trim().length()<=0) {++j; continue;}
						try {
							if(text.contentEquals("Први колоквијум")) {
								subject.getGradulation().setFirstColocvium(Integer.parseInt(secondRow.get(++j).text()));
								continue;
							}
						}catch(Exception ex) {
							continue;
						}
						try {
							if(text.contentEquals("Други колоквијум")) {
								subject.getGradulation().setSecondColocvium(Integer.parseInt(secondRow.get(++j).text()));
								continue;
							}
						}catch(Exception ex) {
							continue;
						}
						try {
							if(text.contentEquals("Лабораторијске вјежбе")) {
								subject.getGradulation().setLaboratoryExercies(Integer.parseInt(secondRow.get(++j).text()));
								continue;
							}
						}catch(Exception ex) {
							continue;
						}
						try {
							if(text.contentEquals("Завршни испит")) {
								subject.getGradulation().setFinalExams(Integer.parseInt(secondRow.get(++j).text()));
								continue;
							}
						}catch(Exception ex) {
							continue;
						}
						gradeSystItems.add(secondRow.get(j).text()+": "+secondRow.get(++j).text());
					}
					
					Collections.sort(gradeSystItems);
					String otherExams = "";
					for(String exam: gradeSystItems) 
						otherExams+=exam+"\n";
					subject.getGradulation().setOtherExams(otherExams.trim());
					subject.setNotes(rows.get(17-rowMinus).getElementsByTag("td").get(0).text());
					subject.setDataMajor(rows.get(18-rowMinus).getElementsByTag("td").get(0).text().split(":",2)[1].trim());
				}
			}
			return subject;
		}catch(Exception ex) {
			return new StandardSubject();
		}
	}
	
	public SubjectController getSubjectController() {
		return subjectController;
	}

	public void update(HttpServletRequest request) {
		Subject subject = get(request);
		if(subject==null) {
			message = "Подаци о предмету нису учитани.";
			return; 
		}
		subjectController.remove(subject.getSubjectId());
		subjectController.add((StandardSubject) subject);
		message = "Подаци о предмету су учитани.";
	}
}
