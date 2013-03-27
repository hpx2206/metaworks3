package org.uengine.codi.mw3.marketplace.model;

import org.uengine.codi.ITool;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppInformation;
import org.uengine.codi.mw3.marketplace.IApp;

public class SuccessForm implements ITool{
	
	String successMsg;
		public String getSuccessMsg() {
			return successMsg;
		}
		
		public void setSuccessMsg(String successMsg) {
			this.successMsg = successMsg;
		}
	
	IApp app;
		public IApp getApp() {
			return app;
		}
		public void setApp(IApp app) {
			this.app = app;
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
		uploadApp.setAppId(this.getApp().getAppId());
		uploadApp.databaseMe().setStatus(AppInformation.STATUS_APPROVED);
		
		
	}

}
