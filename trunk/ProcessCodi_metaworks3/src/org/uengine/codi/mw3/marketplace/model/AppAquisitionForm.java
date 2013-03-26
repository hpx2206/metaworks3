package org.uengine.codi.mw3.marketplace.model;


import org.metaworks.annotation.Face;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.marketplace.AppInformation;
import org.uengine.codi.mw3.marketplace.IApp;
import org.uengine.codi.mw3.model.IUser;

public class AppAquisitionForm implements ITool{

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
	
	IUser writer;
		public IUser getWriter() {
			return writer;
		}
		public void setWriter(IUser writer) {
			this.writer = writer;
		}
	
	AppInformation appInfo;	
		public AppInformation getAppInfo() {
			return appInfo;
		}
		public void setAppInfo(AppInformation appInfo) {
			this.appInfo = appInfo;
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
		// TODO Auto-generated method stub
		
	}

}
