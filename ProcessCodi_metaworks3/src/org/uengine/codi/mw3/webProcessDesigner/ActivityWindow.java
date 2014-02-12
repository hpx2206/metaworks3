package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;


public class ActivityWindow  {

	String id;
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	ActivityPanel activityPanel;
		public ActivityPanel getActivityPanel() {
			return activityPanel;
		}
		public void setActivityPanel(ActivityPanel activityPanel) {
			this.activityPanel = activityPanel;
		}

	public ActivityWindow(){
		activityPanel = new ActivityPanel();
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] apply(){
		String viewId = this.getActivityPanel().getActivity().getActivityView().getId();
		return new Object[]{new ApplyProperties( viewId, this.getActivityPanel()), new Remover(new PropertiesWindow())};
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] cancel(){
		return new Object[]{new Remover(new PropertiesWindow())};
		
	}
}
