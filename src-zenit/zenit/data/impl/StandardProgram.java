package zenit.data.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;

import zenit.data.model.Program;

/**
 * Објекат помоћу ког се воде студијски програми. 
 * @author VM
 * @version 1.0
 */
public class StandardProgram implements Program, Serializable, Cloneable, Comparable<StandardProgram>{
	@BsonProperty("program_id")       private String id = "";
	@BsonProperty("program_name")     private String name = "";
	@BsonProperty("program_subjects") private List<String> subjects = new ArrayList<>();
	
	@BsonProperty("program")          private String program = "";
	@BsonProperty("course")           private String course = "";
	
	private static final long serialVersionUID = -6891720850943781709L;
	
	public StandardProgram() {}
		
	public void setId(String id) {
		if(id==null)  id = "";
		this.id = id;
	}

	public StandardProgram(String subjectId) {
		if(subjectId==null) subjectId = "";
		for(char c: subjectId.toCharArray()) {
			if(Character.isUpperCase(c)) continue;
			if(Character.isLowerCase(c)) continue;
			if(Character.isDigit(c)) continue;
			if(c=='~') continue;
			if(c=='!') continue;
			if(c==' ') continue;
			if(c=='-') continue;
			if(c=='_') continue;
			if(c=='#') continue;
			if(c=='*') continue;
			if(c=='$') continue;
			if(c=='%') continue;
			if(c=='&') continue;
			if(c=='.') continue;
			if(c==',') continue;
			if(c==':') continue;
			if(c==';') continue;
			if(c=='?') continue;
			if(c=='>') continue;
			if(c=='<') continue;
			if(c=='=') continue;
			if(c=='(') continue;
			if(c==')') continue;
			if(c=='/') continue;
			if(c=='+') continue;
			if(c=='`') continue;
			if(c==' ') continue;
			throw new RuntimeException();
		}
		this.id = subjectId; 
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		if(name==null) name = "";
		this.name = name;
	}

	@Override
	public List<String> getSubjects() {
		return new ArrayList<>(subjects);
	}

	@Override
	public void setSubjects(List<String> subjects) {
		if(subjects==null) subjects = new ArrayList<>();
		this.subjects = new ArrayList<>(subjects);
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public void addSubject(String subject) {
		if(subject==null) return; 
		if(subject.trim().length()==0) return; 
		if(subjects.contains(subject)) return;
		subjects.add(subject); 
	}
	
	@Override
	public void removeSubject(String subject) {
		subjects.remove(subject); 
	}

	@Override 
	public String toString() {
		return id; 
	}
	
	@Override
	public int hashCode() {
		return id.hashCode(); 
	}
	
	@Override
	public StandardProgram clone() {
		StandardProgram program = new StandardProgram(id);
		program.setName(name);
		program.setSubjects(subjects);
		return program; 
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof StandardProgram) {
			StandardProgram program = (StandardProgram) object;
			return id.contentEquals(program.id);
		}
		return false; 
	}
	
	@Override
	public int compareTo(StandardProgram argument) {
		return id.compareTo(argument.id);
	}
	
	public StandardProgram apply(String id) {
		if(id==null) return null; 
		StandardProgram program = clone(); 
		program.id = id;
		return this;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
