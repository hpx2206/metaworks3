package org.uengine.codi.mw3.project.oce;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.codi.hudson.HudsonJobApi;
import org.uengine.codi.hudson.HudsonJobDDTO;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.knowledge.ProjectInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

import com.jcraft.jsch.ChannelExec;

@Face(displayName="서버",
ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathForArray="dwr/metaworks/genericfaces/GridFace.ejs",
ejsPathMappingByContext = {
		"{how: 'inList', face: 'dwr/metaworks/genericfaces/GridFace_Row.ejs'}",
	},
options={"addBtnName", "removeBtnName", "hideEditBtn", "showExtraBtn", "callbackAddBtn", "callbackConfirmBtn", "popupWidth", "gridButtons"},
values={"$project.server.add", "$project.server.remove", "true", "true", "add", "confirm", "500", "refresh,statistics"})
public class KtProjectServer  implements ITool, ContextAware{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	@Autowired
	transient ProcessManagerRemote processManager;

	@AutowiredFromClient
	transient public Session session;

	String name;
	@Name
	@Face(displayName="$project.server.name")		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
	
	String type;
		@Available(how=MetaworksContext.HOW_IN_LIST)
		@Face(displayName="$project.server.type")
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
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
		
	@Hidden
	@Face(displayName="$project.server.add")	
	@ServiceMethod	
	public void add(){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
	}
		
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_NONE)	
	public KtProjectServer confirm(){
		
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

		String targetUserId;
		String targetPassword;
		String scriptStart;
		
		JschCommand jschServerBehaviour = new JschCommand();
		if("DB".equals(this.getType())){
			targetUserId = "cubrid";
			targetPassword = "cubrid";
			scriptStart = "cubrid service start";
			jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
			jschServerBehaviour.runCommand(scriptStart);
		}else{
			targetUserId = "jboss";
			targetPassword = "jboss";
			scriptStart = "/usr/lib/jvm/java-7-oracle/bin/java -server -XX:+UseCompressedOops -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Djava.net.preferIPv4Stack=true -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true -Djboss.server.default.config=standalone.xml -Dorg.jboss.boot.log.file=/ssw/jboss-eap-6.0/standalone/log/boot.log -Dlogging.configuration=file:/ssw/jboss-eap-6.0/standalone/configuration/logging.properties -jar /ssw/jboss-eap-6.0/jboss-modules.jar -mp /ssw/jboss-eap-6.0/modules org.jboss.as.standalone -Djboss.home.dir=/ssw/jboss-eap-6.0  -Djboss.server.base.dir=/ssw/jboss-eap-6.0/standalone -b 0.0.0.0 2>&1";
			
			// 무한 루프를 돌수 있기에 따로 명령어를 구동함
			jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
			ChannelExec channel = (ChannelExec)jschServerBehaviour.getJschSession().openChannel("exec");
			
			((ChannelExec)channel).setCommand(scriptStart);
			channel.setInputStream(null);
			channel.connect();
			
			while(true){
				Thread.sleep(2000);
				this.status();
				if( ProjectServer.SERVER_STATUS_RUNNING.equals(this.getStatus() )){
					break;
				}
			}
			channel.disconnect();
		}
		if(jschServerBehaviour.getJschSession() != null)
			jschServerBehaviour.getJschSession().disconnect();
	}
	
	@Available(when={ProjectServer.SERVER_STATUS_RUNNING})
	@Face(displayName="$project.server.stop")
	@ServiceMethod(callByContent=true)
	public void stop() throws Exception {
		this.setStatus(ProjectServer.SERVER_STATUS_STOPPED);
		this.getMetaworksContext().setWhen(this.getStatus());
		
//		String targetUserId = GlobalContext.getPropertyString("vm.target.user");
//		String targetPassword= GlobalContext.getPropertyString("vm.target.password");
		String targetUserId;
		String targetPassword;
		String scriptStop;
		
		if("DB".equals(this.getType())){
			targetUserId = "cubrid";
			targetPassword = "cubrid";
			scriptStop = "sh cubrid service stop";
		}else{
			targetUserId = "jboss";
			targetPassword = "jboss";
			scriptStop = "/usr/lib/jvm/java-7-oracle/bin/java -server -XX:+UseCompressedOops -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Djava.net.preferIPv4Stack=true -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true -Dlogging.configuration=file:/ssw/jboss-eap-6.0/bin/jboss-cli-logging.properties -jar /ssw/jboss-eap-6.0/jboss-modules.jar -mp /ssw/jboss-eap-6.0/modules org.jboss.as.cli -Djboss.home.dir=/ssw/jboss-eap-6.0  -Djboss.server.base.dir=/ssw/jboss-eap-6.0/standalone -c command=:shutdown";
		}
		
		
		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
		
		jschServerBehaviour.runCommand(scriptStop);
		
		if(jschServerBehaviour.getJschSession() != null)
			jschServerBehaviour.getJschSession().disconnect();
		
	}

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
	public Object refresh(){
		
		return this;
	}
	
	public void status() throws Exception {
		
		if("DB".equals(this.getType())){
			String targetUserId = "cubrid";
			String targetPassword = "cubrid";
			String scriptStatus = "netstat -an | grep 8001|grep -v grep |grep LISTEN|wc -l";
			
			JschCommand jschServerBehaviour = new JschCommand();
			jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
			
			String status = jschServerBehaviour.runCommand(scriptStatus);
			
			if(jschServerBehaviour.getJschSession() != null)
				jschServerBehaviour.getJschSession().disconnect();

			if("1".equals(status)){
				this.setStatus(ProjectServer.SERVER_STATUS_RUNNING);					
			}else{
				this.setStatus(ProjectServer.SERVER_STATUS_STOPPED);
			}
			
		}else 		if(!"Not Allocated".equals(this.getIp())){
//			String targetUserId = GlobalContext.getPropertyString("vm.target.user");
//			String targetPassword= GlobalContext.getPropertyString("vm.target.password");
			
			String targetUserId = "jboss";
			String targetPassword = "jboss";
			
			String scriptStatus = "netstat -an | grep 8080|grep -v grep |grep LISTEN|wc -l";
//			String scriptStarting = "ps -ef|grep service|grep jboss|grep -v grep|wc -l";
					
			JschCommand jschServerBehaviour = new JschCommand();
			jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
			
//			String starting = jschServerBehaviour.runCommand(scriptStarting);
			String status = jschServerBehaviour.runCommand(scriptStatus);
			
			if("1".equals(status)){
				this.setStatus(ProjectServer.SERVER_STATUS_RUNNING);					
			}else{
				this.setStatus(ProjectServer.SERVER_STATUS_STOPPED);
			}
			
			if(jschServerBehaviour.getJschSession() != null)
				jschServerBehaviour.getJschSession().disconnect();
		}
		
		this.getMetaworksContext().setWhen(this.getStatus());
	}
	
	@Hidden
	@Face(displayName="통계")
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object statistics(){
		IFrame iframe = new IFrame("http://192.168.212.77:10086/admin?status");
//		iframe.setWidth("1000");
//		iframe.setHeight("630");
		
		return new Popup(980, 600, iframe);
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
