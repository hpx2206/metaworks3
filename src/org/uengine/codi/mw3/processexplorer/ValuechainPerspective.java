package org.uengine.codi.mw3.processexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.webProcessDesigner.MajorProcessDefinitionNode;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ValueChain;
import org.uengine.kernel.ValueChainDefinition;

public class ValuechainPerspective extends Perspective  implements ContextAware {

	ArrayList<Tree> valueChainTreeList;
		public ArrayList<Tree> getValueChainTreeList() {
			return valueChainTreeList;
		}
		public void setValueChainTreeList(ArrayList<Tree> valueChainTreeList) {
			this.valueChainTreeList = valueChainTreeList;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	boolean	isLoaded;
		public boolean isLoaded() {
			return isLoaded;
		}
		public void setLoaded(boolean isLoaded) {
			this.isLoaded = isLoaded;
		}
		
	public ValuechainPerspective() {
		setLabel("Valuechain");
		valueChainTreeList = new ArrayList<Tree>();
		setLoaded(false);
		
		this.setMetaworksContext(new MetaworksContext());
	}
	@Override
	public void loadChildren() throws Exception {
		
		if(!isLoaded()) {
			Workspace workspace = new Workspace();
			workspace.load(session);
			
			for(Project project: workspace.getProjects()){
				File file = new File(project.getPath());
				findChildValueChain(file);
			}
			setLoaded(true);
		}
	}
	
	public void makeValueChain(File childFile) throws Exception{
		String definitionString = Editor.loadByPath(childFile.getPath());
		ValueChainDefinition def = (ValueChainDefinition) GlobalContext.deserialize(definitionString);
		
		TreeNode rootNode = new TreeNode();
		rootNode.setId(childFile.getPath() + "Node");
		TextContext text= def.getName();
		rootNode.setName(text.getText());
		rootNode.setRoot(true);
		rootNode.setExpanded(true);
		rootNode.setType(TreeNode.TYPE_FILE_VALUECHAIN);
		rootNode.setFolder(true);
		
		List<ValueChain> valueChainList = def.getChildValueChains();
		if( valueChainList != null ){
			for(int i=0; i < valueChainList.size(); i++){
				ValueChain valueChain = valueChainList.get(i);
				if( valueChain.getMajorProcessDefinitionNode() != null ){
				    MajorProcessDefinitionNode node = valueChain.getMajorProcessDefinitionNode();
				    node.setMetaworksContext(this.getMetaworksContext());
				    node.injectionMetaworksContext(this.getMetaworksContext(), node.getChild());
				    node.makeMinorNodeToFolder(node.getChild());
				    
				    rootNode.add(node);
				}
			}
		}
		
		Tree tree = new Tree();
		tree.setId(childFile.getPath() + "Tree");
		tree.setNode(rootNode);
		valueChainTreeList.add(tree);
	}
	public void findChildValueChain(File childFile) throws Exception{
		String[] childFilePaths = childFile.list();
		for(int i=0; i<childFilePaths.length; i++){
			File child = new File(childFile.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
			if(child.isDirectory()){
				findChildValueChain(child);
			}else{
				String type = ResourceNode.findNodeType(child.getName());
				if(!type.equals(TreeNode.TYPE_FILE_VALUECHAIN)){
					continue;
				}
				makeValueChain(child);
			}
		}
	}

}
