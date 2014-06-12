package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;

@Face(ejsPath = "dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/MappingTree.ejs")
public class SubProcessResourceTree extends Tree {
	String subProcessDefinitionId;
	@Hidden
		public String getSubProcessDefinitionId() {
			return subProcessDefinitionId;
		}
		public void setSubProcessDefinitionId(String subProcessDefinitionId) {
			this.subProcessDefinitionId = subProcessDefinitionId;
		}

	boolean preLoaded;
	@Hidden
		public boolean isPreLoaded() {
			return preLoaded;
		}
		public void setPreLoaded(boolean preLoaded) {
			this.preLoaded = preLoaded;
		}	
	
	@ServiceMethod(callByContent= true, target=ServiceMethodContext.TARGET_SELF)	
	public void init() throws Exception{
		
		String treeId = this.getId();
		
		TreeNode rootnode = new TreeNode();
		rootnode.setRoot(true);
		rootnode.setId(treeId + "Root");
		rootnode.setType(TreeNode.TYPE_FOLDER);
		rootnode.setFolder(true);
		rootnode.setLoaded(true);
		rootnode.setExpanded(true);
		rootnode.setAlign(this.getAlign());
		
		if( this.getSubProcessDefinitionId() != null ){
			
			ProcessDesignerContentPanel subDesignerContentPanel = new ProcessDesignerContentPanel();
			subDesignerContentPanel.setAlias(this.getSubProcessDefinitionId());
			subDesignerContentPanel.setUseClassLoader(true);
			subDesignerContentPanel.load();
			
			// 서브프로세스 변수
			 ArrayList<ProcessVariable> variableList = subDesignerContentPanel.getProcessVariablePanel().getVariableList();
			// 서브프로세스 롤
			ArrayList<Role>	 roleList = subDesignerContentPanel.getRolePanel().getRoleList();
			
			RoleTreeNode roleNode = new RoleTreeNode();
			roleNode.setId(treeId + "Roles");
			roleNode.setType(TreeNode.TYPE_FOLDER);
			roleNode.setFolder(true);
			roleNode.setAlign(this.getAlign());
			roleNode.load(roleList);

			VariableTreeNode variableTreeNode = new VariableTreeNode();
			variableTreeNode.setId(treeId + "Variables");
			variableTreeNode.setTreeId(treeId);
			variableTreeNode.setType(TreeNode.TYPE_FOLDER);
			variableTreeNode.setFolder(true);
			variableTreeNode.setAlign(this.getAlign());
			variableTreeNode.load(variableList);

			String processName = "";
			if( subDesignerContentPanel.getProcessNameView().getAlias() != null ){
				processName = subDesignerContentPanel.getProcessNameView().getAlias();
			}else{
				processName = this.getSubProcessDefinitionId().replace('.','@').split("@")[0];
			}
			rootnode.setName(processName);	// 최상위 폴더 이름은 서브프로세스 이름
			rootnode.add(roleNode);
			rootnode.add(variableTreeNode);
			
		}else{
			rootnode.setName("서브 프로세스를 등록해주세요.");
		}
		
		this.setNode(rootnode);
		setPreLoaded(true);
	}
}
