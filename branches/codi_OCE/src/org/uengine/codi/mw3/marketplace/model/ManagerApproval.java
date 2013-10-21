package org.uengine.codi.mw3.marketplace.model;

import java.util.Date;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Range;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.project.oce.KtProjectCreateRequest;
import org.uengine.codi.mw3.project.oce.KtProjectServers;

@Face(
	ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
	options={"fieldOrder"},
	values={"approval"}
)
public class ManagerApproval implements ITool  {
	
	public ManagerApproval() { 
	}
	
	String approval;
		@Face(displayName="승인여부")
		@Range(options={"$Approval", "$Reject"}, values={"approval", "reject"})
		public String getApproval() {
			return approval;
		}
		public void setApproval(String approval) {
			this.approval = approval;
		}
		
	int appId;
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}

	String appName;
		public String getAppName() {
			return appName;
		}
	
		public void setAppName(String appName) {
			this.appName = appName;
		}
		
	String comcode;
		public String getComcode() {
			return comcode;
		}
		public void setComcode(String comcode) {
			this.comcode = comcode;
		}
		
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeComplete() throws Exception {
//		
//		AppMapping appMapping = new AppMapping();
//		
//		appMapping.setAppId(this.getAppId());
//		appMapping.setAppName(this.getAppName());
//		appMapping.setComCode(this.getComcode());
//		appMapping.setIsDeleted(false);
//		
//		appMapping.createDatabaseMe();
//		
		
		App app = new App();
		
		app.setAppId(this.getAppId());
		app.databaseMe().setStatus(App.STATUS_PUBLISHED);
	}

	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
		App app = new App();
		
		app.setAppId(this.getAppId());
		app.copyFrom(app.databaseMe());
		
		System.out.println("projectId = " + app.getProject().getId());
		System.out.println("projectId = " + app.getAppName());
		final String appName = app.getAppName();
		final String projectId = app.getProject().getId();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					KtProjectCreateRequest ktProjectCreateRequest = new KtProjectCreateRequest();
					CloudInfo cloudInfo = ktProjectCreateRequest.createRequset(appName , null);
					insertCloudInfo(projectId , cloudInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
	}
	
	public void insertCloudInfo(String projectId, CloudInfo cloudInfo) throws Exception{
		
		cloudInfo.setProjectId(projectId);
		cloudInfo.setServerInfo("KT Cloud");
		cloudInfo.setServerGroup(KtProjectServers.SERVER_PROB);
		cloudInfo.setModdate(new Date());
		
		ICloudInfo iCInfo = cloudInfo.findServerByServerName(projectId , cloudInfo.getServerName() , KtProjectServers.SERVER_PROB);
		if( iCInfo.next() ){
			cloudInfo.setId(iCInfo.getId());
			cloudInfo.syncToDatabaseMe();
		}else{
			cloudInfo.setId(cloudInfo.createNewId());
			cloudInfo.createDatabaseMe();
		}
		
	}
}
