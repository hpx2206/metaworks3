
package org.uengine.codi.mw3.knowledge;
import java.io.FileInputStream;
import java.util.ArrayList;
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
import org.metaworks.website.Download;
import org.metaworks.website.MetaworksFile;
import org.metaworks.website.OpenBrowser;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TopicFollowers;
import org.uengine.codi.mw3.project.ProjectCreate;
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

	String svn;
		@Face(displayName="$project.svn")
		public String getSvn() {
			return svn;
		}
		public void setSvn(String svn) {
			this.svn = svn;
		}
			
	String war;
		@Face(displayName="$project.war")
		public String getWar() {
			return war;
		}
	
		public void setWar(String war) {
			this.war = war;
		}
	String sql;
		public String getSql() {
			return sql;
		}
	
		public void setSql(String sql) {
			this.sql = sql;
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
		this.setLogoFile(new MetaworksFile());
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
			
			Object logo = null;
			this.projectName = wfNode.getName();
			this.domainName = this.getProjectName() + ".com";			
			setType(wfNode.getVisType());
			if("svn".equals(this.getType())){
				this.svn = GlobalContext.getPropertyString("vm.manager.url") + "/" + this.getProjectName(); 
				setType("svn");
			}else if("war".equals(this.getType())){
				
				Object warUrl = null;
				Object sqlUrl = null;
				XStream xstream = new XStream();
				if(wfNode.getExt() != null){
					Object xstreamStr =  xstream.fromXML(wfNode.getExt());
					if(xstreamStr != null){
						Map<String, Object> list = (Map<String,Object>) xstreamStr;
					
						warUrl = list.get("warFile_Url");
						sqlUrl = list.get("sqlFile_Url");
						logo = list.get("logoFile");
					}
				}
						this.war = (String) warUrl;
						this.sql = (String) sqlUrl;
						setType("war");
			}
			this.description = wfNode.getDescription();
			this.logoFile = (MetaworksFile) logo;
			
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
	public void releaseProject() {

	}

	@Face(displayName = "허드슨")
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public void hudson() {

	}

	@Face(displayName = "권한관리")
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public void committer() {

	}

	@Face(displayName = "서버관리")
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_APPEND)
	public Object[] server() throws Exception {
		
		ModalWindow modal = new ModalWindow();
		
		ProjectServers ProjectServers = new ProjectServers();
		ProjectServers.loadOceServer(this.getProjectName());
		
		modal.setPanel(ProjectServers);
		modal.setWidth(600);
		modal.setHeight(500);
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
//			projectTitle.setLogoFile(wfNode.getLogoFile());
			
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
			projectTitle.setWarFile((MetaworksFile) warFile);
			projectTitle.setSqlFile((MetaworksFile) sqlFile);
		}
		return new ModalWindow(projectTitle, 500, 400, "정보변경");
	}
	
	@Face(displayName="개발환경요청")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] require() throws Exception{
		
		String defId = "projectProcess/oceProjectRequset.process";
		
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
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session); //should return instanceListPanel not the instanceList only since there're one or more instanceList object in the client-side
		instanceListPanel.getInstanceList().load();

		if("sns".equals(session.getEmployee().getPreferUX())){
			return new Object[]{instanceListPanel, new Remover(new Popup())};
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
}
