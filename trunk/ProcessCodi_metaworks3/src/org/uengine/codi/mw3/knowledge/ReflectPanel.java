package org.uengine.codi.mw3.knowledge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.hudson.HudsonJobApi;
import org.uengine.codi.hudson.HudsonJobDDTO;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.StartCodi;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;

import com.jcraft.jsch.ChannelExec;

@Face(ejsPath = "", options = { "fieldOrder" }, values = { "serverSelect,reflectVersion,check,warFile,sqlFile,comment" })
public class ReflectPanel {

	MetaworksFile warFile;
		@Face(displayName = "$WarFile")
		@Available(when=MetaworksContext.WHEN_EDIT)
		public MetaworksFile getWarFile() {
			return warFile;
		}
		public void setWarFile(MetaworksFile warFile) {
			this.warFile = warFile;
		}

	MetaworksFile sqlFile;
		@Face(displayName = "$SqlFile")
		@Available(when={MetaworksContext.WHEN_EDIT, MetaworksContext.WHEN_NEW})
		public MetaworksFile getSqlFile() {
			return sqlFile;
		}
		public void setSqlFile(MetaworksFile sqlFile) {
			this.sqlFile = sqlFile;
		}
	
	SelectBox serverSelect;
		@Face(displayName = "$ServerList")
		@Available(where="IaaS")
		public SelectBox getServerSelect() {
			return serverSelect;
		}
		public void setServerSelect(SelectBox serverSelect) {
			this.serverSelect = serverSelect;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	String comment;
		@Face(displayName = "$Comment", ejsPath = "dwr/metaworks/genericfaces/richText.ejs", options={"rows", "cols"}, values = {"5", "50"})
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		
	Boolean check;
		@Face(displayName="$previousVersion")
		@Available(when={MetaworksContext.WHEN_EDIT, MetaworksContext.WHEN_NEW})
		public Boolean getCheck() {
			return check;
		}
		public void setCheck(Boolean check) {
			this.check = check;
		}
		
	SelectBox reflectVersion;
		@Face(displayName = "$ReflectVersion")
		@Available(when={MetaworksContext.WHEN_EDIT, MetaworksContext.WHEN_NEW})
		public SelectBox getReflectVersion() {
			return reflectVersion;
		}
		public void setReflectVersion(SelectBox reflectVersion) {
			this.reflectVersion = reflectVersion;
		}
		
		
	@Face(displayName = "$reflect")
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	public Remover reflect() throws Exception {
		// 배포 할ㄸㅐ 할일들
		int reflectVer;
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getProjectId());
		wfNode.copyFrom(wfNode.databaseMe());
		FilepathInfo filepathinfo = new FilepathInfo();
		filepathinfo.setProjectId(wfNode.getId());
		CloudInfo cloudInfo = new CloudInfo();
		
		String host = GlobalContext.getPropertyString("vm.manager.ip","localhost");
		String userId = GlobalContext.getPropertyString("vm.manager.user","root");
		String passwd = GlobalContext.getPropertyString("vm.manager.password","root");
		String command;
		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin(host, userId, passwd);
		
		if(!check){
			if (this.getWarFile()!= null && this.getWarFile().getFileTransfer() != null
					&& this.getWarFile().getFilename() != null
					&& this.getWarFile().getFilename().length() > 0){
				
				this.getWarFile().upload();
				filepathinfo.setWarPath(this.getWarFile().getUploadedPath());
				filepathinfo.setWarFileName(this.getWarFile().getFilename());
			}
			
			if (this.getSqlFile()!= null && this.getSqlFile().getFileTransfer() != null
					&& this.getSqlFile().getFilename() != null
					&& this.getSqlFile().getFilename().length() > 0){
				this.getSqlFile().upload();
				filepathinfo.setSqlPath(this.getSqlFile().getUploadedPath());
				filepathinfo.setSqlFileName(this.getSqlFile().getFilename());
				
			}
			
			reflectVer = filepathinfo.findReflectVersion(filepathinfo.getProjectId());
			if (reflectVer == 0) {
				reflectVer = 1;
			} else {
				reflectVer++;
			}
			
			filepathinfo.setReflectVer(reflectVer);
			filepathinfo.setReleaseVer(filepathinfo.findReleaseVersion(filepathinfo.getProjectId()));
			filepathinfo.setFileType(wfNode.getVisType());
			filepathinfo.setId(filepathinfo.createNewId());
			filepathinfo.setComment(this.getComment());
			filepathinfo.setModdate(new Date());
			filepathinfo.setDistributor(session.getEmployee().getEmpName());
			
			filepathinfo.createDatabaseMe();
			filepathinfo.flushDatabaseMe();
		}else{
			filepathinfo.setId(Integer.parseInt(this.getReflectVersion().getSelected()));
			filepathinfo.copyFrom(filepathinfo.databaseMe());
		}
		
		if ("war".equals(wfNode.getVisType())) {
			// IaaS 연동 시("war"파일 첨부)
			if("1".equals(StartCodi.USE_IAAS)){
				
				FileTransmition fileTransmition = new FileTransmition();
				jschServerBehaviour.sessionLogin(cloudInfo.getServerIp(), cloudInfo.getRootId(), cloudInfo.getRootPwd());
				cloudInfo.setId(Long.parseLong(this.getServerSelect().toString()));
				cloudInfo.copyFrom(cloudInfo.databaseMe());
				
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/jbossKill.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				command = "sh /root/jbossKill.sh";
				jschServerBehaviour.runCommand(command);
				
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/war/" + filepathinfo.getWarPath(), cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/ssw/jboss-eap-6.0/standalone/deployments");
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/sql/" + filepathinfo.getSqlPath(), cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/startUp.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				
				command = "sh /root/startUp.sh" + " " + wfNode.getName() + " " + filepathinfo.getSqlPath();
				jschServerBehaviour.runCommand(command);
				
			//IaaS 미 연동 시("war"파일 첨부)
			}else{
				
				/**
				 * 작성:cjs
				 * 
				 * IaaS 연동이 제외된 시나리오에서 war 파일을 첨부 할 경우 순서는
				 * 
				 * 1. mysql(port:3307)에 데이터베이스를 생성한다.
				 * 2. 생성한 데이터베이스에 첨부한 **.sql 파일을 실행한다.
				 * 3. tomcat_dev 를 shutdown 한다.
				 * 4. tomcat_dev/webapps에 첨부한 **.war 파일을 복사한다.
				 * 5. tomcat_deb 를 start 시킨다. 
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
//				command = GlobalContext.getPropertyString("vm.mysql.createDatabase","/oce/script/mysql/createDB.sh") + " \"" + ProjectInfo.MYSQL_PROJECT_PORT + "\"" + " \"" + wfNode.getProjectAlias() + "\"";
//				this.command(command);

				
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
				String sqlFilePath = GlobalContext.getPropertyString("filesystem.path") + "/" + this.getSqlFile().getUploadedPath();
				
				command = GlobalContext.getPropertyString("vm.mysql.loadScript","/oce/script/mysql/execSql.sh") + " \"" + ProjectInfo.MYSQL_PROJECT_PORT + "\"" + " \"" + wfNode.getProjectAlias() + "\"" + " \"" + sqlFilePath + "\"";
				jschServerBehaviour.runCommand(command);
				
				/*
				 * 3. tomcat_dev 를 shutdown 한다.
				 * sell 명령어 = {path}/killDev.sh
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.tomcat.killDevServer","/oce/script/tomcat/killDev.sh");
				jschServerBehaviour.runCommand(command);
				
				/*
				 * 4. tomcat_dev/webapps에 첨부한 **.war 파일을 복사한다.
				 * sell 명령어 = {path}/copyWarDev.sh "warFileName" "warFilePath" 
				 * 
				 * "warFileName" = 첨부한 war파일의 파일이름을 넘긴다. test.war일경우 "test" 값만 넘깁니다. (동일한 이름의 war파일이 존재 할 경우 폴더 및 war파일을 삭제한다.)
				 * "warFilePath" = 첨부한 war파일이 저장된 full 경로를 넘깁니다.
				 * 
				 */
				
				String warFileName = this.getWarFile().getFilename().replace(".war", "");
				String warFilePath = GlobalContext.getPropertyString("filesystem.path") + "/" + this.getWarFile().getUploadedPath();
				
				command = GlobalContext.getPropertyString("vm.tomcat.warCopyToDev") + " \"" + warFileName + "\"" + " \"" + warFilePath + "\"";
				jschServerBehaviour.runCommand(command);
				
				
				
				/*
				 * 5. tomcat_dev 를 start 시킨다.
				 * sell 명령어 = {path}/startDev.sh
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.tomcat.startDevServer","/oce/script/tomcat/startDev.sh");
				jschServerBehaviour.runCommand(command);
				
			}
			
			if( jschServerBehaviour.getJschSession() != null )
				jschServerBehaviour.getJschSession().disconnect();
			
		} else if ("svn".equals(wfNode.getVisType())) {
		
			if("1".equals(StartCodi.USE_IAAS)){	// IaaS 연동 시("svn" 빌드)
				cloudInfo.setId(Long.parseLong(this.getServerSelect().toString()));
				cloudInfo.copyFrom(cloudInfo.databaseMe());
				if(!check){
					String nextBuilderNumber = null;
					String builderResult = null;
					String hudsonURL = GlobalContext.getPropertyString("vm.hudson.url","http://localhost:8080/hudson/");
					
					long timeoutTime = 200000;
					long sleepTime = 5000;
					long tryTime = 0;
					
					String tmp;
					jschServerBehaviour.sessionLogin(cloudInfo.getServerIp(), cloudInfo.getRootId(), cloudInfo.getRootPwd());
					
					FileTransmition fileTransmition = new FileTransmition();
					
					fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/jbossKill.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
					command = "sh /root/jbossKill.sh";
					jschServerBehaviour.runCommand(command);
					
					if(jschServerBehaviour.getJschSession() != null)
						jschServerBehaviour.getJschSession().disconnect();
					
					jschServerBehaviour.sessionLogin(host, userId, passwd);		
					
					command = GlobalContext.getPropertyString("vm.hudson.setting","/home/hudson/script/hudsonSetting.sh") + " " + wfNode.getProjectAlias() + " " + cloudInfo.getServerIp() + " " + cloudInfo.getRootPwd();
					jschServerBehaviour.runCommand(command);
					
					HudsonJobApi hudsonJobApi = new HudsonJobApi();
		
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
					
					command = GlobalContext.getPropertyString("vm.svn.checkVersion","/home/hudson/script/svnCheck.sh") + " " + wfNode.getProjectAlias();
					tmp = jschServerBehaviour.runCommand(command);
					
					if(jschServerBehaviour.getJschSession() != null)
						jschServerBehaviour.getJschSession().disconnect();
					
					jschServerBehaviour.sessionLogin(cloudInfo.getServerIp(), cloudInfo.getRootId(), cloudInfo.getRootPwd());
					
					fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/jbossStart.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
					command = "sh /root/jbossStart.sh";
					
					if( jschServerBehaviour.getJschSession() == null )
						throw new Exception("not connected");
					
					ChannelExec channel = (ChannelExec)jschServerBehaviour.getJschSession().openChannel("exec");
					
					((ChannelExec)channel).setCommand(command);
					channel.setInputStream(null);
					channel.connect();
					
					channel.disconnect();
					
					filepathinfo.setReflectVer(Integer.parseInt(tmp));
					filepathinfo.setFileType(wfNode.getVisType());
					filepathinfo.setId(filepathinfo.createNewId());
					filepathinfo.setComment(this.getComment());
					filepathinfo.setModdate(new Date());
					filepathinfo.setDistributor(session.getEmployee().getEmpName());
					
					if(jschServerBehaviour.getJschSession() != null)
						jschServerBehaviour.getJschSession().disconnect();
					
					filepathinfo.createDatabaseMe();
					filepathinfo.flushDatabaseMe();
				}
			
			//IaaS 미 연동시("svn" 빌드)
			}else{
				
				/**
				 * 작성:cjs
				 * 
				 * IaaS 연동이 제외된 시나리오에서 svn 커밋버전을 빌드 할때 반영순서는 
				 * 
				 * 1. mysql(port:3307)에 데이터베이스를 생성한다.
				 * 2. 생성한 데이터베이스에 첨부한 **.sql 파일을 실행한다.
				 * 3. tomcat_dev 를 shutdown 한다.
				 * 4. hudsonSetting.sh를 실행시켜 실행시켜 환경을 설정한다
				 * 5. hudsonBuild.sh를 실행시켜 프로젝트를 빌드한다.
				 * 6. svnVersion을 체크하여 반영시 svn의 커밋버전을 기록한다.
				 * 7. tomcat_dev/webapps에 build된 war 파일을 복사한다.
				 * 8. tomcat_deb 를 start 시킨다. 
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
//				command = GlobalContext.getPropertyString("vm.mysql.createDatabase","/oce/script/mysql/createDB.sh") + " \"" + ProjectInfo.MYSQL_PROJECT_PORT + "\"" + " \"" + wfNode.getProjectAlias() + "\"";
//				this.command(command);
				
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
				String sqlFilePath = GlobalContext.getPropertyString("filesystem.path") + "/" + this.getSqlFile().getUploadedPath();
				command = GlobalContext.getPropertyString("vm.mysql.loadScript","/oce/script/mysql/execSql.sh") + " \"" + ProjectInfo.MYSQL_PROJECT_PORT + "\"" + " \"" + wfNode.getProjectAlias() + "\"" + " \"" + sqlFilePath + "\"";
				jschServerBehaviour.runCommand(command);
				
				/*
				 * 3. tomcat_dev 를 shutdown 한다.
				 * sell 명령어 = {path}/killDev.sh
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.tomcat.killDevServer","/oce/script/tomcat/killDev.sh");
				jschServerBehaviour.runCommand(command);
				
				/*
				 * 4. hudsonSetting.sh를 실행시켜 환경을 설정한다.
				 * sell 명령어 = {path}/hudsonSetting.sh "projectAlias" 
				 * 
				 * "projectAlias" = 프로젝트 생성 시 입력한 alias 값입니다.
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.hudson.setting","/home/hudson/script/hudsonSetting.sh") + " \"" + wfNode.getProjectAlias() + "\"" + " \"dev\"";
				jschServerBehaviour.runCommand(command);
				
				String nextBuilderNumber = null;
				String builderResult = null;
				String hudsonURL = GlobalContext.getPropertyString("vm.hudson.url","http://localhost:8080/hudson/");
				HudsonJobApi hudsonJobApi = new HudsonJobApi();
				
				long timeoutTime = 200000;
				long sleepTime = 5000;
				long tryTime = 0;
				
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
				
				/*
				 * 5. hudsonBuild.sh를 프로젝트를 빌드한다.
				 * sell 명령어 = {path}/hudsonMakeJob.sh "projectAlias" 
				 * 
				 * "projectAlias" = 프로젝트 생성 시 입력한 alias 값입니다.
				 * 
				 */
				
				command = GlobalContext.getPropertyString("vm.hudson.build","/home/hudson/script/hudsonBuild.sh") + " " + wfNode.getProjectAlias();
				jschServerBehaviour.runCommand(command);
				
				//hudson build monitering popup
				MetaworksRemoteService.pushTargetScript(Login.getSessionIdWithUserId(session.getUser().getUserId()),
						"if(mw3.getAutowiredObject('org.uengine.codi.mw3.knowledge.ProjectInfo')!=null) mw3.getAutowiredObject('org.uengine.codi.mw3.knowledge.ProjectInfo').__getFaceHelper().callHudsonConsole",
						new Object[]{});
				
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
						throw new Exception(e);
					}
				}
				
				/*
				 * 6. svnVersion을 체크하여 반영시 svn의 커밋버전을 기록한다.
				 * sell 명령어 = {path}/svnCheck.sh "projectAlias" 
				 * 
				 * "projectAlias" = 프로젝트 생성 시 입력한 alias 값입니다.
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.svn.checkVersion","/home/hudson/script/svnCheck.sh") + " \"" + wfNode.getProjectAlias() + "\"";
				String svnVersion = jschServerBehaviour.runCommand(command);
				
				/*
				 * 7. tomcat_dev/webapps에 build된 war 파일을 복사한다.
				 * sell 명령어 = {path}/copyWarDev.sh "projectAlias"
				 * 
				 * "projectAlias" = 프로젝트 생성 시 입력한 alias 값입니다.
				 * 
				 */
				
				command = GlobalContext.getPropertyString("vm.tomcat.svnCopyToDev") + " \"" + wfNode.getProjectAlias() + "\"";
				jschServerBehaviour.runCommand(command);
				
				/*
				 * 8. tomcat_deb 를 start 시킨다.
				 * sell 명령어 = {path}/startDev.sh
				 * 
				 */
				command = GlobalContext.getPropertyString("vm.tomcat.startDevServer","/oce/script/tomcat/startDev.sh");
				jschServerBehaviour.runCommand(command);
				
				filepathinfo.setReflectVer(Integer.parseInt(svnVersion));
				filepathinfo.setFileType(wfNode.getVisType());
				filepathinfo.setSqlPath(this.getSqlFile().getUploadedPath());
				filepathinfo.setSqlFileName(this.getSqlFile().getFilename());
				filepathinfo.setId(filepathinfo.createNewId());
				filepathinfo.setComment(this.getComment());
				filepathinfo.setModdate(new Date());
				filepathinfo.setDistributor(session.getEmployee().getEmpName());
				
				filepathinfo.createDatabaseMe();
				filepathinfo.flushDatabaseMe();
				
				if(jschServerBehaviour.getJschSession() != null)
					jschServerBehaviour.getJschSession().disconnect();
				
			}
		}

		return new Remover(new ModalWindow());
	}
	
	public String command(String cmd){
		String result = null;
		StringBuffer strbuf = new StringBuffer ( );
		BufferedReader br = null;
		try{ 
			Process proc = Runtime.getRuntime ( ).exec ( cmd );
			br = new BufferedReader ( new InputStreamReader ( proc.getInputStream ( ) ) );
			String line;
			while ( ( line = br.readLine ( ) ) != null )
				strbuf.append ( line );
			br.close ( );
		}catch ( Exception e ) { result = "command file : " + e.toString(); }
		  
		result = strbuf.toString ( );
		return result;
	 }

	@AutowiredFromClient
	public Session session;
}
