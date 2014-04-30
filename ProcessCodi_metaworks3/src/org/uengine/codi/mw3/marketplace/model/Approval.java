package org.uengine.codi.mw3.marketplace.model;
import java.io.File;
import java.io.InputStream;

import org.uengine.codi.ITool; 
import org.uengine.codi.mw3.ide.AmazonService;
import org.uengine.codi.mw3.ide.DockerService;
import org.uengine.codi.mw3.ide.IStorageService;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.kernel.GlobalContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Order;

@Face(displayName="approval", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class Approval implements ITool{ 

	public Approval() {
	}

	String name;
		@Order(value=1)
		@Face(displayName="앱 이름")
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name = name;
		}
	
	String category;
		@Order(value=2)
		@Face(displayName="종류")
		public String getCategory(){
			return category;
		}
		public void setCategory(String category){
			this.category = category;
		}
	
	String approved;
		@Order(value=3)
		@Face(displayName="승인여부", ejsPath="dwr/metaworks/genericfaces/RadioButton.ejs", options={"승인", "미승인"}, values={"yes", "no"})
		public String getApproved(){
			return approved;
		}
		public void setApproved(String approved){
			this.approved = approved;
		}
	
	String opinion;
		@Order(value=4)
		@Face(displayName="의견", ejsPath="dwr/metaworks/genericfaces/richText.ejs", options={"cols", "rows"}, values={"30", "5"})
		public String getOpinion(){
			return opinion;
		}
		public void setOpinion(String opinion){
			this.opinion = opinion;
		}
		
	int appId;
		@Hidden
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}
	@Override
	public void onLoad() throws Exception {
	}

	@Override
	public void beforeComplete() throws Exception {
	}

	@Override
	public void afterComplete() throws Exception {
		String projectId = null;
		String projectName = null;
		if( "yes".equals(approved)){
			App uploadApp = new App();
			
			uploadApp.setAppId(this.getAppId());
			uploadApp.databaseMe().setStatus(App.STATUS_APPROVED);
			projectId = uploadApp.databaseMe().getProjectId();
		}
		WfNode wfNode = new WfNode();
		wfNode.setId(projectId);
		projectName = wfNode.databaseMe().getName();
		
		String reopsitoryService = GlobalContext.getPropertyString("file.repository.service");
		
		IStorageService storageService=null;
		
		if("amazon".equals(reopsitoryService)){
			storageService = new AmazonService();
		}else if("docker".equals(reopsitoryService)){
			storageService = new DockerService();
		}
		storageService.putObject(projectId, projectName,true);
	}

}