package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.model.Session;

public class MajorProcessDefinitionNode extends TreeNode  implements ContextAware {
	@AutowiredFromClient
	transient public Session session;
	
	@AutowiredFromClient
	public ProcessViewerPanel processViewerPanel;
	
	@AutowiredFromClient
	public Project project;
	
	public final static String TYPE_PROJECT = "project";
	
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}		
	
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}			
	transient MajorProcessDefinitionNode parentNode;	
		public MajorProcessDefinitionNode getParentNode() {
			return parentNode;
		}
		public void setParentNode(MajorProcessDefinitionNode parentNode) {
			this.parentNode = parentNode;
		}
	public MajorProcessDefinitionNode(){
		setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow("tree");
	}
	
	public MajorProcessDefinitionNode(Project project){
		this();
		
		this.setId(project.getId());
		this.setName(project.getId());
		this.setType(TreeNode.TYPE_FOLDER);
		this.setFolder(true);
		
		this.setPath(project.getPath());
		this.setAlias(project.getId()+ File.separatorChar+ "root");
		this.setParentId(project.getId());
		
	}
	
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "defId","alias","treeId"},target=ServiceMethodContext.TARGET_POPUP)
	public Object selectProcess() throws Exception {
		ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
		processViewerPanel.session = session;
		processViewerPanel.project = project;
		processViewerPanel.findValuechainView();
		
		ModalWindow modalWindow = new ModalWindow(processViewerPanel);
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		modalWindow.setTitle("$ValueChainEdit");
		
		return modalWindow;
	}
	@Override
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "defId","alias","treeId"},target=ServiceMethodContext.TARGET_APPEND)
	public Object action() throws Exception{
		if("ProcessExplorer".equals(this.getMetaworksContext().getHow()) &&  TreeNode.TYPE_FILE_PROCESS.equals(this.getType())){
			
			ProcessViewWindow processViewWindow = new ProcessViewWindow();
			processViewWindow.setAlias(this.getName());
			processViewWindow.setDefId(this.getId());
			processViewWindow.setPath(path);
			processViewWindow.session = session;
			
			processViewWindow.load();
			return new Object[] { new Refresh(processViewWindow) };
			
		}else if("tree".equals(this.getMetaworksContext().getHow()) &&  TreeNode.TYPE_FILE_PROCESS.equals(this.getType())){
			ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
			processViewerPanel.setDefinitionId(this.getName());
			processViewerPanel.setAlias(this.getPath());
			processViewerPanel.setViewType("definitionView");
			processViewerPanel.loadValuechainView();
			
			ModalWindow modalWindow = new ModalWindow(processViewerPanel);
			modalWindow.setWidth(700);
			modalWindow.setHeight(500);
			modalWindow.setId(this.getPath());
			modalWindow.setTitle(this.getName());
			return modalWindow;
			
		}else{
		return null;
		}
	}
	@ServiceMethod(callByContent=true, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP_OVER_POPUP)
	public Object[] showContextMenu() {
		
		if(this.getMetaworksContext() != null  && "explorer".equals(this.getMetaworksContext().getHow())) {
			return null;
		
		} else {
			session.setClipboard(this);
			return new Object[]{new Refresh(session), new ValueChainContextMenu(this)};
		}
	}
	
	public void injectionMetaworksContext(MetaworksContext context , ArrayList<TreeNode> childNodes){
		this.setMetaworksContext(context);
		if( childNodes != null ){
			for(int i=0; i < childNodes.size();i++){
				if(childNodes.get(i) instanceof MajorProcessDefinitionNode) {
					MajorProcessDefinitionNode node = (MajorProcessDefinitionNode)childNodes.get(i);
					node.setMetaworksContext(context);
					
					if( node.getChild() != null && node.getChild().size() > 0 ){
						injectionMetaworksContext(context, node.getChild() );
					}
					
				} else {
					MinorProcessDefinitionNode node = (MinorProcessDefinitionNode)childNodes.get(i);
					node.setMetaworksContext(context);
				}
			}
		}
	}
	public void removeNullChild(ArrayList<TreeNode> childNodes){
		if( childNodes != null ){
			for(int i=childNodes.size()-1; i >= 0 ;i--){
				if(childNodes.get(i) instanceof MajorProcessDefinitionNode) {
					MajorProcessDefinitionNode node = (MajorProcessDefinitionNode)childNodes.get(i);
					if( node.getChild() != null && node.getChild().size() > 0 ){
						removeNullChild(node.getChild() );
					}
					
				} else {
					MinorProcessDefinitionNode node = (MinorProcessDefinitionNode)childNodes.get(i);
					if( node == null ){
						childNodes.remove(i);
					}
				}
			}
		}
	}
}