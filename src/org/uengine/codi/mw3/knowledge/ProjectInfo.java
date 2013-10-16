
package org.uengine.codi.mw3.knowledge;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.MetadataFile;
import org.metaworks.website.Download;
import org.metaworks.website.MetaworksFile;
import org.metaworks.website.OpenBrowser;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TopicFollowers;
import org.uengine.codi.mw3.project.ProjectCreate;
import org.uengine.codi.mw3.project.oce.KtProjectServers;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

import com.thoughtworks.xstream.XStream;

public class ProjectInfo implements ContextAware {

	MetaworksContext metaworksContext;

	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}

	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}

	String projectId;
		@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	
	String projectName;
		@Face(displayName="$project.name")
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		
	String domainName;
		@Face(displayName="$project.domain")
		public String getDomainName() {
			return domainName; 
		}
		public void setDomainName(String domainName) {
			this.domainName = domainName;
		}
	String description;
		@Face(displayName="$project.description")
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	MetaworksFile logoFile;
		@Face(displayName="로고파일")
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}	
	MetadataFile sqlFile;
		@Face(displayName="sqlFile")
		public MetadataFile getSqlFile() {
			return sqlFile;
		}
		public void setSqlFile(MetadataFile sqlFile) {
			this.sqlFile = sqlFile;
		}

	MetadataFile 	warFile;
		@Face(displayName="WarFile")
		public MetadataFile getWarFile() {
			return warFile;
		}
		public void setWarFile(MetadataFile warFile) {
			this.warFile = warFile;
		}	
	String type;
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
	String hudson;
		public String getHudson() {
			return hudson;
		}
		public void setHudson(String hudson) {
			this.hudson = hudson;
		}
	String defaultIp;
		public String getDefaultIp() {
			return defaultIp;
		}
		public void setDefaultIp(String defaultIp) {
			this.defaultIp = defaultIp;
		}

	/*
	@Hidden
	String os;
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		
	@Hidden
	String db;
		public String getDb() {
			return db;
		}
		public void setDb(String db) {
			this.db = db;
		}
			
	@Hidden
	String was;
		public String getWas() {
			return was;
		}
		public void setWas(String was) {
			this.was = was;
		}
		
	@Hidden
	String vm;
		public String getVm() {
			return vm;
		}
		public void setVm(String vm) {
			this.vm = vm;
		}*/
		
	/*@Hidden
	String ci;
		public String getCi() {
			return ci;
		}
		public void setCi(String ci) {
			this.ci = ci;
		}*/
	
	/*@Face(displayName="@Hudson")
	String hudson;
		public String getHudson() {
			return hudson;
		}
		public void setHudson(String hudson) {
			this.hudson = hudson;
		}
	
	@Face(displayName="@TemplateName")
	String templateName;
		public String getTemplateName() {
			return templateName;
		}
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		
	@Face(displayName="$Ip")
	String ip;
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
	
	@Hidden
	String vmDouwnUrl;
		public String getVmDouwnUrl() {
			return vmDouwnUrl;
		}
		public void setVmDouwnUrl(String vmDouwnUrl) {
			this.vmDouwnUrl = vmDouwnUrl;
		}*/
		
	public ProjectInfo(){
		this(null);
	}
	
	public ProjectInfo(String projectId){
		this.setProjectId(projectId);
//		this.setLogoFile(new MetaworksFile());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public void load() {
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getProjectId());
		
		try {
			wfNode.copyFrom(wfNode.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		if(wfNode.getLinkedInstId() != null){
			String linkedId = String.valueOf(wfNode.getLinkedInstId());*/
			
//			Object logo = null;
			this.projectName = wfNode.getName();
			this.domainName = this.getProjectName() + ".com";			
			setType(wfNode.getVisType());
			if("svn".equals(this.getType())){
				Object logo = null;
				XStream xstream = new XStream();
				if(wfNode.getExt() != null){
					Object xstreamStr =  xstream.fromXML(wfNode.getExt());
					if(xstreamStr != null){
						Map<String, Object> list = (Map<String,Object>) xstreamStr;
						logo = list.get("logoFile");
					}
				}
				this.logoFile = (MetaworksFile) logo;
				setType("svn");
				this.hudson = GlobalContext.getPropertyString("vm.hudson.url") + "/job/" + this.getProjectName();
			}else if("war".equals(this.getType())){
				Object logo = null;
				Object warUrl = null;
				Object sqlUrl = null;
				Object warFile = null;
				Object sqlFile = null;
				XStream xstream = new XStream();
				if(wfNode.getExt() != null){
					Object xstreamStr =  xstream.fromXML(wfNode.getExt());
					if(xstreamStr != null){
						Map<String, Object> list = (Map<String,Object>) xstreamStr;
					
						warUrl = list.get("warFile_Thumbnail");
						sqlUrl = list.get("sqlFile_Thumbnail");
						logo = list.get("logoFile");
						warFile = list.get("warFile");
						sqlFile = list.get("sqlFile");
					}
				}
//						this.war = (String) warUrl;
//						this.sql = (String) sqlUrl;
						this.logoFile = (MetaworksFile) logo;
						this.warFile = (MetadataFile) warFile;
						this.sqlFile = (MetadataFile) sqlFile;
						setType("war");
			}
			this.description = wfNode.getDescription();
			
			/*
			Serializable serial = null;
			serial = processManager.getProcessVariable(linkedId, "", "VMRequest");
			if(serial instanceof VMRequest) {
				VMRequest vmRequest = (VMRequest)serial;
				this.templateName = vmRequest.getVmImageCombo().getSelectedText();
			}
			
			this.ip = (String)(Serializable)processManager.getProcessVariable(linkedId, "", "vm_ip");
			
			this.svn = "svn://" + GlobalContext.getPropertyString("vm.manager.ip") + "/" + this.projectName;
			
			
			this.hudson = GlobalContext.getPropertyString("vm.hudson.url");
			*/
		//}
	}

	@Face(displayName = "$projet.download.sandboxfordeveloper")
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public Download downloadSandbox() throws Exception {
		String fileSystemPath = GlobalContext.getPropertyString(
				"filesystem.path", ".");
		String sendboxPath = fileSystemPath + "/resource/sandbox_final.ova";

		return new Download(new FileTransfer(new String(
				"sandbox_final.ova".getBytes("UTF-8"), "ISO8859_1"), null,
				new FileInputStream(sendboxPath)));
	}

	// @Face(displayName="$projet.download.eclipseforegovframe")
	// @ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	// public Download downloadEclipse() throws Exception{
	// String fileSystemPath =
	// GlobalContext.getPropertyString("filesystem.path",".");
	// String sendboxPath = fileSystemPath + "/resource/govFramEclpse64.zip";
	//
	// return new Download(new FileTransfer(new
	// String("govFramEclpse64.zip".getBytes("UTF-8"),"ISO8859_1"), null, new
	// FileInputStream(sendboxPath)));
	// }

	@Face(displayName = "$projet.download.virtualmachine")
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public OpenBrowser downloadVirtualMachine() throws Exception {

		String url = GlobalContext.getPropertyString("vm.download.url");

		return new OpenBrowser(url);
	}

	@Face(displayName = "release")
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public Object releaseProject() throws Exception{
		WfNode wfNode = new WfNode();
		wfNode.setId(session.getLastSelectedItem());
		wfNode.copyFrom(wfNode.databaseMe());
		
		if(wfNode.getIsDistributed()){
			wfNode.setIsReleased(true);
			wfNode.syncToDatabaseMe();
			wfNode.flushDatabaseMe();
			
			ModalWindow modalWindow = new ModalWindow();
			modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			modalWindow.setWidth(300);
			modalWindow.setHeight(150);
							
			modalWindow.setTitle("$SaveCompleteTitle");
			modalWindow.setPanel(localeManager.getString("Release 성공 하였습니다."));
			modalWindow.getButtons().put("$Confirm", "");		
			
			return modalWindow;
		}else{
			ModalWindow modalWindow = new ModalWindow();
			modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			modalWindow.setWidth(300);
			modalWindow.setHeight(150);
							
			modalWindow.setTitle("$Release 실패");
			modalWindow.setPanel(localeManager.getString("프로젝트 배포가 되지않아 실패 하였습니다."));
			modalWindow.getButtons().put("$Confirm", "");		
	
			return modalWindow;
		}
	}
	
	@ServiceMethod
	public void Confirm() {
		
	}

	@Face(displayName = "배포")
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public Object distribute() throws Exception{
		WfNode wfNode = new WfNode();
		wfNode.setId(session.getLastSelectedItem());
		wfNode.copyFrom(wfNode.databaseMe());
		this.projectName = wfNode.getName();
		Object sqlPath = null;
		Object warPath = null;
		defaultIp = "14.63.225.215";

		XStream xstream = new XStream();
		if (wfNode.getExt() != null) {
			Object xstreamStr = xstream.fromXML(wfNode.getExt());
			if (xstreamStr != null) {
				Map<String, Object> list = (Map<String, Object>) xstreamStr;

				warPath = list.get("warFile_Path");
				sqlPath = list.get("sqlFile_Path");
			}
		}
		if ("war".equals(wfNode.getVisType())) {
			ProcessBuilder pb = null;
			if(wfNode.getConnType() == null){
				pb = new ProcessBuilder(
						"cmd",	// 리눅스 "/bin/sh"
						"/c",	// 리눅스 "-c"		
						"ant -buildfile C:\\test.xml -Dip="
								+ defaultIp
								+ " -Dwar="
								+ GlobalContext.getPropertyString("codebase", "codebase")+ File.separatorChar + warPath
								+ " -Dsql="
								+ GlobalContext.getPropertyString("codebase", "codebase") + File.separatorChar + sqlPath
								+ " -Dpw="
								+ "root"
								+ " -lib C:\\Users\\uEngine");
			}
			else{
				pb = new ProcessBuilder(
						"cmd",	// 리눅스 "/bin/sh"
						"/c",	// 리눅스 "-c"		
						"ant -buildfile C:\\test.xml -Dip="
								+ wfNode.getConnType()
								+ " -Dwar="
								+ GlobalContext.getPropertyString("codebase", "codebase")+ File.separatorChar + warPath
								+ " -Dsql="
								+ GlobalContext.getPropertyString("codebase", "codebase") + File.separatorChar + sqlPath
								+ " -Dpw="
								+ "root"
								+ " -lib C:\\Users\\uEngine");
			}
			
			pb.redirectErrorStream(true);
			Process p = null;
			try {
				p = pb.start();
				
				InputStreamReader isr = null;
				isr = new InputStreamReader(p.getInputStream(), "euc-kr");
				int ch = 0;
				StringBuffer sb = new StringBuffer();
				try {
					while ((ch = isr.read()) > -1) {
						sb.append((char) ch);
						if ((char) ch == '\n') {
							System.out.println(sb.toString());
							sb = new StringBuffer();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CreateDatabase createDatabase = new CreateDatabase();
			if(wfNode.getConnType() == null){
				createDatabase.create("root", defaultIp, "root", wfNode.getName(), sqlPath.toString());
			}
			else{
				createDatabase.create("root", wfNode.getConnType(), "root", wfNode.getName(), sqlPath.toString());
			}
			wfNode.setIsDistributed(true);
			wfNode.syncToDatabaseMe();
			wfNode.flushDatabaseMe();
			
			ModalWindow modalWindow = new ModalWindow();
			modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			modalWindow.setWidth(300);
			modalWindow.setHeight(150);
							
			modalWindow.setTitle("$SaveCompleteTitle");
			modalWindow.setPanel(localeManager.getString("반영되었습니다."));
			modalWindow.getButtons().put("$Confirm", "");		
			
			return modalWindow;
		}
		else if("sns".equals(wfNode.getVisType())){
			String host = GlobalContext.getPropertyString("vm.manager.ip");
			String userId = GlobalContext.getPropertyString("vm.manager.user");
			String passwd = GlobalContext.getPropertyString("vm.manager.password");
			String command;
			
			JschCommand jschServerBehaviour = new JschCommand();
			jschServerBehaviour.sessionLogin(host, userId, passwd);
			
			//Hudson Build
			command = GlobalContext.getPropertyString("vm.hudson.build") + " " + wfNode.getName();
			jschServerBehaviour.runCommand(command);
		}
		return null;

	}

	@Face(displayName = "권한관리")
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public void committer() {

	}

	@Face(displayName = "서버관리")
	@ServiceMethod(payload={"projectName"}, target = ServiceMethodContext.TARGET_APPEND)
	public Object[] server() throws Exception {
		
		ModalWindow modal = new ModalWindow();
		
//		String ip = "14.63.225.192";
		String ip = "14.63.225.215";
		
		KtProjectServers ProjectServers = new KtProjectServers();
		ProjectServers.loadOceServer(this.getProjectName() , ip);
		
		modal.setPanel(ProjectServers);
		modal.setWidth(600);
		modal.setHeight(400);
		modal.setTitle("서버관리");
		
		return new Object[]{modal};
	}

	@Face(displayName = "참여")
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public Object[] participation() throws Exception {
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(session.getLastSelectedItem());
		tm.setUserId(session.getUser().getUserId());

		if (!tm.findByUser().next()) {
			tm.setUserName(session.getUser().getName());
			tm.saveMe();
			tm.flushDatabaseMe();
		}

		TopicFollowers topicFollowers = new TopicFollowers();
		topicFollowers.session = session;
		topicFollowers.load();

		return new Object[] { new Refresh(topicFollowers) };
		}
		
		@Face(displayName="정보변경")
		@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
		public ModalWindow manageProject() throws Exception{
			
			ProjectTitle projectTitle = new ProjectTitle();
			projectTitle.setMetaworksContext(new MetaworksContext());
			projectTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			projectTitle.session = session;
			WfNode wfNode = new WfNode();
			wfNode.setId(session.getLastSelectedItem());
			wfNode.copyFrom(wfNode.databaseMe());
			projectTitle.setTopicId(this.getProjectId());
			projectTitle.setTopicTitle(this.getProjectName());
			projectTitle.setParentId(session.getCompany().getComCode());
			projectTitle.setFileType(wfNode.getVisType());
			projectTitle.setTopicDescription(this.getDescription());
			projectTitle.setLogoFile(this.getLogoFile());
			
			XStream xstream = new XStream();
			if(wfNode.getExt() != null){
				Object xstreamStr =  xstream.fromXML(wfNode.getExt());
		//		Object[] fileList = (Object[])GlobalContext.deserialize(wfNode.getEx1(), Object.class);
				Object sqlFile = null;
				Object warFile = null;
				Object logoFile = null;
				if(xstreamStr != null){
					Map<String, Object> list = (Map<String,Object>) xstreamStr;
				
					warFile = list.get("warFile");
					sqlFile = list.get("sqlFile");
					logoFile = list.get("logoFile");
			}
				System.out.println(warFile);
				System.out.println(sqlFile);
				
			projectTitle.setLogoFile((MetaworksFile) logoFile);	
			projectTitle.setWarFile((MetadataFile) warFile);
			projectTitle.setSqlFile((MetadataFile) sqlFile);
		}
		return new ModalWindow(projectTitle, 500, 400, "정보변경");
	}
	
	@Face(displayName="개발환경요청")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] require() throws Exception{
		
		String defId = "oceProjectRequset.process";
		
		ProcessMap processMap = new ProcessMap();
		processMap.processManager = processManager;
		processMap.session = session;
		processMap.instanceView = instanceViewContent;
		processMap.setDefId(defId);
		
		String instId = processMap.initializeProcess();
				
		ProjectCreate projectCreate = new ProjectCreate();
		projectCreate.setName(this.getProjectName());
		projectCreate.setProjectId(this.getProjectId());
		
//		ResultPayload rp = new ResultPayload();
//		rp.setProcessVariableChange(new KeyedParameter("ProjectCreate", projectCreate));
		
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, processMap.getDefId(), session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		
		processManager.executeProcess(instId);
		processManager.getProcessInstance(instId).setBeanProperty("ProjectCreate", projectCreate);
//		processManager.executeProcessByWorkitem(instId.toString(), rp);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
		instance.databaseMe().setTopicId(this.getProjectId());
		instance.databaseMe().setName("개발환경요청" + " : " + this.getProjectName());
		instance.flushDatabaseMe();
		
		instanceViewContent.session = session;
		instanceViewContent.load(instance);
		
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session); //should return instanceListPanel not the instanceList only since there're one or more instanceList object in the client-side
		instanceListPanel.session = session;
		instanceListPanel.setNewInstancePanel(newInstancePanel);
		instanceListPanel.getInstanceList().load();
		instanceListPanel.projectInfoLoad();
		instanceListPanel.topicFollowersLoad();

		if("sns".equals(session.getEmployee().getPreferUX())){
			return new Object[]{new Refresh(instanceListPanel)};
		}else{
			return new Object[]{new Remover(new Popup() , true), instanceListPanel, instanceViewContent};
		}
		
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;

	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public Locale localeManager;
}
