package zenit.data.adapter.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import zenit.data.adapter.general.ProgramAdapter;
import zenit.data.impl.StandardProgram;

/**
 * Општи облик шаблона промјењивих адаптера за податке о предметима. 
 * @author VM
 * @version 1.0
 */
public class SwitchProgramAdapter implements ProgramAdapter{
	private HashMap<String, ProgramAdapter> adapterMap = new HashMap<>();
	private ProgramAdapter currentAdapter;
	private String currentAdapterName = ""; 
	
	public boolean register(String id, ProgramAdapter adapter) {
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
	
	public ProgramAdapter getCurrentAdapter() {
		return currentAdapter;
	}

	
	public String getCurrentAdapterName() {
		return currentAdapterName;
	}

	public ProgramAdapter getAdapter(String id) {
		return adapterMap.get(id);
	}
	
	public List<String> listAdapters(){
		ArrayList<String> result = new ArrayList<>(adapterMap.keySet());
		Collections.sort(result);
		return result;
	}
	
	@Override
	public List<String> list() {
		if(currentAdapter==null) return new ArrayList<>();
		return currentAdapter.list();
	}

	@Override
	public List<StandardProgram> get() {
		if(currentAdapter==null) return new ArrayList<>();
		return currentAdapter.get();
	}

	@Override
	public StandardProgram get(String id) {
		if(currentAdapter==null) return null;
		return currentAdapter.get(id);
	}

	@Override
	public void insert(StandardProgram program) {
		if(currentAdapter==null) return;
		currentAdapter.insert(program);
	}

	@Override
	public void delete(String id) {
		if(currentAdapter==null) return;
		currentAdapter.delete(id);
	}

	@Override
	public void updateName(String id, String name) {
		if(currentAdapter==null) return; 
		currentAdapter.updateName(id, name);
	}

	@Override
	public boolean updateProgramId(String id, String name) {
		if(currentAdapter==null) return false; 
		return currentAdapter.updateProgramId(id, name);
	}

	@Override
	public void updateProgram(String id, String program) {
		if(currentAdapter==null) return; 
		currentAdapter.updateProgram(id, program);
	}

	@Override
	public void updateCourse(String id, String course) {
		if(currentAdapter==null) return; 
		currentAdapter.updateCourse(id, course);
	}

	@Override
	public boolean testSubject(String programId, String subjectId) {
		if(currentAdapter==null) return false; 
		return currentAdapter.testSubject(programId, subjectId);
	}

	@Override
	public boolean addSubject(String programId, String subjectId) {
		if(currentAdapter==null) return false;
		return currentAdapter.addSubject(programId, subjectId); 
	}

	@Override
	public boolean removeSubject(String programId, String subjectId) {
		if(currentAdapter==null) return false;
		return currentAdapter.removeSubject(programId, subjectId);
	}	
}
