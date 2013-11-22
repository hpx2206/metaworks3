package org.uengine.codi.mw3.project.oce;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalPanel;
import org.uengine.codi.mw3.marketplace.MyVendor;
import org.uengine.codi.mw3.model.Session;

public class AppServerManage {

	@AutowiredFromClient
	public Session session;
	
	KtProbProjectServers projectServers;
		public KtProbProjectServers getProjectServers() {
			return projectServers;
		}
		public void setProjectServers(KtProbProjectServers projectServers) {
			this.projectServers = projectServers;
		}


	@ServiceMethod(callByContent=true)
	public Object manageApp() throws Exception {
		MyVendor myVendor = new MyVendor();
		myVendor.load(session);
		
		return new ModalPanel(myVendor);
	}
}
