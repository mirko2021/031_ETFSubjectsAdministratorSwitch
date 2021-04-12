package zenit.agregator.web.gluenotes;

import java.io.Serializable;

/**
 * Биљешка за предмет. 
 * @author VM
 * @version 1.0
 */
public class SubjectGlueNote implements Serializable, Cloneable, Comparable<SubjectGlueNote>{
	private static final long serialVersionUID = 1144257145926843587L;
	private String key  = "";
	private String type = "";
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		if(key == null) key = "";
		this.key = key;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		if(type==null) type = "";
		this.type = type;
	}

	@Override
	public SubjectGlueNote clone() {
		SubjectGlueNote note = new SubjectGlueNote();
		note.key  = key;
		note.type = type;  
		return note;
	}
	
	@Override
	public int compareTo(SubjectGlueNote o) {
		return key.compareTo(key);
	}
}
