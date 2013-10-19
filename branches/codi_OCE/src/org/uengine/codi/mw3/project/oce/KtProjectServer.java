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
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.ProjectInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;
import org.uengine.codi.mw3.model.Session;

@Face(displayName="개발기",
ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathForArray="dwr/metaworks/genericfaces/GridFace.ejs",
ejsPathMappingByContext = {
		"{how: 'inList', face: 'dwr/metaworks/genericfaces/GridFace_Row.ejs'}",
	},
options={"hideAddBtn" , "hideEditBtn", "hideRemoveBtn", "showExtraBtn" , "popupWidth", "gridButtons"},
values={ "true", "true", "true", "true" , "500", "serverAdd,removeServer,refresh"})
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
	String osType;
	@Face(displayName="$project.server.ostype", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"리눅스(LINUX)","유닉스(UNIX)","Window NT"}, values={"LINUX", "UNIX", "Window"})
		public String getOsType() {
			return osType;
		}
		public void setOsType(String osType) {
			this.osType = osType;
		}
	String wasType;
	@Face(displayName="$project.server.wastype", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"서버엔진 사용안함","Jboss 6.0","Tomcat 7.0"}, values={"사용안함", "Jboss", "Tomcat"})
		public String getWasType() {
			return wasType;
		}
		public void setWasType(String wasType) {
			this.wasType = wasType;
		}
	String dbType;
	@Face(displayName="$project.server.dbtype", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"데이터베이스 사용안함","Cubrid","Mysql","Oracle"}, values={"사용안함", "Cubrid", "Mysql","Oracle"})
		public String getDbType() {
			return dbType;
		}
		public void setDbType(String dbType) {
			this.dbType = dbType;
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
		
	@Face(displayName="$project.server.add")
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object serverAdd() throws Exception{
		String projectId = null;
		if( ktProjectServers != null ){
			projectId = ktProjectServers.getProjectId();
		}
		ModalWindow popup = new ModalWindow();
		NewServer newServer = new NewServer();
		newServer.setProjectId(projectId);
		
		popup.setPanel(newServer);
		popup.setWidth(500);
		popup.setHeight(400);
		
		return popup;
	}
	
	@Face(displayName="$project.server.remove")
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)	
	public KtProjectServer removeServer(){
		if( ktProjectServers != null ){
			String projectId = ktProjectServers.getProjectId();
			System.out.println("3333333333  ==  " + projectId);
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
