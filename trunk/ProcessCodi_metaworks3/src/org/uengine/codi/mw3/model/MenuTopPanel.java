package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class MenuTopPanel {
	
	@AutowiredFromClient
	public Session session;

	public MenuTopPanel(){
	}
		
	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	public Popup showApps() throws Exception{
		
		AllAppList allAppList = new AllAppList();
		allAppList.session = session;
		allAppList.load();
		
		Popup popup = new Popup();
		popup.setPanel(allAppList);
		
		return popup;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	public Popup showChat() throws Exception{
		Popup popup = new Popup();
		
		return popup;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	public Popup showSelfService() throws Exception{
		Popup popup = new Popup();
		
		return popup;
	}
}
