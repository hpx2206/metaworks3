package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.component.Tree;
import org.uengine.codi.mw3.ide.Project;

public class ProcessViewNavigator {
	
	Tree processDefinitionTree;
		public Tree getProcessDefinitionTree() {
			return processDefinitionTree;
		}
		public void setProcessDefinitionTree(Tree processDefinitionTree) {
			this.processDefinitionTree = processDefinitionTree;
		}
		
	@AutowiredFromClient
	public ProcessViewPanel processViewPanel;
	
	String id;
	@Id
	@Hidden
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String defId;
	public String alias;
	
	public String getDefId() {
		return defId;
	}
	public void setDefId(String defId) {
		this.defId = defId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void load(Project project){
		ProcessDefinitionNode processDefinitionNode = new ProcessDefinitionNode();
		processDefinitionNode.setId(project.getName());
		processDefinitionNode.setRoot(true);
		processDefinitionNode.setHidden(true);
		
		processDefinitionNode.add(new ProcessDefinitionNode(project));
		
		Tree tree = new Tree();
		tree.setId(project.getName());
		tree.setNode(processDefinitionNode);
		
		setProcessDefinitionTree(tree);
			
	}
	public void loadTree(){
		
//		ProcessDefinitionNode rootnode = new ProcessDefinitionNode();
//		rootnode.setRoot(true);
//		rootnode.setId("DefinitionList");
//		rootnode.setTreeId("DefinitionList");
//		rootnode.setName("DefinitionList");
//		rootnode.setType(TreeNode.TYPE_FOLDER);
//		rootnode.setFolder(true);
//		rootnode.setLoaded(true);
//		rootnode.setExpanded(true);
//		Tree tree = new Tree();
//		tree.setNode(rootnode);
//	
//		setProcessDefinitionTree(tree);
	}
}
