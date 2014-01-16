package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.uengine.kernel.GlobalContext;

public class UpcommingTodoPerspective extends CollapsePerspective {
	
	public UpcommingTodoPerspective() throws Exception {
		this.setLabel("$UpcomingTodo");
		this.setLoader(true);
		this.setSelected(true);
	}
	
	@Override
	protected void loadChildren() throws Exception {
		super.loadChildren();
		
		Navigation navigation = new Navigation(session);
		navigation.setPerspectiveType(Perspective.TYPE_COMMINGTODO);
			
		IInstance instanceContents = Instance.load(navigation, 0, Integer.parseInt(GlobalContext.getPropertyString("commingtodo.list.count", "2")));
		instanceContents.getMetaworksContext().setHow(Perspective.TYPE_COMMINGTODO);
		this.setChild(instanceContents);	
	}
	
	@Override
	@Hidden
	public Popup popupAdd() throws Exception{
		return null;
	}
	
}
