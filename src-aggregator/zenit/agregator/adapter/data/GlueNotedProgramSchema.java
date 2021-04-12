package zenit.agregator.adapter.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.agregator.web.gluenotes.SubjectGlueNote;
import zenit.data.impl.StandardSubject;

/**
 * Схема са подршком за замјене залијепљеним биљешкама умјесто резултата, 
 * гдје постоји. 
 * @author VM
 * @version 1.0
 */
public class GlueNotedProgramSchema extends SemestralProgramSchema{
	private static final long serialVersionUID = 826423807006100314L;
	private ProgramGlueNotes glueNotes; 
	
	public GlueNotedProgramSchema(ProgramGlueNotes glueNotes){
		this.glueNotes = glueNotes;
	}
	
	public ProgramGlueNotes getGlueNotes() {
		return glueNotes;
	}

	@Override
	public String getStudyProgram() {
		return super.getStudyProgram();
	}

	@Override
	public void setStudyProgram(String studyProgram) {
		super.setStudyProgram(studyProgram);
	}

	@Override
	public boolean add(String semester, StandardSubject subject) {
		return super.add(semester, subject);
	}

	@Override
	public List<StandardSubject> get(String semester) {
		List<StandardSubject> result = super.get(semester); 
		for(StandardSubject subject: result) {
			List<SubjectGlueNote> gns = glueNotes.getSubjectsTypeNotes();
			if(gns == null) gns = new  ArrayList<>();	
			for(SubjectGlueNote glueNote: gns) 
					if(subject.getSubjectId().contentEquals(glueNote.getKey())) 
						if(glueNote.getType().length()!=0)
							subject.setSubjectState(glueNote.getType());
		}
		return result;
	}

	@Override
	public boolean exists(String semester, String id) {
		return super.exists(semester, id);
	}

	@Override
	public String semester(String subject) {
		return super.semester(subject);
	}

	@Override
	public StandardSubject subject(String subject) {
		StandardSubject sub =  super.subject(subject);
		if(sub==null) return null;
		List<SubjectGlueNote> gns = glueNotes.getSubjectsTypeNotes();
		if(gns == null) gns = new  ArrayList<>();	
		for(SubjectGlueNote glueNote: gns) 
			if(sub.getSubjectId().contentEquals(glueNote.getKey())) 
				if(glueNote.getType().length()==0)
					sub.setSubjectState(glueNote.getType());
		return sub; 
	}

	@Override
	public void removeSemester(String semester) {
		super.removeSemester(semester);
	}

	@Override
	public void removeSubject(String subject) {
		super.removeSubject(subject);
	}

	@Override
	public List<String> semesters() {
		return super.semesters();
	}

	@Override
	public int countSemesters() {
		return super.countSemesters();
	}

	@Override
	public HashMap<String, Integer> countBySemesters() {
		return super.countBySemesters();
	}

	@Override
	public int countSubjects(String semester) {
		return super.countSubjects(semester);
	}

	@Override
	public int countSubjects() {
		return super.countSubjects();
	}

	@Override
	public int ectsInSemester(String semester) {
		return super.ectsInSemester(semester);
	}

	public String ectsInSemesterString(String semester) {
		int ects = super.ectsInSemester(semester);
		switch(semester.intern()) {
			case "0": 
				if(glueNotes.getS0f2()!=null)
					if(glueNotes.getS0f2().length()!=0)
						return glueNotes.getS0f2(); 
				break;
			case "1":
				if(glueNotes.getS1f2()!=null)
					if(glueNotes.getS1f2().length()!=0)
						return glueNotes.getS1f2(); 
				break;
			case "2": 
				if(glueNotes.getS2f2()!=null)
					if(glueNotes.getS2f2().length()!=0)
						return glueNotes.getS2f2(); 
				break;
			case "3": 
				if(glueNotes.getS3f2()!=null)
					if(glueNotes.getS3f2().length()!=0)
						return glueNotes.getS3f2(); 
				break;
			case "4": 
				if(glueNotes.getS4f2()!=null)
					if(glueNotes.getS4f2().length()!=0)
						return glueNotes.getS4f2(); 
				break;
			case "5": 
				if(glueNotes.getS5f2()!=null)
					if(glueNotes.getS5f2().length()!=0)
						return glueNotes.getS5f2(); 
				break;
			case "6": 
				if(glueNotes.getS6f2()!=null)
					if(glueNotes.getS6f2().length()!=0)
						return glueNotes.getS6f2(); 
				break;
			case "7": 
				if(glueNotes.getS7f2()!=null)
					if(glueNotes.getS7f2().length()!=0)
						return glueNotes.getS7f2(); 
				break;
			case "8": 
				if(glueNotes.getS8f2()!=null)
					if(glueNotes.getS8f2().length()!=0)
						return glueNotes.getS8f2(); 
				break;
		}
		return ""+ects; 
	}
	
	@Override
	public HashMap<String, Integer> ectsBySemester() {
		return super.ectsBySemester();
	}

	@Override
	public int ectsTotal() {
		return super.ectsTotal();
	}

	@Override
	public int lessonsInSemester(String semester) {
		return super.lessonsInSemester(semester);
	}
	
	
	public String lessonsInSemesterString(String semester) {
		int lessons = super.lessonsInSemester(semester);
		switch(semester.intern()) {
		case "0": 
			if(glueNotes.getS0f3()!=null)
				if(glueNotes.getS0f3().length()!=0)
					return glueNotes.getS0f3(); 
			break;
		case "1":
			if(glueNotes.getS1f3()!=null)
				if(glueNotes.getS1f3().length()!=0)
					return glueNotes.getS1f3(); 
			break;
		case "2": 
			if(glueNotes.getS2f3()!=null)
				if(glueNotes.getS2f3().length()!=0)
					return glueNotes.getS2f3(); 
			break;
		case "3": 
			if(glueNotes.getS3f3()!=null)
				if(glueNotes.getS3f3().length()!=0)
					return glueNotes.getS3f3(); 
			break;
		case "4": 
			if(glueNotes.getS4f3()!=null)
				if(glueNotes.getS4f3().length()!=0)
					return glueNotes.getS4f3(); 
			break;
		case "5": 
			if(glueNotes.getS5f3()!=null)
				if(glueNotes.getS5f3().length()!=0)
					return glueNotes.getS5f3(); 
			break;
		case "6": 
			if(glueNotes.getS6f3()!=null)
				if(glueNotes.getS6f3().length()!=0)
					return glueNotes.getS6f3(); 
			break;
		case "7": 
			if(glueNotes.getS7f3()!=null)
				if(glueNotes.getS7f3().length()!=0)
					return glueNotes.getS7f3(); 
			break;
		case "8": 
			if(glueNotes.getS8f3()!=null)
				if(glueNotes.getS8f3().length()!=0)
					return glueNotes.getS8f3(); 
			break;
		}
		return ""+lessons;
	}

	public String getF1(String semester) {
		switch(semester.intern()) {
		case "0": 
			if(glueNotes.getS0f1()==null) return "";
			return glueNotes.getS0f1(); 
		case "1":
			if(glueNotes.getS1f1()==null) return "";
			return glueNotes.getS1f1(); 
		case "2": 
			if(glueNotes.getS2f1()==null) return "";
			return glueNotes.getS2f1(); 
		case "3": 
			if(glueNotes.getS3f1()==null) return "";
			return glueNotes.getS3f1(); 
		case "4": 
			if(glueNotes.getS4f1()==null) return "";
			return glueNotes.getS4f1(); 
		case "5": 
			if(glueNotes.getS5f1()==null) return "";
			return glueNotes.getS5f1(); 
		case "6": 
			if(glueNotes.getS6f1()==null) return "";
			return glueNotes.getS6f1(); 
		case "7": 
			if(glueNotes.getS7f1()==null) return "";
			return glueNotes.getS7f1(); 
		case "8": 
			if(glueNotes.getS8f1()==null) return "";
			return glueNotes.getS8f1(); 
		}
		return "";
	}
	
	public String getF4(String semester) {
		switch(semester.intern()) {
		case "0": 
			if(glueNotes.getS0f4()==null) return "";
			return glueNotes.getS0f4(); 
		case "1":
			if(glueNotes.getS1f4()==null) return "";
			return glueNotes.getS1f4(); 
		case "2": 
			if(glueNotes.getS2f4()==null) return "";
			return glueNotes.getS2f4(); 
		case "3": 
			if(glueNotes.getS3f4()==null) return "";
			return glueNotes.getS3f4(); 
		case "4": 
			if(glueNotes.getS4f4()==null) return "";
			return glueNotes.getS4f4(); 
		case "5": 
			if(glueNotes.getS5f4()==null) return "";
			return glueNotes.getS5f4(); 
		case "6": 
			if(glueNotes.getS6f4()==null) return "";
			return glueNotes.getS6f4(); 
		case "7": 
			if(glueNotes.getS7f4()==null) return "";
			return glueNotes.getS7f4(); 
		case "8": 
			if(glueNotes.getS8f4()==null) return "";
			return glueNotes.getS8f4(); 
		}
		return "";
	}
	
	@Override
	public HashMap<String, Integer> lessonsBySemester() {
		return super.lessonsBySemester();
	}

	@Override
	public int lessonsTotal() {
		return super.lessonsTotal();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
