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
import org.uengine.codi.mw3.StartCodi;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.FileTransmition;
import org.uengine.codi.mw3.knowledge.FilepathInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.knowledge.IFilepathInfo;
import org.uengine.codi.mw3.knowledge.ProjectInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppMapping;
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
		app.copyFrom(app.databaseMe());
		
		
		WfNode wfNode = new WfNode();
		wfNode.setId(app.getProject().getId());
		wfNode.copyFrom(wfNode.databaseMe());
		
		if(app.STATUS_PUBLISHED.equals(app.databaseMe().getStatus())){
//			if("1".equals(StartCodi.USE_IAAS)){
//
//				WfNode wfNode = new WfNode();
//				wfNode.setId(app.getProject().getId());
//				wfNode.copyFrom(wfNode.databaseMe());
//				Object warFile = null;
//				Object sqlPath = null;
//
//				CloudInfo cloudInfo = wfNode.getCloudInfo();
//				ICloudInfo cInfo = cloudInfo.findServerByProjectId(cloudInfo.getProjectId() , KtProjectServers.SERVER_PROB);
//				while(cInfo.next()){
//					// TODO 서버가 여러개 있는 경우를 체크해서 올려야함
//					cloudInfo.copyFrom(cInfo);
//				}
//				
//				XStream xstream = new XStream();
//				if (wfNode.getExt() != null) {
//					Object xstreamStr = xstream.fromXML(wfNode.getExt());
//					if (xstreamStr != null) {
//						Map<String, Object> list = (Map<String, Object>) xstreamStr;
//
//						warFile = list.get("warFile_Thumbnail");
//						sqlPath = list.get("sqlFile_Path");
//					}
//				}
//				String tenantId;
//				if(TenantContext.getThreadLocalInstance()!=null && TenantContext.getThreadLocalInstance().getTenantId()!=null){
//					tenantId = TenantContext.getThreadLocalInstance().getTenantId();
//				}else{
//					tenantId = session.getCompany().getComCode();
//				}
//				if(warFile != null){
//					//this.databaseMe().setUrl("Http://" + this.getAppName() + ".uenginecloud.com:8080/" + warFile.toString() + "/" + tenantId);
////					this.databaseMe().setUrl("Http://27.1.126.73:9090/UrlRewrite/uengine/index.jsp");	
//				}else{
//					//this.databaseMe().setUrl("Http://" + this.getAppName() + ".uenginecloud.com:8080/" + tenantId);
////					this.databaseMe().setUrl("Http://27.1.126.73:9090/UrlRewrite/uengine/index.jsp");
//				}
//				syncToDatabaseMe();
//				flushDatabaseMe();
//				
//				//데이터베이스 생성
//				if(TenantContext.getThreadLocalInstance().getTenantId()!=null && sqlPath.toString()!= null){
//					CreateDatabase createDatabase = new CreateDatabase();
//					createDatabase.create(cloudInfo.getRootId(), cloudInfo.getServerIp(), cloudInfo.getRootPwd(), tenantId, sqlPath.toString());
//				}
//			}
//			
			AppMapping appmapping = new AppMapping();
			
			appmapping.setAppId(this.getAppId());
			appmapping.setAppName(this.getAppName());
			appmapping.setComCode(this.getComcode());
			appmapping.setIsDeleted(false);
			appmapping.setUrl(app.getUrl());
			appmapping.createDatabaseMe();
			appmapping.flushDatabaseMe();
			
		}else{
			app.databaseMe().setStatus(App.STATUS_APPROVED);
			
			
			if(!("1".equals(StartCodi.USE_IAAS))){
				String host = GlobalContext.getPropertyString("vm.manager.ip","localhost");
				String userId = GlobalContext.getPropertyString("vm.manager.user","root");
				String passwd = GlobalContext.getPropertyString("vm.manager.password","root");
				
				String command ="";
				String projectAlias = wfNode.getProjectAlias();
				
				JschCommand jschServerBehaviour = new JschCommand();
				
				jschServerBehaviour.sessionLogin(host, userId, passwd);
				
				
				/**
				 * 작성:cjs
				 * 
				 * IaaS 연동이 제외된 시나리오에서 app등록 처리 순서는.. 
				 * 
				 * 1. mysql(port:3308)에 데이터베이스를 생성한다.
				 * 2. 생성한 데이터베이스에 등록된 **.sql 파일을 실행한다.
				 * 3. tomcat_prod 를 shutdown 한다.
				 * 4. tomcat_prod/webapps에 tomcat_dev에서 구동중인 **.war 파일을 복사한다.
				 * 		4.1. svn 일경우 project Alias 를 매개변수로 넘겨준다
				 * 		4.2. war 파일일경우 등록된 war 파일명을 매개변수로 넘겨준다
				 * 5. tomcat_prod 를 start 시킨다. 
				 * 
				 */
				
				
				/*
				 * 1. 프로젝트 명으로 데이터베이스 생성하기 
				 * sell 명령어 = {path}/createDS.sh "databasePort" "projectAlias" 
				 * 
				 * "projectAlias" = 프로젝트 생성 시 입력한 alias 값입니다.
				 * "databasePort" =	3306 : garuda engine 관련 mysql
				 * 					3307 : 프로젝트 생성 및 운영 관련 mysql
				 * 					3308 : 앱 생성 및 운영 관련 mysql
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.mysql.createDatabase","/oce/script/mysql/createDB.sh") + " \"" + ProjectInfo.MYSQL_APP_PORT + "\"" + " \"" + projectAlias + "\"";
				jschServerBehaviour.runCommand(command);
				
				
				/*
				 * 2. 생성한 데이터베이스에 첨부한 **.sql 파일을 실행하기
				 * sell 명령어 = {path}/execSql.sh "databasePort" "projectAlias" "sqlFilePath"
				 * 
				 * "projectAlias" = 프로젝트 생성 시 입력한 alias 값입니다.
				 * "databasePort" =	3306 : garuda engine 관련 mysql
				 * 					3307 : 프로젝트 생성 및 운영 관련 mysql
				 * 					3308 : 앱 생성 및 운영 관련 mysql
				 * "sqlFilePath"  = sql이 저장된 파일 경로를 불러온다.
				 * 
				 */
				
				FilepathInfo filepathinfo = new FilepathInfo();
				filepathinfo.setProjectId(app.getProject().getId());
				
				IFilepathInfo fileInfo = filepathinfo.findReflectforProjectId();
				
				
				while(fileInfo.next()){
					filepathinfo.setFileType(fileInfo.getFileType());
					filepathinfo.setSqlPath(fileInfo.getSqlPath());
					filepathinfo.setSqlFileName(fileInfo.getSqlFileName());
					filepathinfo.setWarPath(fileInfo.getWarPath());
					filepathinfo.setWarFileName(fileInfo.getWarFileName());
				}
				
				String sqlFilePath = GlobalContext.getPropertyString("filesystem.path") + "/" + filepathinfo.getSqlPath();
				command = GlobalContext.getPropertyString("vm.mysql.loadScript","/oce/script/mysql/execSql.sh") + " \"" + ProjectInfo.MYSQL_APP_PORT + "\"" + " \"" + projectAlias + "\"" + " \"" + sqlFilePath + "\"";
				jschServerBehaviour.runCommand(command);
				
				/*
				 * 3. tomcat_prod 를 shutdown 한다.
				 * sell 명령어 = {path}/killProd.sh
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.tomcat.killProdServer","/oce/script/tomcat/killProd.sh");
				jschServerBehaviour.runCommand(command);
				
				if("war".equals(filepathinfo.getFileType())){
					projectAlias = filepathinfo.getWarFileName().replace(".war", "");
					
					/*
					 * 4. tomcat_prod/webapps에 tomcat_dev에서 구동중인 **.war 파일을 복사한다.
					 * 		4.1. svn 일경우 project Alias 를 매개변수로 넘겨준다
					 * 		4.2. war 파일일경우 등록된 war 파일명을 매개변수로 넘겨준다
					 * sell 명령어 = {path}/devToProd.sh "projctAlias" "subDomain" 
					 * 
					 * "projctAlias" = 프로젝트 생성 시 작성한 alias값을 넘겨줍니다.
					 * "subDomain" = 앱 등록 시 입력한 domain을 받아옵니다.
					 * 
					 */
					command = GlobalContext.getPropertyString("vm.tomcat.devWarToProd") + " \"" + projectAlias + "\"" + " \"" + app.getSubDomain() + "\"";
					jschServerBehaviour.runCommand(command);
					
				}else if("svn".equals(filepathinfo.getFileType())){
					
					/*
					 * 4-1. hudsonMakeJob.sh를 실행시켜 앱 등록 시 입력한 도메인으로 job을 생성한다.
					 * sell 명령어 = {path}/hudsonMakeJob.sh "subDomain" 
					 * 
					 * "subDomain" = 앱 등록 시 입력한 subDomain값 입니다. subDomain은 중복되는 값이 없는 key 값이어야 합니다.
					 * 
					 */
					command = GlobalContext.getPropertyString("vm.hudson.createJob","/home/hudson/script/hudsonMakeJob.sh") + " " + app.getSubDomain();
					jschServerBehaviour.runCommand(command);
					
					/*
					 * 4-2. hudsonBuild.sh를 실행시켜 환경을 설정한다. (생성한 job에 svn세팅 및 빌드환경 세팅 등)
					 * sell 명령어 = {path}/hudsonBuild.sh "subDomain" 
					 * 
					 * "subDomain" = 앱 등록 시 입력한 subDomain값 입니다. subDomain은 중복되는 값이 없는 key 값이어야 합니다.
					 * 
					 */
					command = GlobalContext.getPropertyString("vm.hudson.appSetting") + " \"" + app.getSubDomain() + "\"" + " \"prod\"" + " \"" + projectAlias + "\"";
					jschServerBehaviour.runCommand(command);
					
					String nextBuilderNumber = null;
					String builderResult = null;
					String hudsonURL = GlobalContext.getPropertyString("vm.hudson.url","http://localhost:8080/hudson/");
					HudsonJobApi hudsonJobApi = new HudsonJobApi();
					
					long timeoutTime = 200000;
					long sleepTime = 5000;
					long tryTime = 0;
					
					while(nextBuilderNumber == null){
						HudsonJobDDTO hudsonJobDDTO = hudsonJobApi.hudsonJobApiXmlParser(hudsonURL, app.getSubDomain());
						
						nextBuilderNumber = hudsonJobDDTO.getNextBuilderNumber();
						
						

						System.out.println("nextBuilderNumber: " + nextBuilderNumber);
						try {
							tryTime += sleepTime;
							Thread.sleep(sleepTime);
						} catch (Exception e) {
						}
						
						if(tryTime > timeoutTime)
							break;
						
						
						
					}
					
					
					/*
					 * 4-3. hudsonBuild.sh를 프로젝트를 빌드한다.
					 * sell 명령어 = {path}/hudsonBuild.sh "subDomain" 
					 * 
					 * "subDomain" = 앱 등록 시 입력한 subDomain값 입니다. subDomain은 중복되는 값이 없는 key 값이어야 합니다.
					 * 
					 */
					command = GlobalContext.getPropertyString("vm.hudson.build","/home/hudson/script/hudsonBuild.sh") + " " + app.getSubDomain();
					jschServerBehaviour.runCommand(command);
					
					while(builderResult == null){
						HudsonJobDDTO hudsonJobDDTO = hudsonJobApi.hudsonJobApiXmlParser(hudsonURL, app.getSubDomain());
						
						if(nextBuilderNumber.equals(hudsonJobDDTO.getLastSuccessfulBuild().getNumber()))
							builderResult = "SUCCESS";
						else if(nextBuilderNumber.equals(hudsonJobDDTO.getLastUnSuccessfulBuild().getNumber()))
							builderResult = "UNSUCCESS";
						else if(nextBuilderNumber.equals(hudsonJobDDTO.getLastFailedBuild().getNumber()))
							builderResult = "FAILED";
						
						System.out.println("builderResult: " + builderResult);
						try {
							Thread.sleep(5000);
						} catch (Exception e) {
							throw new Exception(e);
						}
					}
					
					/*
					 * 4-4. tomcat_dev/webapps에 build된 war 파일을 복사한다.
					 * sell 명령어 = {path}/copyWarDev.sh "subDomain" 
					 * 
					 * "subDomain" = 앱 등록 시 입력한 subDomain값 입니다. subDomain은 중복되는 값이 없는 key 값이어야 합니다.
					 * 
					 */
					command = GlobalContext.getPropertyString("vm.tomcat.svnCopyToProd") + " \"" + app.getSubDomain() + "\"";
					jschServerBehaviour.runCommand(command);
				}
				
				/*
				 * 5. tomcat_prod 를 start 시킨다.
				 * sell 명령어 = {path}/startProd.sh
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.tomcat.startProdServer","/oce/script/tomcat/startProd.sh");
				jschServerBehaviour.runCommand(command);
				
				
				if( jschServerBehaviour.getJschSession() == null )
					throw new Exception("not connected");
				else
					jschServerBehaviour.getJschSession().disconnect();
			}
			
		}
		
		
//		final String appName = app.getAppName();
//		final String projectId = app.getProject().getId();
//		final int runningVersion = app.getRunningVersion();
//		final String projectType = app.getProject().getVisType();
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					KtProjectCreateRequest ktProjectCreateRequest = new KtProjectCreateRequest();
//					CloudInfo cloudInfo = ktProjectCreateRequest.createRequset(appName , null);
//					insertCloudInfo(projectId , cloudInfo, runningVersion, projectType);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}				
//			}
//		}).start();
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
			
			String host = GlobalContext.getPropertyString("vm.manager.ip","localhost");
			String userId = GlobalContext.getPropertyString("vm.manager.user","root");
			String passwd = GlobalContext.getPropertyString("vm.manager.password","root");
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
				String hudsonURL = GlobalContext.getPropertyString("vm.hudson.url","http://localhost:8080/hudson/");
				
				long timeoutTime = 200000;
				long sleepTime = 5000;
				long tryTime = 0;
				HudsonJobApi hudsonJobApi = new HudsonJobApi();

				jschServerBehaviour.sessionLogin(host, userId, passwd);
				
				command = GlobalContext.getPropertyString("vm.svn.svnRevision","/home/hudson/script/hudsonRevision.sh") + " " + wfNode.getProjectAlias() + " " + cloudInfo.getServerIp() + " " 
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
				
				command = GlobalContext.getPropertyString("vm.hudson.build","/home/hudson/script/hudsonBuild.sh") + " " + wfNode.getProjectAlias();
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
