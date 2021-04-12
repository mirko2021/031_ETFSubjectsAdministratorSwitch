package zenit.agregator.adapter.general;


import java.util.HashMap;
import java.util.List;

import zenit.agregator.adapter.data.GlueNotedProgramSchema;
import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.data.adapter.general.ProgramAdapter;
import zenit.data.adapter.general.SubjectAdapter;
import zenit.data.impl.StandardSubject;

/**
 * Уопштени оквир за апстракције спојених предмта.
 * @author VM
 * @version 1.0
 */
public interface AbstractProgramSubjectsAdapter {
	public ProgramAdapter getProgramAdapter();
	public SubjectAdapter getSubjectAdapter();
	public List<StandardSubject> getForSemesters(String semester);
	public List<String> listForSemesters();
	public HashMap<String, List<StandardSubject>> mapForSemesters();
	public List<StandardSubject> getForSemester1();
	public List<StandardSubject> getForSemester2();
	public List<StandardSubject> getForSemester3();
	public List<StandardSubject> getForSemester4();
	public List<StandardSubject> getForSemester5();
	public List<StandardSubject> getForSemester6();
	public List<StandardSubject> getForSemester7();
	public List<StandardSubject> getForSemester8();
	public List<StandardSubject> getForSemester0();
	public GlueNotedProgramSchema semestralSchemaForProgram(String programId, ProgramGlueNotes glueNotes);
}
