package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class SearchBox {

	String keyword;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		

	@ServiceMethod(callByContent=true)
	public Object search() throws Exception{
		InstanceListPanel instListPanel = new InstanceListPanel(session, keyword);
		instListPanel.searchBox = this;
		
		return instListPanel;
		
	}
	
	@AutowiredFromClient
	public Session session;
}
