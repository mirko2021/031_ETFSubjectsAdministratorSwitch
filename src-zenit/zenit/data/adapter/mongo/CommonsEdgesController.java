package zenit.data.adapter.mongo;

import zenit.agregator.adapter.mongo.BasicProgramGNAdapter;
import zenit.agregator.adapter.mongo.ProgramSubjectsAdapter;
import zenit.agregator.adapter.proxy.SwitchProgramGNAdapter;
import zenit.agregator.adapter.proxy.SwitchProgramSubjectsAdapter;
import zenit.data.controller.SwitchAdapterController;
import zenit.merges.adapter.mongo.SubjectMergeAdapter;
import zenit.merges.adapter.proxy.SwitchSubjectMergeAdapter;
import zenit.merges.controller.SubjectMergesController;

/**
 * Заједничке функционалности, када су у питању реактивности и функционалности
 * за адаптере предмета и студијских програма, као и предмета у алијасу. 
 * @author VM
 * @version 1.0
 */
public class CommonsEdgesController {
	private BasicProgramAdapter programAdapter = new BasicProgramAdapter();
	private BasicSubjectAdapter subjectAdapter = new BasicSubjectAdapter();
	private ProgramSubjectsAdapter programSubjectsAdapter = new ProgramSubjectsAdapter(programAdapter, subjectAdapter);
	private BasicProgramGNAdapter basicProgramGNAAdapter = new BasicProgramGNAdapter(programAdapter);
	private SubjectMergesController subjectMergesController = new SubjectMergesController(new SubjectMergeAdapter(subjectAdapter)); 
	
	private SwitchProgramGNAdapter programGNIOSwitch;
	private SwitchProgramSubjectsAdapter programSubjectsIOSwitch; 
	private SwitchSubjectMergeAdapter subjectAliasIOSwitch; 
	private SwitchAdapterController switchIOController; 
	
	public void initializeSwitchController(SwitchAdapterController switchIOController) {
	    this.switchIOController = switchIOController; 
	    this.programGNIOSwitch = switchIOController.getProgramGNIOSwitch();
	    this.programSubjectsIOSwitch = switchIOController.getProgramSubjectsIOSwitch();
	    this.subjectAliasIOSwitch = switchIOController.getSubjectAliasIOSwitch();
	    
	    this.programGNIOSwitch.register("MONGO_LOCAL_001", basicProgramGNAAdapter);
	    this.programSubjectsIOSwitch.register("MONGO_LOCAL_001", programSubjectsAdapter); 
	    this.subjectAliasIOSwitch.register("MONGO_LOCAL_001", subjectMergesController.getAdapter());
	    
	    this.programGNIOSwitch.selectAdapter("MONGO_LOCAL_001");
	    this.programSubjectsIOSwitch.selectAdapter("MONGO_LOCAL_001");
	    this.subjectAliasIOSwitch.selectAdapter("MONGO_LOCAL_001");
	    
	    switchIOController.setSubjectMergesController(this.subjectMergesController); 
	}
	
	public SwitchAdapterController getSwitchIOController() {
		return switchIOController;
	}
	public BasicProgramAdapter getProgramAdapter() {
		return programAdapter;
	}
	public BasicSubjectAdapter getSubjectAdapter() {
		return subjectAdapter;
	}
	public ProgramSubjectsAdapter getProgramSubjectsAdapter() {
		return programSubjectsAdapter;
	}
	public BasicProgramGNAdapter getBasicProgramGNAAdapter() {
		return basicProgramGNAAdapter;
	}
	public SubjectMergesController getSubjectMergesController() {
		return subjectMergesController;
	}

	public SwitchProgramGNAdapter getProgramGNIOSwitch() {
		return programGNIOSwitch;
	}

	public SwitchProgramSubjectsAdapter getProgramSubjectsIOSwitch() {
		return programSubjectsIOSwitch;
	}

	public SwitchSubjectMergeAdapter getSubjectAliasIOSwitch() {
		return subjectAliasIOSwitch;
	}
}
