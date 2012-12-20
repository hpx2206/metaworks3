package org.metaworks.component;

import org.metaworks.annotation.Id;

public class Tree {
	
	public final static String ALIGN_LEFT			= "left";
	public final static String ALIGN_RIGHT			= "right";
	
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	String align;
		public String getAlign() {
			return align;
		}
		public void setAlign(String align) {
			this.align = align;
		}

	TreeNode node;
		public TreeNode getNode() {
			return node;
		}
		public void setNode(TreeNode node) {
			this.node = node;
		}
		
	public Tree() {
		this.setAlign(ALIGN_LEFT);		
	}
}
	
