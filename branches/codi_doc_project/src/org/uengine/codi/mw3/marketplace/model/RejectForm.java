package org.uengine.codi.mw3.marketplace.model;

import org.uengine.codi.ITool;
import org.uengine.codi.mw3.marketplace.App;

public class RejectForm implements ITool {

	int appId;
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}
		

	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeComplete() throws Exception {
		
		// TODO Auto-generated method stub
		App uploadApp = new App();
		
		uploadApp.setAppId(this.getAppId());
		uploadApp.databaseMe().setStatus(App.STATUS_REJECTED);
		
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
