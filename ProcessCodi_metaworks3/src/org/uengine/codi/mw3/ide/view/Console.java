package org.uengine.codi.mw3.ide.view;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.uengine.codi.mw3.ide.CloudContent;

@Face(displayName="$Console")
public class Console implements CloudContent{

	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String name;
		@Name
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
		
	public Console(){
		this.setId("console");
		this.setType(this.getId());
		this.setName("$Console");
	}
		
}
