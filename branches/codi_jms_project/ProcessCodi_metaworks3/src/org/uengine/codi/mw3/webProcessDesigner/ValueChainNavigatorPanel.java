package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.component.Tree;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.Workspace;

public class ValueChainNavigatorPanel {

	Tree majorProcessDefinitionTree;
		public Tree getMajorProcessDefinitionTree() {
			return majorProcessDefinitionTree;
		}
		public void setMajorProcessDefinitionTree(Tree majorProcessDefinitionTree) {
			this.majorProcessDefinitionTree = majorProcessDefinitionTree;
		}
		
	@AutowiredFromClient
	public MajorProcessListPanel majorProcessListPanel;

	public void load(Workspace workspace){
		MajorProcessDefinitionNode majorProcessDefinitionNode = new MajorProcessDefinitionNode();
		majorProcessDefinitionNode.setId(workspace.getId());
		majorProcessDefinitionNode.setRoot(true);
		majorProcessDefinitionNode.setHidden(true);
		
		for(Project project: workspace.getProjects())
			majorProcessDefinitionNode.add(new MajorProcessDefinitionNode(project));
		
		Tree tree = new Tree();
		tree.setId(workspace.getId());
		tree.setNode(majorProcessDefinitionNode);
		
		setMajorProcessDefinitionTree(tree);
	}

}
