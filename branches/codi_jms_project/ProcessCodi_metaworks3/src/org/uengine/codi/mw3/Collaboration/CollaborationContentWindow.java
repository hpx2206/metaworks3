package org.uengine.codi.mw3.collaboration;

import java.util.ArrayList;
import java.util.List;

import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.Session;

public class CollaborationContentWindow extends Window{
	String id;
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

	public CollaborationContentWindow(){
		super();
	}
	
	public CollaborationContentWindow(Session session) throws Exception{
		List<Object> tabs = new ArrayList<Object>();
		
		this.setId(id);
		this.setTabs(tabs);
		
		setPanel(new CollaborationContentPanel(session));
		setTitle("$FavoriteContent");
		
	}
	
	
	
	
}
