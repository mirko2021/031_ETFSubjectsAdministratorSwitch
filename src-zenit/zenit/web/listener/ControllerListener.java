package zenit.web.listener;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import zenit.data.adapter.mongo.CommonsEdgesController;
import zenit.data.controller.SwitchAdapterController;

/**
 * Ослушкивач за формирање и укидање сесијских контролера. 
 * @author VM
 * @version 1.0
 */
@WebListener
public class ControllerListener implements HttpSessionListener {
    private static HashMap<HttpSession, CommonsEdgesController> edgesControllers = new HashMap<>();
	private static HashMap<HttpSession, SwitchAdapterController> adapterControllers = new HashMap<>();
    
	public synchronized static CommonsEdgesController getEdgeController(HttpSession session) {
		return edgesControllers.get(session);
	}
	
	public synchronized static SwitchAdapterController getAdapterController(HttpSession session) {
		return adapterControllers.get(session);
	}

	public ControllerListener() {
    }

    public void sessionCreated(HttpSessionEvent se)  { 
    	synchronized(ControllerListener.class) {
    		edgesControllers.put(se.getSession(), new CommonsEdgesController());
    		adapterControllers.put(se.getSession(), new SwitchAdapterController());
    		edgesControllers.get(se.getSession()).initializeSwitchController(adapterControllers.get(se.getSession()));
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	synchronized(ControllerListener.class) {
    		edgesControllers.remove(se.getSession()); 
    		adapterControllers.remove(se.getSession()); 
    	}
    }
}
