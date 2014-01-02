package org.uengine.codi.mw3.ide;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.metaworks.annotation.Face;

@Face(options={"tabClass"}, values={"org.uengine.codi.mw3.ide.CloudTab"})
public class CloudWindow {

	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	List<Object> tabs;
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
