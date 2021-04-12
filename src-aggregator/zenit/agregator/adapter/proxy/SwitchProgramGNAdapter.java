package zenit.agregator.adapter.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import zenit.agregator.adapter.general.AbstractProgramGNAdapter;
import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.data.adapter.general.ProgramAdapter;

/**
 * Размјењивач адаптера за слијепљене биљешке, у складу са шемама 
 * промјена адаптера из контролера. 
 * @author VM
 * @version 1.0
 */
public class SwitchProgramGNAdapter implements AbstractProgramGNAdapter{
	private HashMap<String, AbstractProgramGNAdapter> adapterMap = new HashMap<>();
	private AbstractProgramGNAdapter currentAdapter;
	private String currentAdapterName = ""; 
	
	public boolean register(String id, AbstractProgramGNAdapter adapter) {
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
	
	public String getCurrentAdapterName() {
		return currentAdapterName;
	}

	public void resetAdapter() {
		currentAdapter = null;
		currentAdapterName = "";
	}
	
	public AbstractProgramGNAdapter getCurrentAdapter() {
		return currentAdapter;
	}

	public AbstractProgramGNAdapter getAdapter(String id) {
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
	public void put(ProgramGlueNotes glueNotes) {
		if(currentAdapter==null) return; 
		currentAdapter.put(glueNotes);
	}

	@Override
	public void insert(ProgramGlueNotes glueNotes) {
		if(currentAdapter==null) return; 
		currentAdapter.insert(glueNotes);
	}

	@Override
	public void remove(ProgramGlueNotes glueNotes) {
		if(currentAdapter==null) return; 
		currentAdapter.remove(glueNotes);
	}

	@Override
	public void update(String programName, ProgramGlueNotes glueNotes) {
		if(currentAdapter==null) return; 
		currentAdapter.update(programName, glueNotes);
	}

	@Override
	public void remove(String glueNotes) {
		if(currentAdapter==null) return; 
		currentAdapter.remove(glueNotes);
	}

	@Override
	public ProgramGlueNotes get(String glueNotes) {
		if(currentAdapter==null) return null;
		return currentAdapter.get(glueNotes);
	}

	@Override
	public List<ProgramGlueNotes> get() {
		if(currentAdapter==null) return new ArrayList<>();
		return currentAdapter.get(); 
	}
}
