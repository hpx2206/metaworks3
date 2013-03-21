package org.uengine.codi.mw3.ide.view;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;

@Face(displayName="$Navigator", ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class Navigator {

	ResourceTree resourceTree;
		public ResourceTree getResourceTree() {
			return resourceTree;
		}
		public void setResourceTree(ResourceTree resourceTree) {
			this.resourceTree = resourceTree;
		}
		
	public void load(String sourceCodeBase, String projectName){
		ResourceNode projectNode = new ResourceNode();
		projectNode.setRoot(true);
		projectNode.setFolder(true);
		projectNode.setType(ResourceNode.TYPE_FOLDER);
		projectNode.setId(sourceCodeBase);
		projectNode.setName(projectName);
		
		ResourceTree resourceTree = new ResourceTree();
		resourceTree.setId(sourceCodeBase);
		resourceTree.setNode(projectNode);

		this.setResourceTree(resourceTree);		
	}
}
