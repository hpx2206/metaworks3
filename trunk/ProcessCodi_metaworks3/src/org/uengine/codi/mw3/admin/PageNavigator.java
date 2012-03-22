package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.knowledge.Knowledge;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.Session;

public class PageNavigator {

	@AutowiredFromClient
	public Session session;
	
	public PageNavigator() {
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goIDE() throws Exception {		
		return new MainPanel(new IDE(session.getUser()));
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goProcess() throws Exception {
		return new MainPanel(new Main(session.getUser()));
	}

	@ServiceMethod(callByContent=true)
	public MainPanel goKnowledge() throws Exception {
		return new MainPanel(new Knowledge(session.getUser()));
	}
}
