package org.uengine.codi.mw3.ide.libraries;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.CloudInstanceWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.model.InstanceViewThreadPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel;
import org.uengine.kernel.Activity;
import org.uengine.kernel.Role;


public class ProcessNode extends TreeNode{
	
	String parentName;
		public String getParentName() {
			return parentName;
		}
		public void setParentName(String parentName) {
			this.parentName = parentName;
		}
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	ActivityNode activityNode;
		public ActivityNode getActivityNode() {
			return activityNode;
		}
		public void setActivityNode(ActivityNode activityNode) {
			this.activityNode = activityNode;
		}
	RoleNode roleNode;
		public RoleNode getRoleNode() {
			return roleNode;
		}
		public void setRoleNode(RoleNode roleNode) {
			this.roleNode = roleNode;
		}
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	@AutowiredFromClient
	public Session session;
		
	@Override
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "projectId"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object action(){
		ResourceNode resourceNode = new ResourceNode();
		resourceNode.setId(this.getId());
		resourceNode.setName(this.getName());
		resourceNode.setType(this.getType());
		resourceNode.setPath(this.getPath());
		
		Editor editor = new ProcessEditor(resourceNode);
		try {
			editor.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
		if( ((ProcessEditor)editor).getProcessDesignerInstanceId() != null ){
			instanceViewThreadPanel.session = session;
			instanceViewThreadPanel.setInstanceId(((ProcessEditor)editor).getProcessDesignerInstanceId());
			instanceViewThreadPanel.load();
		}
		CloudInstanceWindow cloudInstanceWindow = new CloudInstanceWindow();
		cloudInstanceWindow.setPanel(instanceViewThreadPanel);
		
		return new Object[]{new ToAppend(new CloudWindow("editor"), editor) , new Refresh(cloudInstanceWindow, true) };
	}		
	
	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_SELF)
	public Object expand() throws Exception{
		ProcessDesignerContentPanel processDesignerContentPanel = new ProcessDesignerContentPanel();
		processDesignerContentPanel.setAlias(this.getPath());
		processDesignerContentPanel.setBasePath("");
		processDesignerContentPanel.load();
		
		ProcessDesignerContainer container = processDesignerContentPanel.getProcessDesignerContainer();
		ArrayList<Activity> activityList = container.getActivityList();
		for(int aCount = 0; aCount < activityList.size(); aCount++) {
			ActivityNode activityNode = new ActivityNode();
			Activity activity = activityList.get(aCount);
			String name = "";
			if( activity.getName() != null && activity.getName().getText() != null){
				name = activity.getName().getText();
			}else{
				name = activity.getClass().getName() + activity.getTracingTag();
			}
			activity.setActivityView(null);
			activityNode.setActivity(activity);
			activityNode.setId("activity_" + activity.getTracingTag());
			activityNode.setName(name);
			activityNode.setPath(this.getPath());
			activityNode.setParentId(this.getId());
			activityNode.setType(ResourceNode.TYPE_ACTIVITY);
			this.setActivityNode(activityNode);
			this.add(activityNode);
		}
		ArrayList<Role> roleList = container.getRolePanel().getRoleList();
		for(int rCount = 0; rCount < roleList.size(); rCount++) {
			RoleNode roleNode = new RoleNode();
			Role role = roleList.get(rCount);
			String name = "";
			if( role.getName() != null ){
				name = role.getName();
			}else{
				name = role.getClass().getName() + role.getDisplayName();
			}
			role.setRoleView(null);
			roleNode.setRole(role);
			roleNode.setId("role_" + role.getDisplayName());
			roleNode.setName(name);
			roleNode.setPath(this.getPath());
			roleNode.setParentId(this.getId());
			roleNode.setType(ResourceNode.TYPE_ROLE);
			this.setRoleNode(roleNode);
			this.add(roleNode);
			
		}
		
		return this;
	}
	
}
