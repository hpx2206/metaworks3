package org.metaworks.component;

public class Tree {
	
	public final static String ALIGN_LEFT			= "left";
	public final static String ALIGN_RIGHT			= "right";
	
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
	
