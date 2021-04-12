package org.unibl.etf.spider.web.model;

import java.io.Serializable;

/**
 * Општи дескриптор када су предмети у питању. 
 * @author MV
 * @version 1.0
 */
public class SubjectDescriptor implements Serializable, Cloneable, Comparable<SubjectDescriptor>{
	private static final long serialVersionUID = -6944297905266117841L;
	private String href = ""; 
	private String found = "";
	private String semester = "";
	private String ects = ""; 
	private String name = ""; 
	private String type = ""; 
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(type == null) type=""; 
		this.type = type;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		if(href==null) href = "";
		this.href = href;
	}
	
	public String getFound() {
		return found;
	}

	public void setFound(String found) {
		if(found==null) found = ""; 
		this.found = found;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		if(semester==null) semester = ""; 
		this.semester = semester;
	}

	public String getEcts() {
		return ects;
	}

	
	public void setEcts(String ects) {
		if(ects==null) ects = ""; 
		this.ects = ects;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		if(name==null) name = "";
		this.name = name;
	}

	
	@Override 
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return name; 
	}
	
	@Override 
	public SubjectDescriptor clone() {
		SubjectDescriptor sd = new SubjectDescriptor();
		sd.ects = ects; 
		sd.found = found; 
		sd.href = href; 
		sd.name = name;
		sd.type = type;
		sd.semester = semester;
		return sd; 
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof SubjectDescriptor) {
			SubjectDescriptor sd = (SubjectDescriptor) object; 
			return name.contentEquals(sd.name); 
		}
		return false; 
	}
	
	@Override
	public int compareTo(SubjectDescriptor sd) {
		return name.compareTo(sd.name);
	}
}
