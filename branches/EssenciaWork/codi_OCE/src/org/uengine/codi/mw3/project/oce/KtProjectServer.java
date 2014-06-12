package org.uengine.codi.mw3.project.oce;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.FilepathInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;
import org.uengine.codi.mw3.knowledge.ReleasePanel;
import org.uengine.codi.mw3.model.Session;

@Face(displayName="개발기",
ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathForArray="dwr/metaworks/org/uengine/codi/mw3/project/oce/KtProjectServer.ejs",
ejsPathMappingByContext = {
		"{how: 'inList', face: 'dwr/metaworks/genericfaces/GridFace_Row.ejs'}",
	},
options={"hideAddBtn" , "hideEditBtn", "showExtraBtn","removeBtnName" ,"callbackRemoveBtn", "popupWidth", "gridButtons"},
values={ "true", "true", "true" ,"$project.server.remove","removeServer"  , "500", "serverAdd,refresh"})
public class KtProjectServer  implements ITool, ContextAware{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	@AutowiredFromClient
	transient public Session session;
	
	@AutowiredFromClient
	transient public KtProjectServers ktProjectServers;
	
	@AutowiredFromClient
	transient public ProjectInfo projectInfo;
	
	public KtProjectServer(){
	}
	
	String id;
	@Id
	@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	String name;
	@Name
	@Face(displayName="$project.server.name")		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String projectId;
	@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	String ip;
		@Available(how=MetaworksContext.HOW_IN_LIST)
		@Face(displayName="$project.server.ip")
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
	String serviceTemplete;
	@Face(displayName="$project.server.serviceTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"KT_KOR-Central A"}, values={"KT_KOR-Central A"})
		public String getServiceTemplete() {
			return serviceTemplete;
		}
		public void setServiceTemplete(String serviceTemplete) {
			this.serviceTemplete = serviceTemplete;
		}
		
	String osTemplete;
	@Face(displayName="$project.server.osTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", 
			options={"Centos 6.3 64bit_jboss",
						"Centos 6.3 64bit_cubrid",
						"Centos 6.3 64bit_jboss_cubird",
						"Ubuntu 12.04 64bit_jboss",
						"Ubuntu 12.04 64bit_cubrid",
						"Ubuntu 12.04 64bit_jboss_cubird",
						"WIN 2008 R2 64bit [Korean]_jboss",
						"WIN 2008 R2 64bit [Korean]-cubrid",
						"WIN 2008 R2 64bit [Korean]_jboss_cubird"}, 
			values={"Centos 6.3 64bit_jboss",
						"Centos 6.3 64bit_cubrid",
						"Centos 6.3 64bit_jboss_cubird",
						"Ubuntu 12.04 64bit_jboss",
						"Ubuntu 12.04 64bit_cubrid",
						"Ubuntu 12.04 64bit_jboss_cubird",
						"WIN 2008 R2 64bit [Korean]_jboss",
						"WIN 2008 R2 64bit [Korean]-cubrid",
						"WIN 2008 R2 64bit [Korean]_jboss_cubird"})
		public String getOsTemplete() {
			return osTemplete;
		}
		public void setOsTemplete(String osTemplete) {
			this.osTemplete = osTemplete;
		}

	String hwTemplete;
	@Face(displayName="$project.server.hwTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", 
			options={"1 vCore	1 GB	100GB",
						"1 vCore	2 GB	100GB",
						"2 vCore	2 GB	100GB",
						"2 vCore	4 GB	100GB",
						"4 vCore	4 GB	100GB",
						"4 vCore	8 GB	100GB",
						"8 vCore	8 GB	100GB",
						"8 vCore	16 GB	100GB",
						"12 vCore	16 GB	100GB"}, 
			values={"1 vCore	1 GB	100GB",
						"1 vCore	2 GB	100GB",
						"2 vCore	2 GB	100GB",
						"2 vCore	4 GB	100GB",
						"4 vCore	4 GB	100GB",
						"4 vCore	8 GB	100GB",
						"8 vCore	8 GB	100GB",
						"8 vCore	16 GB	100GB",
						"12 vCore	16 GB	100GB"})
		public String getHwTemplete() {
			return hwTemplete;
		}
		public void setHwTemplete(String hwTemplete) {
			this.hwTemplete = hwTemplete;
		}
	
	String status;
		@Available(how=MetaworksContext.HOW_IN_LIST)
		@Face(displayName="$project.server.status")
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	boolean checked;
	@Hidden
		public boolean isChecked() {
			return checked;
		}
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
		
	String serverGroup;
		@Hidden
		public String getServerGroup() {
			return serverGroup;
		}
		public void setServerGroup(String serverGroup) {
			this.serverGroup = serverGroup;
		}
	@Face(displayName="$project.server.add")
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object serverAdd() throws Exception{
		String projectId = null;
		String serverGroup = null;
		if( ktProjectServers != null ){
			projectId = ktProjectServers.getProjectId();
			serverGroup = ktProjectServers.getServerGroup();
		}
		ModalWindow popup = new ModalWindow();
		NewServer newServer = new NewServer();
		newServer.setProjectId(projectId);
		newServer.setServerGroup(serverGroup);
		
		popup.setTitle("$project.server.add");
		popup.setPanel(newServer);
		popup.setWidth(500);
		popup.setHeight(400);
		
		return popup;
	}
	
	@Face(displayName="$project.server.remove")
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)	
	public KtProjectServer removeServer() throws Exception{
		if( ktProjectServers != null ){
			for(int i=0; i < ktProjectServers.getServerList().length;  i++){
				KtProjectServer server = ktProjectServers.getServerList()[i];
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
	
	@Available(when={ProjectServer.SERVER_STATUS_STOPPED})
	@Face(displayName="$project.server.start")
	@ServiceMethod(callByContent=true)
	public void start() throws Exception {
		this.setStatus(ProjectServer.SERVER_STATUS_STARTING);
		this.getMetaworksContext().setWhen(this.getStatus());
		
//		String targetUserId = GlobalContext.getPropertyString("vm.target.user");
//		String targetPassword= GlobalContext.getPropertyString("vm.target.password");

//		String targetUserId;
//		String targetPassword;
//		String scriptStart;
//		
//		JschCommand jschServerBehaviour = new JschCommand();
//		if("DB".equals(this.getType())){
//			targetUserId = "cubrid";
//			targetPassword = "cubrid";
//			scriptStart = "cubrid service start";
//			jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
//			jschServerBehaviour.runCommand(scriptStart);
//		}else{
//			targetUserId = "jboss";
//			targetPassword = "jboss";
//			scriptStart = "/usr/lib/jvm/java-7-oracle/bin/java -server -XX:+UseCompressedOops -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Djava.net.preferIPv4Stack=true -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true -Djboss.server.default.config=standalone.xml -Dorg.jboss.boot.log.file=/ssw/jboss-eap-6.0/standalone/log/boot.log -Dlogging.configuration=file:/ssw/jboss-eap-6.0/standalone/configuration/logging.properties -jar /ssw/jboss-eap-6.0/jboss-modules.jar -mp /ssw/jboss-eap-6.0/modules org.jboss.as.standalone -Djboss.home.dir=/ssw/jboss-eap-6.0  -Djboss.server.base.dir=/ssw/jboss-eap-6.0/standalone -b 0.0.0.0 2>&1";
//			
//			// 무한 루프를 돌수 있기에 따로 명령어를 구동함
//			jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
//			ChannelExec channel = (ChannelExec)jschServerBehaviour.getJschSession().openChannel("exec");
//			
//			((ChannelExec)channel).setCommand(scriptStart);
//			channel.setInputStream(null);
//			channel.connect();
//			
//			while(true){
//				Thread.sleep(2000);
//				this.status();
//				if( ProjectServer.SERVER_STATUS_RUNNING.equals(this.getStatus() )){
//					break;
//				}
//			}
//			channel.disconnect();
//		}
//		if(jschServerBehaviour.getJschSession() != null)
//			jschServerBehaviour.getJschSession().disconnect();
	}
	
//	@Available(when={ProjectServer.SERVER_STATUS_RUNNING})
//	@Face(displayName="$project.server.stop")
//	@ServiceMethod(callByContent=true)
//	public void stop() throws Exception {
//		this.setStatus(ProjectServer.SERVER_STATUS_STOPPED);
//		this.getMetaworksContext().setWhen(this.getStatus());
//		
////		String targetUserId = GlobalContext.getPropertyString("vm.target.user");
////		String targetPassword= GlobalContext.getPropertyString("vm.target.password");
//		String targetUserId;
//		String targetPassword;
//		String scriptStop;
//		
//		if("DB".equals(this.getType())){
//			targetUserId = "cubrid";
//			targetPassword = "cubrid";
//			scriptStop = "sh cubrid service stop";
//		}else{
//			targetUserId = "jboss";
//			targetPassword = "jboss";
//			scriptStop = "/usr/lib/jvm/java-7-oracle/bin/java -server -XX:+UseCompressedOops -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Djava.net.preferIPv4Stack=true -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true -Dlogging.configuration=file:/ssw/jboss-eap-6.0/bin/jboss-cli-logging.properties -jar /ssw/jboss-eap-6.0/jboss-modules.jar -mp /ssw/jboss-eap-6.0/modules org.jboss.as.cli -Djboss.home.dir=/ssw/jboss-eap-6.0  -Djboss.server.base.dir=/ssw/jboss-eap-6.0/standalone -c command=:shutdown";
//		}
//		
//		
//		JschCommand jschServerBehaviour = new JschCommand();
//		jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
//		
//		jschServerBehaviour.runCommand(scriptStop);
//		
//		if(jschServerBehaviour.getJschSession() != null)
//			jschServerBehaviour.getJschSession().disconnect();
		
//	}

	@Available(when={ProjectServer.SERVER_STATUS_STOPPED})
	@Face(displayName="$project.server.deploy")
	@ServiceMethod(callByContent=true)
	public void deploy() throws Exception {
	}
	
	//@Available(when={SERVER_STATUS_STARTED, SERVER_STATUS_STOPPED, SERVER_STATUS_STARTING, SERVER_STATUS_STOPPING, SERVER_STATUS_DEPLOYING, SERVER_STATUS_RUNNING})
	@Face(displayName="$project.server.log")
	@ServiceMethod(callByContent=true)
	@Hidden
	public void log(){
		
	}
	
	@Hidden
	@Face(displayName="$project.server.refresh")
	@ServiceMethod(callByContent=true)
	public Object refresh() throws Exception{
		if( ktProjectServers != null ){
			ktProjectServers.loadOceServer();
			
			return ktProjectServers;
		}
		return this;
	}
	
	@Face(displayName = "$devrelease")
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow release() throws Exception{
		SelectBox reflectVersion = new SelectBox();
		ReleasePanel releasePanel = new ReleasePanel();
		FilepathInfo filepathInfo = new FilepathInfo();
		ModalWindow modalWindow = new ModalWindow();
		
		reflectVersion = filepathInfo.findReleaseVersions(this.getProjectId());
		releasePanel.setReflectVersion(reflectVersion);
		releasePanel.setMetaworksContext(new MetaworksContext());
		releasePanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		modalWindow.setTitle("$devrelease");
		modalWindow.setPanel(releasePanel);
		modalWindow.setHeight(200);
		modalWindow.setWidth(300);
		
		return modalWindow;
		
		
		
		
		
		
//		if( ktProjectServers != null ){
//			for(int i=0; i < ktProjectServers.getServerList().length;  i++){
//				KtProjectServer server = ktProjectServers.getServerList()[i];
//				if( server.isChecked() ){
//					// 삭제 요청한 노드
//					// TODO 프로세스에 cancel 을 날려야함
//					CloudInfo cloudInfo = new CloudInfo();
//					ICloudInfo iCloudInfo = cloudInfo.findServerByServerName(server.getProjectId() , server.getName() , server.getServerGroup() );
//					if( iCloudInfo.next() ){
//						cloudInfo.copyFrom(iCloudInfo);
//						if( cloudInfo.getServerIp() != null && !"".equals(cloudInfo.getServerIp())){
//							KtProjectDeleteRequest ktProjectDeleteRequest = new KtProjectDeleteRequest();
//							cloudInfo.databaseMe().setStatus(ProjectServer.SERVER_STATUS_STOPPING);
//							ktProjectDeleteRequest.setCloudInfo(cloudInfo);
//							ktProjectDeleteRequest.deleteRequest();
//						}else{
//							cloudInfo.deleteDatabaseMe();
//						}
//					}
//				}
//			}
//			
//		}
		
	}
	
	public void status() throws Exception {
		
	}
	
	@Override
	public void onLoad() throws Exception {
		
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
