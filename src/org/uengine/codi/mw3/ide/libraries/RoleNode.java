package org.uengine.codi.mw3.ide.libraries;

import org.metaworks.component.TreeNode;
import org.uengine.kernel.Role;

public class RoleNode extends TreeNode{
	
	Role Role;
		public Role getRole() {
			return Role;
		}
		public void setRole(Role role) {
			Role = role;
		}
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	
}


