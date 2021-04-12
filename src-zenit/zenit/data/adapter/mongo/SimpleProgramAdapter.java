package zenit.data.adapter.mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.data.adapter.general.MongoAdapter;
import zenit.data.adapter.general.ProgramAdapter;
import zenit.data.impl.StandardProgram;

/**
 * Адаптер за студијске програме, а реактиван на базу предмета, такође поштује и егзистенцијална 
 * огранишења предмета. 
 * @author VM
 * @version 1.0
 */
public class SimpleProgramAdapter implements ProgramAdapter, MongoAdapter<StandardProgram>{
	private CommonsEdgesController edgeController; 
	
	public SimpleProgramAdapter(CommonsEdgesController edgeController) {
		if(edgeController==null) throw new NullPointerException();
		this.edgeController = edgeController; 
	}
	
	public CommonsEdgesController getEdgeController() {
		return edgeController;
	}

	@Override
	public MongoClient getDriver() {
		return edgeController.getProgramAdapter().getDriver();
	}

	@Override
	public MongoDatabase getDatabase() {
		return edgeController.getProgramAdapter().getDatabase();
	}

	@Override
	public MongoCollection<StandardProgram> getTable() {
		return edgeController.getProgramAdapter().getTable();
	}

	@Override
	public List<String> list() {
		return edgeController.getProgramAdapter().list();
	}

	@Override
	public List<StandardProgram> get() {
		return edgeController.getProgramAdapter().get();
	}

	@Override
	public StandardProgram get(String id) {
		StandardProgram program = edgeController.getProgramAdapter().get(id);
		if(program==null) return null;
		for(String subject: new ArrayList<>(program.getSubjects()))
			if(edgeController.getSubjectAdapter().get(subject)==null) {
				program.removeSubject(subject);
				removeSubject(id, subject);
			} 
		return program;
	}

	@Override
	public void insert(StandardProgram program) {
		edgeController.getProgramAdapter().insert(program);
	}

	@Override
	public void delete(String id) {
		edgeController.getProgramAdapter().delete(id);
		edgeController.getBasicProgramGNAAdapter().remove(id);
	}

	@Override
	public void updateName(String id, String name) {
		edgeController.getProgramAdapter().updateName(id, name);
	}

	@Override
	public boolean updateProgramId(String id, String name) {
		try {
			ProgramGlueNotes notes = edgeController.getBasicProgramGNAAdapter().get(id); 
			edgeController.getProgramAdapter().updateProgramId(id, name);
			if(notes==null) return true; 
			notes.setProgramName(name);
			edgeController.getBasicProgramGNAAdapter().remove(id);
			edgeController.getBasicProgramGNAAdapter().insert(notes);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public void updateProgram(String id, String program) {
		edgeController.getProgramAdapter().updateProgram(id, program);
	}

	@Override
	public void updateCourse(String id, String course) {
		edgeController.getProgramAdapter().updateCourse(id, course);
	}

	@Override
	public boolean testSubject(String programId, String subjectId) {
		if(edgeController.getSubjectAdapter().get(subjectId)==null) return false;
		return edgeController.getProgramAdapter().testSubject(programId, subjectId);
	}

	@Override
	public boolean addSubject(String programId, String subjectId) {
		if(edgeController.getSubjectAdapter().get(subjectId)==null) throw new RuntimeException("Subject not found."); 
		return edgeController.getProgramAdapter().addSubject(programId, subjectId);
	}

	@Override
	public boolean removeSubject(String programId, String subjectId) {
		return edgeController.getProgramAdapter().removeSubject(programId, subjectId);
	}
}
