package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
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
		InstanceList instList = new InstanceList();
		instList.load(session.login, session.navigation, keyword);
				
		return instList;
		
	}
	
	@AutowiredFromClient
	public Session session;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}		
}
