package org.uengine.codi.mw3.ide;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.menu.WindowContextMenu;
import org.uengine.codi.mw3.model.Session;

public class CloudTab implements Tab {

	@AutowiredFromClient
	public Session session;
	
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
		
	String className;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		
	public CloudTab(){
		this(null);
	}
	
	public CloudTab(String id){
		this.setId(id);
	}
	
	@ServiceMethod(payload={"id", "parentId", "name"},mouseBinding="right", target=ServiceMethodContext.TARGET_STICK)
	public Object[] showContextMenu() {
		session.setClipboard(this);
		
		return new Object[]{new Refresh(session), new WindowContextMenu()};
	}
}
