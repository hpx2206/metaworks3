package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

public class MinorProcessDefinitionNode extends TreeNode implements ContextAware {
	@AutowiredFromClient
	transient public Session session;
	
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
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	transient MajorProcessDefinitionNode parentNode;	
		public MajorProcessDefinitionNode getParentNode() {
			return parentNode;
		}
		public void setParentNode(MajorProcessDefinitionNode parentNode) {
			this.parentNode = parentNode;
		}
		@Override
		@ServiceMethod(payload={"id", "name", "path", "type", "folder", "defId","alias","treeId"},target=ServiceMethodContext.TARGET_POPUP)
		public Object action() throws Exception {
			
			ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
			processViewerPanel.setAlias(this.getPath());
			processViewerPanel.setDefinitionId(this.getName());
			processViewerPanel.setViewType("definitionView");
			processViewerPanel.loadValuechainView();
			
			ModalWindow modalWindow = new ModalWindow(processViewerPanel);
			modalWindow.setWidth(700);
			modalWindow.setHeight(500);
			modalWindow.setTitle("view Process");
			
			return modalWindow;
		}		
	@ServiceMethod(payload={"id", "name", "path", "folder" , "child"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP_OVER_POPUP)
	public Object[] showContextMenu() {
		session.setClipboard(this);
		return new Object[]{new Refresh(session), new ValueChainContextMenu(this)};
	}
		
		
}
