package zenit.data.controller;

import java.util.ArrayList;
import java.util.List;

import zenit.agregator.adapter.proxy.SwitchProgramGNAdapter;
import zenit.agregator.adapter.proxy.SwitchProgramSubjectsAdapter;
import zenit.data.adapter.proxy.SwitchProgramAdapter;
import zenit.data.adapter.proxy.SwitchSubjectAdapter;
import zenit.merges.adapter.proxy.SwitchSubjectMergeAdapter;
import zenit.merges.controller.SubjectMergesController;

/**
 * Контролер који се односи на промјењивост адаптера података према 
 * базама података. 
 * @author VM
 * @version 1.0
 */
public class SwitchAdapterController {
	private SwitchProgramAdapter programIOSwitch; 
	private SwitchSubjectAdapter subjectIOSwitch;
	private SwitchProgramGNAdapter programGNIOSwitch;
	private SwitchProgramSubjectsAdapter programSubjectsIOSwitch; 
	private SwitchSubjectMergeAdapter subjectAliasIOSwitch; 
	
	private SubjectMergesController subjectMergesController; 
	
	public SwitchAdapterController() {
		programIOSwitch = new SwitchProgramAdapter();
		subjectIOSwitch = new SwitchSubjectAdapter();
		programGNIOSwitch = new SwitchProgramGNAdapter();
		subjectAliasIOSwitch = new SwitchSubjectMergeAdapter();
		programSubjectsIOSwitch = new SwitchProgramSubjectsAdapter();
	}
	
	public SwitchProgramAdapter getProgramIOSwitch() {
		return programIOSwitch;
	}
	
	public SwitchSubjectAdapter getSubjectIOSwitch() {
		return subjectIOSwitch;
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

	public SubjectMergesController getSubjectMergesController() {
		return subjectMergesController;
	}

	public void setSubjectMergesController(SubjectMergesController subjectMergesController) {
		this.subjectMergesController = subjectMergesController;
	}
	
	public List<String> list(){	
		ArrayList<String> result = new ArrayList<>();
		List<String> subjectAdapters            = subjectIOSwitch.listAdapters();
        List<String> programAdapters            = programIOSwitch.listAdapters();
        List<String> programGNIOAdapters        = programGNIOSwitch.listAdapters();
        List<String> programSubjectsIOAdapters  = programSubjectsIOSwitch.listAdapters();
        List<String> switchSubjectMergeAdapters = subjectAliasIOSwitch.listAdapters(); 
        for(String adapter: subjectAdapters) {
        	if(!programAdapters.contains(adapter))            continue;
        	if(!programSubjectsIOAdapters.contains(adapter))  continue;
        	if(!programGNIOAdapters.contains(adapter))        continue;
        	if(!switchSubjectMergeAdapters.contains(adapter)) continue;
        	result.add(adapter); 
        }
        return result;
	}
	
	public boolean update(String schema) {
		if(!list().contains(schema)) return false; 
		subjectIOSwitch.selectAdapter(schema); 
		programIOSwitch.selectAdapter(schema);
		programGNIOSwitch.selectAdapter(schema);
		programSubjectsIOSwitch.selectAdapter(schema);
		subjectAliasIOSwitch.selectAdapter(schema);
		return true;
	}
	
	public String current() {
		String subjectAdapters            = subjectIOSwitch.getCurrentAdapterName(); 
        String programAdapters            = programIOSwitch.getCurrentAdapterName();
        String programGNIOAdapters        = programGNIOSwitch.getCurrentAdapterName();
        String programSubjectsIOAdapters  = programSubjectsIOSwitch.getCurrentAdapterName();
        String switchSubjectMergeAdapters = subjectAliasIOSwitch.getCurrentAdapterName();
        
        if(!subjectAdapters.contains(subjectAdapters)) return "";
        if(!subjectAdapters.contains(programAdapters)) return ""; 
        if(!subjectAdapters.contains(programGNIOAdapters)) return ""; 
        if(!subjectAdapters.contains(programSubjectsIOAdapters)) return ""; 
        if(!subjectAdapters.contains(switchSubjectMergeAdapters)) return ""; 
        
        return subjectAdapters; 
	}
}
