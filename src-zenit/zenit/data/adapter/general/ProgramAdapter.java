package zenit.data.adapter.general;

import java.util.List;
import zenit.data.impl.StandardProgram;

/**
 * Адаптер према бази података која се односи на студијске програме. Апстракција. 
 * @author VM
 * @version 1.0
 */
public interface ProgramAdapter {
	public List<String> list();
	public List<StandardProgram> get();
	public StandardProgram get(String id);
	public void insert(StandardProgram program);
	public void delete(String id);
	public void updateName(String id, String name);
	public boolean updateProgramId(String id, String name);
	public void updateProgram(String id, String program);
	public void updateCourse(String id, String course);
	public boolean testSubject(String programId, String subjectId);
	public boolean addSubject(String programId, String subjectId);
	public boolean removeSubject(String programId, String subjectId);
}
