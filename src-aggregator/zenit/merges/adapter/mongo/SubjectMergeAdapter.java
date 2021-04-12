package zenit.merges.adapter.mongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import zenit.data.adapter.general.MongoAdapter;
import zenit.data.adapter.mongo.BasicSubjectAdapter;
import zenit.merges.adapter.general.AbstractSubjectMergesAdapter;
import zenit.merges.model.SubjectMergesStructure;

/**
 * Адаптер према бази података МОНГО кад су у питању студијски програми. 
 * @author VM
 * @version 1.0
 */
public class SubjectMergeAdapter implements MongoAdapter<SubjectMergesStructure>, AbstractSubjectMergesAdapter{
	private BasicSubjectAdapter subjectAdapter; 
	private MongoCollection<SubjectMergesStructure> collection; 
	
	public SubjectMergeAdapter(BasicSubjectAdapter subjectAdapter) {
		if(subjectAdapter==null) throw new NullPointerException();
		this.subjectAdapter = subjectAdapter; 
		this.collection = getDatabase().getCollection("ze_aliases", SubjectMergesStructure.class);
	}
	
	@Override
	public MongoClient getDriver() {
		return subjectAdapter.getDriver();
	}

	@Override
	public MongoDatabase getDatabase() {
		return subjectAdapter.getDatabase();
	}

	@Override
	public MongoCollection<SubjectMergesStructure> getTable() {
		return collection;
	}
	
	public boolean put(SubjectMergesStructure object) {
		if(contains(object.getAliasId())) remove(object);
		collection.insertOne(object);
		return true;
	}
	
	public boolean set(SubjectMergesStructure object) {
		if(contains(object.getAliasId())) return false;
		collection.insertOne(object);
		return true;
	}
	
	public boolean remove(SubjectMergesStructure object) {
		if(!contains(object.getAliasId())) return false;
		collection.deleteOne(Document.parse("{aliasId:'"+object.getAliasId()+"'}")); 
		return true;
	}
	
	public boolean remove(String aliasId) {
		try {
			if(!contains(aliasId)) return false;
			collection.deleteOne(Document.parse("{'aliasId':'"+aliasId+"'}")); 
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public void update(String aliasId, SubjectMergesStructure aliasData) {
		if(!contains(aliasId)) throw new RuntimeException("Target not  found.");
		if(!aliasId.contentEquals(aliasData.getAliasId()) && contains(aliasData.getAliasId())) throw new RuntimeException("Forbidden destination.");
		if(aliasId.contentEquals(aliasData.getAliasId())) remove(aliasId); 
		put(aliasData);
	}
	
	public List<SubjectMergesStructure> get() {
		ArrayList<SubjectMergesStructure> list = new ArrayList<>();
		for(SubjectMergesStructure structure : collection.find()) 
			list.add(structure);
		Collections.sort(list);
		return list; 
	}
	
	public SubjectMergesStructure  get(String aliasId) {
		SubjectMergesStructure result = null;
		for(SubjectMergesStructure structure : collection.find(Document.parse("{aliasId:'"+aliasId+"'}"))) 
			return structure;
		return result;
	}
	
	public boolean  contains(String aliasId) {
		return get(aliasId)!=null;
	}
	
	public int count() {
		return get().size();
	}
	
	public boolean acceptInfo(String aliasId, SubjectMergesStructure object) {
		if(object==null) return false;
		if(get(aliasId)==null) return false;
		if(!aliasId.contentEquals(object.getAliasId()) && get(object.getAliasId())!=null) return false;
		try {
			collection.updateOne(Filters.eq("aliasId",aliasId), Updates.combine(
					Updates.set("mainSubjectId",object.getMainSubjectId()), 
					Updates.set("aliasId",object.getAliasId())
				)); 			
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	

	public boolean testSubject(String aliasId, String subjectId) {
		SubjectMergesStructure structure = get(aliasId);
		if(structure==null) return false;
		return structure.getRelatedSubjectsIds().contains(subjectId); 
	}
	
	
	public boolean addSubject(String aliasId, String subjectId) {
		if(testSubject(aliasId, subjectId)) return false;
		collection.updateOne(Document.parse("{'aliasId':'"+aliasId+"'}"), Document.parse("{'$push':{'relatedSubjectsIds':'"+subjectId+"'}}"));
		return true;
		
	}
	
	public boolean removeSubject(String aliasId, String subjectId) {
		if(!testSubject(aliasId, subjectId)) return false; 
		collection.updateOne(Document.parse("{'aliasId':'"+aliasId+"'}"), Document.parse("{'$pull':{'relatedSubjectsIds':'"+subjectId+"'}}"));
		return true;
	}
}
