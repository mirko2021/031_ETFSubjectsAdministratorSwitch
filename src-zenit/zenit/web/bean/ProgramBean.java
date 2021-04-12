package zenit.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import zenit.data.controller.ProgramController;
import zenit.data.impl.StandardProgram;
import zenit.data.model.Program;
import zenit.web.listener.ControllerListener;

/**
 * Зрно које се веже за управљање студијским програмима. 
 * @author VM
 * @version 1.0
 */
public class ProgramBean implements Serializable{
	private static final long serialVersionUID = -9128266360597210257L;

	private transient ProgramController programController; 
	private boolean initialized; 
	
	public void initialize(HttpSession session) {
		if(initialized) return; 
		programController = new ProgramController(ControllerListener.getEdgeController(session));
		programController.initializeSwitchAdapter(ControllerListener.getAdapterController(session));
		initialized = true; 
	}
	
	private boolean success = false; 
	private String message = ""; 
	private UtilBean utilBean = new UtilBean();
	
	public ProgramController getProgramController() {
		return programController;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public UtilBean getUtilBean() {
		return utilBean;
	}
	
	public List<String> getProgramKeys(){
		List<String> keys = programController.getProgramIds();
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
			String id = request.getParameter("program_name");
			if(id==null) id = "";
			if(id.trim().length()==0) message="Додавање предмета није успјело.";
			if(id.trim().length()==0) return;
			if(!programController.add(new StandardProgram(id))) { 
				message="Додавање студијског програма није успјело.";
				success=false;
			}
			else {
				message="Додавање студијског програма је успјело.";
				success=true;
			}
		}catch(Exception ex) {
			message="Додавање студијског програма није успјело.";
			success=false;
		}
	}
	
	public void remove(HttpServletRequest request) {
		try {
			String id = request.getParameter("program_name");
			if(id==null) id = "";
			if(id.trim().length()==0) message="Брисање студијског програма није успјело.";
			if(id.trim().length()==0) return;
			programController.remove(id);
			message="Брисање студијског програма је успјело.";
			success=true;
		}catch(Exception ex) {
			message="Брисање студијског програма није успјело.";
			success=false;
		}
	}
	
	public Program get(HttpServletRequest request) {
		String id = request.getParameter("program_name");
		if(id==null) return null;
		return programController.get(id);
	}
	
	public boolean test(String programName) {
		return programController.getProgramAdapter().get(programName) != null;
	}
	
	
	public Program get(String programName) {
		return programController.getProgramAdapter().get(programName);
	}
	
	public void updateBasic(HttpServletRequest request) {
		try {
			String programName = request.getParameter("program_id");
			String programId   = request.getParameter("program_name"); 
			String program     = request.getParameter("program"); 
			String course      = request.getParameter("course"); 
			if(programName==null || programName.trim().length()==0) { success=false; message = "Промјена параметара студијског програма није успјела."; return;}
			if(programId==null || programId.trim().length()==0) { success=false; message = "Промјена параметара студијског програма није успјела."; return;}
			if(program==null) program = "";
			if(course==null) course = ""; 
			
			if(!programName.contentEquals(programId))
				if(!programController.getProgramAdapter().updateProgramId(programId, programName)) 
					throw new RuntimeException();
			
			programController.updateName(programName, programId);
			programController.updateProgram(programName, program);
			programController.updateCourse(programName, course);
			
			
			message = "Промјена параметара студијског програма је успјела.";
			success =  true;
		}catch(Exception ex) {
			message = "Промјена параметара студијског програма није успјела.";
			success = false;
		}
	}
	
	public List<String> listSubjects(HttpServletRequest request) {
		try {
			String programName = request.getParameter("program_name");
			List<String> subjects = programController.getProgramAdapter().get(programName).getSubjects();
			Collections.sort(subjects);
			return subjects;
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public void addSubject(HttpServletRequest request) {
		try {
			String programName = request.getParameter("program_name");
			String subject     = request.getParameter("subject_id");
			if(programName==null) throw new NullPointerException();
			if(subject==null)     throw new NullPointerException();
			StandardProgram program = programController.get(programName);
			if(program.getSubjects().contains(subject)) {
				message = "Повезивање предмета извршено је и раније.";
				success =  false;
				return;
			}
			programController.getProgramAdapter().addSubject(programName, subject);
			message = "Повезивање предмета за студијског програма је успјело.";
			success =  true;
		}catch(Exception ex) {
			message = "Повезивање предмета за студијског програма није успјело.";
			success =  false;
		}
	}
	
	public void removeSubject(HttpServletRequest request) {
		try {
			String programName = request.getParameter("program_name");
			String subject     = request.getParameter("subject_id");
			if(programName==null) throw new NullPointerException();
			if(subject==null)     throw new NullPointerException();
			programController.getProgramAdapter().removeSubject(programName, subject);
			message = "Одпајање предмета студијског програма је успјело.";
			success =  true;
		}catch(Exception ex) {
			message = "Одпајање предмета студијског програма није успјело.";
			success =  false;
		}
	}
}
