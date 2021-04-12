package zenit.data.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;

import zenit.data.model.Subject;

/**
 * Уобичајена репрезентација за предмет. 
 * @author MV
 * @version 1.0
 */
public class StandardSubject implements Subject, Cloneable, Comparable<StandardSubject>{
	private static final long serialVersionUID = 9209107725752868827L;
	
	@BsonProperty("subject_id")                private String subjectId = "";
	@BsonProperty("subject_name")              private String subjectName = ""; 
	@BsonProperty("semestar")                  private String semestar = ""; 
	@BsonProperty("lessons_found")             private String lessonsFound = ""; 
	@BsonProperty("ects_value")                private String ectsValue = ""; 
	@BsonProperty("lectors")                   private String lectors = ""; 
	@BsonProperty("edges")                     private String edges = ""; 
	@BsonProperty("edges_form")                private String edgesForm = ""; 
	@BsonProperty("subject_target")            private String subjectTarget = ""; 
	@BsonProperty("subject_realisations")      private String subjectRealisations = ""; 
	@BsonProperty("subject_content")           private String subjectContent = ""; 
	@BsonProperty("method_lection_transfer")   private String methodLectionTransfer = ""; 
	@BsonProperty("references")                private List<String> references = new ArrayList<>(); 
	@BsonProperty("data_major")                private String dataMajor = "";
	@BsonProperty("gradulation")               private StandardGradulation gradulation = new StandardGradulation(); 
	@BsonProperty("subject_notes")			   private String notes = ""; 
	@BsonProperty("subject_state") 			   private String subjectState = "";
	
	public StandardSubject() {}
	
	public StandardSubject(String id) {
		if(id==null) id="";
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
			throw new RuntimeException();
		}
		subjectId = id; 
	}
	
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String getSubjectId() {
		return subjectId;
	}
	
	@Override
	public String getSubjectName() {
		return subjectName;
	}

	@Override
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	@Override
	public String getSemestar() {
		return semestar;
	}
	
	@Override
	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}
	
	@Override
	public String getLessonsFound() {
		return lessonsFound;
	}

	@Override
	public void setLessonsFound(String lessonsFound) {
		this.lessonsFound = lessonsFound;
	}

	@Override
	public String getEctsValue() {
		return ectsValue;
	}
	
	@Override
	public void setEctsValue(String ectsValue) {
		this.ectsValue = ectsValue;
	}

	@Override
	public String getLectors() {
		return lectors;
	}

	@Override
	public void setLectors(String lectors) {
		if(lectors==null) lectors = "";
		this.lectors = lectors;
	}

	@Override
	public String getEdges() {
		return edges;
	}

	@Override
	public void setEdges(String edges) {
		if(edges==null) edges = ""; 
		this.edges = edges;
	}

	@Override
	public String getEdgesForm() {
		return edgesForm;
	}

	@Override
	public void setEdgesForm(String edgesForm) {
		if(edgesForm==null) edgesForm = ""; 
		this.edgesForm = edgesForm;
	}

	@Override
	public String getSubjectTarget() {
		return subjectTarget;
	}

	@Override
	public void setSubjectTarget(String subjectTarget) {
		if(subjectTarget==null) subjectTarget="";
		this.subjectTarget = subjectTarget;
	}

	@Override
	public String getSubjectRealisations() {
		return subjectRealisations;
	}

	@Override
	public void setSubjectRealisations(String subjectRealisations) {
		if(subjectRealisations=="") subjectRealisations = "";
		this.subjectRealisations = subjectRealisations;
	}

	@Override
	public String getSubjectContent() {
		return subjectContent;
	}

	@Override
	public void setSubjectContent(String subjectContent) {
		if(subjectContent=="") subjectContent = ""; 
		this.subjectContent = subjectContent;
	}

	@Override
	public String getMethodLectionTransfer() {
		return methodLectionTransfer;
	}

	@Override
	public void setMethodLectionTransfer(String methodLectionTransfer) {
		if(methodLectionTransfer==null) methodLectionTransfer="";
		this.methodLectionTransfer = methodLectionTransfer;
	}

	@Override
	public List<String> getReferences() {
		return references;
	}

	@Override
	public void setReferences(List<String> references) {
		if(references==null) references = new ArrayList<>(); 
		this.references = references;
	}

	@Override
	public String getDataMajor() {
		return dataMajor;
	}

	@Override
	public void setDataMajor(String dataMajor) {
		if(dataMajor==null) dataMajor = ""; 
		this.dataMajor = dataMajor;
	}
	
	@Override
	public StandardGradulation getGradulation() {
		return gradulation;
	}

	@Override
	public void setGradulation(StandardGradulation gradulation) {
		if(gradulation==null) gradulation = new StandardGradulation(); 
		this.gradulation = gradulation;
	}

	@Override
	public int hashCode() {
		return subjectId.hashCode(); 
	}
	
	@Override 
	public String toString() {
		return "STANDARD SUBJECT >> "+ subjectId;
	}
	
	@Override
	public StandardSubject clone() {
		StandardSubject standardSubject = new StandardSubject(subjectId);
		standardSubject.dataMajor = dataMajor; 
		standardSubject.ectsValue = ectsValue;
		standardSubject.edges = edges; 
		standardSubject.edgesForm = edgesForm; 
		standardSubject.gradulation = new StandardGradulation();
		standardSubject.gradulation.setFinalExams(gradulation.getFinalExams());
		standardSubject.gradulation.setFirstColocvium(gradulation.getFirstColocvium());
		standardSubject.gradulation.setFirstColocvium(gradulation.getSecondColocvium());
		standardSubject.gradulation.setLaboratoryExercies(gradulation.getLaboratoryExercies());
		standardSubject.lectors = lectors;
		standardSubject.lessonsFound = lessonsFound; 
		standardSubject.methodLectionTransfer = methodLectionTransfer; 
		standardSubject.references = references;
		standardSubject.semestar = semestar;
		standardSubject.subjectContent = subjectContent;
		standardSubject.subjectName = subjectName;
		standardSubject.subjectRealisations = subjectRealisations; 
		standardSubject.subjectTarget = subjectTarget; 
		return standardSubject; 
	}
	
	public StandardSubject apply(String neoId) {
		StandardSubject standardSubject = new StandardSubject(neoId);
		standardSubject.dataMajor = dataMajor; 
		standardSubject.ectsValue = ectsValue;
		standardSubject.edges = edges; 
		standardSubject.edgesForm = edgesForm; 
		standardSubject.gradulation = new StandardGradulation();
		standardSubject.gradulation.setFinalExams(gradulation.getFinalExams());
		standardSubject.gradulation.setFirstColocvium(gradulation.getFirstColocvium());
		standardSubject.gradulation.setFirstColocvium(gradulation.getSecondColocvium());
		standardSubject.gradulation.setLaboratoryExercies(gradulation.getLaboratoryExercies());
		standardSubject.lectors = lectors;
		standardSubject.lessonsFound = lessonsFound; 
		standardSubject.methodLectionTransfer = methodLectionTransfer; 
		standardSubject.references = references;
		standardSubject.semestar = semestar;
		standardSubject.subjectContent = subjectContent;
		standardSubject.subjectName = subjectName;
		standardSubject.subjectRealisations = subjectRealisations; 
		standardSubject.subjectTarget = subjectTarget; 
		return standardSubject; 
	}
	
	@Override 
	public boolean equals(Object o) {
		if(o instanceof StandardSubject) {
			StandardSubject ss = (StandardSubject) o;
			return subjectId.contentEquals(ss.getSubjectId());
		}
		return false; 
	}
	
	@Override
	public int compareTo(StandardSubject ss) {
		return subjectId.compareTo(ss.subjectId);
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public void setNotes(String notes) {
		if(notes == null) throw new RuntimeException();
		this.notes = notes;
	}
	
	@Override
	public String getSubjectState() {
		return subjectState;
	}
	
	@Override
	public void setSubjectState(String subjectState) {
		if(subjectState==null) subjectState = ""; 
		this.subjectState = subjectState;
	}
}
