package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class InstanceListPanel {
	
	public InstanceListPanel(){}
	
	public InstanceListPanel(Session session) throws Exception{
		this.instList = new InstanceList();
		this.instList.load(session.getUser(), null);
		
		this.searchBox = new SearchBox(session.getUser());
/*		this.searchBox.setMetaworksContext(new MetaworksContext());		
		this.searchBox.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);;	*/	
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
	public ContentWindow newInstance() throws Exception{
		return new NewInstancePanel();
	}
		

}
