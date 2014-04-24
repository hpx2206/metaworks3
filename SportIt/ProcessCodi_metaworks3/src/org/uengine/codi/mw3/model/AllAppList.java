package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.view.IFrameApplication;
import org.uengine.codi.mw3.knowledge.ProjectPanel;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IAppMapping;
import org.uengine.codi.mw3.marketplace.Marketplace;
import org.uengine.codi.mw3.processexplorer.ProcessExplorer;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

public class AllAppList {

	@AutowiredFromClient
	public Session session;

	ArrayList<AppMapping> myAppsList;
		public ArrayList<AppMapping> getMyAppsList() {
			return myAppsList;
		}
		public void setMyAppsList(ArrayList<AppMapping> myAppsList) {
			this.myAppsList = myAppsList;
		}
		
	public AllAppList(){
		myAppsList = new ArrayList<AppMapping>();
	}
	public void load() throws Exception{
		AppMapping myapps = new AppMapping();
		
		myapps.setComCode(session.getCompany().getComCode());
		myapps.setIsDeleted(false);
		myapps.session = session;
		
		//전체 리스트 나오게
		IAppMapping getAppsList = myapps.findMyApps(0);
		
		while(getAppsList.next()){
			
			AppMapping app = new AppMapping();
			
			app.setAppId(getAppsList.getAppId());
			app.setAppName(getAppsList.getAppName());
			app.setComCode(getAppsList.getComCode());
			app.setIsDeleted(getAppsList.getIsDeleted());
			app.setLogoFile(getAppsList.getLogoFile());
			app.setUrl(getAppsList.getUrl());
			app.setAppType(getAppsList.getAppType());
			
			app.setMetaworksContext(new MetaworksContext());
			app.getMetaworksContext().setWhere(OceMain.WHERE_DASHBOARD);

			myAppsList.add(app);
			
		}
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] goSNS() throws Exception {
		SNS sns = new SNS(session);
		
		return new Object[]{new Refresh(sns), new Refresh(sns.loadTopCenterPanel(session)), new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] goIDE() throws Exception {
		
		//CloudIDE cloudIDE = new CloudIDE(session, new DefaultProject());
		ProjectPanel projectPanel = new ProjectPanel();
		projectPanel.session = session;
		projectPanel.setMetaworksContext(new MetaworksContext());
		projectPanel.getMetaworksContext().setHow("selectproject");
		projectPanel.load();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setPanel(projectPanel);
		modalWindow.setTitle("프로젝트 선택");
		modalWindow.setHeight(300);
		
		return new Object[]{modalWindow, new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] goAppStore() throws Exception {
		
		Marketplace marketplace = new Marketplace(session);
		
		return new Object[]{new Refresh(marketplace), new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] goTadPole() throws Exception {
		
		IFrame iframe = new IFrame("http://" + GlobalContext.getPropertyString("tadpole.url"));
		
		IFrameApplication application = new IFrameApplication();
		application.setContent(iframe);
		
		return new Object[]{new Refresh(application), new Refresh(application.loadTopCenterPanel(session)), new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] goJMS() throws Exception{
		ProcessExplorer processExplorer = new ProcessExplorer();
		processExplorer.load(session);
		
		return new Object[]{new Refresh(processExplorer), new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
}
