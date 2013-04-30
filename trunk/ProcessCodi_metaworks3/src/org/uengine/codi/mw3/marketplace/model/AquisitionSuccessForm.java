package org.uengine.codi.mw3.marketplace.model;

import org.uengine.codi.ITool;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IApp;

public class AquisitionSuccessForm implements ITool{
	
	IApp app;
		public IApp getApp() {
			return app;
		}
		public void setApp(IApp app) {
			this.app = app;
		}

	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

		
	@Override
	public void onLoad() throws Exception {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		
		App uploadApp = new App();
		uploadApp.setAppId(app.getAppId());
		uploadApp.databaseMe().setInstallCnt(app.getInstallCnt() + 1);
		uploadApp.databaseMe().setUrl(this.getUrl());
		
		AppMapping addApp = new AppMapping();
		
		addApp.setAppId(app.getAppId());
		addApp.setAppName(app.getAppName());
		addApp.setComCode(app.getVendorId());
		addApp.setIsDeleted(false);
		
		if(addApp.findMe().size() == 0 )
			addApp.createDatabaseMe();
		
	}

}
