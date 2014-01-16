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
		
		String title = "새로쓰기";
		
		if(Perspective.MODE_TOPIC.equals(session.getLastPerspecteMode()) ||
		   Perspective.MODE_PROJECT.equals(session.getLastPerspecteType())){
			title = "'" + session.getWindowTitle() + "' 에 새로쓰기";
			
			newInstancePanel.setTopicNodeId(session.getLastSelectedItem());
		}

		NewInstanceWindow window = new NewInstanceWindow(newInstancePanel);
		window.setTitle(title);
		
		return window;
	}

}
