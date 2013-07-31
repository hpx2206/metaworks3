package org.uengine.codi.mw3.model;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.knowledge.WfPanel;

public class PersonalPerspective extends Perspective {

	public PersonalPerspective() {
		setLabel("Personal");
	}

	@ServiceMethod
	public Object[] loadAllICanSee() throws Exception{
		Object[] returnObject = loadInstanceListPanel("allICanSee", null);
		
		/*
		if("sns".equals(session.getEmployee().getPreferUX()) ){
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
		*/
		
		return returnObject;
		
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
	
	//기존 calendar
	/*@ServiceMethod
	public InstanceListPanel calendar() throws Exception{
		InstanceListPanel instanceListPanel = new InstanceListPanel(session);
		instanceListPanel.session = session;
		instanceListPanel.switchToScheduleCalendar();
		// TODO: 2013-01-10
		//instanceListPanel.setNewInstantiator(null);
		
		return instanceListPanel;
	}*/
	
	@ServiceMethod
	public Object[] calendar() throws Exception{
		if("sns".equals(session.getEmployee().getPreferUX()) ){
			//js 호출부분
			Browser.withSession(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Runnable(){
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("if(mw3.getAutowiredObject('org.uengine.codi.mw3.model.MuiltiViewTab')!=null) mw3.getAutowiredObject('org.uengine.codi.mw3.model.MuiltiViewTab').__getFaceHelper().selectTab", new Object[]{"2" });
				}
				
			});
			
			return null;
		}else{
			InstanceListPanel instanceListPanel = new InstanceListPanel(session);
			instanceListPanel.session = session;
			instanceListPanel.switchToScheduleCalendar();
//			instanceListPanel.setNewInstantiator(null);
			
			return new Object[]{instanceListPanel};
		}
	}
}
