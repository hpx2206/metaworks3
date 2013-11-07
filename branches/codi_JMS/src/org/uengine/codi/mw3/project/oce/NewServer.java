package org.uengine.codi.mw3.project.oce;

import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dao.UniqueKeyGenerator;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;
import org.uengine.codi.mw3.knowledge.ProjectTitle;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(displayName="$project.server.add", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class NewServer extends Templete{
	
	@AutowiredFromClient
	transient public KtProjectServers ktProjectServers;
	
	@Autowired
	transient public ProcessManagerRemote processManager;

	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@AutowiredFromClient
	transient public Session session;
	
	
	String projectId;
	@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		
	String projectName;
	@Available(when={MetaworksContext.WHEN_EDIT})	
	@Face(displayName="$project.server.name")	
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		String serviceTemplete;
		@Face(displayName="$project.server.serviceTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"KT_KOR-Central A"}, values={"KT_KOR-Central A"})
			public String getServiceTemplete() {
				return serviceTemplete;
			}
			public void setServiceTemplete(String serviceTemplete) {
				this.serviceTemplete = serviceTemplete;
			}
			
		String osTemplete;
		@Face(displayName="$project.server.osTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", 
				options={"Centos 6.3 64bit_jboss",
							"Centos 6.3 64bit_cubrid",
							"Centos 6.3 64bit_jboss_cubird",
							"Ubuntu 12.04 64bit_jboss",
							"Ubuntu 12.04 64bit_cubrid",
							"Ubuntu 12.04 64bit_jboss_cubird",
							"WIN 2008 R2 64bit [Korean]_jboss",
							"WIN 2008 R2 64bit [Korean]-cubrid",
							"WIN 2008 R2 64bit [Korean]_jboss_cubird"}, 
				values={"Centos 6.3 64bit_jboss",
							"Centos 6.3 64bit_cubrid",
							"Centos 6.3 64bit_jboss_cubird",
							"Ubuntu 12.04 64bit_jboss",
							"Ubuntu 12.04 64bit_cubrid",
							"Ubuntu 12.04 64bit_jboss_cubird",
							"WIN 2008 R2 64bit [Korean]_jboss",
							"WIN 2008 R2 64bit [Korean]-cubrid",
							"WIN 2008 R2 64bit [Korean]_jboss_cubird"})
			public String getOsTemplete() {
				return osTemplete;
			}
			public void setOsTemplete(String osTemplete) {
				this.osTemplete = osTemplete;
			}

		String hwTemplete;
		@Face(displayName="$project.server.hwTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", 
				options={"1 vCore	1 GB	100GB",
							"1 vCore	2 GB	100GB",
							"2 vCore	2 GB	100GB",
							"2 vCore	4 GB	100GB",
							"4 vCore	4 GB	100GB",
							"4 vCore	8 GB	100GB",
							"8 vCore	8 GB	100GB",
							"8 vCore	16 GB	100GB",
							"12 vCore	16 GB	100GB"}, 
				values={"1 vCore	1 GB	100GB",
							"1 vCore	2 GB	100GB",
							"2 vCore	2 GB	100GB",
							"2 vCore	4 GB	100GB",
							"4 vCore	4 GB	100GB",
							"4 vCore	8 GB	100GB",
							"8 vCore	8 GB	100GB",
							"8 vCore	16 GB	100GB",
							"12 vCore	16 GB	100GB"})
			public String getHwTemplete() {
				return hwTemplete;
			}
			public void setHwTemplete(String hwTemplete) {
				this.hwTemplete = hwTemplete;
			}
	
	ProjectTitle projectTitle;
		@Hidden	
		public ProjectTitle getProjectTitle() {
			return projectTitle;
		}
		public void setProjectTitle(ProjectTitle projectTitle) {
			this.projectTitle = projectTitle;
		}
		
	App app;
		@Hidden	
		public App getApp() {
			return app;
		}
		public void setApp(App app) {
			this.app = app;
		}

	String serverGroup;
		@Hidden
		public String getServerGroup() {
			return serverGroup;
		}
		public void setServerGroup(String serverGroup) {
			this.serverGroup = serverGroup;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
		
	@Face(displayName="$Create")	
	@Available(how={"appCreate"} , when={MetaworksContext.WHEN_NEW})
	@ServiceMethod(callByContent=true)
	public Object[] appfinish() throws Exception {
		
		app.processManager = processManager;
		app.session = session;
		app.instanceView = instanceViewContent;
		
		CloudInfo cloudInfo = new CloudInfo();
		cloudInfo.setId(cloudInfo.createNewId());
		cloudInfo.setProjectId(app.getAttachProject().getSelected());
		cloudInfo.setServerName(app.getAppName());
		cloudInfo.setOsTemplete(this.getOsTemplete());
		cloudInfo.setHwTemplete(this.getHwTemplete());
		cloudInfo.setServiceTemplete(this.getServiceTemplete());
		cloudInfo.setStatus(ProjectServer.SERVER_STATUS_STARTING);
		cloudInfo.setModdate(new Date());
		
		cloudInfo.setServerGroup(serverGroup);
		cloudInfo.createDatabaseMe();
		
		cloudInfo.flushDatabaseMe();
		
		return new Object[]{app.save()};
	}
	@Face(displayName="$Create")	
	@Available(how={"projectCreate"} , when={MetaworksContext.WHEN_NEW})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] finalfinish() throws Exception {
		projectTitle.session = session;
		projectTitle.saveMe();
		
		this.setProjectId(projectTitle.getTopicId());
		this.setProjectName(projectTitle.getTopicTitle());
		createServerRequset();
		
		return projectTitle.save();
	}
	
	@Available(when={MetaworksContext.WHEN_EDIT})	
	@Face(displayName="$Create")	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] finish() throws Exception {
		createServerRequset();
		ktProjectServers.loadOceServer();
		return new Object[]{new Remover(new ModalWindow()),  new Refresh(ktProjectServers) };
	}
	
	public void createServerRequset() throws Exception{
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
		projectCreate.setOsTemplete(this.getOsTemplete());
		projectCreate.setHwTemplete(this.getHwTemplete());
		projectCreate.setServiceTemplete(this.getServiceTemplete());
		
		ResultPayload rp = new ResultPayload();
		rp.setProcessVariableChange(new KeyedParameter("ProjectCreate", projectCreate));
		
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, processMap.getDefId(), session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		
		processManager.executeProcess(instId);
//			processManager.getProcessInstance(instId).setBeanProperty("ProjectCreate", projectCreate);
		processManager.executeProcessByWorkitem(instId.toString(), rp);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
		instance.databaseMe().setTopicId(this.getProjectId());
		instance.databaseMe().setName("개발환경요청" + " : " + this.getProjectName());
		instance.flushDatabaseMe();
	}

	@Face(displayName="$Cancel")	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());
	}
}
