package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.Session;

public class InstanceMonitorNavigator {

	@AutowiredFromClient
	public Session session;
	
	IInstance instance;
		public IInstance getInstance() {
			return instance;
		}
		public void setInstance(IInstance instance) {
			this.instance = instance;
		}

	public void load(String instId) throws Exception {
		IInstance instance = Instance.loadProcessFormRootInstId(instId);
		instance.getMetaworksContext().setHow("instanceNavigatorWeb");
		
		this.setInstance(instance);
	}
}
