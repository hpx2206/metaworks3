package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class SearchBox {
	
	@AutowiredFromClient
	public Session session;
	
	public SearchBox(){		
	}

	boolean keyUpSearch;
		public boolean isKeyUpSearch() {
			return keyUpSearch;
		}
		public void setKeyUpSearch(boolean keyUpSearch) {
			this.keyUpSearch = keyUpSearch;
		}
		
	boolean keyEntetSearch;
		public boolean isKeyEntetSearch() {
			return keyEntetSearch;
		}
		public void setKeyEntetSearch(boolean keyEntetSearch) {
			this.keyEntetSearch = keyEntetSearch;
		}

	String keyword;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		
	@ServiceMethod(callByContent=true)
	public Object[] search() throws Exception{
//		
//		InstanceList instanceList = new InstanceList();
//		instanceList.init();
//		instanceList.setKeyword(getKeyword());
//		instanceList.load(session);
//		
//		//instanceListPanel.setSearchBox(this);
//		
//		return new Object[]{instanceList};
		
		session.setSearchKeyword(getKeyword());
		
		return Perspective.loadInstanceListPanel(session, session.getLastPerspecteType(), session.getLastSelectedItem());
	}	
}
