package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Role;
import org.uengine.codi.mw3.model.SearchBox;


public class RoleSelector extends Selector {	

	public RoleSelector(){
	}
		
	@Override
	public void load() throws Exception {
		
		org.uengine.codi.mw3.model.RolePanel roleList = new org.uengine.codi.mw3.model.RolePanel();
		roleList.setId("/ROOT/");
		roleList.setSearchBox(new SearchBox());
		roleList.session = session;
		roleList.setPicker(true);
		roleList.load();
		
		this.setTarget(roleList);
		
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow(MetaworksContext.HOW_EVER);
	}
	
	@ServiceMethod(callByContent=true, eventBinding=EventContext.EVENT_CHANGE)
	public void valueSetting() throws Exception{
		if( this.target != null && this.target instanceof Role){
			this.setName(((Role)target).getRoleName());
		}
	}
}