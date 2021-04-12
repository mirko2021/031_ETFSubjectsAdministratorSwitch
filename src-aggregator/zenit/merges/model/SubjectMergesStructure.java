package zenit.merges.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Општа структура за повезаност истоврсне измјењивости садржаја 
 * везана за предмет. 
 * @author VM
 * @version 1.0
 */
public class SubjectMergesStructure implements Serializable, Cloneable, Comparable<SubjectMergesStructure>{
	private static final long serialVersionUID = -5156969048962283139L;
	private String aliasId = "";
	private String mainSubjectId = ""; 
	private List<String> relatedSubjectsIds = new ArrayList<>();
	
	public SubjectMergesStructure() {}
	
	public static boolean testAliasId(String id) {
		for(char c: id.toCharArray()) {
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
			return false;
		}
		return true; 
	}
	
	public SubjectMergesStructure(String aliasId) {
		if(aliasId==null) aliasId = "";
		if(!testAliasId(aliasId)) throw new RuntimeException();
		this.aliasId = aliasId;
	}
	public SubjectMergesStructure(String aliasId, String mainSubjectId) {
		if(mainSubjectId==null) mainSubjectId = "";
		if(!testAliasId(aliasId)) throw new RuntimeException();
		this.aliasId = aliasId;
		this.mainSubjectId = mainSubjectId;
	}
	
	public String getAliasId() {
		return aliasId;
	}
	public void setAliasId(String aliasId) {
		if(aliasId==null) aliasId=""; 
		if(!testAliasId(aliasId)) throw new RuntimeException();
		this.aliasId = aliasId;
	}
	public String getMainSubjectId() {
		return mainSubjectId;
	}
	public void setMainSubjectId(String mainSubjectId) {
		if(mainSubjectId==null) mainSubjectId = ""; 
		this.mainSubjectId = mainSubjectId;
	}
	public List<String> getRelatedSubjectsIds() {
		return new ArrayList<>(relatedSubjectsIds);
	}
	public void setRelatedSubjectsIds(List<String> relatedSubjectsIds) {
		if( relatedSubjectsIds==null)  relatedSubjectsIds = new ArrayList<>(); 
		this.relatedSubjectsIds = new ArrayList<>(relatedSubjectsIds);
	}
	
	@Override
	public String toString() {
		return "SUBJECT CHANGER MERGER >> "+aliasId;
	}
	@Override
	public int hashCode() {
		return aliasId.hashCode(); 
	}
	
	@Override
	public SubjectMergesStructure clone() {
		SubjectMergesStructure copy = new SubjectMergesStructure(aliasId);
		copy.mainSubjectId = mainSubjectId; 
		copy.relatedSubjectsIds = new ArrayList<>(relatedSubjectsIds); 
		return copy;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof SubjectMergesStructure) {
			SubjectMergesStructure union = new SubjectMergesStructure();
			return aliasId.contentEquals(union.aliasId); 
		}
		return false;
	}
	
	@Override
	public int compareTo(SubjectMergesStructure subject) {
		return aliasId.compareTo(subject.aliasId);
	}
	
	public SubjectMergesStructure uniqueAndFine() {
		relatedSubjectsIds.remove(this.mainSubjectId);
		return this; 
	}
}
