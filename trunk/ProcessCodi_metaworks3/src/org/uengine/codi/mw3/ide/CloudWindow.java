package org.uengine.codi.mw3.ide;

import java.util.ArrayList;
import java.util.List;

import org.metaworks.annotation.Face;

public class CloudWindow {

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	List<Object> tabs;
		@Face(ejsPath="dwr/metaworks/genericfaces/Tab.ejs")
		public List<Object> getTabs() {
			return tabs;
		}
		public void setTabs(List<Object> tabs) {
			this.tabs = tabs;
		}
		
	public CloudWindow(){
		this(null);
	}
	
	public CloudWindow(String id){
		List<Object> tabs = new ArrayList<Object>();

		this.setId(id);
		this.setTabs(tabs);

	}
	
}
