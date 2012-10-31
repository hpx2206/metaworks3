package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.knowledge.WfPanel;

public class PersonalPerspective extends Perspective {

	public PersonalPerspective() {
		setLabel("Personal");
	}

	@ServiceMethod
	public Object[] loadAllICanSee() throws Exception{
		Object[] returnObject = loadInstanceListPanel("allICanSee", null);
		if( session != null && "sns".equals(session.getTheme()) ){
			WfPanel wfPanel = new WfPanel();
			String comcode = session.getCompany().getComCode();
			wfPanel.session = session;
			wfPanel.load(comcode);
			Object[] returnObject2 = new Object[ returnObject.length + 1 ];
			for( int i = 0; i < returnObject.length; i++){
				returnObject2[i] = returnObject[i];
			}
			returnObject2[returnObject.length] = wfPanel;
			return returnObject2;
		}else{
			return returnObject;
		}
//		return loadInstanceListPanel("allICanSee", null);
	}
	
	@ServiceMethod
	public Object[] loadAll() throws Exception{
		return loadInstanceListPanel("all", null);
	}
	
	@ServiceMethod
	public Object[] loadInbox() throws Exception{
		return loadInstanceListPanel("inbox", null);
	}
	
	@ServiceMethod
	public Object[] loadRequest() throws Exception{
		return loadInstanceListPanel("request", null);
	}
	
	@ServiceMethod
	public Object[] loadStopped() throws Exception{
		return loadInstanceListPanel("stopped", null);
	}
	
	@ServiceMethod
	public InstanceListPanel calendar() throws Exception{
		InstanceListPanel instanceListPanel = new InstanceListPanel(session);
		instanceListPanel.session = session;
		instanceListPanel.switchToScheduleCalendar();
		instanceListPanel.setNewInstantiator(null);
		
		return instanceListPanel;
	}
}
