package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class SearchBox {
	
	public SearchBox(){		
	}
	public SearchBox(IUser user){
		setUser(user);	
	}
	
	public IUser user;
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
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
		InstanceList instList = new InstanceList();
		instList.load(getUser(), keyword);
				
		return instList;
		
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}		
}
