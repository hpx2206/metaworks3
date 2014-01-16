package org.uengine.codi.mw3.model;


public class ProcessInfo extends PerspectiveInfo{

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	public ProcessInfo(){
		
	}
	
	public ProcessInfo(Session session){
		this.session = session;
		this.setId(session.getLastSelectedItem());
	}
	
}
