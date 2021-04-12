package zenit.data.adapter.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import zenit.data.adapter.general.SubjectAdapter;
import zenit.data.impl.StandardSubject;

/**
 * Адаптери којима се мијењају предмети. 
 * @author VM
 * @version 1.0
 */
public class SwitchSubjectAdapter implements SubjectAdapter{
	private HashMap<String, SubjectAdapter> adapterMap = new HashMap<>();
	private SubjectAdapter currentAdapter;
	private String currentAdapterName = ""; 
	
	public boolean register(String id, SubjectAdapter adapter) {
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
		currentAdapterName  = "";
	}
	
	public String getCurrentAdapterName() {
		return currentAdapterName;
	}

	public SubjectAdapter getCurrentAdapter() {
		return currentAdapter;
	}
	
	public SubjectAdapter getAdapter(String id) {
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
	public List<StandardSubject> get() {
		if(currentAdapter==null) return new ArrayList<>();
		return currentAdapter.get();
	}

	@Override
	public StandardSubject get(String id) {
		if(currentAdapter==null) return null;
		return currentAdapter.get(id);
	}

	@Override
	public void insert(StandardSubject subject) {
		if(currentAdapter==null) return; 
	    currentAdapter.insert(subject);
	}

	@Override
	public void delete(String id) {
		if(currentAdapter==null) return; 
		currentAdapter.delete(id);
	}

	@Override
	public void update(String oldId, StandardSubject neoSubject) {
		if(currentAdapter==null) return; 
		currentAdapter.update(oldId, neoSubject);
	}
}
