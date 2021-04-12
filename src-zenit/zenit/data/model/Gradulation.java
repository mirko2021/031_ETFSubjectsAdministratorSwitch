package zenit.data.model;

import java.io.Serializable;

/**
 * Односи се на начин оцијењивања. 
 * @author MV
 * @version 1.0
 */
public interface Gradulation extends Serializable{
	public int getFirstColocvium();
	public void setFirstColocvium(int firstColocvium);
	public int getSecondColocvium();
	public void setSecondColocvium(int secondColocvium);
	public int getFinalExams();
	public void setFinalExams(int finalExams);
	public int getLaboratoryExercies();
	public void setLaboratoryExercies(int laboratoryExercies);
	public String getGradulationNotes();
	public void setGradulationNotes(String gradulationNotes);
	public String getOtherExams();
	public void setOtherExams(String otherExams);
}
