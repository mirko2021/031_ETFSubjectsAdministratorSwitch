package zenit.merges.adapter.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import zenit.merges.adapter.general.AbstractSubjectMergesAdapter;
import zenit.merges.model.SubjectMergesStructure;

/**
 * Раздјелник адаптера спојених предмета у складу са схемама улаза излаза.
 * @author VM
 * @version 1.0
 */
public class SwitchSubjectMergeAdapter implements AbstractSubjectMergesAdapter{
	private HashMap<String, AbstractSubjectMergesAdapter> adapterMap = new HashMap<>();
	private AbstractSubjectMergesAdapter currentAdapter;
	private String currentAdapterName = "";
	
	public boolean register(String id, AbstractSubjectMergesAdapter adapter) {
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
	
	public HashMap<String, AbstractSubjectMergesAdapter> getAdapterMap() {
		return adapterMap;
	}

	public String getCurrentAdapterName() {
		return currentAdapterName;
	}

	public AbstractSubjectMergesAdapter getCurrentAdapter() {
		return currentAdapter;
	}

	public AbstractSubjectMergesAdapter getAdapter(String id) {
		return adapterMap.get(id);
	}
	
	public List<String> listAdapters(){
		ArrayList<String> result = new ArrayList<>(adapterMap.keySet());
		Collections.sort(result);
		return result;
	}
	
	@Override
	public boolean put(SubjectMergesStructure object) {
		if(currentAdapter == null) return false; 
		return currentAdapter.put(object);
	}

	@Override
	public boolean set(SubjectMergesStructure object) {
		if(currentAdapter == null) return false; 
		return currentAdapter.set(object);
	}

	@Override
	public boolean remove(SubjectMergesStructure object) {
		if(currentAdapter == null) return false; 
		return currentAdapter.remove(object);
	}

	@Override
	public boolean remove(String aliasId) {
		if(currentAdapter == null) return false; 
		return currentAdapter.remove(aliasId);
	}

	@Override
	public void update(String aliasId, SubjectMergesStructure aliasData) {
		if(currentAdapter == null) return; 
		currentAdapter.update(aliasId, aliasData);
	}

	@Override
	public List<SubjectMergesStructure> get() {
		if(currentAdapter == null) return new ArrayList<>();
		return currentAdapter.get(); 
	}

	@Override
	public SubjectMergesStructure get(String aliasId) {
		if(currentAdapter == null) return null;
		return currentAdapter.get(aliasId);
	}

	@Override
	public boolean contains(String aliasId) {
		if(currentAdapter==null) return false; 
		return currentAdapter.contains(aliasId);
	}

	@Override
	public int count() {
		if(currentAdapter==null) return 0; 
		return currentAdapter.count();
	}

	@Override
	public boolean acceptInfo(String aliasId, SubjectMergesStructure object) {
		if(currentAdapter==null) return false;
		return currentAdapter.acceptInfo(aliasId, object);
	}

	@Override
	public boolean testSubject(String aliasId, String subjectId) {
		if(currentAdapter==null) return false;
		return currentAdapter.testSubject(aliasId, subjectId);
	}

	@Override
	public boolean addSubject(String aliasId, String subjectId) {
		if(currentAdapter==null) return false; 
		return currentAdapter.addSubject(aliasId, subjectId);
	}

	@Override
	public boolean removeSubject(String aliasId, String subjectId) {
		if(currentAdapter==null) return false;
		return currentAdapter.removeSubject(aliasId, subjectId);
	}
}
