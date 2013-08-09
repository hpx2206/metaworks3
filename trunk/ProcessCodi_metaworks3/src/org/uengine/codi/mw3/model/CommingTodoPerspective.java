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
		
		session.setLastPerspecteType("inbox");
		session.setLastSelectedItem("commingTodo");
			
		IInstance instanceContents = Instance.load(new Navigation(session), 0, Integer.parseInt(GlobalContext.getPropertyString("commingtodo.list.count", "2")));
		instanceContents.getMetaworksContext().setHow("commingTodo");
		this.setInstance(instanceContents);	
	}
}
