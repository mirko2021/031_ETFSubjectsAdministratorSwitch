package zenit.agregator.adapter.mongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.model.Projections;

import zenit.agregator.adapter.data.GlueNotedProgramSchema;
import zenit.agregator.adapter.general.AbstractProgramSubjectsAdapter;
import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.data.adapter.mongo.BasicProgramAdapter;
import zenit.data.adapter.mongo.BasicSubjectAdapter;
import zenit.data.impl.StandardProgram;
import zenit.data.impl.StandardSubject;

/**
 * Адаптери за баратање предметима у зависности од
 * семестра и студијског програма.
 * @author VM
 * @version 1.0
 */
public class ProgramSubjectsAdapter implements AbstractProgramSubjectsAdapter{
	private BasicProgramAdapter programAdapter; 
	private BasicSubjectAdapter subjectAdapter; 
	
	public ProgramSubjectsAdapter(BasicProgramAdapter programAdapter, BasicSubjectAdapter subjectAdapter) {
		if(programAdapter==null) throw new NullPointerException();
		if(subjectAdapter==null) throw new NullPointerException();
		this.programAdapter = programAdapter;
		this.subjectAdapter = subjectAdapter; 
	}

	public BasicProgramAdapter getProgramAdapter() {
		return programAdapter;
	}

	public BasicSubjectAdapter getSubjectAdapter() {
		return subjectAdapter;
	}
	
	public List<StandardSubject> getForSemesters(String semester) {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'"+semester+"'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<String> listForSemesters(){
		ArrayList<String> semesters = new ArrayList<>();
		for(StandardSubject subject: subjectAdapter.getTable().find().projection(Projections.include("semestar")))
			if(!semesters.contains(subject.getSemestar())) semesters.add(subject.getSemestar());
		Collections.sort(semesters);
		return semesters; 
	}
	
	public HashMap<String, List<StandardSubject>> mapForSemesters(){
		List<String> semesters = listForSemesters();
		HashMap<String, List<StandardSubject>> semesterMap = new HashMap<>();
		for(String semester: semesters) 
			semesterMap.put(semester, getForSemesters(semester));
		return semesterMap; 
	}
	
	public List<StandardSubject> getForSemester1() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'I'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<StandardSubject> getForSemester2() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'II'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<StandardSubject> getForSemester3() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'III'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<StandardSubject> getForSemester4() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'IV'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<StandardSubject> getForSemester5() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'V'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<StandardSubject> getForSemester6() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'VI'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<StandardSubject> getForSemester7() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'VII'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<StandardSubject> getForSemester8() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{'semestar':'VIII'}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public List<StandardSubject> getForSemester0() {
		ArrayList<StandardSubject> list = new ArrayList<StandardSubject>();
		for(StandardSubject subject: subjectAdapter.getTable().find(Document.parse("{semestar : {$not: {$in : ['I','II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII']}}}")))
			list.add(subject);
		Collections.sort(list);
		return list;
	}
	
	public GlueNotedProgramSchema semestralSchemaForProgram(String programId, ProgramGlueNotes glueNotes) {
		StandardProgram program = programAdapter.get(programId); 
		if(program==null) return null;
		GlueNotedProgramSchema schema = new GlueNotedProgramSchema(glueNotes);
		schema.setStudyProgram(programId);
		for(String subjectId: program.getSubjects()) {
			try {
				StandardSubject subject = subjectAdapter.get(subjectId); 
				String semester = subject.getSemestar();
				switch(semester.intern()) {
					case "I":
						schema.add("1", subject);
						break;
					case "II": 
						schema.add("2", subject);
						break;
					case "III":
						schema.add("3", subject);
						break;
					case "IV": 
						schema.add("4", subject);
						break;
					case "V": 
						schema.add("5", subject);
						break;
					case "VI": 
						schema.add("6", subject);
						break;
					case "VII": 
						schema.add("7", subject);
						break;
					case "VIII":
						schema.add("8", subject);
						break;
					default: 
						schema.add("0", subject);
						break;
				}
			}catch(Exception ex) {
				continue;
			}
		}
		return schema;
	}
}
