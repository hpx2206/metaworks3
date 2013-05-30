package org.uengine.codi.mw3.ide;

public class Project {

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String path;
		public String getPath() {
			return path;
		}
	
		public void setPath(String path) {
			this.path = path;
		}
		
	boolean close;
		public boolean isClose() {
			return close;
		}
		public void setClose(boolean close) {
			this.close = close;
		}
	
	
}
