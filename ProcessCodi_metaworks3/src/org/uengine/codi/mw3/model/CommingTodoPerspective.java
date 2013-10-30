package org.uengine.codi.mw3.model;

import org.uengine.kernel.GlobalContext;

public class CommingTodoPerspective extends Perspective{
	
	IInstance instance;	
		public IInstance getInstance() {
			return instance;
		}
		public void setInstance(IInstance instance) {
			this.instance = instance;
		}
		
	public CommingTodoPerspective() throws Exception {
		setLabel("CommingTodo");
	}
	
	@Override
	protected void loadChildren() throws Exception {
		
		Navigation navigation = new Navigation(session);
		navigation.setPerspectiveType(Perspective.TYPE_COMMINGTODO);
			
		IInstance instanceContents = Instance.load(navigation, 0, Integer.parseInt(GlobalContext.getPropertyString("commingtodo.list.count", "2")));
		instanceContents.getMetaworksContext().setHow(Perspective.TYPE_COMMINGTODO);
		this.setInstance(instanceContents);	
	}
}
