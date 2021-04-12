package zenit.agregator.web.bean;

import java.io.Serializable;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Whitelist;

import zenit.agregator.adapter.data.GlueNotedProgramSchema;
import zenit.agregator.adapter.data.SemestralProgramSchema;
import zenit.agregator.adapter.proxy.SwitchProgramGNAdapter;
import zenit.agregator.adapter.proxy.SwitchProgramSubjectsAdapter;
import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.web.listener.ControllerListener;

/**
 * Зрно које је задужено за манервар са табелама које су везане
 * за студентске програме, како за извјештаје тако и за 
 * ручни унос података.
 * @author VM
 * @version 1.1
 */
public class ProgramReportBean implements Serializable{
	private static final long serialVersionUID = 5184940778052639953L;
	private transient SwitchProgramSubjectsAdapter programSubjectsAdapter;
	private transient SwitchProgramGNAdapter basicProgramGNAAdapter;
	private GlueNotedProgramSchema schema;
	private ProgramGlueNotes notes;
	private boolean initialized;
	private boolean hosted;
	
	
	public void initialize(HttpSession session) {
		if(initialized) return; 
		basicProgramGNAAdapter = ControllerListener.getEdgeController(session).getProgramGNIOSwitch();
		programSubjectsAdapter = ControllerListener.getEdgeController(session).getProgramSubjectsIOSwitch(); 
		initialized = true;
	}
	
	public void refresh(HttpServletRequest request) {
		hosted = false;
		notes = null;
		schema = null;
	}
	
	public SwitchProgramSubjectsAdapter getProgramSubjectsAdapter() {
		return programSubjectsAdapter;
	}
	
	public ProgramGlueNotes notes(HttpServletRequest request){
		String programName = request.getParameter("program_name");
		if(programName==null) return null;
		ProgramGlueNotes notes = basicProgramGNAAdapter.get(programName);
		if(notes==null) return new ProgramGlueNotes();
		return notes; 
	}
	
	public GlueNotedProgramSchema get(HttpServletRequest request){
		String programName = request.getParameter("program_name");
		if(programName==null) return null; 
		return programSubjectsAdapter.semestralSchemaForProgram(programName, notes);
	}
	
	public void rehost(HttpServletRequest request) {
		notes = notes(request);
		schema = get(request);
		hosted = true;
	}
	
	public void host(HttpServletRequest request) {
		if(hosted) return; 
		notes = notes(request);
		schema = get(request);
		hosted = true;
	}

	public SemestralProgramSchema getSchema() {
		return schema;
	}
	
	public String uriEncode(String textForUrl) {
		try {
			return URLEncoder.encode(textForUrl, "UTF-8"); 
		}catch(Exception ex) {
			return "";
		}
	}
	
	public void putGlueNotes(HttpServletRequest request) {
		try {
			String programId = request.getParameter("program_id");
			if(programId==null) return;
			ProgramGlueNotes programGlueNotes = new ProgramGlueNotes();
			programGlueNotes.setProgramName(programId);
			String s0f1 = URLDecoder.decode(request.getParameter("semester_0_field_1")==null?"":request.getParameter("semester_0_field_1"), "UTF-8");
			String s0f2 = URLDecoder.decode(request.getParameter("semester_0_field_2")==null?"":request.getParameter("semester_0_field_2"), "UTF-8");
			String s0f3 = URLDecoder.decode(request.getParameter("semester_0_field_3")==null?"":request.getParameter("semester_0_field_3"), "UTF-8");
			String s0f4 = URLDecoder.decode(request.getParameter("semester_0_field_4")==null?"":request.getParameter("semester_0_field_4"), "UTF-8");
			String s1f1 = URLDecoder.decode(request.getParameter("semester_1_field_1")==null?"":request.getParameter("semester_1_field_1"), "UTF-8");
			String s1f2 = URLDecoder.decode(request.getParameter("semester_1_field_2")==null?"":request.getParameter("semester_1_field_2"), "UTF-8");
			String s1f3 = URLDecoder.decode(request.getParameter("semester_1_field_3")==null?"":request.getParameter("semester_1_field_3"), "UTF-8");
			String s1f4 = URLDecoder.decode(request.getParameter("semester_1_field_4")==null?"":request.getParameter("semester_1_field_4"), "UTF-8");
			String s2f1 = URLDecoder.decode(request.getParameter("semester_2_field_1")==null?"":request.getParameter("semester_2_field_1"), "UTF-8");
			String s2f2 = URLDecoder.decode(request.getParameter("semester_2_field_2")==null?"":request.getParameter("semester_2_field_2"), "UTF-8");
			String s2f3 = URLDecoder.decode(request.getParameter("semester_2_field_3")==null?"":request.getParameter("semester_2_field_3"), "UTF-8");
			String s2f4 = URLDecoder.decode(request.getParameter("semester_2_field_4")==null?"":request.getParameter("semester_2_field_4"), "UTF-8");
			String s3f1 = URLDecoder.decode(request.getParameter("semester_3_field_1")==null?"":request.getParameter("semester_3_field_1"), "UTF-8");
			String s3f2 = URLDecoder.decode(request.getParameter("semester_3_field_2")==null?"":request.getParameter("semester_3_field_2"), "UTF-8");
			String s3f3 = URLDecoder.decode(request.getParameter("semester_3_field_3")==null?"":request.getParameter("semester_3_field_3"), "UTF-8");
			String s3f4 = URLDecoder.decode(request.getParameter("semester_3_field_4")==null?"":request.getParameter("semester_3_field_4"), "UTF-8");
			String s4f1 = URLDecoder.decode(request.getParameter("semester_4_field_1")==null?"":request.getParameter("semester_4_field_1"), "UTF-8");
			String s4f2 = URLDecoder.decode(request.getParameter("semester_4_field_2")==null?"":request.getParameter("semester_4_field_2"), "UTF-8");
			String s4f3 = URLDecoder.decode(request.getParameter("semester_4_field_3")==null?"":request.getParameter("semester_4_field_3"), "UTF-8");
			String s4f4 = URLDecoder.decode(request.getParameter("semester_4_field_4")==null?"":request.getParameter("semester_4_field_4"), "UTF-8");
			String s5f1 = URLDecoder.decode(request.getParameter("semester_5_field_1")==null?"":request.getParameter("semester_5_field_1"), "UTF-8");
			String s5f2 = URLDecoder.decode(request.getParameter("semester_5_field_2")==null?"":request.getParameter("semester_5_field_2"), "UTF-8");
			String s5f3 = URLDecoder.decode(request.getParameter("semester_5_field_3")==null?"":request.getParameter("semester_5_field_3"), "UTF-8");
			String s5f4 = URLDecoder.decode(request.getParameter("semester_5_field_4")==null?"":request.getParameter("semester_5_field_4"), "UTF-8");
			String s6f1 = URLDecoder.decode(request.getParameter("semester_6_field_1")==null?"":request.getParameter("semester_6_field_1"), "UTF-8");
			String s6f2 = URLDecoder.decode(request.getParameter("semester_6_field_2")==null?"":request.getParameter("semester_6_field_2"), "UTF-8");
			String s6f3 = URLDecoder.decode(request.getParameter("semester_6_field_3")==null?"":request.getParameter("semester_6_field_3"), "UTF-8");
			String s6f4 = URLDecoder.decode(request.getParameter("semester_6_field_4")==null?"":request.getParameter("semester_6_field_4"), "UTF-8");
			String s7f1 = URLDecoder.decode(request.getParameter("semester_7_field_1")==null?"":request.getParameter("semester_7_field_1"), "UTF-8");
			String s7f2 = URLDecoder.decode(request.getParameter("semester_7_field_2")==null?"":request.getParameter("semester_7_field_2"), "UTF-8");
			String s7f3 = URLDecoder.decode(request.getParameter("semester_7_field_3")==null?"":request.getParameter("semester_7_field_3"), "UTF-8");
			String s7f4 = URLDecoder.decode(request.getParameter("semester_7_field_4")==null?"":request.getParameter("semester_7_field_4"), "UTF-8");
			String s8f1 = URLDecoder.decode(request.getParameter("semester_8_field_1")==null?"":request.getParameter("semester_8_field_1"), "UTF-8");
			String s8f2 = URLDecoder.decode(request.getParameter("semester_8_field_2")==null?"":request.getParameter("semester_8_field_2"), "UTF-8");
			String s8f3 = URLDecoder.decode(request.getParameter("semester_8_field_3")==null?"":request.getParameter("semester_8_field_3"), "UTF-8");
			String s8f4 = URLDecoder.decode(request.getParameter("semester_8_field_4")==null?"":request.getParameter("semester_8_field_4"), "UTF-8");
			s0f1 = Jsoup.parse(s0f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s0f2 = Jsoup.parse(s0f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s0f3 = Jsoup.parse(s0f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s0f4 = Jsoup.parse(s0f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s1f1 = Jsoup.parse(s1f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s1f2 = Jsoup.parse(s1f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s1f3 = Jsoup.parse(s1f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s1f4 = Jsoup.parse(s1f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s2f1 = Jsoup.parse(s2f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s2f2 = Jsoup.parse(s2f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s2f3 = Jsoup.parse(s2f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s2f4 = Jsoup.parse(s2f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s3f1 = Jsoup.parse(s3f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s3f2 = Jsoup.parse(s3f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s3f3 = Jsoup.parse(s3f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s3f4 = Jsoup.parse(s3f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s4f1 = Jsoup.parse(s4f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s4f2 = Jsoup.parse(s4f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s4f3 = Jsoup.parse(s4f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s4f4 = Jsoup.parse(s4f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s5f1 = Jsoup.parse(s5f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s5f2 = Jsoup.parse(s5f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s5f3 = Jsoup.parse(s5f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s5f4 = Jsoup.parse(s5f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s6f1 = Jsoup.parse(s6f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s6f2 = Jsoup.parse(s6f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s6f3 = Jsoup.parse(s6f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s6f4 = Jsoup.parse(s6f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s7f1 = Jsoup.parse(s7f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s7f2 = Jsoup.parse(s7f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s7f3 = Jsoup.parse(s7f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s7f4 = Jsoup.parse(s7f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s8f1 = Jsoup.parse(s8f1).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s8f2 = Jsoup.parse(s8f2).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s8f3 = Jsoup.parse(s8f3).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s8f4 = Jsoup.parse(s8f4).outputSettings(new OutputSettings().prettyPrint(false)).html();
			s0f1 = Jsoup.clean(s0f1.replaceAll("<br>", "\n").replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s0f2 = Jsoup.clean(s0f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s0f3 = Jsoup.clean(s0f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s0f4 = Jsoup.clean(s0f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();			
			s1f1 = Jsoup.clean(s1f1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s1f2 = Jsoup.clean(s1f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s1f3 = Jsoup.clean(s1f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s1f4 = Jsoup.clean(s1f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s2f1 = Jsoup.clean(s2f1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s2f2 = Jsoup.clean(s2f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s2f3 = Jsoup.clean(s2f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s2f4 = Jsoup.clean(s2f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s3f1 = Jsoup.clean(s3f1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s3f2 = Jsoup.clean(s3f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s3f3 = Jsoup.clean(s3f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s3f4 = Jsoup.clean(s3f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s4f1 = Jsoup.clean(s4f1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s4f2 = Jsoup.clean(s4f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s4f3 = Jsoup.clean(s4f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s4f4 = Jsoup.clean(s4f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s5f1 = Jsoup.clean(s5f1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s5f2 = Jsoup.clean(s5f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s5f3 = Jsoup.clean(s5f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s5f4 = Jsoup.clean(s5f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s6f1 = Jsoup.clean(s6f1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s6f2 = Jsoup.clean(s6f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s6f3 = Jsoup.clean(s6f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s6f4 = Jsoup.clean(s6f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s7f1 = Jsoup.clean(s7f1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s7f2 = Jsoup.clean(s7f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s7f3 = Jsoup.clean(s7f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s7f4 = Jsoup.clean(s7f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s8f1 = Jsoup.clean(s8f1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s8f2 = Jsoup.clean(s8f2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s8f3 = Jsoup.clean(s8f3.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s8f4 = Jsoup.clean(s8f4.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
			s0f1 = StringEscapeUtils.unescapeHtml4(s0f1);
			s0f2 = StringEscapeUtils.unescapeHtml4(s0f2);
			s0f3 = StringEscapeUtils.unescapeHtml4(s0f3);
			s0f4 = StringEscapeUtils.unescapeHtml4(s0f4);
			s1f1 = StringEscapeUtils.unescapeHtml4(s1f1);
			s1f2 = StringEscapeUtils.unescapeHtml4(s1f2);
			s1f3 = StringEscapeUtils.unescapeHtml4(s1f3);
			s1f4 = StringEscapeUtils.unescapeHtml4(s1f4);
			s2f1 = StringEscapeUtils.unescapeHtml4(s2f1);
			s2f2 = StringEscapeUtils.unescapeHtml4(s2f2);
			s2f3 = StringEscapeUtils.unescapeHtml4(s2f3);
			s2f4 = StringEscapeUtils.unescapeHtml4(s2f4);
			s3f1 = StringEscapeUtils.unescapeHtml4(s3f1);
			s3f2 = StringEscapeUtils.unescapeHtml4(s3f2);
			s3f3 = StringEscapeUtils.unescapeHtml4(s3f3);
			s3f4 = StringEscapeUtils.unescapeHtml4(s3f4);
			s4f1 = StringEscapeUtils.unescapeHtml4(s4f1);
			s4f2 = StringEscapeUtils.unescapeHtml4(s4f2);
			s4f3 = StringEscapeUtils.unescapeHtml4(s4f3);
			s4f4 = StringEscapeUtils.unescapeHtml4(s4f4);
			s5f1 = StringEscapeUtils.unescapeHtml4(s5f1);
			s5f2 = StringEscapeUtils.unescapeHtml4(s5f2);
			s5f3 = StringEscapeUtils.unescapeHtml4(s5f3);
			s5f4 = StringEscapeUtils.unescapeHtml4(s5f4);
			s6f1 = StringEscapeUtils.unescapeHtml4(s6f1);
			s6f2 = StringEscapeUtils.unescapeHtml4(s6f2);
			s6f3 = StringEscapeUtils.unescapeHtml4(s6f3);
			s6f4 = StringEscapeUtils.unescapeHtml4(s6f4);
			s7f1 = StringEscapeUtils.unescapeHtml4(s7f1);
			s7f2 = StringEscapeUtils.unescapeHtml4(s7f2);
			s7f3 = StringEscapeUtils.unescapeHtml4(s7f3);
			s7f4 = StringEscapeUtils.unescapeHtml4(s7f4);
			s8f1 = StringEscapeUtils.unescapeHtml4(s8f1);
			s8f2 = StringEscapeUtils.unescapeHtml4(s8f2);
			s8f3 = StringEscapeUtils.unescapeHtml4(s8f3);
			s8f4 = StringEscapeUtils.unescapeHtml4(s8f4);
			programGlueNotes.setS0f1(s0f1);
			programGlueNotes.setS0f2(s0f2);
			programGlueNotes.setS0f3(s0f3);
			programGlueNotes.setS0f4(s0f4);
			programGlueNotes.setS1f1(s1f1);
			programGlueNotes.setS1f2(s1f2);
			programGlueNotes.setS1f3(s1f3);
			programGlueNotes.setS1f4(s1f4);
			programGlueNotes.setS2f1(s2f1);
			programGlueNotes.setS2f2(s2f2);
			programGlueNotes.setS2f3(s2f3);
			programGlueNotes.setS2f4(s2f4);
			programGlueNotes.setS3f1(s3f1);
			programGlueNotes.setS3f2(s3f2);
			programGlueNotes.setS3f3(s3f3);
			programGlueNotes.setS3f4(s3f4);
			programGlueNotes.setS4f1(s4f1);
			programGlueNotes.setS4f2(s4f2);
			programGlueNotes.setS4f3(s4f3);
			programGlueNotes.setS4f4(s4f4);
			programGlueNotes.setS5f1(s5f1);
			programGlueNotes.setS5f2(s5f2);
			programGlueNotes.setS5f3(s5f3);
			programGlueNotes.setS5f4(s5f4);
			programGlueNotes.setS6f1(s6f1);
			programGlueNotes.setS6f2(s6f2);
			programGlueNotes.setS6f3(s6f3);
			programGlueNotes.setS6f4(s6f4);
			programGlueNotes.setS7f1(s7f1);
			programGlueNotes.setS7f2(s7f2);
			programGlueNotes.setS7f3(s7f3);
			programGlueNotes.setS7f4(s7f4);
			programGlueNotes.setS8f1(s8f1);
			programGlueNotes.setS8f2(s8f2);
			programGlueNotes.setS8f3(s8f3);
			programGlueNotes.setS8f4(s8f4);
			for(String parameter: request.getParameterMap().keySet()) 
			   if(parameter.startsWith("subject_state_"))
				  try { 
					    String value = URLDecoder.decode(request.getParameter(parameter),"UTF-8"); 
					    value = Jsoup.parse(value).outputSettings(new OutputSettings().prettyPrint(false)).html();
						value = Jsoup.clean(value.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
					  programGlueNotes.addSubjectTypeNote(parameter.substring("subject_state_".length()), value);
				   } catch(Exception ex) {
					  continue;
				   }
			basicProgramGNAAdapter.update(programGlueNotes.getProgramName(), programGlueNotes);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ProgramGlueNotes getNotes() {
		return notes;
	}

	public void setNotes(ProgramGlueNotes notes) {
		this.notes = notes;
	}

	public void resetGlueNotes(HttpServletRequest request) {
		String programId = request.getParameter("program_id"); 
		if(programId==null) return;
		basicProgramGNAAdapter.remove(programId);
	}
	
	public String escape(String text) {
		return text.replaceAll("&", "&amp;")
				   .replaceAll("\"", "&quot;")
				   .replaceAll("'", "&apos;")
				   .replaceAll("<", "&lt;")
				   .replaceAll(">", "&gt;");
	}

	public SwitchProgramGNAdapter getBasicProgramGNAAdapter() {
		return basicProgramGNAAdapter;
	}
}
