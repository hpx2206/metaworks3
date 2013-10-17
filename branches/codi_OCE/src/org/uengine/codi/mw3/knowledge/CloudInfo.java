package org.uengine.codi.mw3.knowledge;

import net.sf.hibernate.odmg.Database;

import org.metaworks.annotation.Id;

public class CloudInfo {

	String id;
	@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
}
