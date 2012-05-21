package org.uengine.codi.mw3.model;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.common.FacebookComments;
import org.uengine.codi.mw3.knowledge.WorkflowyNode;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

@Face(
		ejsPathMappingByContext=
			{
				"{when: 'newInstance', face: 'org/uengine/codi/mw3/model/ResourceFile_newInstance.ejs'}",
			}		

	)
public class ResourceFile implements ContextAware{

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

	@ServiceMethod(callByContent=true, except="childs")
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
				if("newInstance".equals(getMetaworksContext().getWhen()) && !"process".equals(rf.getObjType()))
					continue;
			}
			
			
			childs.add(rf);
		}
		
	}
	
	
	@ServiceMethod(callByContent=true, except="childs", inContextMenu=true, keyBinding="Ctrl+N")
	public NewChildWindow newChild() throws Exception {
		NewChildWindow newChildWindow = new NewChildWindow(); 
		newChildWindow.setParentFolder(getAlias());
		
		return newChildWindow;
	}
	
	@ServiceMethod(callByContent=true, except="childs", inContextMenu=true, needToConfirm=true)
	public ResourceFile delete() throws Exception {
		
		String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/";
		
		File file = new File(resourceBase + getAlias());
		
		file.delete();
		
		setDeleted(true);
		
		return this;
		
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
				classDesignerContentPanel.load(getAlias());
				
				return classDesignerContentPanel;
				
			}else {
				try{
					ResourceDesigner designer = (ResourceDesigner)Class.forName("org.uengine.codi.mw3.model." + UEngineUtil.toOnlyFirstCharacterUpper(objType) + "Designer").newInstance();
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
	
	
	
	@ServiceMethod(callByContent=true, except="childs")
	public Object[] initiate() throws Exception{
		InstanceViewContent instanceView = instanceViewContent;// = new InstanceViewContent();
		
		String instId = processManager.initializeProcess(getAlias());
		
		
		RoleMapping rm = RoleMapping.create();
		if(session.user!=null)
			rm.setEndpoint(session.user.getUserId());
		
		processManager.setLoggedRoleMapping(rm);
		
		processManager.executeProcess(instId);
		processManager.applyChanges();
	
		IInstance instanceRef = new Instance();
		instanceRef.setInstId(new Long(instId));
		
		instanceView.load(instanceRef);
		
		InstanceListPanel instanceList = new InstanceListPanel(session); //should return instanceListPanel not the instanceList only since there're one or more instanceList object in the client-side
		//instanceList.load(session.login, session.navigation);

		if(newInstancePanel!=null && newInstancePanel.getKnowledgeNodeId() != null){
			WorkflowyNode parent = new WorkflowyNode(newInstancePanel.getKnowledgeNodeId());
			parent.load();
			
			WorkflowyNode child = new WorkflowyNode(WorkflowyNode.makeId());
			child.setName(instanceView.instanceName);
			child.setLinkedInstId(instId);
			parent.addChildNode(child);
			
			child.save();

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
	
}
