package org.uengine.codi.mw3.processexplorer;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class ProcessFormPanel implements ContextAware{
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	Object activityPanel;
		public Object getActivityPanel() {
			return activityPanel;
		}
		public void setActivityPanel(Object activityPanel) {
			this.activityPanel = activityPanel;
		}
		
	public ProcessFormPanel(){
		this.setMetaworksContext(new MetaworksContext());
	}
	
}
