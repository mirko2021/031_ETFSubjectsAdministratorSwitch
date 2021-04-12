package zenit.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Whitelist;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import zenit.data.controller.SubjectController;
import zenit.data.impl.StandardSubject;
import zenit.data.model.Subject;
import zenit.merges.controller.SubjectMergesController;
import zenit.web.listener.ControllerListener;

/**
 * Зрно којим се управља студијским предметима предметима. 
 * @author MV
 * @version 1.1
 */
public class SubjectBean implements Serializable{
	private static final long serialVersionUID = 3617750037754859707L;
	public static final boolean ERROR_REMIX = false;
	
	private transient SubjectMergesController subjectMergesController; 
	private transient SubjectController subjectController;
	private boolean initialized; 
	
	public void initialize(HttpSession session) {
		if(initialized) return; 
		subjectController = new SubjectController(ControllerListener.getEdgeController(session));
		subjectController.initializeSwitchAdapter(ControllerListener.getAdapterController(session));
		subjectMergesController = ControllerListener.getEdgeController(session).getSubjectMergesController();
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
	
	public SubjectController getSubjectController() {
		return subjectController;
	}
	
	public SubjectMergesController getSubjectMergesController() {
		return subjectMergesController;
	}

	public List<String> getSubjectKeys(){
		List<String> keys = subjectController.getSubjectIds();
		Collections.sort(keys);
		return keys;
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
	
	public void add(HttpServletRequest request) {
		try {
			String id = request.getParameter("subject_id");
			if(id==null) id = "";
			if(id.trim().length()==0) message="Додавање предмета није успјело.";
			if(id.trim().length()==0) return;
			if(!subjectController.add(new StandardSubject(id))) { 
				message="Додавање предмета није успјело.";
				success=false;
			}
			else {
				message="Додавање предмета је успјело.";
				success=true;
			}
		}catch(Exception ex) {
			message="Додавање предмета није успјело.";
			success=false;
		}
	}
	
	public void remove(HttpServletRequest request) {
		try {
			String id = request.getParameter("subject_id");
			if(id==null) id = "";
			if(id.trim().length()==0) message="Брисање предмета није успјело.";
			if(id.trim().length()==0) return;
			subjectController.remove(id);
			message="Брисање предмета је успјело.";
			success=true;
		}catch(Exception ex) {
			message="Брисање предмета није успјело.";
			success=false;
		}
	}
	
	public Subject get(HttpServletRequest request) {
		String id = request.getParameter("subject_id");
		if(id==null) return null;
		return subjectController.get(id);
	}
	
	public void update(HttpServletRequest request) {
		  String subjId                = Jsoup.parse(request.getParameter("subj_id")).outputSettings(new OutputSettings().prettyPrint(false)).html(); 
		  String subjectName           = Jsoup.parse(request.getParameter("subject_name")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String semester              = Jsoup.parse(request.getParameter("semestar")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String lessonsFound          = Jsoup.parse(request.getParameter("lessons_found")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String ectsValue             = Jsoup.parse(request.getParameter("ects_value")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String lectors               = Jsoup.parse(request.getParameter("lectors")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String edges                 = Jsoup.parse(request.getParameter("edges")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String edgesForm             = Jsoup.parse(request.getParameter("edges_form")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String subjectTarget         = Jsoup.parse(request.getParameter("subject_target")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String subjectRealisations   = Jsoup.parse(request.getParameter("subject_realisations")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String subjectContent        = Jsoup.parse(request.getParameter("subject_content")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String methodLectionTransfer = Jsoup.parse(request.getParameter("method_lection_transfer")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String dataMajor             = Jsoup.parse(request.getParameter("data_major")).outputSettings(new OutputSettings().prettyPrint(false)).html(); 
		  String subjectNotes          = Jsoup.parse(request.getParameter("subject_notes")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String subjectState          = Jsoup.parse(request.getParameter("subject_state")).outputSettings(new OutputSettings().prettyPrint(false)).html(); 
		  String labs                  = Jsoup.parse(request.getParameter("labs")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String k1                    = Jsoup.parse(request.getParameter("k1")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String k2                    = Jsoup.parse(request.getParameter("k2")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String exams                 = Jsoup.parse(request.getParameter("exams")).outputSettings(new OutputSettings().prettyPrint(false)).html();
		  String examsNotes            = Jsoup.parse(request.getParameter("exams_notes")).outputSettings(new OutputSettings().prettyPrint(false)).html(); 
		  String otherExams            = Jsoup.parse(request.getParameter("other_exams")).outputSettings(new OutputSettings().prettyPrint(false)).html(); 
		  String id                    = Jsoup.parse(request.getParameter("ref_id")).outputSettings(new OutputSettings().prettyPrint(false)).text();
		  
		  String references            = request.getParameter("references");
		  
		  subjId        = Jsoup.clean(subjId.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  subjectName   = Jsoup.clean(subjectName.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  semester      = Jsoup.clean(semester.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  lessonsFound  = Jsoup.clean(lessonsFound.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  ectsValue     = Jsoup.clean(ectsValue.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  lectors       = Jsoup.clean(lectors.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  edges         = Jsoup.clean(edges.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  edgesForm     = Jsoup.clean(edgesForm.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  subjectTarget         = Jsoup.clean(subjectTarget.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  subjectRealisations   = Jsoup.clean(subjectRealisations.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  subjectContent        = Jsoup.clean(subjectContent.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  methodLectionTransfer = Jsoup.clean(methodLectionTransfer.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  dataMajor     = Jsoup.clean(dataMajor.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  subjectNotes  = Jsoup.clean(subjectNotes.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  subjectState  = Jsoup.clean(subjectState.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  labs          = Jsoup.clean(labs.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  k1            = Jsoup.clean(k1.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  k2            = Jsoup.clean(k2.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  exams         = Jsoup.clean(exams.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  examsNotes    = Jsoup.clean(examsNotes.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false));
		  otherExams    = Jsoup.clean(otherExams.replaceAll("<br>", "\n"), "", Whitelist.none(), new OutputSettings().prettyPrint(false)).trim();
		  
		  
		  subjId        = StringEscapeUtils.unescapeHtml4(subjId);
		  subjectName   = StringEscapeUtils.unescapeHtml4(subjectName);
		  semester      = StringEscapeUtils.unescapeHtml4(semester);
		  lessonsFound  = StringEscapeUtils.unescapeHtml4(lessonsFound);
		  ectsValue     = StringEscapeUtils.unescapeHtml4(ectsValue);
		  lectors       = StringEscapeUtils.unescapeHtml4(lectors);
		  edges         = StringEscapeUtils.unescapeHtml4(edges);
		  edgesForm     = StringEscapeUtils.unescapeHtml4(edgesForm);
		  subjectTarget         = StringEscapeUtils.unescapeHtml4(subjectTarget);
		  subjectRealisations   = StringEscapeUtils.unescapeHtml4(subjectRealisations);
		  subjectContent        = StringEscapeUtils.unescapeHtml4(subjectContent);
		  methodLectionTransfer = StringEscapeUtils.unescapeHtml4(methodLectionTransfer);
		  dataMajor     = StringEscapeUtils.unescapeHtml4(dataMajor);
		  subjectNotes  = StringEscapeUtils.unescapeHtml4(subjectNotes);
		  subjectState  = StringEscapeUtils.unescapeHtml4(subjectState);
		  labs          = StringEscapeUtils.unescapeHtml4(labs);
		  k1            = StringEscapeUtils.unescapeHtml4(k1);
		  k2            = StringEscapeUtils.unescapeHtml4(k2);
		  exams         = StringEscapeUtils.unescapeHtml4(exams);
		  examsNotes    = StringEscapeUtils.unescapeHtml4(examsNotes);
		  otherExams    = StringEscapeUtils.unescapeHtml4(otherExams);
		 
		  references = StringEscapeUtils.unescapeHtml4(references); 
		  
		  StandardSubject subject = subjectController.get(id);
		  if(subject == null) {message = "Постављање података за предмет није успјело."; success=false; return;}
		  subject = subject.apply(subjId); 
		  subject.setDataMajor(dataMajor);
		  subject.setEctsValue(ectsValue);
		  subject.setEdges(edgesForm);
		  subject.setLectors(lectors);
		  subject.setLessonsFound(lessonsFound);
		  subject.setMethodLectionTransfer(methodLectionTransfer);
		  subject.setNotes(subjectNotes);
		  subject.setSubjectContent(subjectContent);
		  subject.setSemestar(semester);
		  subject.setSubjectName(subjectName);
		  subject.setSubjectRealisations(subjectRealisations);
		  subject.setSubjectState(subjectState);
		  subject.setSubjectTarget(subjectTarget);
		  subject.setEdges(edges);
		  subject.setEdgesForm(edgesForm);
		  
		  try { 
			  int finalExams = Integer.parseInt(exams); 
			  subject.getGradulation().setFinalExams(finalExams);
		  }catch(Exception ex) {
			 if(ERROR_REMIX) ex.printStackTrace(); 
		  }
		  
		  try { 
			  int c1 = Integer.parseInt(k1); 
			  subject.getGradulation().setFirstColocvium(c1);
		  }catch(Exception ex) {
			  if(ERROR_REMIX) ex.printStackTrace();  
		  }
		  		  
		  try { 
			  int c2 = Integer.parseInt(k2); 
			  subject.getGradulation().setSecondColocvium(c2);
		  }catch(Exception ex) {
			  if(ERROR_REMIX) ex.printStackTrace();
		  }
		  
		  try { 
			  int l = Integer.parseInt(labs); 
			  subject.getGradulation().setLaboratoryExercies(l);
		  }catch(Exception ex) {
			  if(ERROR_REMIX) ex.printStackTrace();
		  }
		  
		  subject.getGradulation().setGradulationNotes(examsNotes);
		  subject.getGradulation().setOtherExams(otherExams);
		  
		  try {
			  JsonParser parser = new JsonParser();
			  JsonArray array = parser.parse(references).getAsJsonArray();
			  ArrayList<String> refs = new ArrayList<>();
			  for(int i=0; i<array.size(); i++) {
				  String ref = array.get(i).getAsString();
				  refs.add(ref.substring(0, ref.length()));
			  }
			  subject.setReferences(refs);
		  }catch(Exception ex) {
			  if(ERROR_REMIX) ex.printStackTrace();
		  }
		  
		  int count = 0; 
		  
		  if(id.contentEquals(subject.getSubjectId())) {
			  if(subjectController.update(id, subject)) 
				  count++;
			  
			  List<String> updates = subjectMergesController.bindedFor(id);
			  
			  for(String update: updates) {
				  subject = subject.apply(update);
				  if(subjectController.update(update, subject)) 
					  count++;
			  }
		  }else {
			  String neoId = subject.getSubjectId();
			  if(subjectController.update(id, subject)) 
				  count++;
			  
			  List<String> updates = subjectMergesController.bindedFor(id);
			  for(String update: updates) {
				  subject = subject.apply(update);
				  if(subjectController.update(update, subject)) 
					  count++;
			  }
			  
			  subjectMergesController.updateSubjectId(id, neoId);
		  }
		  
		 if(count != 0) {
			  message = "Постављање параметара "+count+" предмета је успјешно.";
			  success=true;
		 }else {
			 message = "Постављање параметара предмета није успјешно."; 
			 success = false;
		 }
	}
}
