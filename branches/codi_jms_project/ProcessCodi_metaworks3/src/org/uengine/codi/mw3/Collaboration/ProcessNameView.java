package org.uengine.codi.mw3.Collaboration;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;

public class ProcessNameView{
	
	public ProcessNameView() {
	}
	
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
		
	@ServiceMethod(callByContent = true)	
	public Object[] changeFavoriteIcon(){
		return null;
	}
}
