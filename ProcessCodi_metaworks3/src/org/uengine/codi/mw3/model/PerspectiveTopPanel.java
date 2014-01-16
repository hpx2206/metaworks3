package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

public class PerspectiveTopPanel {

	@AutowiredFromClient
	public Session session;
	
	SearchBox searchBox;		
		@Face(options={"keyupSearch"}, values={"true"})
		public SearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(SearchBox searchBox) {
			this.searchBox = searchBox;
		}

	public PerspectiveTopPanel(){
		InstanceSearchBox searchBox = new InstanceSearchBox();
		searchBox.setKeyUpSearch(true);
		searchBox.setKeyEntetSearch(true);
		setSearchBox(searchBox);
	}
	
	@ServiceMethod
	public ContentWindow newInstance() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.load(session);
		
		NewInstanceWindow window = new NewInstanceWindow(newInstancePanel);
		if( session.getWindowTitle() != null){
			window.setTitle(session.getWindowTitle());
		}
		
		return window;
	}

}
