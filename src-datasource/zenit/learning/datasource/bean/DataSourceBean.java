package zenit.learning.datasource.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import zenit.data.controller.SwitchAdapterController;
import zenit.web.listener.ControllerListener;

/**
 * Зрно за баратање изворима података. 
 * @author VM
 * @version 1.0
 */
public class DataSourceBean implements Serializable{
	private static final long serialVersionUID = -3437689447884920352L;
	private SwitchAdapterController switchAdapterController;
	
	public DataSourceBean() {}
	
	public void initialize(HttpSession session) {
		switchAdapterController = ControllerListener.getAdapterController(session);
	}
	
	public SwitchAdapterController getSwitchAdapterController() {
		return switchAdapterController;
	}
	
	public List<String> list(){
		try {
			List<String> list = switchAdapterController.list();
			return list;
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public boolean set(String dataSource) {
		if(!list().contains(dataSource)) return false;
		switchAdapterController.update(dataSource); 
		return true;
	}
	
	public String get() {
		return switchAdapterController.current(); 
	}
}
