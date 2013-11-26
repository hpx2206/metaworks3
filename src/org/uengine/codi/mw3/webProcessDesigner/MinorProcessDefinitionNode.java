package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.libraries.ActivityNode;
import org.uengine.codi.mw3.ide.libraries.RoleNode;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.RecentItem;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.processexplorer.ProcessExploreContent;
import org.uengine.kernel.Activity;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.Role;

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
	public Object[] action() throws Exception {
		if("explorer".equals(this.getMetaworksContext().getHow())) {
			
			ProcessExploreContent processExploreContent = new ProcessExploreContent();
			processExploreContent.setAlias(this.getPath());
			processExploreContent.setDefId(this.getName());
//			processExploreContent.setPath(this.getPath());
			processExploreContent.session = session;
			processExploreContent.load();
			
			return new Object[]{new Refresh(processExploreContent)};
		}else if("snsView".equals(this.getMetaworksContext().getHow()) &&  TreeNode.TYPE_FILE_PROCESS.equals(this.getType())){
			
			ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
			processViewerPanel.setAlias(this.getPath());
			processViewerPanel.setDefinitionId(this.getName());
			processViewerPanel.setViewType("definitionView");
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
			
			String title = "프로세스 : " + getName();
			Object[] returnObject = Perspective.loadInstanceListPanel(session, "valuechainAll", findptm.getTopicId(), title);
			
			// recentItem 에 create
			RecentItem recentItem = new RecentItem();
			recentItem.setEmpCode(session.getEmployee().getEmpCode());
			recentItem.setItemId(this.getId());
			recentItem.setItemType(this.getType());
			recentItem.setUpdateDate(Calendar.getInstance().getTime());
			
			recentItem.add();
			
			return new Object[]{new Refresh(returnObject[1]), new Refresh(returnObject[0])};
			
			
			
			
			/*ProcessExploreContent processExploreContent = new ProcessExploreContent();
			processExploreContent.setAlias(this.getPath());
			processExploreContent.setDefId(this.getName());
//			processExploreContent.setPath(this.getPath());
			processExploreContent.session = session;
			processExploreContent.load();
			
			ModalWindow modalWindow = new ModalWindow();
			modalWindow.setPanel(processExploreContent);
			modalWindow.setWidth(700);
			modalWindow.setHeight(500);
			modalWindow.setTitle(this.getName());
			
			return modalWindow;*/
		}else if("viewer".equals(this.getMetaworksContext().getHow())){
			ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
			processViewerPanel.setAlias(this.getPath());
			processViewerPanel.setDefinitionId(this.getName());
			processViewerPanel.setViewType("definitionView");
			processViewerPanel.loadValuechainView();
			
			ModalWindow modalWindow = new ModalWindow(processViewerPanel);
			modalWindow.setWidth(700);
			modalWindow.setHeight(500);
			modalWindow.setTitle("view Process");
			
			return new Object[]{modalWindow};
			
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
			
			// recentItem 에 create
			RecentItem recentItem = new RecentItem();
			recentItem.setEmpCode(session.getEmployee().getEmpCode());
			recentItem.setItemId(this.getId());
			recentItem.setItemType(this.getType());
			recentItem.setUpdateDate(Calendar.getInstance().getTime());
			
			recentItem.add();
			
			return new Object[]{new Refresh(returnObject[0]), new Refresh(returnObject[1])};
//			return returnObject;
			
		}else if("process".equals(this.getMetaworksContext().getHow())){
			
		}
		return null;
		
	}		
	
	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_SELF)
	public Object expand() throws Exception{
		ProcessDesignerContentPanel processDesignerContentPanel = new ProcessDesignerContentPanel();
		processDesignerContentPanel.setAlias(this.getPath());
		processDesignerContentPanel.setBasePath("");
		processDesignerContentPanel.setUseClassLoader(true);
		processDesignerContentPanel.load();
		
		ProcessDesignerContainer container = processDesignerContentPanel.getProcessDesignerContainer();
		ArrayList<Activity> activityList = container.getActivityList();
		for(int aCount = 0; aCount < activityList.size(); aCount++) {
			MinorProcessDefinitionNode activityNode = new MinorProcessDefinitionNode();
			Activity activity = activityList.get(aCount);
			if( activity instanceof HumanActivity ){
				String name = "";
				if( activity.getName() != null && activity.getName().getText() != null){
					name = activity.getDescription().getText();
				}else{
					name = activity.getClass().getName() + activity.getTracingTag();
				}
				activityNode.setId("activity_" + activity.getTracingTag());
				activityNode.setName(name);
				activityNode.setPath(this.getPath());
				activityNode.setParentId(this.getId());
				activityNode.setType(ResourceNode.TYPE_ACTIVITY);
				activityNode.getMetaworksContext().setHow("activity");
				this.add(activityNode);
			}
		}
		this.setLoaded(false);
		return this;
	}
	@ServiceMethod(callByContent=true, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP_OVER_POPUP)
	public Object[] showContextMenu() {
		if(this.getMetaworksContext() != null && "explorer".equals(this.getMetaworksContext().getHow())) {
			return null;
			
		} else {
			session.setClipboard(this);
			return new Object[]{new Refresh(session), new ValueChainContextMenu(this)};
		}
	}
	
}
