package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

public class MajorProcessDefinitionNode extends TreeNode  implements ContextAware {
	@AutowiredFromClient
	transient public Session session;
	
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
			
	public MajorProcessDefinitionNode(){
		setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow("tree");
	}
	
	public ArrayList<TreeNode> loadExpand(){
		
		return null;
	}
	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_SELF)
	public Object expand() throws Exception{
//		ArrayList<TreeNode> child = new ArrayList<TreeNode>();
	
	
//		this.setChild(child);
	
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
		if( TreeNode.TYPE_FILE_PROCESS.equals(this.getType())){
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
		}
		return null;
	}
	@ServiceMethod(payload={"id", "name", "path", "folder" , "child"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP_OVER_POPUP)
	public Object[] showContextMenu() {
		session.setClipboard(this);
		return new Object[]{new Refresh(session), new ValueChainContextMenu(this)};
	}
}