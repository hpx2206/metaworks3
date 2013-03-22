package org.uengine.codi.mw3.ide;

public class CloudTab {

	String id;
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
		
	public CloudTab(){
		this(null);
	}
	
	public CloudTab(String id){
		this.setId(id);
	}
	
}
