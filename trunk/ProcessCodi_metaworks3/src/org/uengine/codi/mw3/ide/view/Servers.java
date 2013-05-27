package org.uengine.codi.mw3.ide.view;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.ServerNode;

@Face(displayName="$Servers", ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class Servers {

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
	Tree serverTree;
		public Tree getServerTree() {
			return serverTree;
		}
		public void setServerTree(Tree serverTree) {
			this.serverTree = serverTree;
		}

	public Servers(){
		this.setId("servers");
		this.setType(this.getId());
		this.setName("$Servers");
	}
	
	public void load(){
		ServerNode node = new ServerNode();
		node.setId("servers");
		node.setName("Servers");
		node.setExpanded(true);
		node.setRoot(true);
		node.setHidden(true);
		
		ServerNode tomcatNode = new ServerNode();
		tomcatNode.setId("Tomcat v7.0 Server at 192.168.0.225");
		tomcatNode.setName("Tomcat v7.0 Server at 192.168.0.225 [Stopeed]");
		tomcatNode.setType(TreeNode.TYPE_FILE_SERVER);
		
		node.add(tomcatNode);
		
		Tree tree = new Tree();
		tree.setNode(node);
		
		this.setServerTree(tree);
	}
	
}
