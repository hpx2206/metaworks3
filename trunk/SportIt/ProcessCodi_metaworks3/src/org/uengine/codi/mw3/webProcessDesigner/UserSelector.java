package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.ContactPanel;
import org.uengine.codi.mw3.model.User;

public class UserSelector extends Selector {

	public UserSelector(){
		
	}
	
	@Override
	public void load() throws Exception {
		ContactPanel contactPanel = new ContactPanel();
		contactPanel.session = session;
		contactPanel.setPicker(true);
		contactPanel.load();
		
		this.setTarget(contactPanel);
		
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow(MetaworksContext.HOW_EVER);
	}	
	
	@ServiceMethod(callByContent=true, eventBinding=EventContext.EVENT_CHANGE)
	public void valueSetting() throws Exception{
		if( this.target != null && this.target instanceof User){
			this.setName(((User)target).getName());
		}
	}
	
}
