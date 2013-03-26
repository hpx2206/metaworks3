package org.uengine.codi.mw3.marketplace.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.marketplace.IApp;
import org.uengine.codi.mw3.marketplace.IAppMapping;
import org.uengine.codi.mw3.model.Session;

public class AquisitionApprovalForm implements ITool{

	boolean approval;
		public boolean isApproval() {
			return approval;
		}
		public void setApproval(boolean approval) {
			this.approval = approval;
		}

	String rejectReason;
		@Face(ejsPath="dwr/metaworks/genericfaces/richText.ejs", options={"rows","cols"}, values={"20","80"})
		public String getRejectReason() {
			return rejectReason;
		}
		public void setRejectReason(String rejectReason) {
			this.rejectReason = rejectReason;
		}
		
	IApp app;
		public IApp getApp() {
			return app;
		}
		public void setApp(IApp app) {
			this.app = app;
		}

		
	@AutowiredFromClient
	public Session session;
	
	
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
		// TODO Auto-generated method stub
		
	}

}
