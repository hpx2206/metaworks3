package org.uengine.codi.mw3.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.menu.MainMenu;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmarket.Market;

public class MainMenuTop extends MainMenu {
	
	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	@Face(displayName="&File")
	public SubMenuFile file() throws Exception {
		return new SubMenuFile();
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	@Face(displayName="&Source")
	public SubMenuSource source() throws Exception {
		return new SubMenuSource();
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	@Face(displayName="&Run")
	public SubMenuRun run() throws Exception {
		return new SubMenuRun();
	}	
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	@Face(displayName="&Project")
	public SubMenuProject project() throws Exception {
		return new SubMenuProject();
	}	

	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	@Face(displayName="&Help")
	public SubMenuHelp help() throws Exception {
		return new SubMenuHelp();
	}	
	

	@ServiceMethod
	@Face(displayName="&Market")
	public MainPanel market() throws Exception {
		Market market = new Market();
		market.session = session;
		market.load();
		
		return new MainPanel(market);
		
	}	
	
}
