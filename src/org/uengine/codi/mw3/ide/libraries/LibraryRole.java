package org.uengine.codi.mw3.ide.libraries;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.Role;

public class LibraryRole {
	
	public static final String ROLE_ACTIVITY = "role";
	
	Role role;
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String displayName;
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	public void load() {
		role = new Role();
		
		TextContext textContext = new TextContext();
		textContext.setText("임시 롤 화면 이름");
		role.setDisplayName(textContext);
		
		role.setName("임시 롤 이름");
		role.setMetaworksContext(getMetaworksContext());
		
		this.setType(ROLE_ACTIVITY);
	}
	
	@ServiceMethod(callByContent = true)
	public Object showProperties() {
		
		
		return null;
	}

}













