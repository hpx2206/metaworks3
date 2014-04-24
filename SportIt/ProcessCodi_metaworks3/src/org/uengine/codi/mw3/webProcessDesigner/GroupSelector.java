package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Dept;
import org.uengine.codi.mw3.model.DeptPanel;
import org.uengine.codi.mw3.model.SearchBox;
import org.uengine.codi.mw3.model.User;

public class GroupSelector extends Selector {	

	public GroupSelector(){
	}
		
	@Override
	public void load() throws Exception {
		
		DeptPanel deptList = new DeptPanel();
		deptList.setId("/ROOT/");
		deptList.setPicker(true);
		deptList.setSearchBox(new SearchBox());
		deptList.loadWithCheckFollowedDept(session);
		
		this.setTarget(deptList);
		
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow(MetaworksContext.HOW_EVER);
	}	
	
	@ServiceMethod(callByContent=true, eventBinding=EventContext.EVENT_CHANGE)
	public void valueSetting() throws Exception{
		if( this.target != null && this.target instanceof Dept){
			this.setName(((Dept)target).getPartName());
		}
	}
}
