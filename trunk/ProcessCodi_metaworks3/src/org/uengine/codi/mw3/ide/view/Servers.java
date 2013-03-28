package org.uengine.codi.mw3.ide.view;

import org.metaworks.annotation.Face;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.CloudContent;
import org.uengine.codi.mw3.ide.ServerNode;

@Face(displayName="$Servers", ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class Servers implements CloudContent{

	Tree serverTree;
		public Tree getServerTree() {
			return serverTree;
		}
		public void setServerTree(Tree serverTree) {
			this.serverTree = serverTree;
		}

	public Servers(){
		
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
