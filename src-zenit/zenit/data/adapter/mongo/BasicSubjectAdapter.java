package zenit.data.adapter.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import zenit.data.adapter.general.MongoAdapter;
import zenit.data.adapter.general.SubjectAdapter;
import zenit.data.impl.StandardGradulation;
import zenit.data.impl.StandardSubject;
import zenit.merges.model.SubjectMergesStructure;


/**
 * Адаптер за предмете према МОНГО бази података. 
 * @author MV
 * @version 1.0
 */
public class BasicSubjectAdapter implements SubjectAdapter, MongoAdapter<StandardSubject>{
	private MongoClient driver; 
	private MongoDatabase database; 
	private MongoCollection<StandardSubject> table;
	
	public BasicSubjectAdapter() {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
				.register(StandardSubject.class)
				.register(StandardGradulation.class)
				.register(SubjectMergesStructure.class)
				.automatic(false).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		MongoClientOptions opt = MongoClientOptions.builder()
				.codecRegistry(codecRegistry)
				.build();
		
		this.driver = new MongoClient(new ServerAddress("localhost", 27017), opt);
		this.database = driver.getDatabase("ze_learning"); 
		this.table = this.database.getCollection("ze_subjects", StandardSubject.class);
	}
	
	public MongoClient getDriver() {
		return driver;
	}
	public MongoDatabase getDatabase() {
		return database;
	}
	public MongoCollection<StandardSubject> getTable() {
		return table;
	}
	
	public List<String> list(){
		ArrayList<String> result = new ArrayList<>();
		for(StandardSubject subj: table.find())
			result.add(subj.getSubjectId());
		return result; 
	}
	
	public List<StandardSubject> get(){
		ArrayList<StandardSubject> subjects = new ArrayList<>();
		for(StandardSubject subj: table.find())
			subjects.add(subj);
		return subjects;
	}
	
	public StandardSubject get(String id){
		if(id==null) return null;
		for(StandardSubject subj: table.find(Document.parse("{'subject_id':'"+id+"'}")))  
			return subj;
		return null;
	}
	
	public void insert(StandardSubject subject) {
		if(get(subject.getSubjectId())!=null) return;
		else table.insertOne(subject);
	}
	
	public void delete(String id) {
		table.deleteOne(Document.parse("{'subject_id':'"+id+"'}"));
	}

	@Override
	public void update(String oldId, StandardSubject neoSubject) {
		delete(oldId);
		insert(neoSubject);
	}
}
