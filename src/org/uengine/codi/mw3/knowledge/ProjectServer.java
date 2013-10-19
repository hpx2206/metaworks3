package org.uengine.codi.mw3.knowledge;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.codi.deltacloud.DeltaCloud;
import org.uengine.codi.deltacloud.Image;
import org.uengine.codi.hudson.HudsonJobApi;
import org.uengine.codi.hudson.HudsonJobDDTO;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.project.VMHardwareProfileCombo;
import org.uengine.codi.mw3.project.VMImageCombo;
import org.uengine.codi.mw3.project.VMRealmCombo;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(displayName="서버",
	  ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
	  ejsPathForArray="dwr/metaworks/genericfaces/GridFace.ejs",
	  ejsPathMappingByContext = {
			"{how: 'inList', face: 'dwr/metaworks/genericfaces/GridFace_Row.ejs'}",
		},
	  options={"addBtnName", "removeBtnName", "hideEditBtn", "showExtraBtn", "callbackAddBtn", "callbackConfirmBtn", "popupWidth", "gridButtons"},
	  values={"$project.server.add", "$project.server.remove", "true", "true", "add", "confirm", "500", "refresh,statistics"})
public class ProjectServer implements ITool, ContextAware {

	public final static String SERVER_STATUS_REQUEST 		= "Request";
	public final static String SERVER_STATUS_APPROVAL 		= "Approval";
	public final static String SERVER_STATUS_CREATING 		= "Creating";
	public final static String SERVER_STATUS_STOPPED 		= "Stopped";
	public final static String SERVER_STATUS_STARTED			= "Started";
	public final static String SERVER_STATUS_STARTING 		= "Starting";
	public final static String SERVER_STATUS_STOPPING		= "Stopping";
	public final static String SERVER_STATUS_RUNNING 		= "Running";
	public final static String SERVER_STATUS_DEPLOYING 		= "Deploying";
	
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

	@AutowiredFromClient
	transient public ProjectInfo projectInfo;
	
	String instId;
		@Id
		@Hidden
		public String getInstId() {
			return instId;
		}
		public void setInstId(String instId) {
			this.instId = instId;
		}

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

	String group;
		@Face(displayName="$project.server.group")
		@Range(options={"개발", "운영"}, values={"dev", "prod"})
		//@Available(when={MetaworksContext.WHEN_EDIT, MetaworksContext.WHEN_NEW})
		@Hidden
		public String getGroup() {
			return group;
		}
		public void setGroup(String group) {
			this.group = group;
		}

	String vmRealmId;
		@Hidden
		public String getVmRealmId() {
			return vmRealmId;
		}
		public void setVmRealmId(String vmRealmId) {
			this.vmRealmId = vmRealmId;
		}
	
	String vmImageId;
		@Hidden
		public String getVmImageId() {
			return vmImageId;
		}
		public void setVmImageId(String vmImageId) {
			this.vmImageId = vmImageId;
		}
	
	String vmHardwareProfileId;
		@Hidden
		public String getVmHardwareProfileId() {
			return vmHardwareProfileId;
		}
		public void setVmHardwareProfileId(String vmHardwareProfileId) {
			this.vmHardwareProfileId = vmHardwareProfileId;
		}
		
	transient VMRealmCombo vmRealmCombo;
		@Available(when=MetaworksContext.WHEN_NEW)
		@Face(displayName="망")
		public VMRealmCombo getVmRealmCombo() {
			return vmRealmCombo;
		}
		public void setVmRealmCombo(VMRealmCombo vmRealmCombo) {
			this.vmRealmCombo = vmRealmCombo;
		}
	
	transient VMImageCombo vmImageCombo;
		@Available(when=MetaworksContext.WHEN_NEW)
		@Face(displayName="템플릿")
		public VMImageCombo getVmImageCombo() {
			return vmImageCombo;
		}
		public void setVmImageCombo(VMImageCombo vmImageCombo) {
			this.vmImageCombo = vmImageCombo;
		}
	
	transient VMHardwareProfileCombo vmHardwareProfileCombo;
		@Available(when=MetaworksContext.WHEN_NEW)
		@Face(displayName="스펙")
		public VMHardwareProfileCombo getVmHardwareProfileCombo() {
			return vmHardwareProfileCombo;
		}
		public void setVmHardwareProfileCombo(VMHardwareProfileCombo vmHardwareProfileCombo) {
			this.vmHardwareProfileCombo = vmHardwareProfileCombo;
		}
		
	@Hidden
	@ServiceMethod(callByContent=true, eventBinding="change", bindingFor="vmRealmCombo", bindingHidden=true)
	public void changeVMRealmCombo(){
		this.setVmImageCombo(new VMImageCombo(this.getVmRealmCombo().getSelected(), null));
	}
		
	@Hidden
	@Face(displayName="$project.server.add")	
	@ServiceMethod	
	public void add(){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		// TODO: KIAT 에서 활성화
		this.setVmRealmCombo(new VMRealmCombo(null));
		this.setVmImageCombo(new VMImageCombo(this.getVmRealmCombo().getSelected(), null));
		this.setVmHardwareProfileCombo(new VMHardwareProfileCombo(null));
	}
		
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_NONE)	
	public ProjectServer confirm(){
		
		String type = null;
		
		//TODO: KIAT 에서 활성화
		try {
			DeltaCloud deltaCloud = new DeltaCloud();
			
			// realm case
			ArrayList<Image> images = deltaCloud.images();
			
			for(int i=0;i<images.size();i++){
				Image image = images.get(i);
				
				if(image.getId().equals(this.getVmImageCombo().getSelected())){
					type = image.getDescription().split("_")[3];
					type = type.replace('|', ',');
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setVmRealmId(this.getVmRealmCombo().getSelected());
		this.setVmImageId(this.getVmImageCombo().getSelected());
		this.setVmHardwareProfileId(this.getVmHardwareProfileCombo().getSelected());
		
		this.setStatus(SERVER_STATUS_REQUEST);
		this.setIp("Not Allocated");
		this.setType(type);
		this.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);

		
		try {
			// initialize process		
			String defId = "CreateProject.process";
			String instId = processManager.initializeProcess(defId);

			RoleMapping rm = RoleMapping.create();
			if(session.getUser() != null){
				rm.setName("Initiator");
				rm.setEndpoint(session.getUser().getUserId());
				
				processManager.putRoleMapping(instId, rm);
			}
			processManager.setLoggedRoleMapping(rm);
				
					
			ResultPayload rp = new ResultPayload();
			rp.setProcessVariableChange(new KeyedParameter("ProjectServer", this));
			
			RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, defId, session);
			roleMappingPanel.putRoleMappings(processManager, instId);
			
			processManager.executeProcessByWorkitem(instId.toString(), rp);
			processManager.applyChanges();
			
			Instance instance = new Instance();
			instance.setInstId(new Long(instId));
			
			IInstance instanceRef = instance.databaseMe();
			instanceRef.setTopicId(projectInfo.getProjectId());
			instanceRef.setName(instanceRef.getDefName() + " : " + this.getName());
			instanceRef.setLastCmnt(instanceRef.getName());
			instanceRef.setLastCmntUser(session.getUser());
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
/*		// TODO : TEST
		this.setStatus(SERVER_STATUS_STOPPED);
		this.getMetaworksContext().setWhen(this.getStatus());
*/		
		return this;
	}
	
	@Available(when={SERVER_STATUS_STOPPED})
	@Face(displayName="$project.server.start")
	@ServiceMethod(callByContent=true)
	public void start() throws Exception {
		this.setStatus(SERVER_STATUS_STARTING);
		this.getMetaworksContext().setWhen(this.getStatus());
		
		String targetUserId = GlobalContext.getPropertyString("vm.target.user");
		String targetPassword= GlobalContext.getPropertyString("vm.target.password");

		String scriptStart = "service jboss -b " + this.getIp() + " > /dev/null 2>&1 &";
		
		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
		
		jschServerBehaviour.runCommand(scriptStart);
		
		if(jschServerBehaviour.getJschSession() != null)
			jschServerBehaviour.getJschSession().disconnect();
	}
	
	@Available(when={SERVER_STATUS_RUNNING})
	@Face(displayName="$project.server.stop")
	@ServiceMethod(callByContent=true)
	public void stop() throws Exception {
		this.setStatus(SERVER_STATUS_STOPPED);
		this.getMetaworksContext().setWhen(this.getStatus());
		
		String targetUserId = GlobalContext.getPropertyString("vm.target.user");
		String targetPassword= GlobalContext.getPropertyString("vm.target.password");
		
		String scriptStop = "service jboss-cli --connect command=:shutdown";
		
		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
		
		jschServerBehaviour.runCommand(scriptStop);
		
		if(jschServerBehaviour.getJschSession() != null)
			jschServerBehaviour.getJschSession().disconnect();
		
	}

	@Available(when={SERVER_STATUS_STOPPED})
	@Face(displayName="$project.server.deploy")
	@ServiceMethod(callByContent=true)
	public void deploy() throws Exception {
		deploy(false);
	}
	
	public void deploy(boolean showLog) throws Exception {
		this.setStatus(SERVER_STATUS_STOPPED);
		this.getMetaworksContext().setWhen(this.getStatus());	
		
		String host = GlobalContext.getPropertyString("vm.manager.ip");
		String userId = GlobalContext.getPropertyString("vm.manager.user");
		String passwd = GlobalContext.getPropertyString("vm.manager.password");

		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin(host, userId, passwd);
		
		// create Hudson job
		String scriptCreateJob = GlobalContext.getPropertyString("vm.hudson.createJob"); 
		String scriptHudsonSetting = GlobalContext.getPropertyString("vm.hudson.setting");
		String scriptHudsonBuild = GlobalContext.getPropertyString("vm.hudson.build");
		
		String paramProjectName = "\"" + projectInfo.getProjectName() + "\"";
		String paramIP = "\"" + this.getIp() + "\"";
		
		String hudsonURL = GlobalContext.getPropertyString("vm.hudson.url");
		String nextBuilderNumber = null;
		String builderResult = null;
		
		long timeoutTime = 200000;
		long sleepTime = 5000;
		long tryTime = 0;
		
		
		jschServerBehaviour.runCommand(scriptCreateJob + " " + paramProjectName);
		jschServerBehaviour.runCommand(scriptHudsonSetting + " " +paramProjectName + " " + paramIP + " " + "\"\\/ssw\\/data\\/jboss\\/standalone\\/deployments\"");

		if(showLog){
			ModalWindow modal = new ModalWindow(new IFrame("http://192.168.212.76:9090/hudson/job/"+ projectInfo.getProjectName()), 0, 0, "배포");
			modal.setId("deploy");
		
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{modal});
		}
		
		HudsonJobApi hudsonJobApi = new HudsonJobApi();

		while(nextBuilderNumber == null){
			HudsonJobDDTO hudsonJobDDTO = hudsonJobApi.hudsonJobApiXmlParser(hudsonURL, projectInfo.getProjectName());
			
			nextBuilderNumber = hudsonJobDDTO.getNextBuilderNumber();
			try {
				tryTime += sleepTime;
				Thread.sleep(sleepTime);
			} catch (Exception e) {
			}
			
			if(tryTime > timeoutTime)
				break;
		}
		
		// build hudson
		jschServerBehaviour.runCommand(scriptHudsonBuild + " " + paramProjectName);

		if(showLog)
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new Refresh(new IFrame("http://192.168.212.76:9090/hudson/job/" + projectInfo.getProjectName() + "/" + nextBuilderNumber + "/console"))});

		while(builderResult == null){
			HudsonJobDDTO hudsonJobDDTO = hudsonJobApi.hudsonJobApiXmlParser(hudsonURL, projectInfo.getProjectName());
			
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
		
		if(showLog){
			ModalWindow modal = new ModalWindow();
			modal.setId("deploy");
			
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new Remover(modal)});
		}
		
		if(jschServerBehaviour.getJschSession() != null)
			jschServerBehaviour.getJschSession().disconnect();
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
		ProjectServers projectServers = new ProjectServers(projectInfo.getProjectId(), this.getGroup());
		projectServers.processManager = processManager;
		projectServers.load();		
		
		return projectServers;
	}
	
	public void status() throws Exception {
		
		if("DB".equals(this.getType())){
			this.setStatus(SERVER_STATUS_RUNNING);
			
		}else 		if(!"Not Allocated".equals(this.getIp())){
			String targetUserId = GlobalContext.getPropertyString("vm.target.user");
			String targetPassword= GlobalContext.getPropertyString("vm.target.password");
			
			String scriptStatus = "netstat -an | grep 8080|grep -v grep |grep LISTEN|wc -l";
			String scriptStarting = "ps -ef|grep service|grep jboss|grep -v grep|wc -l";
					
			JschCommand jschServerBehaviour = new JschCommand();
			jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
			
			String starting = jschServerBehaviour.runCommand(scriptStarting);
			String status = jschServerBehaviour.runCommand(scriptStatus);
			
			if("1".equals(starting)){
				if("1".equals(status)){
					this.setStatus(SERVER_STATUS_RUNNING);					
				}else{
					this.setStatus(SERVER_STATUS_STARTING);
				}
			}else{
				this.setStatus(SERVER_STATUS_STOPPED);
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
