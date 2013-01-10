package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class MultiViewPanel implements ContextAware {
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	MuiltiViewTab muiltiViewTab;
		public MuiltiViewTab getMuiltiViewTab() {
			return muiltiViewTab;
		}
		public void setMuiltiViewTab(MuiltiViewTab muiltiViewTab) {
			this.muiltiViewTab = muiltiViewTab;
		}
	InstanceFollowers followers;
		public InstanceFollowers getFollowers() {
			return followers;
		}
		public void setFollowers(InstanceFollowers followers) {
			this.followers = followers;
		}	
	public void load(Session session) throws Exception{
		
		followers = new InstanceFollowers();
		
		MuiltiViewTab muiltiViewTab = new MuiltiViewTab();
		muiltiViewTab.load();
		
		muiltiViewTab.panel.session = session;
		muiltiViewTab.panel.load(session.getCompany().getComCode());	
		
		muiltiViewTab.scheduleCalendar.session = session;
		muiltiViewTab.scheduleCalendar.load();
		
		setMuiltiViewTab(muiltiViewTab);
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
}
