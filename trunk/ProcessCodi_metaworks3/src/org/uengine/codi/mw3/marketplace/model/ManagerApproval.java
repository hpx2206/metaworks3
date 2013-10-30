package org.uengine.codi.mw3.marketplace.model;

import java.util.Date;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Range;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.JDBCConnectionFactory;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.ITool;
import org.uengine.codi.hudson.HudsonJobApi;
import org.uengine.codi.hudson.HudsonJobDDTO;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.FileTransmition;
import org.uengine.codi.mw3.knowledge.FilepathInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.project.oce.KtProjectCreateRequest;
import org.uengine.codi.mw3.project.oce.KtProjectServers;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;

import com.jcraft.jsch.ChannelExec;

@Face(
	ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
	options={"fieldOrder"},
	values={"approval"}
)
public class ManagerApproval implements ITool  {
	
	public ManagerApproval() { 
	}
	
	public static ConnectionFactory connectionFactory;
	
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
		
//		App app = new App();
//		
//		app.setAppId(this.getAppId());
//		app.databaseMe().setStatus(App.STATUS_PUBLISHED);
	}

	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
		App app = new App();
		
		app.setAppId(this.getAppId());
		app.databaseMe().setStatus(App.STATUS_APPROVED);
		app.copyFrom(app.databaseMe());
		
		final String appName = app.getAppName();
		final String projectId = app.getProject().getId();
		final int runningVersion = app.getRunningVersion();
		final String projectType = app.getProject().getVisType();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					KtProjectCreateRequest ktProjectCreateRequest = new KtProjectCreateRequest();
					CloudInfo cloudInfo = ktProjectCreateRequest.createRequset(appName , null);
					insertCloudInfo(projectId , cloudInfo, runningVersion, projectType);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
	}
	
	public void insertCloudInfo(String projectId, CloudInfo cloudInfo, int runningVersion, String projectType) throws Exception{
		TransactionContext tx = new TransactionContext(); //once a TransactionContext is created, it would be cached by ThreadLocal.set, so, we need to remove this after the request processing. 
		try{
			tx.setManagedTransaction(false);
			tx.setAutoCloseConnection(true);
			
			String connectionString = GlobalContext.getPropertyString("jdbc.url", null);
			if(connectionString!=null){
				String driverClass = GlobalContext.getPropertyString("jdbc.driverClassName", null);
				String userId = GlobalContext.getPropertyString("jdbc.username", "root");
				String password = GlobalContext.getPropertyString("jdbc.password", "root");
				
				JDBCConnectionFactory cf = new JDBCConnectionFactory();
				cf.setConnectionString(connectionString);
				cf.setDriverClass(driverClass);
				cf.setUserId(userId);
				cf.setPassword(password);
				connectionFactory = cf;
			}
			if(connectionFactory!=null){
				tx.setConnectionFactory(connectionFactory);
			}
			cloudInfo.setProjectId(projectId);
			cloudInfo.setServerInfo("KT Cloud");
			cloudInfo.setServerGroup(KtProjectServers.SERVER_PROB);
			cloudInfo.setModdate(new Date());
			cloudInfo.setStatus(ProjectServer.SERVER_STATUS_RUNNING);
			
			ICloudInfo iCInfo = cloudInfo.findServerByServerName(projectId , cloudInfo.getServerName() , KtProjectServers.SERVER_PROB);
			if( iCInfo.next() ){
				cloudInfo.setId(iCInfo.getId());
				cloudInfo.setOsTemplete(iCInfo.getOsTemplete());
				cloudInfo.setHwTemplete(iCInfo.getHwTemplete());
				cloudInfo.setServiceTemplete(iCInfo.getServiceTemplete());
				cloudInfo.syncToDatabaseMe();
			}else{
				cloudInfo.setId(cloudInfo.createNewId());
				cloudInfo.createDatabaseMe();
				cloudInfo.flushDatabaseMe();
			}
			
			String host = GlobalContext.getPropertyString("vm.manager.ip");
			String userId = GlobalContext.getPropertyString("vm.manager.user");
			String passwd = GlobalContext.getPropertyString("vm.manager.password");
			String command;
			JschCommand jschServerBehaviour = new JschCommand();
			WfNode wfNode = new WfNode();
			wfNode.setId(projectId);
			wfNode.copyFrom(wfNode.databaseMe());
			FilepathInfo filepathInfo = new FilepathInfo();
			filepathInfo.setId(runningVersion);
			filepathInfo.copyFrom(filepathInfo.databaseMe());
			
			if("svn".equals(wfNode.getVisType())){
				String nextBuilderNumber = null;
				String builderResult = null;
				String hudsonURL = GlobalContext.getPropertyString("vm.hudson.url");
				
				long timeoutTime = 200000;
				long sleepTime = 5000;
				long tryTime = 0;
				HudsonJobApi hudsonJobApi = new HudsonJobApi();

				jschServerBehaviour.sessionLogin(host, userId, passwd);
				
				command = GlobalContext.getPropertyString("vm.svn.svnRevision") + " " + wfNode.getProjectAlias() + " " + cloudInfo.getServerIp() + " " 
						+ cloudInfo.getRootPwd() + " " + filepathInfo.getReflectVer();
				
				
				
				jschServerBehaviour.runCommand(command);

				while(nextBuilderNumber == null){
					HudsonJobDDTO hudsonJobDDTO = hudsonJobApi.hudsonJobApiXmlParser(hudsonURL, wfNode.getProjectAlias());
					
					nextBuilderNumber = hudsonJobDDTO.getNextBuilderNumber();
					try {
						tryTime += sleepTime;
						Thread.sleep(sleepTime);
					} catch (Exception e) {
					}
					
					if(tryTime > timeoutTime)
						break;
				}
				
				command = GlobalContext.getPropertyString("vm.hudson.build") + " " + wfNode.getProjectAlias();
				jschServerBehaviour.runCommand(command);
				
				while(builderResult == null){
					HudsonJobDDTO hudsonJobDDTO = hudsonJobApi.hudsonJobApiXmlParser(hudsonURL, wfNode.getProjectAlias());
					
					if(nextBuilderNumber.equals(hudsonJobDDTO.getLastSuccessfulBuild().getNumber()))
						builderResult = "SUCCESS";
					else if(nextBuilderNumber.equals(hudsonJobDDTO.getLastUnSuccessfulBuild().getNumber()))
						builderResult = "UNSUCCESS";
					else if(nextBuilderNumber.equals(hudsonJobDDTO.getLastFailedBuild().getNumber()))
						builderResult = "FAILED";
					
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
					}
				}
				
				if(jschServerBehaviour.getJschSession() != null)
					jschServerBehaviour.getJschSession().disconnect();
				
				jschServerBehaviour.sessionLogin(cloudInfo.getServerIp(), cloudInfo.getRootId(), cloudInfo.getRootPwd());
				
				FileTransmition fileTransmition = new FileTransmition();
				
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/jbossStart.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				command = "sh /root/jbossStart.sh";
				
				if( jschServerBehaviour.getJschSession() == null )
					throw new Exception("not connected");
				
				ChannelExec channel = (ChannelExec)jschServerBehaviour.getJschSession().openChannel("exec");
				
				((ChannelExec)channel).setCommand(command);
				channel.setInputStream(null);
				channel.connect();
				
				channel.disconnect();
				
			}
			else if("war".equals(wfNode.getVisType())){
				jschServerBehaviour.sessionLogin(cloudInfo.getServerIp(), cloudInfo.getRootId(), cloudInfo.getRootPwd());
				FileTransmition fileTransmition = new FileTransmition();
				
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/jbossKill.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				command = "sh /root/jbossKill.sh";
				jschServerBehaviour.runCommand(command);
				
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/war/" + filepathInfo.getWarPath(), cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/ssw/jboss-eap-6.0/standalone/deployments");
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/sql/" + filepathInfo.getSqlPath(), cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/startUp.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				
				command = "sh /root/startUp.sh" + " " + wfNode.getName() + " " + filepathInfo.getSqlPath();
				
				if( jschServerBehaviour.getJschSession() == null )
					throw new Exception("not connected");
				
				ChannelExec channel = (ChannelExec)jschServerBehaviour.getJschSession().openChannel("exec");
				
				((ChannelExec)channel).setCommand(command);
				channel.setInputStream(null);
				channel.connect();
				
				channel.disconnect();
				
				if(jschServerBehaviour.getJschSession() != null)
					jschServerBehaviour.getJschSession().disconnect();
			}
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			throw e;
		}finally{
			tx.releaseResources();
		}
	}
}
