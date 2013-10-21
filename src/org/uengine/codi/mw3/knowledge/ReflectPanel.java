package org.uengine.codi.mw3.knowledge;

import java.io.File;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.metadata.MetadataFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;

@Face(ejsPath = "", options = { "fieldOrder" }, values = { "serverSelect,warFile,sqlFile" })
public class ReflectPanel {

	MetadataFile warFile;
		@Face(displayName = "$WarFile")
		@Available(when=MetaworksContext.WHEN_NEW)
		public MetadataFile getWarFile() {
			return warFile;
		}
		public void setWarFile(MetadataFile warFile) {
			this.warFile = warFile;
		}

	MetadataFile sqlFile;
		@Face(displayName = "$SqlFile")
		@Available(when=MetaworksContext.WHEN_NEW)
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
		cloudInfo.setId(Long.parseLong(this.getServerSelect().toString()));
		cloudInfo.copyFrom(cloudInfo.databaseMe());
		String host = GlobalContext.getPropertyString("vm.manager.ip");
		String userId = GlobalContext.getPropertyString("vm.manager.user");
		String passwd = GlobalContext.getPropertyString("vm.manager.password");
		String command;
		JschCommand jschServerBehaviour = new JschCommand();
		
		
		if ("war".equals(wfNode.getVisType())) {
			if (this.getWarFile().getFileTransfer() != null
					&& this.getWarFile().getFilename() != null
					&& this.getWarFile().getFilename().length() > 0)

				if (this.getWarFile() != null) {
					String codebase = GlobalContext.getPropertyString("codebase", "codebase");
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
					String codebase = GlobalContext.getPropertyString("codebase", "codebase");
					MetadataFile resourceFile = new MetadataFile();
					resourceFile.setFilename(this.getSqlFile().getFilename());
					resourceFile.setUploadedPath(this.getSqlFile().getUploadedPath());
					resourceFile.setMimeType(this.getSqlFile().getMimeType());
					setSqlFile(resourceFile);
				}
			
			jschServerBehaviour.sessionLogin(cloudInfo.getServerIp(), cloudInfo.getRootId(), cloudInfo.getRootPwd());
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

			filepathinfo.createDatabaseMe();
			filepathinfo.flushDatabaseMe();
			
//			FileTransmition fileTransmition = new FileTransmition();
//			fileTransmition.send("D:/codi/codebase/" + filepathinfo.getWarPath(), cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/ssw/jboss-eap-6.0/standalone/deployments");
//			fileTransmition.send("D:/codi/codebase/" + filepathinfo.getSqlPath(), cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
//			fileTransmition.send("C:/startUp.sh", cloudInfo.getRootId(), cloudInfo.getRootPwd(), cloudInfo.getServerIp(), "/root");
			
//			command = "sh /root/startUp.sh" + " " + wfNode.getName() + " " + filepathinfo.getSqlPath();
//			jschServerBehaviour.runCommand(command);
			
		} else if ("svn".equals(wfNode.getVisType())) {
			
			jschServerBehaviour.sessionLogin(host, userId, passwd);
			
			command = GlobalContext.getPropertyString("vm.hudson.build") + " " + wfNode.getName();
			jschServerBehaviour.runCommand(command);
			
		}

		return new Remover(new ModalWindow());
	}

	@AutowiredFromClient
	public Session session;
}
