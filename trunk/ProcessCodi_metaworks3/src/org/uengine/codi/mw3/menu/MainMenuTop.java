package org.uengine.codi.mw3.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.menu.MainMenu;

public class MainMenuTop extends MainMenu {
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="&File")
	public SubMenuFile file() throws Exception {
		return new SubMenuFile();
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="&Edit")
	public SubMenuEdit edit() throws Exception {
		return new SubMenuEdit();
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="&Source")
	public SubMenuSource source() throws Exception {
		return new SubMenuSource();
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Refac&tor")
	public SubMenuRefactor refactor() throws Exception {
		return new SubMenuRefactor();
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="&Navigate")
	public SubMenuNavigate navigate() throws Exception {
		return new SubMenuNavigate();
	}	

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Se&arch")
	public SubMenuSearch search() throws Exception {
		return new SubMenuSearch();
	}	

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="&Project")
	public SubMenuProject project() throws Exception {
		return new SubMenuProject();
	}	

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="&Run")
	public SubMenuRun run() throws Exception {
		return new SubMenuRun();
	}	
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="&Window")
	public SubMenuWindow window() throws Exception {
		return new SubMenuWindow();
	}	

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="&Help")
	public SubMenuHelp help() throws Exception {
		return new SubMenuHelp();
	}	
	
}
