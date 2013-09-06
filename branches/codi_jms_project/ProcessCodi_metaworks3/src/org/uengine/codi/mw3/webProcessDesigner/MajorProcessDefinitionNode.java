package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ValueChain;
import org.uengine.kernel.ValueChainDefinition;

public class MajorProcessDefinitionNode extends TreeNode  implements ContextAware {
	@AutowiredFromClient
	transient public Session session;
	
	@AutowiredFromClient
	public ProcessViewerPanel processViewerPanel;
	
	public final static String TYPE_PROJECT = "project";
	
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	MetaworksContext metaworksContext;
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
	
	public ArrayList<TreeNode> loadExpand(){
		
		return null;
	}
	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_SELF)
	public Object expand() throws Exception{
		MajorProcessDefinitionNode majorProcessDefinitionNode = new MajorProcessDefinitionNode();
		File file = new File(this.getPath());
		String[] childFilePaths = file.list();
			for(int i=0; i<childFilePaths.length; i++){
				File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
				if( !childFile.isDirectory()){
					String type = ResourceNode.findNodeType(childFile.getName());
					
					if(!type.equals(TreeNode.TYPE_FILE_VALUECHAIN)){
						continue;
					}
					MinorProcessDefinitionNode valueChainNode = new MinorProcessDefinitionNode();
					valueChainNode.setType(TreeNode.TYPE_FILE_VALUECHAIN);
					valueChainNode.setFolder(true);
					valueChainNode.setName(childFile.getName());
//					valueChainNode.setId(this.getId() + File.separatorChar + childFile.getName());
					valueChainNode.setId(file.getPath());
					valueChainNode.setExpanded(true);
					valueChainNode.setPath(childFile.getPath());
//					valueChainNode.setPath(this.getPath() + File.separatorChar + childFile.getName());
					valueChainNode.setDefId(childFile.getName());
					valueChainNode.setAlias(childFile.getName());
					valueChainNode.setParentId(this.getId());
					valueChainNode.setMetaworksContext(getMetaworksContext());
					
					majorProcessDefinitionNode.add(valueChainNode);

					String valuechainDef = Editor.loadByPath(childFile.getPath());
					ValueChainDefinition def = (ValueChainDefinition) GlobalContext.deserialize(valuechainDef);
					
					List<ValueChain> valueList = def.getChildValueChains();
					
					for(ValueChain calueChain : valueList){
						ValueChain valueChain = calueChain;
						valueChain.setMetaworksContext(new MetaworksContext());
						valueChain.getMetaworksContext().setHow("ProcessExplorer");
						MajorProcessDefinitionNode childNode = valueChain.getMajorProcessDefinitionNode();
						childNode.setMetaworksContext(new MetaworksContext());
						childNode.getMetaworksContext().setHow("ProcessExplorer");
						if( childNode != null ){
							majorProcessDefinitionNode.add(childNode);
						}
					}
				}
			}
		
		

		
		return this;
	}
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "defId","alias","treeId"},target=ServiceMethodContext.TARGET_POPUP)
	public Object selectProcess() {
		ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
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
		
		
		
		
//		
//		
//		if("tree".equals(this.getMetaworksContext().getHow()) &&  TreeNode.TYPE_FILE_PROCESS.equals(this.getType())){
//			ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
//			processViewerPanel.setDefinitionId(this.getName());
//			processViewerPanel.setAlias(this.getPath());
//			processViewerPanel.setViewType("definitionView");
//			processViewerPanel.loadValuechainView();
//			
//			ModalWindow modalWindow = new ModalWindow(processViewerPanel);
//			modalWindow.setWidth(700);
//			modalWindow.setHeight(500);
//			modalWindow.setId(this.getPath());
//			modalWindow.setTitle(this.getName());
//			return modalWindow;
//		}else if( this.getTreeId() != null && this.getTreeId().equals("valuechain")){
//			if( this.getTreeId() != null && this.getTreeId().equals("valuechain")){
//				ProcessViewWindow processViewWindow = new ProcessViewWindow();
//				processViewWindow.setAlias(this.getAlias());
//				processViewWindow.setDefId("");
//				processViewWindow.setPath(path);
//				processViewWindow.session = session;
//				
//				processViewWindow.load();
//				return new Object[] { new Refresh(processViewWindow) };
//			}
//			if( alias != null && !this.isFolder() && this.getType().equals(TreeNode.TYPE_FILE_PROCESS)){
//				processViewerPanel = new ProcessViewerPanel();
//				processViewerPanel.setDefinitionId(defId);
//				processViewerPanel.setAlias(this.getAlias());
//				processViewerPanel.setViewType("definitionEditor");
//				processViewerPanel.loadDefinitionView();
//				return new Object[] { new Refresh(processViewerPanel.processViewPanel) };
//			}else{
//				return null;
//			}
//		}
	}
	@ServiceMethod(payload={"id", "name", "path", "folder" , "child"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP_OVER_POPUP)
	public Object[] showContextMenu() {
		session.setClipboard(this);
		return new Object[]{new Refresh(session), new ValueChainContextMenu(this)};
	}
	
	public void injectionMetaworksContext(MetaworksContext context , ArrayList<TreeNode> childNodes){
		if( childNodes != null ){
			for(int i=0; i < childNodes.size();i++){
				MajorProcessDefinitionNode node = (MajorProcessDefinitionNode)childNodes.get(i);
				node.setMetaworksContext(context);
				if( node.getChild() != null && node.getChild().size() > 0 ){
					injectionMetaworksContext(context, node.getChild() );
				}
			}
		}
	}
}