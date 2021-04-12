package zenit.merges.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import zenit.merges.adapter.general.AbstractSubjectMergesAdapter;
import zenit.merges.model.SubjectMergesStructure;

/**
 * Контролер који се веже за повезане предмете. 
 * @author VM
 * @version 1.2
 */
public class SubjectMergesController {
	private AbstractSubjectMergesAdapter adapter;

	public SubjectMergesController(AbstractSubjectMergesAdapter adapter) {
		if(adapter==null) throw new NullPointerException();
		this.adapter = adapter; 
	}
	
	public AbstractSubjectMergesAdapter getAdapter() {
		return adapter;
	}
	
	public List<String> bindedFor(String id){
		ArrayList<String> result = new ArrayList<>();
		for(SubjectMergesStructure sms: getAdapter().get()) {
			if(sms.getRelatedSubjectsIds().contains(id))
				for(String subject: sms.getRelatedSubjectsIds()) {
					if(subject.contentEquals(id)) continue;
					if(result.contains(subject)) continue;
					result.add(subject);
				}
		}
		Collections.sort(result);
		return result; 
	}
	
	public void updateSubjectId(String oldSubjectId, String neoSubjectId) {
		for(SubjectMergesStructure sms: getAdapter().get()) {
			if(sms.getRelatedSubjectsIds().contains(oldSubjectId)) { 
				getAdapter().removeSubject(sms.getAliasId(), oldSubjectId);
				getAdapter().addSubject(sms.getAliasId(), neoSubjectId);
			}
		}
	}
}
