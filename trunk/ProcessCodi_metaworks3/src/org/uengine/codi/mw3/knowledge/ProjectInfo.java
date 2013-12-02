
package org.uengine.codi.mw3.knowledge;
import java.io.File;
import java.io.FileInputStream;

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
import org.metaworks.component.SelectBox;
import org.metaworks.component.TreeNode;
import org.metaworks.metadata.MetadataBundle;
import org.metaworks.metadata.MetadataFile;
import org.metaworks.website.Download;
import org.metaworks.website.MetaworksFile;
import org.metaworks.website.OpenBrowser;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.StartCodi;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor;
import org.uengine.codi.mw3.model.GroupInfo;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.project.oce.KtProjectServers;
import org.uengine.codi.mw3.project.oce.ProjectCreate;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.codi.util.CodiFileUtil;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProjectInfo extends GroupInfo implements ContextAware {
	
	public final static String MYSQL_GARUDA_PORT = "3306";
	public final static String MYSQL_PROJECT_PORT = "3307";
	public final static String MYSQL_APP_PORT = "3308";

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
	Boolean check;
		@Face(displayName="$topicSecuopt")
		public Boolean getCheck() {
			return check;
		}
		public void setCheck(Boolean check) {
			this.check = check;
		}
	String svn;
		public String getSvn() {
			return svn;
		}
		public void setSvn(String svn) {
			this.svn = svn;
		}
	
	String projectAlias;
		public String getProjectAlias() {
			return projectAlias;
		}
		public void setProjectAlias(String projectAlias) {
			this.projectAlias = projectAlias;
		}
	
	boolean useIaaS;
		public boolean isUseIaaS() {
			return useIaaS;
		}
		public void setUseIaaS(boolean useIaaS) {
			this.useIaaS = useIaaS;
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
		//this(null);
	}
	public ProjectInfo(String projectId){
		this.setProjectId(projectId);
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public ProjectInfo(Session session){
		this.session = session;
		this.setProjectId(session.getLastSelectedItem());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public void load() throws Exception {
		this.setUseIaaS("1".equals(StartCodi.USE_IAAS));
		followersLoad();
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getProjectId());
		wfNode.copyFrom(wfNode.databaseMe());
		
/*		if(wfNode.getLinkedInstId() != null){
			String linkedId = String.valueOf(wfNode.getLinkedInstId());*/
			
//			Object logo = null;
		MetaworksFile logoFile = new MetaworksFile();
		logoFile.setUploadedPath(wfNode.getUrl());
		logoFile.setFilename(wfNode.getThumbnail());
		setLogoFile(logoFile);
		
		this.projectName = wfNode.getName();
		this.domainName = wfNode.getProjectAlias() + ".oce.com";
		this.setProjectAlias(wfNode.getProjectAlias());
		setType(wfNode.getVisType());
		if("svn".equals(this.getType())){
			this.setHudson(GlobalContext.getPropertyString("vm.hudson.url") + "/job/" + wfNode.getProjectAlias());
			this.setSvn(GlobalContext.getPropertyString("vm.manager.url") + "/" + wfNode.getProjectAlias());
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

	@Face(displayName = "$release")
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_POPUP)
	public ModalWindow releaseProject() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		
		FilepathInfo filepathInfo = new FilepathInfo();
		filepathInfo.setProjectId(this.getProjectId());
		
		SelectBox reflectVersion = new SelectBox();
		reflectVersion = filepathInfo.findReflectVersions();
		
		ReleasePanel releasePanel = new ReleasePanel();
		releasePanel.setProjectId(this.getProjectId());
		
		if("war".equals(this.getType())){
			MetaworksFile sqlFile = new MetaworksFile();
			MetaworksFile warFile = new MetaworksFile();
			
			releasePanel.setMetaworksContext(new MetaworksContext());
			releasePanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			
			releasePanel.setSqlFile(sqlFile);
			releasePanel.setWarFile(warFile);
			releasePanel.setReflectVersion(reflectVersion);
			releasePanel.setCheck(false);
			
			modalWindow.setHeight(360);
			modalWindow.setWidth(540);
		}
		else if("svn".equals(this.getType())){
			releasePanel.setMetaworksContext(new MetaworksContext());
			releasePanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			
			releasePanel.setReflectVersion(reflectVersion);
			modalWindow.setHeight(250);
			modalWindow.setWidth(540);
		}
		
		modalWindow.setTitle("$release");
		modalWindow.setPanel(releasePanel);
		
		return modalWindow;
	}

	@Face(displayName = "$reflect")
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_POPUP)
	public Object distribute() throws Exception{
		
		ModalWindow modalWindow = new ModalWindow();
		
		FilepathInfo filepathInfo = new FilepathInfo();
		filepathInfo.setProjectId(this.getProjectId());
		
		SelectBox serverSelect = new SelectBox();
		
		SelectBox reflectVersion = new SelectBox();
		reflectVersion = filepathInfo.findReflectVersions();
		
		CloudInfo cloudInfo = new CloudInfo();
		
		MetaworksFile sqlFile = new MetaworksFile();

		ReflectPanel reflectPanel = new ReflectPanel();
		reflectPanel.setMetaworksContext(new MetaworksContext());
		reflectPanel.setReflectVersion(reflectVersion);
		reflectPanel.setProjectId(this.getProjectId());
		reflectPanel.setSqlFile(sqlFile);
		
		// IaaS 연동 시
		if("1".equals(StartCodi.USE_IAAS)){
			reflectPanel.getMetaworksContext().setHow("IaaS");
			
			ICloudInfo findListing = cloudInfo.findServerByProjectId(this.getProjectId(), "dev");
			while(findListing.next()){
				serverSelect.add(findListing.getServerName() + " : " + findListing.getServerIp(), String.valueOf(findListing.getId()));
			}
			
			reflectPanel.setServerSelect(serverSelect);
		}
	
		if("war".equals(this.getType())){

			MetaworksFile warFile = new MetaworksFile();
			
			reflectPanel.setWarFile(warFile);
			reflectPanel.setCheck(false);
			reflectPanel.setMetaworksContext(new MetaworksContext());
			reflectPanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			modalWindow.setHeight(370);
			
		}
		else if("svn".equals(this.getType())){

			reflectPanel.setMetaworksContext(new MetaworksContext());
			reflectPanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			modalWindow.setHeight(350);
			
		}
		
		modalWindow.setWidth(500);
		modalWindow.setTitle("$reflect");
		modalWindow.setPanel(reflectPanel);
		
		return modalWindow;
	}

	@Face(displayName = "$hudson")
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_POPUP)
	public Object hudson() {
		IFrame iframe = new IFrame();
		iframe.setSrc(GlobalContext.getPropertyString("vm.hudson.url") + "job/" + this.getProjectAlias());
		iframe.setHeight(1000);
		iframe.setWidth(1000);
		return new ModalWindow(iframe, 0, 0, "$hudson");
	}

	@Face(displayName = "$devmanage")
	@ServiceMethod(payload={"projectName" , "projectId"}, target = ServiceMethodContext.TARGET_APPEND)
	public Object[] server() throws Exception {
		
		ModalWindow modal = new ModalWindow();
		KtProjectServers ProjectServers = new KtProjectServers();
		ProjectServers.setProjectId(this.getProjectId());
		ProjectServers.loadOceServer();
		
		modal.setPanel(ProjectServers);
		modal.setWidth(850);
		modal.setHeight(300);
		modal.setTitle("$devmanage");
		
		return new Object[]{modal};
	}
	
	@Face(displayName = "$metadata")
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_APPEND)
	public ModalWindow metadata() throws Exception {
		ModalWindow modalWindow = new ModalWindow();
		String tenantId = "root";
//		if(TenantContext.getThreadLocalInstance()!=null && TenantContext.getThreadLocalInstance().getTenantId()!=null){
//			tenantId = TenantContext.getThreadLocalInstance().getTenantId();
//		}
		
		String mainPath = MetadataBundle.getProjectBasePath(projectName, tenantId );
		CodiFileUtil.mkdirs(mainPath);

		// OCE 모드에서 codi제거
		Project main = new Project();
		main.setId(projectId);
		main.setPath(mainPath);
		main.load();

		// 아래에서 this 는 위에 project 값

		ResourceNode node = new ResourceNode();
		node.setProjectId(main.getId());
		node.setId(main.getId() + File.separatorChar + Project.METADATA_FILENAME);
		node.setName(Project.METADATA_FILENAME);
		node.setPath(main.getPath() + File.separatorChar + Project.METADATA_FILENAME);
		node.setParentId(main.getId());
		node.setType(TreeNode.TYPE_FILE_METADATA);
		node.setMetaworksContext(getMetaworksContext());
		node.setFolder(false);

		MetadataEditor editor = new MetadataEditor(node);
		editor.loadPage();

		
		modalWindow.setPanel(editor);
		modalWindow.setWidth(800);
		modalWindow.setHeight(700);
		modalWindow.setTitle("$metadata");
		
		return modalWindow;
	}
//	@Face(displayName = "참여")
//	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
//	public Object[] participation() throws Exception {
//		TopicMapping tm = new TopicMapping();
//		tm.setTopicId(session.getLastSelectedItem());
//		tm.setUserId(session.getUser().getUserId());
//
//		if (!tm.findByUser().next()) {
//			tm.setUserName(session.getUser().getName());
//			tm.saveMe();
//			tm.flushDatabaseMe();
//		}
//
//		TopicFollowers topicFollowers = new TopicFollowers();
//		topicFollowers.session = session;
//		topicFollowers.load();
//
//		return new Object[] { new Refresh(topicFollowers) };
//		}
//		
//		@Face(displayName="정보변경")
//		@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
//		public ModalWindow manageProject() throws Exception{
//			
//			ProjectTitle projectTitle = new ProjectTitle();
//			projectTitle.setMetaworksContext(new MetaworksContext());
//			projectTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
//			projectTitle.session = session;
//			WfNode wfNode = new WfNode();
//			wfNode.setId(session.getLastSelectedItem());
//			wfNode.copyFrom(wfNode.databaseMe());
//			projectTitle.setTopicId(this.getProjectId());
//			projectTitle.setTopicTitle(this.getProjectName());
//			projectTitle.setParentId(session.getCompany().getComCode());
//			projectTitle.setFileType(wfNode.getVisType());
//			projectTitle.setTopicDescription(this.getDescription());
//			projectTitle.setLogoFile(this.getLogoFile());
//			
//			XStream xstream = new XStream();
//			if(wfNode.getExt() != null){
//				Object xstreamStr =  xstream.fromXML(wfNode.getExt());
//		//		Object[] fileList = (Object[])GlobalContext.deserialize(wfNode.getEx1(), Object.class);
//				Object sqlFile = null;
//				Object warFile = null;
//				Object logoFile = null;
//				if(xstreamStr != null){
//					Map<String, Object> list = (Map<String,Object>) xstreamStr;
//				
//					warFile = list.get("warFile");
//					sqlFile = list.get("sqlFile");
//					logoFile = list.get("logoFile");
//			}
//				System.out.println(warFile);
//				System.out.println(sqlFile);
//				
//			projectTitle.setLogoFile((MetaworksFile) logoFile);	
//			projectTitle.setWarFile((MetadataFile) warFile);
//			projectTitle.setSqlFile((MetadataFile) sqlFile);
//		}
//		return new ModalWindow(projectTitle, 500, 400, "정보변경");
//	}
	
	@Hidden
	@Face(displayName="$devcommitterManagement") 
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	public Object manageCommitter() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		
		ProjectCommitter projectCommitter = new ProjectCommitter();
		projectCommitter.setProjectAlias(this.getProjectAlias());
		projectCommitter.setProjectId(this.getProjectId());
		projectCommitter.setUserId(session.getUser().getUserId());
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getProjectId());
		wfNode.copyFrom(wfNode.databaseMe());
		projectCommitter.setProjectName(this.getProjectName());
//		projectCommitter.setAccount(session.getUser().getName());
		projectCommitter.setManagerAccount(wfNode.getAuthorId());
		projectCommitter.load();
		modalWindow.setPanel(projectCommitter);
		modalWindow.setWidth(620);
		modalWindow.setHeight(500);
		modalWindow.setTitle("개발권한관리");
		
		return modalWindow;
		
	}
	
	@Face(displayName="$devserverrequest")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] require() throws Exception{
		
		session.setLastPerspecteType(ProjectNode.TYPE_PROJECT);
		session.setLastSelectedItem(this.getProjectId());
		
		instanceViewContent.session = session;
		
		String defId = "projectProcess/projectCre3.process";
		
		ProcessMap processMap = new ProcessMap();
		processMap.processManager = processManager;
		processMap.session = session;
		processMap.instanceView = instanceViewContent;
		processMap.setDefId(defId);
		
		String instId = processMap.initializeProcess();
				
		ProjectCreate projectCreate = new ProjectCreate();
		projectCreate.setProjectId(this.getProjectId());
		projectCreate.setProjectName(this.getProjectName());
		
		
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, processMap.getDefId(), session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		
		processManager.executeProcess(instId);
		processManager.getProcessInstance(instId).setBeanProperty("ProjectCreate", projectCreate);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
		instance.databaseMe().setTopicId(this.getProjectId());
		instance.databaseMe().setName("개발환경요청" + " : " + this.getProjectName());
		instance.flushDatabaseMe();
		
		if( instanceViewContent == null ){
			instanceViewContent = new InstanceViewContent();
		}
		instanceViewContent.session = session;
		instanceViewContent.load(instance);
		
		
		if("oce".equals(session.getUx()) || "oce_project".equals(session.getUx())){
			
			session.setUx("oce_project");
			
			Perspective perspective = new Perspective();
			perspective.session = session;
			
			Object[] returnObject =  perspective.loadInstanceListPanel(ProjectNode.TYPE_PROJECT, getProjectId());
			return new Object[]{new Refresh(returnObject[1])};
		}
		
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session); //should return instanceListPanel not the instanceList only since there're one or more instanceList object in the client-side
		instanceListPanel.session = session;
		instanceListPanel.setNewInstancePanel(newInstancePanel);
		instanceListPanel.getInstanceList().load();
		instanceListPanel.topicFollowersLoad();

		if("sns".equals(session.getEmployee().getPreferUX())){
			return new Object[]{new Remover(new ModalWindow() , true) , new Refresh(instanceListPanel)};
		}else{
			return new Object[]{new Remover(new ModalWindow() , true) , new Remover(new Popup() , true), new Refresh(instanceListPanel), new Refresh(instanceViewContent)};
		}
		
	}
		
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;

	@AutowiredFromClient
	public Locale localeManager;
}
