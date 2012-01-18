package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs",
      options={"hideLabels", "layoutPanelName"}, values={"true", "worklist"})

public class InstanceListPanel {
	
	public InstanceListPanel(){}
	
	public InstanceListPanel(Session session, String keyword) throws Exception{
		this.instList = new InstanceList();
		this.instList.load(session.login, session.navigation, keyword);
		
		this.searchBox = new SearchBox();
	}

	public InstanceListPanel(Session session) throws Exception{
		this(session, null);
	}
	
	SearchBox searchBox;
		public SearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(SearchBox searchBox) {
			this.searchBox = searchBox;
		}

	InstanceList instList;
		public InstanceList getInstList() {
			return instList;
		}
		public void setInstList(InstanceList instList) {
			this.instList = instList;
		}
		
		
	@ServiceMethod
	public ContentPanel newInstance() throws Exception{
		return new NewInstancePanel();
	}
		

}
