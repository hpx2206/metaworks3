package org.uengine.codi.mw3.selfservice;

import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IAppMapping;
import org.uengine.codi.mw3.model.Session;


/**
 * 
 * @author JISUN
 * 
 * 앱리스트를 호출한다.
 *
 */
public class AppNode {
	
	IAppMapping appMapping;
		public IAppMapping getAppMapping() {
			return appMapping;
		}
		public void setAppMapping(IAppMapping appMapping) {
			this.appMapping = appMapping;
		}
		
	public AppNode() {
		// TODO Auto-generated constructor stub
	}
	
	public void load(Session session) throws Exception {
		
		AppMapping appMp = new AppMapping();
		appMp.setComCode(session.getCompany().getComCode());
		appMp.setIsDeleted(false);
		
		IAppMapping appList =appMp.findMyApps();
		appList.getMetaworksContext().setWhen("filter");
		
		
		setAppMapping(appList);
		
	}

}
