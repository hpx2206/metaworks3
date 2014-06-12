package org.uengine.codi.mw3.project.oce;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class ProjectGuideForm implements ITool {
	
	String projectId;
	@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	String projectName; 
	@Hidden
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
	String resultStr;
	@NonEditable
	@Face(displayName="진행여부.")
		public String getResultStr() {
			return resultStr;
		}
		public void setResultStr(String resultStr) {
			this.resultStr = resultStr;
		}
		
	public ProjectGuideForm(){
		
	}
	@Override
	public void onLoad() throws Exception {
//		System.out.println("ProjectGuideForm , projectId = " + projectId);
//		System.out.println("ProjectGuideForm , projectName = " + projectName);
		CloudInfo cloudInfo = new CloudInfo();
		ICloudInfo iCInfo = cloudInfo.findServerByServerName(projectId , cloudInfo.getServerName() , KtProjectServers.SERVER_DEV);
		if( iCInfo.next() ){
			setResultStr("생성완료 되었습니다. <br/> '완료' 버튼을 클릭하여 프로세스를 완료시켜주세요");
		}else{
			setResultStr("생성중 입니다. <br/> 생성이 완료 되면 '완료' 버튼을 클릭하여 프로세스를 완료시켜주세요.");
		}
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		
	}
}
