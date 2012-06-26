package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.knowledge.Knowledge;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.PinterestMain;
import org.uengine.codi.mw3.model.Session;

public class PageNavigator {

	@AutowiredFromClient
	public Session session;
	
	public PageNavigator() {		
	}
	
	public PageNavigator(String pageName) {
		setPageName(pageName);
	}
	
	String pageName;
		@Hidden
		public String getPageName() {
			return pageName;
		}	
		public void setPageName(String pageName) {
			this.pageName = pageName;
		}

	@ServiceMethod(callByContent=true)
	public MainPanel goIDE() throws Exception {		
		return new MainPanel(new IDE(session));
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goProcess() throws Exception {
		return new MainPanel(new Main(session));
	}

	@ServiceMethod(callByContent=true)
	public MainPanel goPinterest() throws Exception {
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, north__size:52");
		outerLayout.setCenter(new PinterestMain(session));
		
		return new MainPanel(outerLayout);
	}

	@ServiceMethod(callByContent=true)
	@Test(scenario="first", starter=true, instruction="<br><br><br><br><br><br>R&D 지식모드 (Knowledge Mode) 로 넘어갑니다..", next="autowiredObject.org.uengine.codi.mw3.knowledge.WfNode.add()")	
	public MainPanel goKnowledge() throws Exception {
		return new MainPanel(new Knowledge(session));
	}
	
	
}
