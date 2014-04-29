package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudIDE;
import org.uengine.codi.mw3.ide.DefaultProject;

public class TopMenuPanel {
	
	@AutowiredFromClient
	public Session session;

	public TopMenuPanel(){
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
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] showSelfService() throws Exception{
		CloudIDE cloudIDE = new CloudIDE(session, new DefaultProject());
		
		return new Object[]{new Refresh(cloudIDE), new Refresh(cloudIDE.loadTopCenterPanel(session))};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow popupFeedback(){
		return new ModalWindow(new ContactUs(),800,570,"피드백");
	}

}
