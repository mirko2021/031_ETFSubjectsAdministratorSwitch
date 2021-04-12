package zenit.agregator.adapter.general;

import java.util.List;

import zenit.agregator.web.gluenotes.ProgramGlueNotes;
import zenit.data.adapter.general.ProgramAdapter;

/**
 * Уопштени адаптер за слијепљене биљешке при предметима. 
 * @author VM
 * @version 1.0
 */
public interface AbstractProgramGNAdapter {
	public ProgramAdapter getProgramAdapter();
	public void put(ProgramGlueNotes glueNotes);
	public void insert(ProgramGlueNotes glueNotes);
	public void remove(ProgramGlueNotes glueNotes);
	public void update(String programName, ProgramGlueNotes glueNotes);
	public void remove(String glueNotes);
	public ProgramGlueNotes get(String glueNotes);
	public List<ProgramGlueNotes> get();
}
