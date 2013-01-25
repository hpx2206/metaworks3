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
	
	public AddFollowerPanel(Session session, String instanceId, String type) throws Exception{
		this();
		FollowerSelectPanel followerSelectPanel = new FollowerSelectPanel();
		followerSelectPanel.load(session, type);
		
		Window window = new Window(followerSelectPanel, "Add Follower");
		setContactPanel(window);
		/*
		ContactPanel contactPanel = new ContactPanel(loginUser);
		contactPanel.getContactListPanel().setId(type);
		contactPanel.getContactListPanel().getLocalContactList().getMetaworksContext().setWhen(type);		
		contactPanel.getContactListPanel().getSocialContactList().getMetaworksContext().setWhen(type);
		contactPanel.getUser().getMetaworksContext().setWhen(type);
		
		Window window = new Window(contactPanel, "Add Follower");
		setContactPanel(window);
		
//		setContactListPanel(contactListPanel);
 * 
 */
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
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

}
