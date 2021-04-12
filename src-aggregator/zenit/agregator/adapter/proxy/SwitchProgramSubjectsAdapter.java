package zenit.agregator.adapter.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import zenit.agregator.adapter.data.GlueNotedProgramSchema;
import zenit.agregator.adapter.general.AbstractProgramSubjectsAdapter;
import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.data.adapter.general.ProgramAdapter;
import zenit.data.adapter.general.SubjectAdapter;
import zenit.data.impl.StandardSubject;

/**
 * Адаптер који се прилагођвања схеми адаптера и промјена, 
 * а за спојене прдмете. 
 * @author VM
 * @version 1.0
 */
public class SwitchProgramSubjectsAdapter implements AbstractProgramSubjectsAdapter{
	private HashMap<String, AbstractProgramSubjectsAdapter> adapterMap = new HashMap<>();
	private AbstractProgramSubjectsAdapter currentAdapter;
	private String currentAdapterName = ""; 
	
	public boolean register(String id, AbstractProgramSubjectsAdapter adapter) {
		if(id==null) return false;
		if(adapter==null) return false;
		if(id.trim().length()==0) return false; 
		adapterMap.put(id, adapter);
		return true;
	}
	
	public void removeAdapter(String id) {
		adapterMap.remove(id);
	}
	
	public void selectAdapter(String id) {
		currentAdapter = adapterMap.get(id);
		currentAdapterName = id; 
	}
	
	public void resetAdapter() {
		currentAdapter = null; 
		currentAdapterName = "";
	}
	
	public String getCurrentAdapterName() {
		return currentAdapterName;
	}

	public AbstractProgramSubjectsAdapter getCurrentAdapter() {
		return currentAdapter;
	}

	public AbstractProgramSubjectsAdapter getAdapter(String id) {
		return adapterMap.get(id);
	}
	
	public List<String> listAdapters(){
		ArrayList<String> result = new ArrayList<>(adapterMap.keySet());
		Collections.sort(result);
		return result;
	}
	
	@Override
	public ProgramAdapter getProgramAdapter() {
		if(currentAdapter==null) return null; 
		return currentAdapter.getProgramAdapter();
	}

	@Override
	public SubjectAdapter getSubjectAdapter() {
		if(currentAdapter==null) return null;
		return currentAdapter.getSubjectAdapter();
	}

	@Override
	public List<StandardSubject> getForSemesters(String semester) {
		if(currentAdapter==null) return new ArrayList<>();
		return currentAdapter.getForSemesters(semester);
	}

	@Override
	public List<String> listForSemesters() {
		if(currentAdapter==null) return new ArrayList<>();
		return currentAdapter.listForSemesters();
	}

	@Override
	public HashMap<String, List<StandardSubject>> mapForSemesters() {
		if(currentAdapter==null) return new HashMap<>();
		return currentAdapter.mapForSemesters();
	}

	@Override
	public List<StandardSubject> getForSemester1() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester1();
	}

	@Override
	public List<StandardSubject> getForSemester2() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester2();
	}

	@Override
	public List<StandardSubject> getForSemester3() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester3();
	}

	@Override
	public List<StandardSubject> getForSemester4() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester4();
	}

	@Override
	public List<StandardSubject> getForSemester5() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester5();
	}

	@Override
	public List<StandardSubject> getForSemester6() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester6();
	}

	@Override
	public List<StandardSubject> getForSemester7() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester7();
	}

	@Override
	public List<StandardSubject> getForSemester8() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester8();
	}

	@Override
	public List<StandardSubject> getForSemester0() {
		if(currentAdapter==null) return new ArrayList<>(); 
		return currentAdapter.getForSemester0();
	}

	@Override
	public GlueNotedProgramSchema semestralSchemaForProgram(String programId, ProgramGlueNotes glueNotes) {
		if(currentAdapter==null) return null;
		return currentAdapter.semestralSchemaForProgram(programId, glueNotes);
	}
	
}
