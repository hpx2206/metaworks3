package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.CloudIDE;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IAppMapping;
import org.uengine.codi.mw3.marketplace.Marketplace;

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
			
			app.setMetaworksContext(new MetaworksContext());
			app.getMetaworksContext().setWhere(OceMain.WHERE_DASHBOARD);

			myAppsList.add(app);
			
		}
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] goSNS() throws Exception {
		SNS sns = new SNS(session);
		
		return new Object[]{new Refresh(sns), new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] goIDE() throws Exception {
		
		CloudIDE cloudIDE = new CloudIDE(session);
		
		return new Object[]{new Refresh(cloudIDE), new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] goAppStore() throws Exception {
		
		Marketplace marketplace = new Marketplace();
		marketplace.session = session;
		marketplace.load();
		
		return new Object[]{new Refresh(marketplace), new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
}
