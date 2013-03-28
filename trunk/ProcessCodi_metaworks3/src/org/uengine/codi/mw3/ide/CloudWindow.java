package org.uengine.codi.mw3.ide;

import java.util.ArrayList;
import java.util.List;

public class CloudWindow {

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	List<CloudContent> tabs;
		public List<CloudContent> getTabs() {
			return tabs;
		}
		public void setTabs(List<CloudContent> tabs) {
			this.tabs = tabs;
		}
		
	public CloudWindow(){
		this(null);
	}
	
	public CloudWindow(String id){
		List<CloudContent> tabs = new ArrayList<CloudContent>();

		this.setId(id);
		this.setTabs(tabs);

	}
	
}
