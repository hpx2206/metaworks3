package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class TodoBadge{
	
	int newItemCount;
		public int getNewItemCount() {
			return newItemCount;
		}
		public void setNewItemCount(int newItemCount) {
			this.newItemCount = newItemCount;
		}
	
	public TodoBadge(){
		
	}
	
	@ServiceMethod
	public void refresh() throws Exception{
	
		/*
		PersonalPerspective personalPerspective = new PersonalPerspective();

		personalPerspective.session = session;
		personalPerspective.session.getMetaworksContext().setWhen("todoBadge");
		personalPerspective.loadInbox();
		personalPerspective.session.getMetaworksContext().setWhen(null);
		*/
		
		setNewItemCount(session.getTodoListCount());
	}
	
	@ServiceMethod(target="popup", loader="org.uengine.codi.mw3.model.Popup")
	public Object[] showList() throws Exception{

/*		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		personalPerspective.loadInbox();
*/		
		
		session.getMetaworksContext().setWhen("todoBadge");
		session.setLastPerspecteType("inbox");
		session.setSearchKeyword(null);
		
		InstanceList instList = new InstanceList();
		instList.load(session);
		
		setNewItemCount(session.getTodoListCount());
		
		Popup popup = new Popup();
		popup.setName("Todo List");
		popup.setPanel(instList);
		
		return new Object[]{new Refresh(this), popup};
	}
	
	@AutowiredFromClient
	public Session session;

}
