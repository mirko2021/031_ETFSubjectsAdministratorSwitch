package zenit.data.model;

import java.io.Serializable;
import java.util.List;

import zenit.data.impl.StandardGradulation;

/**
 * Интерфејс који се однои на предмете. 
 * @author MV
 * @version 1.0
 */
public interface Subject extends Serializable{
	public String getSubjectId();
	public String getSubjectName();
	public void setSubjectName(String subjectName);
	public String getSemestar();
	public void setSemestar(String semestar);
	public String getLessonsFound();
	public void setLessonsFound(String lessonsFound);
	public String getEctsValue();
	public void setEctsValue(String ectsValue);
	public String getLectors();
	public void setLectors(String lectors);
	public String getEdges();
	public void setEdges(String edges);
	public String getEdgesForm();
	public void setEdgesForm(String edgesForm);
	public String getSubjectTarget();
	public void setSubjectTarget(String subjectTarget);
	public String getSubjectRealisations();
	public void setSubjectRealisations(String subjectRealisations);
	public String getSubjectContent();
	public void setSubjectContent(String subjectContent);
	public String getMethodLectionTransfer();
	public void setMethodLectionTransfer(String methodLectionTransfer);
	public List<String> getReferences();
	public void setReferences(List<String> references);
	public String getDataMajor();
	public void setDataMajor(String dataMajor);
	public StandardGradulation getGradulation();
	public void setGradulation(StandardGradulation gradulation);
	public String getNotes();
	public void setNotes(String notes);
	public String getSubjectState();
	public void setSubjectState(String subjectState);
	
}
