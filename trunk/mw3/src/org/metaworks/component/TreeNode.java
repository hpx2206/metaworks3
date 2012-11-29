package org.metaworks.component;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPathForArray="genericfaces/CleanArrayFace.ejs")
public class TreeNode {

	public final static String TYPE_FOLDER = "folder";
	public final static String TYPE_FILE_CSS = "css";
	public final static String TYPE_FILE_HTML = "html";
	public final static String TYPE_FILE_CODE = "page_white_code";
	public final static String TYPE_FILE_TEXT = "page_white_text";
	public final static String TYPE_FILE_IMAGE = "image";
	
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	String parentId;
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}

	boolean root;
		public boolean isRoot() {
			return root;
		}
		public void setRoot(boolean root) {
			this.root = root;
		}
		
	boolean expanded;
		public boolean isExpanded() {
			return expanded;
		}
		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}

	boolean loaded;
		public boolean isLoaded() {
			return loaded;
		}
		public void setLoaded(boolean loaded) {
			this.loaded = loaded;
		}

	ArrayList<TreeNode> child;
		public ArrayList<TreeNode> getChild() {
			return child;
		}
		public void setChild(ArrayList<TreeNode> child) {
			this.child = child;
		}
		
	public TreeNode() {
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();
		
		this.setChild(child);
	}
	
	/*
	 * private function
	 */
	public void add(TreeNode node) {
		this.getChild().add(node);
	}
	
	/*
	 * service method
	 */
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object expand() throws Exception { 
		// Override method
		
		return null;
	}
	
	@ServiceMethod(payload={"id", "name"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object action() throws Exception {
		// Override method
		
		return null;
	}
	
}
