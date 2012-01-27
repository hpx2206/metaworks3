package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.processmanager.ProcessManagerFactoryBean;
import org.uengine.processmanager.ProcessManagerRemote;

public class InstanceListPanel {
	
	public InstanceListPanel(){}
	
	public InstanceListPanel(Session session) throws Exception{
		this.instList = new InstanceList();
		this.instList.load(session.login, session.navigation, null);
		
		this.searchBox = new SearchBox();
		this.searchBox.setMetaworksContext(new MetaworksContext());		
		this.searchBox.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);;		
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
