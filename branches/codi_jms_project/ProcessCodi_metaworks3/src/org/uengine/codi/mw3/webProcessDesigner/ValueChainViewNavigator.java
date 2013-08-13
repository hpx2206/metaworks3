package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.Workspace;

public class ValueChainViewNavigator {

	Tree processDefinitionTree;
		public Tree getProcessDefinitionTree() {
			return processDefinitionTree;
		}
		public void setProcessDefinitionTree(Tree processDefinitionTree) {
			this.processDefinitionTree = processDefinitionTree;
		}

	public void load(Workspace workspace){
		ProcessDefinitionNode processDefinitionNode = new ProcessDefinitionNode();
		processDefinitionNode.setId(workspace.getId());
		processDefinitionNode.setRoot(true);
		processDefinitionNode.setHidden(true);
		
		for(Project project: workspace.getProjects())
			processDefinitionNode.add(new ProcessDefinitionNode(project));
		
		Tree tree = new Tree();
		tree.setId(workspace.getId());
		tree.setNode(processDefinitionNode);
		
		setProcessDefinitionTree(tree);
			
	}

}
