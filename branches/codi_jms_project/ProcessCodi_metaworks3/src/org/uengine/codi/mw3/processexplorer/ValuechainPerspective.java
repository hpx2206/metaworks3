package org.uengine.codi.mw3.processexplorer;

import java.io.File;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.MajorProcessDefinitionNode;
import org.uengine.codi.mw3.webProcessDesigner.MinorProcessDefinitionNode;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewerPanel;
import org.uengine.kernel.ValueChain;

public class ValuechainPerspective extends Perspective  implements ContextAware {

	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
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

	String path;
		public String getPath() {
			return path;
		}
	
		public void setPath(String path) {
			this.path = path;
		}
	
	String name;
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
	String id;
		@Id
		public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
		}
	
	String type;
		public String getType() {
			return type;
		}
	
		public void setType(String type) {
			this.type = type;
		}
	
	boolean folder;
		public boolean isFolder() {
			return folder;
		}
	
		public void setFolder(boolean folder) {
			this.folder = folder;
		}
		
	Tree majorProcessDefinitionTree;
		public Tree getMajorProcessDefinitionTree() {
			return majorProcessDefinitionTree;
		}
		public void setMajorProcessDefinitionTree(Tree majorProcessDefinitionTree) {
			this.majorProcessDefinitionTree = majorProcessDefinitionTree;
		}
		
	String valueChainName;
		public String getValueChainName() {
			return valueChainName;
		}
		public void setValueChainName(String valueChainName) {
			this.valueChainName = valueChainName;
		}
	
	ValueChain valueChain;
		public ValueChain getValueChain() {
			return valueChain;
		}
		public void setValueChain(ValueChain valueChain) {
			this.valueChain = valueChain;
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

	public final static String TYPE_PROJECT 			= "project";
		
	public ValuechainPerspective() {
		setLabel("Valuechain");
	}
	@AutowiredFromClient
	public ProcessViewerPanel processViewerPanel;
	
	@Override
	public void loadChildren() throws Exception {
		
		Workspace workspace = new Workspace();
		workspace.load();

		MajorProcessDefinitionNode majorProcessDefinitionNode = new MajorProcessDefinitionNode();
//		majorProcessDefinitionNode = valueChain.getMajorProcessDefinitionNode();
		majorProcessDefinitionNode.setId(workspace.getId());
		majorProcessDefinitionNode.setExpanded(true);
		majorProcessDefinitionNode.setName("가치사슬");
//		majorProcessDefinitionNode.setType(TreeNode.TYPE_FOLDER);
		majorProcessDefinitionNode.setFolder(true);
//		majorProcessDefinitionNode.setHidden(true);
		
		majorProcessDefinitionNode.session = session;
		
		
		for(Project project: workspace.getProjects()){
//			majorProcessDefinitionNode.add(new MajorProcessDefinitionNode(project));
			File file = new File(project.getPath());
			String[] childFilePaths = file.list();
			
			for(int i=0; i<childFilePaths.length; i++){
				File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
				if(childFile.isDirectory()){
					
					MinorProcessDefinitionNode valueChainNode = new MinorProcessDefinitionNode();
//					valueChainNode.setId(this.getId() + File.separatorChar + childFile.getName());
					valueChainNode.setId(file.getPath());
					valueChainNode.setProjectId(this.getProjectId());
					valueChainNode.setName(childFile.getName());
					valueChainNode.setDefId(childFile.getName());
//					valueChainNode.setExpanded(true);
//					valueChainNode.setPath(this.getPath() + File.separatorChar + childFile.getName());
					valueChainNode.setPath(childFile.getPath());
					valueChainNode.setAlias(childFile.getName());
					valueChainNode.setMetaworksContext(getMetaworksContext());
					valueChainNode.setParentId(this.getId());
					valueChainNode.setFolder(true);
					valueChainNode.setType(TreeNode.TYPE_FOLDER);
					
					
					
					
					
					majorProcessDefinitionNode.add(valueChainNode);
				}
			}
			
			
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
				}
			}
		}
			//majorProcessDefinitionNode.add(new ProcessDefinitionNode(project));
		
		Tree tree = new Tree();
		tree.setId(workspace.getId());
		tree.setNode(majorProcessDefinitionNode);
		
		setMajorProcessDefinitionTree(tree);
		
		
		
		
		
		
		
		
//		String treeId = "valuechain";
//		Workspace workspace = new Workspace();
//		workspace.load();
//	
//		// TODO Auto-generated method stub
//		ProcessDefinitionNode processDefinitionNode = new ProcessDefinitionNode();
//		processDefinitionNode.setId(workspace.getId());
//		processDefinitionNode.setRoot(true);
//		processDefinitionNode.setHidden(true);
//		processDefinitionNode.setTreeId(treeId);
//		
//		for (Project project : workspace.getProjects()){
//			ProcessDefinitionNode node = new ProcessDefinitionNode(project);
//			node.setTreeId(treeId);
//	
//			processDefinitionNode.add(node);
//		
//		}
//		Tree tree = new Tree();
//		tree.setId(treeId);
//		tree.setNode(processDefinitionNode);
//
//		setProcessDefinitionTree(tree);

	}

	@AutowiredFromClient
	ProcessNameView processNameView;



}
