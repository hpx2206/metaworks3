package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class MenuTopPanel {
	
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}

	public MenuTopPanel(){
	}
	public MenuTopPanel(Session session){
		this.setSession(session);
	}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Popup showApps() throws Exception{
		Popup popup = new Popup();
		AllAppList allAppList = new AllAppList();
		allAppList.setSession(this.getSession());
		allAppList.load();
		popup.setPanel(allAppList);
		
		return popup;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Popup showChat() throws Exception{
		Popup popup = new Popup();
		
		return popup;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Popup showSelfService() throws Exception{
		Popup popup = new Popup();
		
		return popup;
	}
}
