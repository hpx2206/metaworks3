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

	@AutowiredFromClient
	public Session session;

	public TodoBadge(){
		
	}
	
	@ServiceMethod
	public void refresh() throws Exception{
	
		/*PersonalPerspective personalPerspective = new PersonalPerspective();

		personalPerspective.session = session;
		personalPerspective.session.getMetaworksContext().setWhen("todoBadge");
		personalPerspective.loadInbox();
		personalPerspective.session.getMetaworksContext().setWhen(null);*/
		
		setNewItemCount(Instance.countTodo(session));
	}
	
	@ServiceMethod(target="popup", loader="org.uengine.codi.mw3.model.Popup")
	public Object[] showList() throws Exception{

/*		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		personalPerspective.loadInbox();
*/		
		
		session.setLastPerspecteType("inbox");
		session.setSearchKeyword(null);
		
		InstanceList instList = new InstanceList(session);
		instList.load();
		
		Popup popup = new Popup();
		popup.setName("Todo List");
		popup.setPanel(instList);
		
		this.refresh();
		
		return new Object[]{new Refresh(session), new Refresh(this), popup};
	}
}
