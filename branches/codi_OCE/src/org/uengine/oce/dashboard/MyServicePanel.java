package org.uengine.oce.dashboard;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.OcePageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.ide.CloudIDE;
import org.uengine.codi.mw3.marketplace.Marketplace;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.selfservice.SelfService;

public class MyServicePanel {
	@AutowiredFromClient
	public Session session;
	    @Hidden
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
	public MyServicePanel(){
		
	}
	
	public MyServicePanel(Session session){
			
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goSelfServicePortal() throws Exception {
		
		
		SelfService selfService = new SelfService();
		selfService.session = session;
		selfService.setPageNavigator(new OcePageNavigator());
		selfService.load();
		
		return new MainPanel(selfService);
		
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goIDE() throws Exception {
		CloudIDE cloudIDE = new CloudIDE();
		cloudIDE.setPageNavigator(new OcePageNavigator());
		cloudIDE.load(session);
		
		return new MainPanel(cloudIDE);
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goMarketplace() throws Exception {
		
		Marketplace marketplace = new Marketplace();
		marketplace.session = session;
		marketplace.setPageNavigator(new OcePageNavigator());
		marketplace.load();
		
		return new MainPanel(marketplace);
	}
}
