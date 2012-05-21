package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class InstanceListPanel {
	
	public InstanceListPanel(){}


	public InstanceListPanel(Session session) {
		super();
		setSearchBox(new SearchBox());
	}
	
	SearchBox searchBox;
		
		public SearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(SearchBox searchBox) {
			this.searchBox = searchBox;
		}

	InstanceList instanceList;
		public InstanceList getInstanceList() {
			return instanceList;
		}
		public void setInstanceList(InstanceList instanceList) {
			this.instanceList = instanceList;
		}
	

	@ServiceMethod
	public ContentWindow newInstance() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.load();
		
		return newInstancePanel;
	}
}
