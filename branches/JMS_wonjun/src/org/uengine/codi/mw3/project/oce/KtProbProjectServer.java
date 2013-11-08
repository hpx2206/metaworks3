package org.uengine.codi.mw3.project.oce;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;

@Face(displayName="운영기",
ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathForArray="dwr/metaworks/org/uengine/codi/mw3/project/oce/KtProjectServer.ejs",
ejsPathMappingByContext = {
		"{how: 'inList', face: 'dwr/metaworks/genericfaces/GridFace_Row.ejs'}",
	},
options={"hideAddBtn" , "hideEditBtn", "showExtraBtn","removeBtnName" ,"callbackRemoveBtn", "popupWidth", "gridButtons"},
values={ "true", "true", "true" ,"$project.server.prod.remove","removeServerProb"  , "500", "serverAddProb,refreshProb"})
public class KtProbProjectServer  extends KtProjectServer{
	
	@AutowiredFromClient
	transient public KtProbProjectServers ktProbProjectServers;
	
	public KtProbProjectServer(){
	}
	
	@Face(displayName="$project.server.prod.add")
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object serverAddProb() throws Exception{
		String projectId = null;
		String serverGroup = null;
		if( ktProbProjectServers != null ){
			projectId = ktProbProjectServers.getProjectId();
			serverGroup = ktProbProjectServers.getServerGroup();
		}
		ModalWindow popup = new ModalWindow();
		NewServer newServer = new NewServer();
		newServer.setProjectId(projectId);
		newServer.setServerGroup(serverGroup);
		
		popup.setTitle("$project.server.prod.add");
		popup.setPanel(newServer);
		popup.setWidth(500);
		popup.setHeight(400);
		
		return popup;
	}
	
	@Face(displayName="$project.server.prod.remove")
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)	
	public KtProbProjectServer removeServerProb() throws Exception{
		if( ktProbProjectServers != null ){
			for(int i=0; i < ktProbProjectServers.getServerLists().length;  i++){
				KtProbProjectServer server = (KtProbProjectServer) ktProbProjectServers.getServerLists()[i];
				if( server.isChecked() ){
					// 삭제 요청한 노드
					// TODO 프로세스에 cancel 을 날려야함
					CloudInfo cloudInfo = new CloudInfo();
					ICloudInfo iCloudInfo = cloudInfo.findServerByServerName(server.getProjectId() , server.getName() , server.getServerGroup() );
					if( iCloudInfo.next() ){
						cloudInfo.copyFrom(iCloudInfo);
						if( cloudInfo.getServerIp() != null && !"".equals(cloudInfo.getServerIp())){
							KtProjectDeleteRequest ktProjectDeleteRequest = new KtProjectDeleteRequest();
							cloudInfo.databaseMe().setStatus(ProjectServer.SERVER_STATUS_STOPPING);
							ktProjectDeleteRequest.setCloudInfo(cloudInfo);
							ktProjectDeleteRequest.deleteRequest();
						}else{
							cloudInfo.deleteDatabaseMe();
						}
					}
				}
			}
			
		}
		return this;
	}
	
	@Hidden
	@Face(displayName="$project.server.refresh")
	@ServiceMethod(callByContent=true)
	public Object refreshProb() throws Exception{
		if( ktProbProjectServers != null ){
			ktProbProjectServers.loadOceServer();
			
			return ktProbProjectServers;
		}
		return this;
	}
}
