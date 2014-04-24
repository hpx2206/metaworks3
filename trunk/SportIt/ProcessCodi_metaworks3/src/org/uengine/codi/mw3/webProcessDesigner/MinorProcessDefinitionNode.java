package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.ListPanel;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;
import org.uengine.jms.mw3.model.JmsProcessInfo;

public class MinorProcessDefinitionNode extends TreeNode implements ContextAware {
	@AutowiredFromClient
	transient public Session session;
	
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
		
	public MinorProcessDefinitionNode() {
		setMetaworksContext(new MetaworksContext());
	}
	
	@Override
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "defId","alias","treeId", "metaworksContext"},target=ServiceMethodContext.TARGET_APPEND)
	public Object action() throws Exception {
		if("explorer".equals(this.getMetaworksContext().getHow())) {
			
			ProcessViewWindow processViewWindow = new ProcessViewWindow();
			processViewWindow.setAlias(this.getPath());
			processViewWindow.setDefId(this.getName());
//			processViewWindow.setPath(this.getPath());
			processViewWindow.session = session;
			processViewWindow.load();
			
			return new Refresh(processViewWindow);
		}else if("snsView".equals(this.getMetaworksContext().getHow()) &&  TreeNode.TYPE_FILE_PROCESS.equals(this.getType())){
			
			ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
			processViewerPanel.setAlias(this.getPath());
			processViewerPanel.setDefinitionId(this.getPath());
			processViewerPanel.setViewType("definitionView");
			processViewerPanel.setUseClassLoader(true);
			processViewerPanel.loadValuechainView();
			
			String path = this.getPath();
			if( (this.getPath().substring(0, 1)).equals(File.separator)  ){
				path = path.substring(1);
			}
			String processName = processViewerPanel.getProcessViewPanel().getProcessViewer().getTitle();
			ProcessTopicMapping ptm = new ProcessTopicMapping();
			ptm.setProcessName(processName);
			ptm.setType("process");
			ptm.setProcessPath(path);
			IProcessTopicMapping findptm = ptm.findByNameByType();
			if(findptm==null){
				throw new Exception("wrong access");
			}
			
			InstanceListPanel instanceListPanel = Perspective.loadInstanceList(session, Perspective.MODE_PROCESS, Perspective.TYPE_NEWSFEED, this.getPath());

			Locale locale = new Locale(session);
			locale.load();

			String title = locale.getString("$Process") + " - " + this.getName();
			session.setWindowTitle(title);

			JmsProcessInfo processInfo = new JmsProcessInfo(session);
			processInfo.setDefId(this.getPath());
			processInfo.load();
			
			return new Object[]{ new Refresh(session, true), new Refresh(new ListPanel(instanceListPanel, processInfo)) };
			
		}else if("activity".equals(this.getMetaworksContext().getHow())){
			ProcessTopicMapping ptm = new ProcessTopicMapping();
			String path = this.getPath();
			if( (this.getPath().substring(0, 1)).equals(File.separator)  ){
				path = path.substring(1);
			}
			ptm.setProcessName(this.getName());
			ptm.setType("activity");
			ptm.setProcessPath(path);
			IProcessTopicMapping findptm = ptm.findByNameByType();
			
			if(findptm==null){
				throw new Exception("wrong access");
			}
			
			String title = "액티비티 : " + getName();
			Object[] returnObject = Perspective.loadInstanceListPanel(session, "activity", findptm.getTopicId(), title);
			
			return new Object[]{new Refresh(returnObject[0]), new Refresh(returnObject[1])};
		} 
		// TODO 바로 추가시에는 how가 viewer가 아니다.. 기본적으로 path가 있을 경우 프로세스를 보여준다.
//		} else if("viewer".equals(this.getMetaworksContext().getHow())){
		if(TreeNode.TYPE_FILE_PROCESS.equals(this.getType()) && this.getPath() != null){
			ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
			processViewerPanel.setAlias(this.getPath());
			processViewerPanel.setDefinitionId(this.getName());
			processViewerPanel.setViewType("definitionView");
			processViewerPanel.setUseClassLoader(true);
			processViewerPanel.loadValuechainView();
			
			ModalWindow modalWindow = new ModalWindow(processViewerPanel);
			modalWindow.setWidth(700);
			modalWindow.setHeight(500);
			modalWindow.setTitle("view Process");
			
			return modalWindow;
			
		} else {
			return null;
		}
		
	}		
	@ServiceMethod(callByContent=true, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP_OVER_POPUP)
	public Object[] showContextMenu() {
		if(this.getMetaworksContext() != null && "explorer".equals(this.getMetaworksContext().getHow()) 
				&& "snsView".equals(this.getMetaworksContext().getHow()) 
				&& "activity".equals(this.getMetaworksContext().getHow()) ) {
			return null;
			
		} else {
			session.setClipboard(this);
			return new Object[]{new Refresh(session), new ValueChainContextMenu(this)};
		}
	}
	
}
