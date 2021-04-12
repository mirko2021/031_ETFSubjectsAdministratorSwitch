package zenit.agregator.web.gluenotes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Замјенске биљешке за тебеле на којима се приказује програм. 
 * @author VM
 * @version 1.0
 */
public class ProgramGlueNotes implements Serializable, Cloneable, Comparable<ProgramGlueNotes>{
	private static final long serialVersionUID = 6258327316255621462L;
	private String programName = ""; 
	
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		if(programName==null) programName = ""; 
		this.programName = programName;
	}

	@Override 
	public String toString() {
		return "PROGRAM GLUE NOTES >> "+programName;
	}
	
	@Override
	public int hashCode() {
		return programName.hashCode(); 
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof ProgramGlueNotes) {
			ProgramGlueNotes notes = (ProgramGlueNotes) object; 
			return programName.contentEquals(notes.programName); 
		}
		return false; 
	}
	
	@Override
	public int compareTo(ProgramGlueNotes programNotes) {
		return programName.compareTo(programNotes.programName);
	}
	
	@Override 
	public ProgramGlueNotes clone() {
		ProgramGlueNotes notes = new ProgramGlueNotes();
		notes.programName = programName;
		notes.subjectsTypeNotes = new ArrayList<>(subjectsTypeNotes);
		notes.s0f1 = s0f1; 
		notes.s0f2 = s0f2; 
		notes.s0f3 = s0f3; 
		notes.s0f4 = s0f4; 
		notes.s1f1 = s1f1; 
		notes.s1f2 = s1f2; 
		notes.s1f3 = s1f3; 
		notes.s1f4 = s1f4;
		notes.s2f1 = s2f1; 
		notes.s2f2 = s2f2; 
		notes.s2f3 = s2f3; 
		notes.s2f4 = s2f4;
		notes.s3f1 = s3f1; 
		notes.s3f2 = s3f2; 
		notes.s3f3 = s3f3; 
		notes.s3f4 = s3f4;
		notes.s4f1 = s4f1; 
		notes.s4f2 = s4f2; 
		notes.s4f3 = s4f3; 
		notes.s4f4 = s4f4;
		notes.s5f1 = s5f1; 
		notes.s5f2 = s5f2; 
		notes.s5f3 = s5f3; 
		notes.s5f4 = s5f4;
		notes.s6f1 = s6f1; 
		notes.s6f2 = s6f2; 
		notes.s6f3 = s6f3; 
		notes.s6f4 = s6f4;
		notes.s7f1 = s7f1; 
		notes.s7f2 = s7f2; 
		notes.s7f3 = s7f3; 
		notes.s7f4 = s7f4;
		notes.s8f1 = s8f1; 
		notes.s8f2 = s8f2; 
		notes.s8f3 = s8f3; 
		notes.s8f4 = s8f4;
		return notes;
	}
	
	private List<SubjectGlueNote> subjectsTypeNotes = new ArrayList<>();
	private String s0f1;
	private String s0f2;
	private String s0f3;
	private String s0f4;
	private String s1f1;
	private String s1f2;
	private String s1f3;
	private String s1f4;
	private String s2f1;
	private String s2f2;
	private String s2f3;
	private String s2f4;
	private String s3f1;
	private String s3f2;
	private String s3f3;
	private String s3f4;
	private String s4f1;
	private String s4f2;
	private String s4f3;
	private String s4f4;
	private String s5f1;
	private String s5f2;
	private String s5f3;
	private String s5f4;
	private String s6f1;
	private String s6f2;
	private String s6f3;
	private String s6f4;
	private String s7f1;
	private String s7f2;
	private String s7f3;
	private String s7f4;
	private String s8f1;
	private String s8f2;
	private String s8f3;
	private String s8f4;

	public String getS0f1() {
		return s0f1;
	}

	public void setS0f1(String s0f1) {
		this.s0f1 = s0f1;
	}

	public String getS0f2() {
		return s0f2;
	}

	public void setS0f2(String s0f2) {
		this.s0f2 = s0f2;
	}

	public String getS0f3() {
		return s0f3;
	}

	public void setS0f3(String s0f3) {
		this.s0f3 = s0f3;
	}

	public String getS0f4() {
		return s0f4;
	}

	public void setS0f4(String s0f4) {
		this.s0f4 = s0f4;
	}

	public String getS1f1() {
		return s1f1;
	}

	public void setS1f1(String s1f1) {
		this.s1f1 = s1f1;
	}

	public String getS1f2() {
		return s1f2;
	}

	public void setS1f2(String s1f2) {
		this.s1f2 = s1f2;
	}

	public String getS1f3() {
		return s1f3;
	}

	public void setS1f3(String s1f3) {
		this.s1f3 = s1f3;
	}

	public String getS1f4() {
		return s1f4;
	}

	public void setS1f4(String s1f4) {
		this.s1f4 = s1f4;
	}

	public String getS2f1() {
		return s2f1;
	}

	public void setS2f1(String s2f1) {
		this.s2f1 = s2f1;
	}

	public String getS2f2() {
		return s2f2;
	}

	public void setS2f2(String s2f2) {
		this.s2f2 = s2f2;
	}

	public String getS2f3() {
		return s2f3;
	}

	public void setS2f3(String s2f3) {
		this.s2f3 = s2f3;
	}

	public String getS2f4() {
		return s2f4;
	}

	public void setS2f4(String s2f4) {
		this.s2f4 = s2f4;
	}

	public String getS3f1() {
		return s3f1;
	}

	public void setS3f1(String s3f1) {
		this.s3f1 = s3f1;
	}

	public String getS3f2() {
		return s3f2;
	}

	public void setS3f2(String s3f2) {
		this.s3f2 = s3f2;
	}

	public String getS3f3() {
		return s3f3;
	}

	public void setS3f3(String s3f3) {
		this.s3f3 = s3f3;
	}

	public String getS3f4() {
		return s3f4;
	}

	public void setS3f4(String s3f4) {
		this.s3f4 = s3f4;
	}

	public String getS4f1() {
		return s4f1;
	}

	public void setS4f1(String s4f1) {
		this.s4f1 = s4f1;
	}

	public String getS4f2() {
		return s4f2;
	}

	public void setS4f2(String s4f2) {
		this.s4f2 = s4f2;
	}

	public String getS4f3() {
		return s4f3;
	}

	public void setS4f3(String s4f3) {
		this.s4f3 = s4f3;
	}

	public String getS4f4() {
		return s4f4;
	}

	public void setS4f4(String s4f4) {
		this.s4f4 = s4f4;
	}

	public String getS5f1() {
		return s5f1;
	}

	public void setS5f1(String s5f1) {
		this.s5f1 = s5f1;
	}

	public String getS5f2() {
		return s5f2;
	}

	public void setS5f2(String s5f2) {
		this.s5f2 = s5f2;
	}

	public String getS5f3() {
		return s5f3;
	}

	public void setS5f3(String s5f3) {
		this.s5f3 = s5f3;
	}

	public String getS5f4() {
		return s5f4;
	}

	public void setS5f4(String s5f4) {
		this.s5f4 = s5f4;
	}

	public String getS6f1() {
		return s6f1;
	}

	public void setS6f1(String s6f1) {
		this.s6f1 = s6f1;
	}

	public String getS6f2() {
		return s6f2;
	}

	public void setS6f2(String s6f2) {
		this.s6f2 = s6f2;
	}

	public String getS6f3() {
		return s6f3;
	}

	public void setS6f3(String s6f3) {
		this.s6f3 = s6f3;
	}

	public String getS6f4() {
		return s6f4;
	}

	public void setS6f4(String s6f4) {
		this.s6f4 = s6f4;
	}

	public String getS7f1() {
		return s7f1;
	}

	public void setS7f1(String s7f1) {
		this.s7f1 = s7f1;
	}

	public String getS7f2() {
		return s7f2;
	}

	public void setS7f2(String s7f2) {
		this.s7f2 = s7f2;
	}

	public String getS7f3() {
		return s7f3;
	}

	public void setS7f3(String s7f3) {
		this.s7f3 = s7f3;
	}

	public String getS7f4() {
		return s7f4;
	}

	public void setS7f4(String s7f4) {
		this.s7f4 = s7f4;
	}

	public String getS8f1() {
		return s8f1;
	}

	public void setS8f1(String s8f1) {
		this.s8f1 = s8f1;
	}

	public String getS8f2() {
		return s8f2;
	}

	public void setS8f2(String s8f2) {
		this.s8f2 = s8f2;
	}

	public String getS8f3() {
		return s8f3;
	}

	public void setS8f3(String s8f3) {
		this.s8f3 = s8f3;
	}

	public String getS8f4() {
		return s8f4;
	}

	public void setS8f4(String s8f4) {
		this.s8f4 = s8f4;
	}

	public List<SubjectGlueNote> getSubjectsTypeNotes() {
		return new ArrayList<>(subjectsTypeNotes);
	}
	
	public void addSubjectTypeNote(String subjectId, String note) {
		if(subjectId==null) return; 
		if(subjectId.trim().length()==0) return; 
		if(note==null) note=""; 
		boolean exists = false;
		for(SubjectGlueNote n: this.subjectsTypeNotes) 
			if(n.getKey().contentEquals(subjectId)) 
				exists = true;
		if(!exists) {
			SubjectGlueNote glueNote = new SubjectGlueNote(); 
			glueNote.setKey(subjectId);
			glueNote.setType(note);
			subjectsTypeNotes.add(glueNote);
		}
	}	
	
	public void putSubjectTypeNote(String subjectId, String note) {
		if(subjectId==null) return; 
		if(subjectId.trim().length()==0) return; 
		if(note==null) note=""; 	
		for(SubjectGlueNote n: this.subjectsTypeNotes) 
			if(n.getKey().contentEquals(subjectId)) {
				subjectsTypeNotes.remove(n);
				SubjectGlueNote glueNote = new SubjectGlueNote(); 
				glueNote.setKey(subjectId);
				glueNote.setType(note);
				subjectsTypeNotes.add(glueNote);
				return;
			}
	}
	
	public void removeSubjectTypeNote(String subjectId) {
		for(SubjectGlueNote n: this.subjectsTypeNotes) 
			if(n.getKey().contentEquals(subjectId)) 
				subjectsTypeNotes.remove(n);
	}
	
	public String getSubjectTypeNote(String subjectId) {
		for(SubjectGlueNote n: this.subjectsTypeNotes)
			if(n.getKey().contentEquals(subjectId)) return n.getType();
		return null;
	}

	public void setSubjectsTypeNotes(List<SubjectGlueNote> subjectsTypeNotes) {
		this.subjectsTypeNotes = new ArrayList<>(subjectsTypeNotes);
	}
}
