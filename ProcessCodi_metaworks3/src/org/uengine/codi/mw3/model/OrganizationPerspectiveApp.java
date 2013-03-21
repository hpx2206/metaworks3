package org.uengine.codi.mw3.model;

import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IApp;
import org.uengine.codi.mw3.marketplace.IAppMapping;

public class OrganizationPerspectiveApp extends Perspective{
	
	public OrganizationPerspectiveApp(){
		setLabel("App");
		
	}
	
	
	IAppMapping appList;
		public IAppMapping getAppList() {
			return appList;
		}
	
		public void setAppList(IAppMapping appList) {
			this.appList = appList;
		}
		

	protected void loadChildren() throws Exception {
		
		AppMapping appMp = new AppMapping();
		appMp.setComCode(session.getCompany().getComCode());
		appMp.setIsDeleted(false);
		
		IAppMapping addedApps =appMp.findMyApps();
		addedApps.getMetaworksContext().setWhen("filter");
		
		setAppList(addedApps);
		
//		IApp publishedApps = App.findPublishedApps(session);
//		publishedApps.getMetaworksContext().setWhen("filter");
//		
//		setAppList(publishedApps);
		
	}

	protected void unloadChildren() throws Exception {
		setAppList(null);
	}
	
}
