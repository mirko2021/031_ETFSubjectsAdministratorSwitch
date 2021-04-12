package zenit.merges.bean;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import zenit.merges.controller.SubjectMergesController;
import zenit.merges.model.SubjectMergesStructure;
import zenit.web.listener.ControllerListener;

/**
 * Зрно које се користи за формирање спојених предмета, као и 
 * баратање истим. 
 * @author VM
 * @version 1.0
 */
public class SubjectMergeBean implements Serializable{
	private static final long serialVersionUID = 2865583240127893542L;
	private transient SubjectMergesController aliasController;
	private boolean initialized; 
	private boolean success; 
	private String message = "";
	
	public void initialize(HttpSession session) {
		if(initialized) return; 
		aliasController = ControllerListener.getEdgeController(session).getSubjectMergesController(); 
		initialized = true; 
	}
	
	public List<SubjectMergesStructure> get(){
		return aliasController.getAdapter().get();
	}
	
	public SubjectMergesController getAliasController() {
		return aliasController;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if(message==null) message = ""; 
		this.message = message;
	}
	
	public String consumeMessage() {
		try {
			return message; 
		}finally {
			reset();
		}
	}
	
	public void reset() {
		success = false; 
		message = "";
	}
	
	public void add(HttpServletRequest request) {
		try {
			String aliasName = request.getParameter("alias_name");
			String aliasKey  = request.getParameter("alias_key");
			SubjectMergesStructure object = new SubjectMergesStructure(aliasName, aliasKey); 
			if(!aliasController.getAdapter().set(object)) throw new RuntimeException();
			success = true;
			message = "Спојница за предмете је успјешно додана.";
		}catch(Exception ex) {
			success = false;
			message = "Спојница за предмете није успјешно додана.";
		}
	}
	
	public void remove(HttpServletRequest request) {
		try {
			String aliasKey  = request.getParameter("alias_name");
			success = aliasController.getAdapter().remove(aliasKey);
			if(!success) throw new RuntimeException();
			message = "Спојница за предмете је успјешно обрисана.";
		}catch(Exception ex) {
			success = false;
			message = "Спојница за предмете није успјешно обрисана.";
		}
	}
	
	public void accept(HttpServletRequest request) {
		try {
			String aliasName = request.getParameter("alias_name");
			String aliasNameUpdate = request.getParameter("alias_name_update");
			String aliasKeyUpdate = request.getParameter("alias_key_update");
			if(aliasNameUpdate==null) throw new NullPointerException();
			if(aliasKeyUpdate==null) throw new NullPointerException(); 
			if(aliasName==null) throw new RuntimeException();
			SubjectMergesStructure object = new SubjectMergesStructure(aliasNameUpdate, aliasKeyUpdate); 
			if(!aliasController.getAdapter().acceptInfo(aliasName, object)) 
				throw new RuntimeException();
			success = true;
			message = "Пријем карактеристика спојнице је успјешан.";
		}catch(Exception ex) {
			success = false;
			message = "Пријем карактеристика спојнице није успјешан.";
		}
	}
	
	public SubjectMergesStructure get(String aliasName) {
		try {
			return aliasController.getAdapter().get(aliasName); 
		}catch(Exception ex) {
			return null;
		}
	}
	
	public void mergeSubject(HttpServletRequest request) {
		try {
			String programId = request.getParameter("general_alias_name");
			String subjectId = request.getParameter("merge_subject_id"); 
			success = true; 
			message = "Спајање предмета успјешно.";
			aliasController.getAdapter().addSubject(programId, subjectId); 
		}catch(Exception ex) {
			success = false; 
			message = "Спајање предмета није успјешно.";
		}
	}
	
	public void splitSubject(HttpServletRequest request) {
		try {
			String programId = request.getParameter("general_alias_name");
			String subjectId = request.getParameter("merge_subject_id"); 
			aliasController.getAdapter().removeSubject(programId, subjectId); 
			success = true; 
			message = "Раздвајање предмета успјешно.";
		}catch(Exception ex) {
			success = false; 
			message = "Раздвајање предмета није успјешно";
		}
	}
	
	public List<String> list(String programName){
		try {
			SubjectMergesStructure data = get(programName);
			if(data == null) throw new RuntimeException();
			List<String> subjects = data.getRelatedSubjectsIds();
			Collections.sort(subjects);
			return subjects; 
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public String encodeURI(String text) {
		try {
			return URLEncoder.encode(text,"UTF-8");
		}catch(Exception ex) {
			return "";
		}
	}
}
