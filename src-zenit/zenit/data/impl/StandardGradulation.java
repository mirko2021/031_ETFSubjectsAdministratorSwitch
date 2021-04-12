package zenit.data.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;

import zenit.data.model.Gradulation;

/**
 * Уобичајена репрезентација за податке о оцјењивању. 
 * @author MV
 * @version 1.0
 */
public class StandardGradulation implements Gradulation{
	private static final long serialVersionUID = 3214114392814170351L;
	
	@BsonProperty("gradulation_notes")    private String gradulationNotes = "";
	@BsonProperty("first_colocvium")      private int firstColocvium = 0;
	@BsonProperty("second_colocvium")     private int secondColocvium = 0; 
	@BsonProperty("final_exams")          private int finalExams = 0; 
	@BsonProperty("laboratory_exercise")  private int laboratoryExercies = 0;
	@BsonProperty("other_exams")		  private String otherExams = ""; 
	@BsonProperty("grade_data")           private HashMap<String, String> dataMap = new HashMap<>(); 
	
	@Override
	public int getFirstColocvium() {
		return firstColocvium;
	}
	
	@Override
	public void setFirstColocvium(int firstColocvium) {
		this.firstColocvium = firstColocvium;
	}
	
	@Override
	public int getSecondColocvium() {
		return secondColocvium;
	}
	
	@Override
	public void setSecondColocvium(int secondColocvium) {
		this.secondColocvium = secondColocvium;
	}
	
	@Override
	public int getFinalExams() {
		return finalExams;
	}
	
	@Override
	public void setFinalExams(int finalExams) {
		this.finalExams = finalExams;
	}
	
	@Override
	public int getLaboratoryExercies() {
		return laboratoryExercies;
	}
	
	@Override
	public void setLaboratoryExercies(int laboratoryExercies) {
		this.laboratoryExercies = laboratoryExercies;
	}
	
	@Override
	public String getGradulationNotes() {
		return gradulationNotes;
	}

	@Override
	public void setGradulationNotes(String gradulationNotes) {
		if(gradulationNotes==null) gradulationNotes = "";
		this.gradulationNotes = gradulationNotes;
	}

	public String getOtherExams() {
		return otherExams;
	}

	public void setOtherExams(String otherExams) {
		if(otherExams==null) otherExams = ""; 
		this.otherExams = otherExams;
	}

	public HashMap<String, String> getDataMap() {
		return new HashMap<>(dataMap);
	}

	public void setDataMap(HashMap<String, String> dataMap) {
		this.dataMap = new HashMap<>(dataMap);
	}
	
	public List<String> getAllKeys(){
		ArrayList<String> list = new ArrayList<>(dataMap.values());
		Collections.sort(list);
		return list; 
	}
	
	public String getData(String key) {
		return dataMap.get(key); 
	}
	
	public void putData(String key, String value) {
		dataMap.put(key, value); 
	}
}
