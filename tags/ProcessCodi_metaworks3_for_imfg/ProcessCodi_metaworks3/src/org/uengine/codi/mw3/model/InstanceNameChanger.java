package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;


public class InstanceNameChanger {

	String instanceName;
		@Name
		@Range(size=30)
		//@Face(ejsPath="genericfaces/richText.ejs", options={"cols=")
		public String getInstanceName() {
			return instanceName;
		}
		public void setInstanceName(String instanceName) {
			this.instanceName = instanceName;
		}

	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}

	//good example for Refresh
	@ServiceMethod(callByContent=true, keyBinding="enter", target="popup")
	public Refresh change() throws Exception{
		Instance instance = new Instance();
		instance.setInstId(new Long(instanceId));
		instance.databaseMe().setName(getInstanceName());
		
		
		return new Refresh(instance.databaseMe());
	}
	
	
	
}
