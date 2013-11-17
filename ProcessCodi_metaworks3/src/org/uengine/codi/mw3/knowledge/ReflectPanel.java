package org.uengine.codi.mw3.knowledge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.metadata.MetadataFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.hudson.HudsonJobApi;
import org.uengine.codi.hudson.HudsonJobDDTO;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;

import com.jcraft.jsch.ChannelExec;

@Face(ejsPath = "", options = { "fieldOrder" }, values = { "serverSelect,reflectVersion,check,warFile,sqlFile,comment" })
public class ReflectPanel {

	MetadataFile warFile;
		@Face(displayName = "$WarFile")
		@Available(when=MetaworksContext.WHEN_EDIT)
		public MetadataFile getWarFile() {
			return warFile;
		}
		public void setWarFile(MetadataFile warFile) {
			this.warFile = warFile;
		}

	MetadataFile sqlFile;
		@Face(displayName = "$SqlFile")
		@Available(when={MetaworksContext.WHEN_EDIT, MetaworksContext.WHEN_NEW})
		public MetadataFile getSqlFile() {
			return sqlFile;
		}
		public void setSqlFile(MetadataFile sqlFile) {
			this.sqlFile = sqlFile;
		}
	
	SelectBox serverSelect;
		@Face(displayName = "$ServerList")
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
		
		
	@Face(displayName = "$devreflect")
	@ServiceMethod(callByContent = true)
	public Remover reflect() throws Exception {
		// 배포 할ㄸㅐ 할일들
		int reflectVer;
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getProjectId());
		wfNode.copyFrom(wfNode.databaseMe());
		FilepathInfo filepathinfo = new FilepathInfo();
		filepathinfo.setProjectId(wfNode.getId());
		CloudInfo cloudInfo = new CloudInfo();
		
		String host = GlobalContext.getPropertyString("vm.manager.ip");
		String userId = GlobalContext.getPropertyString("vm.manager.user");
		String passwd = GlobalContext.getPropertyString("vm.manager.password");
		String command;
		JschCommand jschServerBehaviour = new JschCommand();
		
		
		if ("war".equals(wfNode.getVisType())) {
			if(!check){
				if (this.getWarFile().getFileTransfer() != null
						&& this.getWarFile().getFilename() != null
						&& this.getWarFile().getFilename().length() > 0)
	
					if (this.getWarFile() != null) {
						MetadataFile resourceFile = new MetadataFile();
						resourceFile.setFilename(this.getWarFile().getFilename());
						resourceFile.setUploadedPath(this.getWarFile().getUploadedPath());
						resourceFile.setMimeType(this.getWarFile().getMimeType());
						setWarFile(resourceFile);
	
					}
				if (this.getSqlFile().getFileTransfer() != null
						&& this.getSqlFile().getFilename() != null
						&& this.getSqlFile().getFilename().length() > 0)
	
					if (this.getSqlFile() != null) {
						MetadataFile resourceFile = new MetadataFile();
						resourceFile.setFilename(this.getSqlFile().getFilename());
						resourceFile.setUploadedPath(this.getSqlFile().getUploadedPath());
						resourceFile.setMimeType(this.getSqlFile().getMimeType());
						setSqlFile(resourceFile);
					}
				
				reflectVer = filepathinfo.findReflectVersion(filepathinfo.getProjectId());
				if (reflectVer == 0) {
					reflectVer = 1;
				} else {
					reflectVer++;
				}
	
				filepathinfo.setSqlPath(this.getSqlFile().getFilename());
				filepathinfo.setWarPath(this.getWarFile().getFilename());
				filepathinfo.setReflectVer(reflectVer);
				filepathinfo.setReleaseVer(filepathinfo.findReleaseVersion(filepathinfo.getProjectId()));
				filepathinfo.setFileType(wfNode.getVisType());
				filepathinfo.setId(filepathinfo.createNewId());
				filepathinfo.setComment(this.getComment());
				filepathinfo.setModdate(new Date());
				filepathinfo.setDistributor(session.getEmployee().getEmpName());
				
				filepathinfo.createDatabaseMe();
				filepathinfo.flushDatabaseMe();
			}
			else{
				filepathinfo.setId(Integer.parseInt(this.getReflectVersion().getSelected()));
				filepathinfo.copyFrom(filepathinfo.databaseMe());
			}
			
			if("1".equals(GlobalContext.getPropertyString("iaas.use", "1"))){	// IaaS 연동 시
				FileTransmition fileTransmition = new FileTransmition();
	//			jschServerBehaviour.sessionLogin(cloudInfo.getServerIp(), cloudInfo.getRootId(), cloudInfo.getRootPwd());
				cloudInfo.setId(Long.parseLong(this.getServerSelect().toString()));
				cloudInfo.copyFrom(cloudInfo.databaseMe());
				
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/jbossKill.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				command = "sh /root/jbossKill.sh";
				jschServerBehaviour.runCommand(command);
				
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/war/" + filepathinfo.getWarPath(), cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/ssw/jboss-eap-6.0/standalone/deployments");
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/sql/" + filepathinfo.getSqlPath(), cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				fileTransmition.send(GlobalContext.getPropertyString("codebase", "codebase") + "/startUp.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
				
				command = "sh /root/startUp.sh" + " " + wfNode.getName() + " " + filepathinfo.getSqlPath();
				
				if( jschServerBehaviour.getJschSession() == null )
					throw new Exception("not connected");
				
				ChannelExec channel = (ChannelExec)jschServerBehaviour.getJschSession().openChannel("exec");
				
				((ChannelExec)channel).setCommand(command);
				channel.setInputStream(null);
				channel.connect();
				
				channel.disconnect();
			}
			else{	//IaaS 미 연동 시
				//톰캣 킬
//				String cmd = "/oce/tomcat_dev/bin/shutdown.sh";
//				this.command(cmd);
//				
//				//폴더랑 와르파일 삭제
//				
//				//와르파일 복사
//				
//				//sql
//				
//				//톰캣 시작
//				cmd = "/oce/tomcat_dev/bin/startup.sh";
//				this.command(cmd);
				
			}
			
		} else if ("svn".equals(wfNode.getVisType())) {
			if("1".equals(GlobalContext.getPropertyString("iaas.use", "1"))){	// IaaS 연동 시
				cloudInfo.setId(Long.parseLong(this.getServerSelect().toString()));
				cloudInfo.copyFrom(cloudInfo.databaseMe());
				if(!check){
					String nextBuilderNumber = null;
					String builderResult = null;
					String hudsonURL = GlobalContext.getPropertyString("vm.hudson.url");
					
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
					
					command = GlobalContext.getPropertyString("vm.hudson.setting") + " " + wfNode.getProjectAlias() + " " + cloudInfo.getServerIp() + " " + cloudInfo.getRootPwd();
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
					
					command = GlobalContext.getPropertyString("vm.svn.checkVersion") + " " + wfNode.getProjectAlias();
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
				else{
					
				}
			}
			else{	//IaaS 미 연동시
				 //톰캣 킬
//				String cmd = GlobalContext.getPropertyString("dev.tomcat.shutdown");
//				this.command(cmd);
//				
//				//허드슨 빌드 커맨드 와르가 생기는 폴더가 픽스를 시켜
//				
//				//기존 폴더랑 와르 파일 삭제
//
//				//와르 파일이 생기고 생긴 파일을 개발 톰캣에 카피
//				
//				//sql 
//				
//				//톰캣 실행				
//				cmd = GlobalContext.getPropertyString("dev.tomcat.start");
//				this.command(cmd);
				
//				filepathinfo.setReflectVer(svn 현재 커밋버전);
//				filepathinfo.setFileType(wfNode.getVisType());
//				filepathinfo.setId(filepathinfo.createNewId());
//				filepathinfo.setComment(this.getComment());
//				filepathinfo.setModdate(new Date());
//				filepathinfo.setDistributor(session.getEmployee().getEmpName());
//				
//				filepathinfo.createDatabaseMe();
//				filepathinfo.flushDatabaseMe();
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
