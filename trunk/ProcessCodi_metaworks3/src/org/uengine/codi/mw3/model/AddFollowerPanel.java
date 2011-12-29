package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;

public class AddFollowerPanel extends ContactPanel{
	
	public AddFollowerPanel(){}
	
	public AddFollowerPanel(ILogin loginUser, String instanceId) throws Exception{
		super(loginUser);
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
		
}
