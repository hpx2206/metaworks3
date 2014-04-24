package org.uengine.oce.dashboard;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.OcePageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.ide.CloudIDE;
import org.uengine.codi.mw3.marketplace.Marketplace;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.selfservice.SelfService;

public class MyServicePanel{
	@AutowiredFromClient
	public Session session;
		
	public MyServicePanel(){
		
	}
	
	public MyServicePanel(Session session){
			
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goSns() throws Exception {

		if(session != null){
			session.setLastSelectedItem("goSns");
			session.setLastPerspecteType("allICanSee");
			session.setUx("sns");
		}
		
		return new MainPanel(new Main(session));
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
		
		// TODO: 이 클래스 없애야함 cjw
		/*
		CloudIDE cloudIDE = new CloudIDE();
		cloudIDE.setPageNavigator(new OcePageNavigator());
		cloudIDE.load(session);
		
		return new MainPanel(cloudIDE);
		*/
		
		return null;
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goMarketplace() throws Exception {
  
		// TODO: 이 클래스 없애야함 cjw
		/*
		Marketplace marketplace = new Marketplace();
		marketplace.session = session;
		marketplace.setPageNavigator(new OcePageNavigator());
		marketplace.load();
		
		return new MainPanel(marketplace);
		*/
		return null;
	}
	
	
}
