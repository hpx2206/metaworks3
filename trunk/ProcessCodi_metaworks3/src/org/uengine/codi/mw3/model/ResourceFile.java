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
import org.uengine.codi.CodiProcessDefinitionFactory;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.admin.ResourcePanel;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Face(
		ejsPathMappingByContext=
			{
				"{when: 'newInstance', face: 'org/uengine/codi/mw3/model/ResourceFile_newInstance.ejs'}",
				"{when: 'appendProcessMap', face: 'org/uengine/codi/mw3/model/ResourceFile_newInstance.ejs'}",				
			}		

	)
@XStreamAlias("resource")
public class ResourceFile implements ContextAware{
	
//	static{
//		CodiDwrServlet.xstream.processAnnotations(ResourceFile.class);
//	}

	public ResourceFile() {
		setMetaworksContext(new MetaworksContext());
		setAlias("");
	}
	
	@AutowiredFromClient
	public PageNavigator pageNavigator;

	@XStreamOmitField
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


	@XStreamAsAttribute
	@XStreamAlias("value")
	String uEngineAlias;
		
		public String getuEngineAlias() {
			return uEngineAlias;
		}
		public void setuEngineAlias(String uEngineAlias) {
			this.uEngineAlias = uEngineAlias;
		}

	@XStreamOmitField
	boolean isFolder;
	
		public boolean isFolder() {
			return isFolder;
		}
	
		public void setFolder(boolean isDirectory) {
			this.isFolder = isDirectory;
		}

	@XStreamOmitField
	String objType;			
		public String getObjType() {
			return objType;
		}
	
		public void setObjType(String type) {
			this.objType = type;
		}

	@XStreamAsAttribute
	String name;
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		

	@XStreamOmitField
	boolean isOpened;	
		public boolean isOpened() {
			return isOpened;
		}
	
		public void setOpened(boolean isOpened) {
			this.isOpened = isOpened;
		}
		
	@XStreamOmitField
	boolean isDeleted;

		public boolean isDeleted() {
			return isDeleted;
		}
	
		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

	@XStreamImplicit
	ArrayList<ResourceFile> childs;

		public ArrayList<ResourceFile> getChilds() {
			return childs;
		}
	
		public void setChilds(ArrayList<ResourceFile> childFiles) {
			this.childs = childFiles;
		}
		
	@XStreamOmitField
	String filter;
		public String getFilter() {
			return filter;
		}
		public void setFilter(String filter) {
			this.filter = filter;
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
			rf.setuEngineAlias("[" + childAlias + "]");
			rf.setFolder(f.isDirectory());
			rf.setMetaworksContext(getMetaworksContext());
			
			if(f.isDirectory()){
				rf.setObjType("folder");
			}else{
				rf.setObjType(childAlias.substring(childAlias.lastIndexOf(".")+1));

				//ignores other types except process if 'newInstance' mode
				if(getMetaworksContext()!=null && (("appendProcessMap".equals(getMetaworksContext().getWhen()) || "newInstance".equals(getMetaworksContext().getWhen())) && !"process".equals(rf.getObjType())))
					continue;
			}
			
			rf.setFilter(getFilter());
			
			if(f.isDirectory() || getFilter()==null || (getFilter().indexOf(rf.getObjType()) > -1))
				childs.add(rf);
		}
		
	}
	
	@ServiceMethod
	public void drillDownDeeply(){
		drillDown();
		
		if(childs!=null)
		for(ResourceFile child : childs){
			if(child.isFolder())
				child.drillDownDeeply();
		}
	}

	
	
	@ServiceMethod(callByContent=true, except="childs", inContextMenu=true, keyBinding="Ctrl+N", target="popup")
	public ModalWindow newChild() throws Exception {
		
		NewChildContentPanel newChildContentPanel = new NewChildContentPanel();
		newChildContentPanel.setParentFolder(getAlias());

//		NewChildWindow newChildWindow = new NewChildWindow();
//		newChildWindow.getMetaworksContext().setHow(pageNavigator.getPageName());
//		newChildWindow.setParentFolder(getAlias());
		
		
		return new ModalWindow(newChildContentPanel, 800, 540, "New Child...");
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


	@ServiceMethod(inContextMenu=true, callByContent=true, mouseBinding="drag", keyBinding="Ctrl+X")
	public Session cut(){
		session.setClipboard(this);
		return session;
	}
	
	@ServiceMethod(inContextMenu=true, callByContent=true, mouseBinding="drop", keyBinding="Ctrl+V")
	public Object[] paste(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceFile && isFolder()){
			ResourceFile fileInClipboard = (ResourceFile) clipboard;
			String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();

			new File(resourceBase + fileInClipboard.getAlias()).renameTo(
					new File(resourceBase + getAlias() + "/" + fileInClipboard.getName())
				);
			
			drillDown();
			
			return new Object[]{fileInClipboard, this};
		}else{
			return new Object[]{this};
		}
		
		
	}
	
	@ServiceMethod(inContextMenu=true, callByContent=true, target="popup", keyBinding="Ctrl+R")
	public Popup rename(){
		FileRenamer fileRenamer = new FileRenamer();
		fileRenamer.setFile(this);
		
		Popup renamer = new Popup(fileRenamer);
		
		return renamer;
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
					designer.setAlias(alias);
					designer.load();
					
					ContentWindow contentWindow = new ContentWindow();
					contentWindow.setTitle(alias);
					contentWindow.setPanel(designer);
					contentWindow.getMetaworksContext().setHow("ide");
					
					return contentWindow;
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
		
		ProcessManagerBean pmb = (ProcessManagerBean)processManager;
		CodiProcessDefinitionFactory.getInstance(pmb.getTransactionContext()).removeFromCache(getAlias());
		
		ResourcePanel resourcePanel = new ResourcePanel();
		
		resourcePanel.refresh();
		
		return resourcePanel;
	}
	
	@ServiceMethod(callByContent=true, except="childs")
	public Object[] appendProcessMap() throws Exception {
		String name = this.getName();

		ProcessMap processMap = new ProcessMap();

		if(name.endsWith(".process")){
			name = name.substring(0, name.length() - 8);
			
			org.uengine.kernel.ProcessDefinition procDef = processManager.getProcessDefinition(getAlias());
			String fullCommandPhrase = procDef.getDescription().getText();

			if(fullCommandPhrase!=null){
				int commandCotentStarts = fullCommandPhrase.indexOf(':');
				if(-1 < commandCotentStarts){
	
					processMap.setCmPhrase(fullCommandPhrase);
					processMap.setCmTrgr(fullCommandPhrase.substring(0, commandCotentStarts));
				}
			}
		}
			
		processMap.setMapId(session.getCompany().getComCode() + "." + this.getAlias());
		processMap.setDefId(this.getAlias());
		processMap.setName(name);
		processMap.setComCode(session.getCompany().getComCode());
		
		if(!processMap.confirmExist())
			throw new Exception("$AlreadyAddedApp");

		processMap.createMe();
		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load(session);
		
		return new Object[]{processMapList, new Remover(new ModalWindow())};		
	}

	
	protected IUser friend;

	
	@ServiceMethod
	public org.uengine.kernel.ProcessDefinition loadProcessDefinition() throws Exception{
		return processManager.getProcessDefinition(getAlias());
	}

	/**
	 * Not used anymore ... see ProcessMap.initiate instead
	 * @return
	 * @throws Exception
	 */
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
		
		instanceView.session = session;
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
	transient public NewInstancePanel newInstancePanel;
	
	@AutowiredFromClient
	transient public Session session;
	


	@Autowired
	transient public InstanceViewContent instanceViewContent;

	@Autowired
	transient public ProcessManagerRemote processManager;

	
	transient MetaworksContext metaworksContext;
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
