package org.uengine.codi.mw3.ide;

import org.metaworks.annotation.Id;

public class Source {
	
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
		
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	
}
