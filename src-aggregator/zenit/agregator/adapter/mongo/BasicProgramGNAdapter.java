package zenit.agregator.adapter.mongo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import zenit.agregator.adapter.general.AbstractProgramGNAdapter;
import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.data.adapter.general.MongoAdapter;
import zenit.data.adapter.mongo.BasicProgramAdapter;

/**
 * Адаптер којим се барата са биљешкама за замјенски приказ о 
 * бази података програма. 
 * @author VM
 * @version 1.0
 */
public class BasicProgramGNAdapter implements MongoAdapter<ProgramGlueNotes>, AbstractProgramGNAdapter{
	private BasicProgramAdapter programAdapter;
	private MongoCollection<ProgramGlueNotes> collection; 
	
	public BasicProgramGNAdapter(BasicProgramAdapter programAdapter) {
		if(programAdapter==null) throw new NullPointerException();
		this.programAdapter=programAdapter; 
		this.collection = getDatabase().getCollection("ze_programs_gn", ProgramGlueNotes.class);
	}
	
	public BasicProgramAdapter getProgramAdapter() {
		return programAdapter;
	}


	@Override
	public MongoClient getDriver() {
		return programAdapter.getDriver();
	}

	@Override
	public MongoDatabase getDatabase() {
		return programAdapter.getDatabase();
	}

	@Override
	public MongoCollection<ProgramGlueNotes> getTable() {
		return collection;
	}
	
	public void put(ProgramGlueNotes glueNotes) {
		if(get(glueNotes.getProgramName())!=null) remove(glueNotes);
		insert(glueNotes);
	}
	
	public void insert(ProgramGlueNotes glueNotes) {
		collection.insertOne(glueNotes);
	}
	
	public void remove(ProgramGlueNotes glueNotes) {
		if(glueNotes==null) return; 
		collection.deleteOne(Document.parse("{programName:'"+glueNotes.getProgramName()+"'}")); 
	}
	
	public void update(String programName, ProgramGlueNotes glueNotes) {
		if(get(glueNotes.getProgramName())!=null && !glueNotes.getProgramName().contentEquals(programName)) throw new RuntimeException(programName);
		if(get(glueNotes.getProgramName())!=null) remove(glueNotes);
		insert(glueNotes);
	}
	
	public void remove(String glueNotes) {
		collection.deleteOne(Document.parse("{programName:'"+glueNotes+"'}")); 
	}
	
	public ProgramGlueNotes get(String glueNotes) {
		ProgramGlueNotes result = null; 
		for(ProgramGlueNotes notes :collection.find(Document.parse("{programName:'"+glueNotes+"'}")))
			result = notes;
		return result;
	}
	
	public List<ProgramGlueNotes> get() {
		ArrayList<ProgramGlueNotes> list = new ArrayList<>();
		for(ProgramGlueNotes notes : collection.find()) 
			list.add(notes);
		Collections.sort(list);
		return list;
	}
}
