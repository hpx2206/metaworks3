package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class ContactSearchBox extends SearchBox implements ContextAware{

	public ContactSearchBox(){	
		super();
	}

	@ServiceMethod(callByContent=true)
	public Object[] search() throws Exception{
		String userId = session.getUser().getUserId();

		ContactListPanel contactListPanel = new ContactListPanel();		
		contactListPanel.setMetaworksContext(getMetaworksContext());
		contactListPanel.load(userId, getKeyword());
		contactListPanel.getLocalContactList().setMetaworksContext(getMetaworksContext());
		contactListPanel.getSocialContactList().setMetaworksContext(getMetaworksContext());
		
		return new Object[]{contactListPanel}; //contactListPanel.getLocalContactList(), contactListPanel.getSocialContactList()};
	}
	
	MetaworksContext metaworksContext;
	
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
}
