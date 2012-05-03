package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;


public class AddContactPanel {
	
	public AddContactPanel() {
		this.friends = new User();
	}
	
	IUser friends;
		public IUser getFriends() {
			return friends;
		}
		public void setFriends(IUser friends) {
			this.friends = friends;
		}

	@AutowiredFromClient
	public Session session;

	
}
