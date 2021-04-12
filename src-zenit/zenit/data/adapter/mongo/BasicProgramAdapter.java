package zenit.data.adapter.mongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.agregator.web.gluenotes.SubjectGlueNote;
import zenit.data.adapter.general.MongoAdapter;
import zenit.data.adapter.general.ProgramAdapter;
import zenit.data.impl.StandardProgram;

/**
 * Адаптер према бази података која се односи на студијске програме. 
 * @author VM
 * @version 1.1
 */
public class BasicProgramAdapter implements ProgramAdapter, MongoAdapter<StandardProgram>{
	private MongoClient driver; 
	private MongoDatabase database; 
	private MongoCollection<StandardProgram> table;
	
	public BasicProgramAdapter() {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
				.register(StandardProgram.class)
				.register(ProgramGlueNotes.class)
				.register(SubjectGlueNote.class)
				.automatic(false).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		MongoClientOptions opt = MongoClientOptions.builder()
				.codecRegistry(codecRegistry)
				.build();
		
		this.driver = new MongoClient(new ServerAddress("localhost", 27017), opt);
		this.database = driver.getDatabase("ze_learning"); 
		this.table = this.database.getCollection("ze_programs", StandardProgram.class);
	}

	public MongoClient getDriver() {
		return driver;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public MongoCollection<StandardProgram> getTable() {
		return table;
	}
	
	public List<String> list(){
		ArrayList<String> result = new ArrayList<>();
		for(StandardProgram prog: table.find())
			result.add(prog.getId());
		Collections.sort(result);
		return result; 
	}
	
	public List<StandardProgram> get(){
		ArrayList<StandardProgram> subjects = new ArrayList<>();
		for(StandardProgram prog: table.find())
			subjects.add(prog);
		Collections.sort(subjects);
		return subjects;
	}
	
	public StandardProgram get(String id){
		if(id==null) return null;
		for(StandardProgram prog: table.find(Document.parse("{'program_id':'"+id+"'}"))) {
			return prog;
		}
		return null;
	}
	
	public void insert(StandardProgram program) {
		if(get(program.getId())!=null) return;
		else table.insertOne(program);
	}
	
	
	public void delete(String id) {
		table.deleteOne(Document.parse("{'program_id':'"+id+"'}"));
	}
	
	public void updateName(String id, String name) {
		table.updateOne(Filters.eq("program_id", id), Updates.set("program_name", name));
	}
	
	public boolean updateProgramId(String id, String name) {
		if(get(id)   == null) return false; 
		if(get(name) != null) return false;
		table.updateOne(Filters.eq("program_id", id), Updates.set("program_id", name));
		return true;
	}
	
	public void updateProgram(String id, String program) {
		table.updateOne(Filters.eq("program_id", id), Updates.set("program", program));
	}
	
	public void updateCourse(String id, String course) {
		table.updateOne(Filters.eq("program_id", id), Updates.set("course", course));
	}
	
	@SuppressWarnings("unused")
	public boolean testSubject(String programId, String subjectId) {
		boolean ok = false;  
		for(StandardProgram p: table.find(Document.parse("{program_subjects: [\""+subjectId+"\"] }")))   ok = true;
		return ok;
	}
	
	public boolean addSubject(String programId, String subjectId) {
		if(testSubject(programId, subjectId)) return false;
		table.updateOne(Document.parse("{'program_id':'"+programId+"'}"), Document.parse("{'$push':{'program_subjects':'"+subjectId+"'}}"));
		return true;
		
	}
	
	public boolean removeSubject(String programId, String subjectId) {
		table.updateOne(Document.parse("{'program_id':'"+programId+"'}"), Document.parse("{'$pull':{'program_subjects':'"+subjectId+"'}}"));
		return true;
	}
}
