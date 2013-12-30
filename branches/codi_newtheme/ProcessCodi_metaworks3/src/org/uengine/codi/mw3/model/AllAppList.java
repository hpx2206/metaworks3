package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.ide.CloudIDE;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IAppMapping;
import org.uengine.codi.mw3.marketplace.Marketplace;

public class AllAppList {

	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}

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
	
	@ServiceMethod(callByContent=true)
	public Object[] goIDE() throws Exception {
		CloudIDE cloudIDE = new CloudIDE();
		cloudIDE.setPageNavigator(new PageNavigator());
		cloudIDE.load(session);
			
		return new Object[]{new MainPanel(cloudIDE), new Remover(new Popup(), true)};
	}
	
	@Hidden
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public Object[] goAppStore() throws Exception {
		
		Marketplace marketplace = new Marketplace();
		marketplace.session = session;
		marketplace.setPageNavigator(new PageNavigator());
		marketplace.load();
		
		return new Object[]{new MainPanel(marketplace), new Remover(new Popup(), true)};
	}
}
