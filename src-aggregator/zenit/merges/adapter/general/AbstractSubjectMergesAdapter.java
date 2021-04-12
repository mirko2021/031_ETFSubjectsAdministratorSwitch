package zenit.merges.adapter.general;

import java.util.List;

import zenit.merges.model.SubjectMergesStructure;

/**
 * Општи адаптер за спојене предмете. 
 * @author VM
 * @version 1.0
 */
public interface AbstractSubjectMergesAdapter {
	public boolean put(SubjectMergesStructure object);
	public boolean set(SubjectMergesStructure object);
	public boolean remove(SubjectMergesStructure object);
	public boolean remove(String aliasId);
	public void update(String aliasId, SubjectMergesStructure aliasData);
	public List<SubjectMergesStructure> get();
	public SubjectMergesStructure  get(String aliasId);
	public boolean  contains(String aliasId);
	public int count();
	public boolean acceptInfo(String aliasId, SubjectMergesStructure object);
	public boolean testSubject(String aliasId, String subjectId);
	public boolean addSubject(String aliasId, String subjectId);
	public boolean removeSubject(String aliasId, String subjectId);
}
