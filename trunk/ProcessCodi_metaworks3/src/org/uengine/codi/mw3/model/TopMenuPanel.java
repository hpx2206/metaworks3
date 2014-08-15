package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudIDE;
import org.uengine.codi.mw3.ide.DefaultProject;
import org.uengine.codi.mw3.selfservice.SelfService;

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
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object[] showSelfService() throws Exception{
		
		if(/* codi 내부인지 */ false){
			CloudIDE cloudIDE = new CloudIDE(session, new DefaultProject());
			
			return new Object[]{new Refresh(cloudIDE), new Refresh(cloudIDE.loadTopCenterPanel(session))};
			
		}else{
			SelfService selfService = new SelfService();
			selfService.session = session;
			selfService.load();
			
//			ModalWindow modal = new ModalWindow(selfService);
//			modal.setWidth(1000);
//			modal.setHeight(700);
//			modal.setTitle("셀프서비스 포탈");
			
			Popup modal = new Popup(1000, 700);
			modal.setPanel(selfService);
			
			return new Object[]{modal};			
		}
		
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow popupFeedback(){
		return new ModalWindow(new ContactUs(),800,570,"피드백");
	}

}
