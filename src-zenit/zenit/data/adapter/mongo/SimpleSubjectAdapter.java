package zenit.data.adapter.mongo;

import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import zenit.data.adapter.general.MongoAdapter;
import zenit.data.adapter.general.SubjectAdapter;
import zenit.data.impl.StandardProgram;
import zenit.data.impl.StandardSubject;

/**
 * Адаптер за податке о предметима који укључује и реакције и ограничења од предмета према програмима и алијастима.
 * @author VM
 * @version 1.0
 */
public class SimpleSubjectAdapter implements SubjectAdapter, MongoAdapter<StandardSubject>{
	private CommonsEdgesController edgeController; 
	
	public SimpleSubjectAdapter(CommonsEdgesController edgeController) {
		if(edgeController==null) throw new NullPointerException();
		this.edgeController = edgeController; 
	}
	
	public CommonsEdgesController getEdgeController() {
		return edgeController;
	}

	@Override
	public MongoClient getDriver() {
		return edgeController.getSubjectAdapter().getDriver();
	}

	@Override
	public MongoDatabase getDatabase() {
		return edgeController.getSubjectAdapter().getDatabase();
	}

	@Override
	public MongoCollection<StandardSubject> getTable() {
		return edgeController.getSubjectAdapter().getTable();
	}

	@Override
	public List<String> list() {
		return edgeController.getSubjectAdapter().list();
	}

	@Override
	public List<StandardSubject> get() {
		return edgeController.getSubjectAdapter().get();
	}

	@Override
	public StandardSubject get(String id) {
		return edgeController.getSubjectAdapter().get(id);
	}

	@Override
	public void insert(StandardSubject subject) {
		edgeController.getSubjectAdapter().insert(subject);
	}

	@Override
	public void delete(String id) {
		edgeController.getSubjectAdapter().delete(id);
		for(StandardProgram program: edgeController.getProgramAdapter().get()) {
			if(program.getSubjects().contains(id))
				edgeController.getProgramAdapter().removeSubject(program.getId(), id); 
		}
	}

	@Override
	public void update(String oldId, StandardSubject neoSubject) {
		edgeController.getSubjectAdapter().update(oldId, neoSubject);
		if(!oldId.contentEquals(neoSubject.getSubjectId()))
		for(StandardProgram program: edgeController.getProgramAdapter().get()) {
			if(program.getSubjects().contains(oldId)) {
				edgeController.getProgramAdapter().removeSubject(program.getId(), oldId);
				edgeController.getProgramAdapter().addSubject(program.getId(), neoSubject.getSubjectId());
			}
		}
	}
}
