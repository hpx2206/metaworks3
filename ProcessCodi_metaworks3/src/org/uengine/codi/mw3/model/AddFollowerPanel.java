package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.ILogin;

public class AddFollowerPanel implements ContextAware{
	
	public AddFollowerPanel(){
		
		setMetaworksContext(new MetaworksContext());
		
	}
	
	public AddFollowerPanel(IUser loginUser, String instanceId) throws Exception{
		this();
		
//		ContactListPanel contactListPanel = new ContactListPanel();
//		contactListPanel.getMetaworksContext().setWhen("addFollower");		
//		contactListPanel.load(loginUser.getUserId());		
		
		ContactPanel contactPanel = new ContactPanel(loginUser);
		contactPanel.getContactListPanel().setId("addFollower");
		contactPanel.getContactListPanel().getLocalContactList().getMetaworksContext().setWhen("addFollower");		
		contactPanel.getContactListPanel().getSocialContactList().getMetaworksContext().setWhen("addFollower");
		contactPanel.getUser().getMetaworksContext().setWhen("addFollower");
		
		Window window = new Window(contactPanel, "Add Follower");
		setContactPanel(window);
		
//		setContactListPanel(contactListPanel);
		setInstanceId(instanceId);
	}
	
	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
	
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}

		
	Window contactPanel;
		public Window getContactPanel() {
			return contactPanel;
		}
	
		public void setContactPanel(Window contactPanel) {
			this.contactPanel = contactPanel;
		}
	
		
//	ContactListPanel contactListPanel;
//		public ContactListPanel getContactListPanel() {
//			return contactListPanel;
//		}
//		public void setContactListPanel(ContactListPanel contactListPanel) {
//			this.contactListPanel = contactListPanel;
//		}


	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

}
