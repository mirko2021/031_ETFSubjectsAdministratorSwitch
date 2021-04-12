package zenit.data.controller;

import java.util.ArrayList;
import java.util.List;

import zenit.data.adapter.general.SubjectAdapter;
import zenit.data.adapter.mongo.CommonsEdgesController;
import zenit.data.adapter.mongo.SimpleSubjectAdapter;
import zenit.data.impl.StandardSubject;

/**
 * Контролер за предмете и листу предмета у бази података. 
 * @author MV
 * @version 1.3
 */
public class SubjectController {
	private List<String> subjectIds = new ArrayList<>();
	private SimpleSubjectAdapter subjectAdapter;
	private SwitchAdapterController switchIOController;
	
	public SubjectController(CommonsEdgesController controller) {
		subjectAdapter = new SimpleSubjectAdapter(controller);
	}
	
	public void initializeSwitchAdapter(SwitchAdapterController switchIOController) {
		this.switchIOController = switchIOController;
		this.switchIOController.getSubjectIOSwitch().register("MONGO_LOCAL_001", subjectAdapter);
		this.switchIOController.getSubjectIOSwitch().selectAdapter("MONGO_LOCAL_001");
	}
	
	public SwitchAdapterController getSwitchIOController() {
		return switchIOController;
	}
	
	public List<String> getSubjectIds() {
		return new ArrayList<>(subjectIds);
	}

	public SubjectAdapter getSubjectAdapter() {
		return switchIOController.getSubjectIOSwitch().getCurrentAdapter();
	}
	
	public SimpleSubjectAdapter getSimpleSubjectAdapter() {
		return subjectAdapter;
	}
	
	public void refresh() {
		subjectIds = subjectAdapter.list();
	}
	
	public StandardSubject get(String id) {
		if(!subjectIds.contains(id)) return null;
		StandardSubject subject = subjectAdapter.get(id);
		if(subject==null) subjectIds.remove(id);
		return subject; 
	}
	
	public void remove(String id) {
		if(!subjectIds.contains(id)) return;
		subjectIds.remove(id);
		subjectAdapter.delete(id);
	}
	
	public boolean add(StandardSubject subject) {
		if(subjectIds.contains(subject.getSubjectId())) return false;
		if(subjectAdapter.get(subject.getSubjectId())!=null) return false;
		subjectAdapter.insert(subject);
		subjectIds.add(subject.getSubjectId());
		return true;
	}
	
	public boolean updateId(String oldId, String newId) {
		if(!subjectIds.contains(oldId)) return false;
		if(subjectIds.contains(newId)) return false;
		StandardSubject ssOld = subjectAdapter.get(oldId);
		StandardSubject ssNew = subjectAdapter.get(newId);
		if(ssOld==null) return false;
		if(ssNew!=null) return false;
		ssNew = ssOld.apply(newId);
		subjectIds.remove(oldId);
		subjectIds.add(ssNew.getSubjectId());
		subjectAdapter.update(oldId, ssNew);
		return true;
	}
	
	public boolean update(String oldId, StandardSubject neoSubject) {
		if(!subjectIds.contains(oldId)) return false;
		if(subjectIds.contains(neoSubject.getSubjectId()))
			if(!oldId.contentEquals(neoSubject.getSubjectId())) return false;
		StandardSubject ssOld = subjectAdapter.get(oldId);
		StandardSubject ssNew = subjectAdapter.get(neoSubject.getSubjectId());
		if(ssOld==null) return false;
		if(ssNew!=null) 
			if(!oldId.contentEquals(neoSubject.getSubjectId()))
				return false;
		subjectAdapter.update(oldId, neoSubject);
		return true;
	}
}
