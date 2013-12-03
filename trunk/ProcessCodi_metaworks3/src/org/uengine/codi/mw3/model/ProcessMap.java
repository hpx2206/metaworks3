package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.DAOUtil;
import org.metaworks.dao.Database;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.website.MetaworksFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.CodiProcessDefinitionFactory;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.knowledge.KnowledgeTool;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.kernel.EJBProcessInstance;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessMap extends Database<IProcessMap> implements IProcessMap {
	
	public final static String PROCESS = "process";
	
	public ProcessMap(){
		setIconFile(new MetaworksFile());		
		setIconColor(new ProcessMapColor("배경선택"));
	}

	@Autowired
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public Session session;

	@AutowiredFromClient
	public NewInstancePanel newInstancePanel;

	@AutowiredFromClient
	public ProcessMapList processMapList;

	String mapId;
		public String getMapId() {
			return mapId;
		}
		public void setMapId(String mapId) {
			this.mapId = mapId;
		}

	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	int no;
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}

	String comCode;
			
		public String getComCode() {
			return comCode;
		}
		public void setComCode(String comCode) {
			this.comCode = comCode;
		}

	MetaworksFile iconFile;	
		public MetaworksFile getIconFile() {
			return iconFile;
		}
		public void setIconFile(MetaworksFile iconFile) {
			this.iconFile = iconFile;
		}

	ProcessMapColor iconColor;	
		public ProcessMapColor getIconColor() {
			return iconColor;
		}
		public void setIconColor(ProcessMapColor iconColor) {
			this.iconColor = iconColor;
		}
		
	RoleMappingPanel roleMappingPanel;
		public RoleMappingPanel getRoleMappingPanel() {
			return roleMappingPanel;
		}
		public void setRoleMappingPanel(RoleMappingPanel roleMappingPanel) {
			this.roleMappingPanel = roleMappingPanel;
		}

	String cmPhrase;
		public String getCmPhrase() {
			return cmPhrase;
		}
		public void setCmPhrase(String cmPhrase) {
			this.cmPhrase = cmPhrase;
		}
		
	String cmTrgr;	
		public String getCmTrgr() {
			return cmTrgr;
		}
		public void setCmTrgr(String cmTrgr) {
			this.cmTrgr = cmTrgr;
		}

	public void createRoleDef() throws Exception {
		org.uengine.kernel.ProcessDefinition definition = processManager.getProcessDefinition(defId);
		for(org.uengine.kernel.Role role : definition.getRoles()){
			RoleMappingDefinition roleMappingDefinition = new RoleMappingDefinition();
			roleMappingDefinition.setRoleDefId(TenantContext.getThreadLocalInstance().getTenantId() + "." + defId + "." + role.getName());
			
			if( "Initiator".equals(role.getName())){
				continue;
			}
			User user = new User();
			user.setUserId(role.getName());
			roleMappingDefinition.setDefId(defId);
			roleMappingDefinition.setRoleName(role.getName());
			roleMappingDefinition.setComCode(TenantContext.getThreadLocalInstance().getTenantId());
			roleMappingDefinition.setMappedRoleCode(role.getName());
			roleMappingDefinition.setRoleDefType(RoleMappingDefinition.ROLE_DEF_TYPE_ROLE);
			
			roleMappingDefinition.createDatabaseMe();
		}
	}
	public void createMe() throws Exception {
		createDatabaseMe();
		flushDatabaseMe();
	}
	
	public Object[] save() throws Exception {
		this.saveMe();
		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load(session);
		
		if("run".equals(this.getMetaworksContext().getHow())){
			
			return this.initiate(); //new Object[]{new Remover(new Popup()), new Refresh(processMapList)};
		}else
			return new Object[]{new Remover(new Popup()), new Refresh(processMapList)};
	}
	
	public Object[] remove() throws Exception {
		deleteDatabaseMe();
		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load(session);

		return new Object[]{processMapList, new Remover(new Popup())};
	}
	
	public void saveMe() throws Exception {
		if(getIconFile().getDeletedPath() != null)
			getIconFile().remove();
		
		if(getIconFile().getFileTransfer() != null && !getIconFile().getFileTransfer().getFilename().isEmpty())
			getIconFile().upload();
		else
			getIconFile().setUploadedPath(null);
		
		
		setComCode(session.getCompany().getComCode());
		
		getRoleMappingPanel().save();
		
		syncToDatabaseMe();
		flushDatabaseMe();
	}

	public Popup modify() throws Exception {
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		this.setRoleMappingPanel(new RoleMappingPanel(processManager, this.getDefId(), session));
		
		Popup popup = new Popup(580, 500);
		popup.setPanel(this);
		popup.setName("프로세스 정보");
		
		if("run".equals(this.getMetaworksContext().getHow())){
			popup.setHeight(210);
			popup.setName("담당자 지정");
		}
		
		return popup;		
	}

	public Remover close() throws Exception {		
		return new Remover(new Popup(this));
	}
		
	public boolean confirmExist() {
		try{
			databaseMe();
		}catch(Exception e){
			return true;
		}
		
		return false;
	}
	
	public static IProcessMap loadList(Session session) throws Exception {
		
		DAOUtil daoUtil = new DAOUtil();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM processMap where comCode=?comCode");
		sb.append(" ORDER BY " + daoUtil.replaceReservedKeyword("no"));

		IProcessMap processMap = (IProcessMap)sql(ProcessMap.class, sb.toString());
		processMap.setComCode(session.getCompany().getComCode());
		processMap.select();
		
		return processMap;
		
	}
	
	@Autowired
	public InstanceViewContent instanceView;
	

	public String initializeProcess() throws Exception {
		
		// 프로세스 케쉬 초기화
		ProcessManagerBean pmb = (ProcessManagerBean)processManager;
		CodiProcessDefinitionFactory.getInstance(pmb.getTransactionContext()).removeFromCache(this.getDefId());
				
		// process 초기화 (instId 생성)
		String instId = processManager.initializeProcess(this.getDefId());

		RoleMapping rm = RoleMapping.create();
		if(session.getUser() != null){
			rm.setName("Initiator");
			rm.setEndpoint(session.getUser().getUserId());
			
			processManager.putRoleMapping(instId, rm);
		}
		
		processManager.setLoggedRoleMapping(rm);

		return instId;
	}	
	
	public void executeProcess(String instId) throws Exception {
		processManager.executeProcess(instId);
		processManager.applyChanges();
	}
	
	public Object[] initiate() throws Exception{
		// InstanceViewContent instanceView// = new InstanceViewContent();
		// 프로세스 실행
		String instId = this.initializeProcess();
		
		String title = null;		
		if(newInstancePanel!=null && newInstancePanel.getNewInstantiator().getTitle()!=null)
			title =  newInstancePanel.getNewInstantiator().getTitle() + (this.getName() != null ? "-" + this.getName() : "");
		
		Instance instanceRef = new Instance();
		instanceRef.setInstId(new Long(instId));
		
		if(newInstancePanel!=null){
//			instanceRef.databaseMe().setSecuopt("" + newInstancePanel.getSecurityLevel());
			
			if(newInstancePanel.getKnowledgeNodeId() != null){
				processManager.executeProcess(instId);
				processManager.applyChanges();

				InstanceViewContent rootInstanceView = instanceView;// = new InstanceViewContent();
				rootInstanceView.session = session;
				rootInstanceView.load(instanceRef);
				

				WfNode parent = new WfNode();
				parent.load(newInstancePanel.getKnowledgeNodeId());

				instanceView.instanceView.instanceNameChanger.setInstanceName(parent.getName());
				instanceView.instanceView.instanceNameChanger.change();

			
//				WfNode child = new WfNode();		
//				child.setName(instanceView.instanceName);
//				child.setLinkedInstId(Long.parseLong(instId));
//				parent.addChildNode(child);
//				child.createMe();
				
				parent.setLinkedInstId(Long.parseLong(instId));
				parent.save();
				
				//setting the first workItem as wfNode referencer
				GenericWorkItem genericWI = new GenericWorkItem();
				
				genericWI.processManager = processManager;
				genericWI.session = session;
				genericWI.setTitle("지식조각을 첨부합니다");//parent.getName());
				GenericWorkItemHandler genericWIH = new GenericWorkItemHandler();
				KnowledgeTool knolTool = new KnowledgeTool();
				knolTool.setNodeId(parent.getId());
				genericWIH.setTool(knolTool);
				
				genericWI.setGenericWorkItemHandler(genericWIH);
				genericWI.setInstId(new Long(instId));
				genericWI.add();
				
				WorkItem newItem = new CommentWorkItem();
				newItem.setInstId(new Long(instanceView.getInstanceView().getInstanceId()));
				newItem.setTaskId(new Long(instanceView.getInstanceView().getInstanceId()));
				newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				
				instanceView.getInstanceView().setNewItem(newItem);
								
				instanceRef.databaseMe().setSecuopt("" + newInstancePanel.getSecurityLevel());
				instanceRef.flushDatabaseMe();
	
				return new Object[]{instanceView, parent};
	
			}
			
			
		}else if(processMapList!=null && processMapList.getParentInstanceId() != null){ //need to attach new instance to the parent instance
			ProcessInstance instanceObject = processManager.getProcessInstance(instId);
			
			//IInstance instance = instanceRef.databaseMe();
		
			EJBProcessInstance ejbParentInstance = (EJBProcessInstance)instanceObject;
			ejbParentInstance.getProcessInstanceDAO().setRootInstId(new Long(processMapList.getParentInstanceId()));
			
			Instance rootInstanceRef = new Instance();
			rootInstanceRef.setInstId(processMapList.getParentInstanceId());
			
			roleMappingPanel = new RoleMappingPanel(processManager, this.getDefId(), session);
			roleMappingPanel.putRoleMappings(processManager, instId);
			processManager.executeProcess(instId);
			
			String[] executedTaskIds = WorkItemHandler.executedActivityTaskIds(instanceObject);
			
			processManager.applyChanges();

			if("sns".equals(session.getEmployee().getPreferUX())){
				InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
				panel.getMetaworksContext().setHow("instanceList");
				panel.getMetaworksContext().setWhere("sns");
				panel.session = session;
				panel.load(processMapList.getParentInstanceId().toString());
				return new Object[]{panel, new Remover(new Popup() , true)};
			}else{
				/*
				 * 2013/12/03 cjw
				 * 서브로 프로세스 실행시에 실행된 워크아이템만 부분 갱신되게 수정
				 */
				InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
				instanceViewThreadPanel.setInstanceId(instId);

				ArrayList<WorkItem> newlyAddedWorkItems = new ArrayList<WorkItem>();
				
				for(String taskId : executedTaskIds){
					WorkItem newlyAppendedWorkItem = new WorkItem();
					newlyAppendedWorkItem.setTaskId(new Long(taskId));
					newlyAppendedWorkItem.copyFrom(newlyAppendedWorkItem.databaseMe());
					
					newlyAddedWorkItems.add(newlyAppendedWorkItem);
				}
				
				return new Object[]{new ToEvent(ServiceMethodContext.TARGET_OPENER, EventContext.EVENT_CLOSE), new ToAppend(instanceViewThreadPanel, newlyAddedWorkItems)};
				//instanceView.load(rootInstanceRef);
				//InstanceViewContent rootInstanceView = instanceView;// = new InstanceViewContent();
				//rootInstanceView.load(rootInstanceRef);
				//return new Object[]{new Remover(new Popup() , true), new Refresh(instanceView)};
			}
		}
		
		
		
		//set the role mappings the administrator set.
		
		roleMappingPanel = new RoleMappingPanel(processManager, this.getDefId(), session);
		ArrayList<IRoleMappingDefinition> roleDefList = roleMappingPanel.getRoleMappingDefinitions();
		for(IRoleMappingDefinition roleDef : roleDefList){
			if( "Initiator".equals(roleDef.getRoleName())){
				continue;
			}
			if( roleDef.getMappedUser().getUserId() == null ){
				this.getMetaworksContext().setHow("run");
				
				return new Object[]{this.modify()};
				/*
				// user가 매핑이 안되어 있다면 유저를 매핑하라고 메시지를 띄우고 프로세스가 진행이 안됨
				String systemAdmin = GlobalContext.getPropertyString("codi.user.id");
				IEmployee correntUser = session.getEmployee();
				Employee emp = new Employee();
				emp.copyFrom(correntUser);
				IEmployee adminUser = emp.findCompanyAdmin();
				if( adminUser != null ){
					int i =0;
					while(adminUser.next() ){
						if( i == 0 ){
							systemAdmin = adminUser.getEmpName();
						}else{
							systemAdmin += " , " + adminUser.getEmpName();
						}
						i++;
					}
				}
				StringBuffer buffer = new StringBuffer();
				buffer.append("<b>현재 프로세스에 역할자가 매핑이 되어 있지 않습니다.<br/>");
				buffer.append(" <span style='color:blue'>" + systemAdmin + "</span> (관리자) 에게 문의하여 주세요.</b> <br/><br/>");
				buffer.append(" <span style='color:blue'><b>* 매핑 가이드 </b></span><br/>");
				buffer.append(" 관리자 Login > 프로세스앱 마우스 오버 > 톱니바퀴 클릭 > 친구선택     ");
				 
				
				ErrorPage message = new ErrorPage();
				message.setErrorMessage(buffer.toString());
				
				ModalWindow errorMessage = new ModalWindow();
				errorMessage.setTitle("");
				errorMessage.setWidth(450);
				errorMessage.setHeight(200);
				errorMessage.setPanel(message);
				
				return new Object[]{errorMessage};
				
//				throw new MetaworksException("역할 매핑이 안되어 있어요. 관리자에게 문의해 주세요.");
				*/
			}
		}
		
		
		
		roleMappingPanel.putRoleMappings(processManager, instId);
		processManager.executeProcess(instId);
		processManager.applyChanges();
		//end
		
		if( session != null && session.getEmployee() != null ){
			((Instance)instanceRef).databaseMe().setInitiator(session.user);
			((Instance)instanceRef).databaseMe().setInitComCd(session.getEmployee().getGlobalCom());
		}
		if( session != null && session.getLastPerspecteType().equalsIgnoreCase("topic")){
			((Instance)instanceRef).databaseMe().setTopicId(session.getLastSelectedItem());
		}
		if(newInstancePanel!=null){
			((Instance)instanceRef).databaseMe().setSecuopt("" + newInstancePanel.getSecurityLevel().getSelected());
			//((Instance)instanceRef).flushDatabaseMe();
		}
		if(newInstancePanel.dueDate != null){
			((Instance)instanceRef).databaseMe().setDueDate(newInstancePanel.getDueDate());
		}
		((Instance)instanceRef).flushDatabaseMe();

		if("sns".equals(session.getEmployee().getPreferUX())){
			InstanceListPanel instanceListPanel = new InstanceListPanel(session); //should return instanceListPanel not the instanceList only since there're one or more instanceList object in the client-side
			instanceListPanel.getInstanceList().load();

			return new Object[]{new Refresh(instanceListPanel), new Remover(new Popup())};
		}else{
			instanceView.session = session;
			instanceView.load(instanceRef);

			if(processMapList!=null && processMapList.getTitle()!=null){
				instanceView.getInstanceView().getInstanceNameChanger().setInstanceName(title);
				instanceView.getInstanceView().getInstanceNameChanger().change();
				instanceView.getInstanceView().setInstanceName(title);
			}
			
			IInstance copyOfInstance = ((Instance)instanceRef).databaseMe();
			copyOfInstance.getMetaworksContext().setWhen("blinking");
			
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new InstanceListener(copyOfInstance)});
			return new Object[]{new ToEvent(ServiceMethodContext.TARGET_OPENER, EventContext.EVENT_CLOSE), new Refresh(instanceView)};
		}

		
	}
	
	@ServiceMethod
	public Object[] processListFilter() throws Exception{
		
		Perspective perspective = new Perspective();
		
		return perspective.loadInstanceListPanel(session, PROCESS, this.getDefId(), "프로세스 : " + this.getDefId());
	}
}


