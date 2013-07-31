package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Hidden;

public class ApplyProperties {

	String id;
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	Object content;
		@Hidden
		public Object getContent() {
			return content;
		}
		public void setContent(Object content) {
			this.content = content;
		}

	public ApplyProperties(String id, Object content){
		this.setId(id);
		this.setContent(content);
	}
}
