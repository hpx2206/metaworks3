package org.uengine.codi.mw3.ide.view;

import java.io.File;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;

@Face(displayName="$Navigator", ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class Navigator {

	String id;
		@Id
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String name;
		@Name
		@Hidden
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String type;
		@Hidden
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	ResourceTree resourceTree;
		public ResourceTree getResourceTree() {
			return resourceTree;
		}
		public void setResourceTree(ResourceTree resourceTree) {
			this.resourceTree = resourceTree;
		}
		
	public Navigator(){
		this.setId("navigator");
		this.setType(this.getId());
		this.setName("$Navigator");		
	}
	
	public void load(String sourceCodeBase, String projectName){
		ResourceNode projectNode = new ResourceNode();
		projectNode.setRoot(true);
		projectNode.setFolder(true);
		projectNode.setType(ResourceNode.TYPE_FOLDER);
		projectNode.setId("");
		projectNode.setName(projectName);
		
		ResourceTree resourceTree = new ResourceTree();
		resourceTree.setId(sourceCodeBase);
		resourceTree.setNode(projectNode);

		this.setResourceTree(resourceTree);		
	}
}
