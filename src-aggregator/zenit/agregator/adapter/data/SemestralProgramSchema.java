package zenit.agregator.adapter.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zenit.data.impl.StandardSubject;

/**
 * Добија се као резултат, упита за 
 * предмете датог студијског програма, 
 * распређене по семестрима. Успутно има 
 * својства агрегације разних параметара. 
 * Као и преборјавања. 
 * @author VM
 * @version 1.0
 */
public class SemestralProgramSchema implements Serializable{
	private static final long serialVersionUID = 8167917543453790531L;
	private String studyProgram = "";
	private HashMap<String, List<StandardSubject>> studyProgramMap = new HashMap<>();
	
	public String getStudyProgram() {
		return studyProgram;
	}
	public void setStudyProgram(String studyProgram) {
		if(studyProgram==null) studyProgram = ""; 
		this.studyProgram = studyProgram;
	} 
	
	public boolean add(String semester, StandardSubject subject) {
		if(semester==null) return false; 
		if(subject==null) return false; 
		if(!studyProgramMap.containsKey(semester)) studyProgramMap.put(semester, new ArrayList<>());
		if(studyProgramMap.get(semester).contains(subject)) return false; 
		studyProgramMap.get(semester).add(subject);
		return true; 
	}
	
	public List<StandardSubject> get(String semester){
		if(studyProgramMap.get(semester)==null) return new ArrayList<>(); 
		ArrayList<StandardSubject> result = new ArrayList<>(studyProgramMap.get(semester));
		Collections.sort(result);
		return result; 
	}
	
	public boolean exists(String semester, String id) {
		try {
			for(StandardSubject subject: get(semester))
				if(id.contentEquals(subject.getSubjectId())) return true; 
			return false; 
		}catch(Exception ex) {
			return false;
		}
	}
	
	public String semester(String subject) {
		for(Map.Entry<String, List<StandardSubject>> me: studyProgramMap.entrySet()) {
			for(StandardSubject s: me.getValue()) {
				if(s.getSubjectId().contentEquals(subject))
					return me.getKey(); 
			}
		}
		return null; 
	}
	
	public StandardSubject subject(String subject) {
		for(Map.Entry<String, List<StandardSubject>> me: studyProgramMap.entrySet()) {
			for(StandardSubject s: me.getValue()) {
				if(s.getSubjectId().contentEquals(subject))
					return s; 
			}
		}
		return null; 
	}
	
	public void removeSemester(String semester) {
		studyProgramMap.remove(semester);
	}
	
	public void removeSubject(String subject) {
		String semester = semester(subject);
		if(semester==null) return; 
		studyProgramMap.get(semester).remove(subject(subject)); 
		if(studyProgramMap.get(semester).size()==0)
			studyProgramMap.remove(semester); 
	}
	
	public List<String> semesters(){
		ArrayList<String> result = new ArrayList<>(studyProgramMap.keySet());
		Collections.sort(result);
		return result; 
	}
	
	public int countSemesters() {
		return studyProgramMap.size(); 
	}
	
	public HashMap<String, Integer> countBySemesters() {
		HashMap<String, Integer> countMap = new HashMap<>();
		for(Map.Entry<String, List<StandardSubject>> me: studyProgramMap.entrySet()) 
			countMap.put(me.getKey(), me.getValue().size());
		return countMap;
	}
	
	public int countSubjects(String semester) {
		try {
			return studyProgramMap.get(semester).size(); 
		}catch(Exception ex) {
			return 0; 
		}
	}
	
	public int countSubjects() {
		int countSum = 0;
		for(List<StandardSubject> subjects: studyProgramMap.values()) 
			countSum+=subjects.size();
		return countSum;
	}
	
	
	public int ectsInSemester(String semester) {
		int ectsSum = 0; 
		List<StandardSubject> subjects = get(semester);
		for(StandardSubject subject: subjects) {
			try {
				String ectsString = subject.getEctsValue(); 
				int ectsValue = Integer.parseInt(ectsString.trim());
				ectsSum += ectsValue; 
			}catch(Exception ex) {
				ectsSum += 0; 
				continue;
			}
		}
		return ectsSum; 
	}
	
	public HashMap<String, Integer> ectsBySemester(){
		HashMap<String, Integer> countMap = new HashMap<>();
		for(Map.Entry<String, List<StandardSubject>> me: studyProgramMap.entrySet()) 
			countMap.put(me.getKey(), ectsInSemester(me.getKey()));
		return countMap;
	}
	
	public int ectsTotal() {
		int ectsSum = 0; 
		for(int ects: ectsBySemester().values())
			ectsSum += ects; 
		return ectsSum; 
	}
	
	public int lessonsInSemester(String semester) {
		int lessonsCountSum = 0; 
		List<StandardSubject> subjects = get(semester);
		for(StandardSubject subject: subjects) {
			try {
				String lessonFoundString = subject.getLessonsFound(); 
				String[] parts = lessonFoundString.split("\\+"); 
				for(String part: parts) {
					try {
						int lessonsCountValue = Integer.parseInt(part.trim()); 
						lessonsCountSum += lessonsCountValue; 
					}catch(Exception ex) {
						continue;
					}
				}
			}catch(Exception ex) {
				lessonsCountSum += 0; 
				continue;
			}
		}
		return lessonsCountSum; 
	}
	
	public HashMap<String, Integer> lessonsBySemester(){
		HashMap<String, Integer> countMap = new HashMap<>();
		for(Map.Entry<String, List<StandardSubject>> me: studyProgramMap.entrySet()) 
			countMap.put(me.getKey(), lessonsInSemester(me.getKey()));
		return countMap;
	}
	
	public int lessonsTotal() {
		int lessonsSum = 0; 
		for(int lessons: lessonsBySemester().values())
			lessonsSum += lessons; 
		return lessonsSum;
	}
}
