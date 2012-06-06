package org.uengine.codi.mw3.model;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.admin.ResourcePanel;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

@Face(
		ejsPathMappingByContext=
			{
				"{when: 'newInstance', face: 'org/uengine/codi/mw3/model/ResourceFile_newInstance.ejs'}",
				"{when: 'appendProcessMap', face: 'org/uengine/codi/mw3/model/ResourceFile_newInstance.ejs'}",				
			}		

	)
public class ResourceFile implements ContextAware{

	public ResourceFile() {
		setMetaworksContext(new MetaworksContext());
	}
	
	@AutowiredFromClient
	public PageNavigator pageNavigator;

	String alias;
		@Id
		public String getAlias() {
			return alias;
		}	
		public void setAlias(String fullPath) {
			this.alias = fullPath;
			
			if(name==null && alias!=null && alias.length() > 0){
				int whereLastSlash = getAlias().lastIndexOf("/");
				
				if(whereLastSlash>-1) {
					int lastIndexOfDot = getAlias().lastIndexOf(".");
					name = getAlias().substring(whereLastSlash+1, lastIndexOfDot != -1 ? lastIndexOfDot : getAlias().length());
				}
			}
			
		}
	
	boolean isFolder;
	
		public boolean isFolder() {
			return isFolder;
		}
	
		public void setFolder(boolean isDirectory) {
			this.isFolder = isDirectory;
		}

	String objType;
			
		public String getObjType() {
			return objType;
		}
	
		public void setObjType(String type) {
			this.objType = type;
		}

	String name;
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		

	boolean isOpened;	
		public boolean isOpened() {
			return isOpened;
		}
	
		public void setOpened(boolean isOpened) {
			this.isOpened = isOpened;
		}
		
	boolean isDeleted;

		public boolean isDeleted() {
			return isDeleted;
		}
	
		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

	ArrayList<ResourceFile> childs;

		public ArrayList<ResourceFile> getChilds() {
			return childs;
		}
	
		public void setChilds(ArrayList<ResourceFile> childFiles) {
			this.childs = childFiles;
		}
	
	@ServiceMethod(callByContent=true, except="childs", target="self")
	public void drillDown(){
		if(isOpened()){
			setOpened(false);
			
			return;
		}	
		
		String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/";
		
		File file = new File(resourceBase + getAlias());
		
		if(file.getName().startsWith("__") && !file.exists()){
			file.mkdirs();
		}
		
		if(!file.isDirectory()){
			return;
		}
		
		setOpened(true);
		
		String[] childFilePaths = file.list();
		childs = new ArrayList<ResourceFile>();
				
		for(int i=0; i<childFilePaths.length; i++){
			ResourceFile rf = new ResourceFile();
			
			String childAlias = (getAlias().length() > 0 ? getAlias() + "/" : "") + childFilePaths[i];
			File f = new File(resourceBase + childAlias);
			
			rf.setName(childFilePaths[i]);
			rf.setAlias(childAlias);
			rf.setFolder(f.isDirectory());
			rf.setMetaworksContext(getMetaworksContext());
			
			if(f.isDirectory()){
				rf.setObjType("folder");
			}else{
				rf.setObjType(childAlias.substring(childAlias.lastIndexOf(".")+1));

				//ignores other types except process if 'newInstance' mode
				if(("appendProcessMap".equals(getMetaworksContext().getWhen()) || "newInstance".equals(getMetaworksContext().getWhen())) && !"process".equals(rf.getObjType()))
					continue;
			}
			
			
			childs.add(rf);
		}
		
	}
	
	
	@ServiceMethod(callByContent=true, except="childs", inContextMenu=true, keyBinding="Ctrl+N")
	public NewChildWindow newChild() throws Exception {
		NewChildWindow newChildWindow = new NewChildWindow();
		newChildWindow.getMetaworksContext().setHow(pageNavigator.getPageName());
		newChildWindow.setParentFolder(getAlias());
		
		return newChildWindow;
	}
	
	@ServiceMethod(callByContent=true, except="childs", inContextMenu=true, needToConfirm=true)
	public Object delete() throws Exception {
		
		String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/";
		
		File file = new File(resourceBase + getAlias());
		
		file.delete();
		
		setDeleted(true);
		
		return new Remover(this);
		
	}	
	
	@ServiceMethod(inContextMenu=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup importFile() throws Exception{
		FileImporter fileImporter = new FileImporter();
		fileImporter.setParentDirectory(getAlias());
		
		Popup popup = new Popup();
		popup.setPanel(fileImporter);
		popup.setMetaworksContext(new MetaworksContext());
		popup.getMetaworksContext().setWhen("edit");
		
		return popup;
	}

	@Face(displayName="Export to Activity App Zip")
	@ServiceMethod(inContextMenu=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup exportToJar() throws Exception{
		FileImporter fileImporter = new FileImporter();
		fileImporter.setParentDirectory(getAlias());
		
		Popup popup = new Popup();
		popup.setPanel(fileImporter);
		popup.setMetaworksContext(new MetaworksContext());
		popup.getMetaworksContext().setWhen("edit");
		
		return popup;
	}

	
	@ServiceMethod(callByContent=true, except="childs")
	public ContentWindow design() throws Exception {
//		try{
		
//			if("form".equals(objType)){
//				FormDesignerContentPanel formDesigner = new FormDesignerContentPanel();
//				formDesigner.load(databaseMe().getDefId().toString());
//	
//				return formDesigner;
//			}else if("process".equals(objType)){
//				processDesigner.load(databaseMe().getDefId().toString());
//				
//				return processDesigner;
				
//			}else
		
			if("class".equals(objType)){
				ClassDesignerContentPanel classDesignerContentPanel = new ClassDesignerContentPanel();
				classDesignerContentPanel.getMetaworksContext().setHow(pageNavigator.getPageName());
				classDesignerContentPanel.load(getAlias());
				
				return classDesignerContentPanel;
				
			}else {
				try{
					ResourceDesigner designer = (ResourceDesigner)Class.forName("org.uengine.codi.mw3.model." + UEngineUtil.toOnlyFirstCharacterUpper(objType) + "Designer").newInstance();
					designer.getMetaworksContext().setHow(pageNavigator.getPageName());
					
					designer.setAlias(alias);
					designer.load();
					
					return designer;
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
//			else if("entity".equals(objType)){
//				entityDesignerWindow.load(databaseMe().getDefId().toString());
//				
//				return entityDesignerWindow;		
//			}
//		}finally{
//			codiPmSVC.remove();
//		}	
		
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	@ServiceMethod(inContextMenu=true)
	public ResourcePanel refresh() throws Exception {
		ResourcePanel resourcePanel = new ResourcePanel();
		
		resourcePanel.refresh();
		
		return resourcePanel;
	}

	
	
	@ServiceMethod(callByContent=true, except="childs")
	public Object[] appendProcessMap() throws Exception {
		String name = this.getName();
		
		if(name.endsWith(".process"));
			name = name.substring(0, name.length() - 8);
			
		ProcessMap processMap = new ProcessMap();
		processMap.setDefId(this.getAlias());
		processMap.setName(name);
		
		if(!processMap.confirmExist())
			throw new Exception("이미 프로세스 맵에 등록된 프로세스입니다.");

		processMap.createMe();
		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load();
		
		return new Object[]{processMapList, new Remover(new ModalWindow())};		
	}

	
	protected IUser friend;

	@ServiceMethod(callByContent=true, except="childs")
	public Object[] initiate() throws Exception{
		InstanceViewContent instanceView = instanceViewContent;// = new InstanceViewContent();
		
		if(pageNavigator!=null)
			instanceView.getMetaworksContext().setHow(pageNavigator.getPageName());
		
		String instId = processManager.initializeProcess(getAlias());
		
		org.uengine.kernel.ProcessDefinition def = processManager.getProcessDefinition(getAlias());
		if(def.getRoles() == null || def.getRoles().length == 0){
			
			//// setting initiator if there's nothing to state the initiator in the definition ////
			org.uengine.kernel.RoleMapping rm = org.uengine.kernel.RoleMapping.create();
			if(session.user!=null){
				rm.setName("initiator");
				rm.setEndpoint(session.user.getUserId());
			}

			processManager.putRoleMapping(instId, rm);

		}
		
		if(friend!=null){
			
			org.uengine.kernel.RoleMapping receiver = org.uengine.kernel.RoleMapping.create();
			receiver.setEndpoint(friend.getUserId());
			receiver.setName("___receiver____");
			
			processManager.putRoleMapping(instId, receiver);

		}
		
		RoleMapping rm = RoleMapping.create();
		if(session.user!=null)
			rm.setEndpoint(session.user.getUserId());
		
		processManager.setLoggedRoleMapping(rm);
		
		processManager.executeProcess(instId);
		processManager.applyChanges();
	
		IInstance instanceRef = new Instance();
		instanceRef.setInstId(new Long(instId));
		
		instanceView.load(instanceRef);
		
		InstanceListPanel instanceList = new InstanceListPanel(); //should return instanceListPanel not the instanceList only since there're one or more instanceList object in the client-side
		instanceList.getInstanceList().load(session);
		
		//instanceList.load(session.login, session.navigation);

		if(newInstancePanel!=null && newInstancePanel.getKnowledgeNodeId() != null){
			WfNode parent = new WfNode();
			parent.load(newInstancePanel.getKnowledgeNodeId());
			
			WfNode child = new WfNode();		
			child.setName(instanceView.instanceName);
			child.setLinkedInstId(Long.parseLong(instId));
			parent.addChildNode(child);
			
			child.createMe();

			return new Object[]{instanceView, parent};

		}
		
		return new Object[]{instanceView, instanceList};

		
	}
	
	@AutowiredFromClient
	public NewInstancePanel newInstancePanel;
	
	@AutowiredFromClient
	public Session session;
	


	@Autowired
	public InstanceViewContent instanceViewContent;

	@Autowired
	public ProcessManagerRemote processManager;

	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
		@ServiceMethod(callByContent=true, payload={"objType"})
		public Object[] loadProcess() throws Exception {
			if(getObjType().equals("process")) {
				return Perspective.loadInstanceListPanel(session, "process", getAlias());
			}
			return null;
		}
	
}
