package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class SearchBox {
	
	@AutowiredFromClient
	public Session session;
	
	public SearchBox(){		
	}
	
	String keyword;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		
	@ServiceMethod(callByContent=true)
	public Object search() throws Exception{
		System.out.println("keyword : " + getKeyword());
		
		InstanceList instanceList = new InstanceList();
		instanceList.init();
		instanceList.setKeyword(getKeyword());
		instanceList.load(session);
		
		//instanceListPanel.setSearchBox(this);
				
		return instanceList;		
	}	
}
