package org.uengine.codi.mw3.marketplace;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.model.Session;

public class AppMapPanel implements ContextAware {
	
	public AppMapPanel() {
		setMetaworksContext(new MetaworksContext());
	}
	
	public void load(Session session) throws Exception {
		
		AppMapList appList = new AppMapList();
		appList.setMetaworksContext(this.getMetaworksContext());
		appList.load(session);
		
		setAppMapList(appList);
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	AppMapList appMapList;
		public AppMapList getAppMapList() {
			return appMapList;
		}
		public void setAppMapList(AppMapList appMapList) {
			this.appMapList = appMapList;
		}
	
}		
