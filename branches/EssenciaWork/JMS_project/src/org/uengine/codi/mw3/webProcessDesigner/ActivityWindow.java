package org.uengine.codi.mw3.webProcessDesigner;


public class ActivityWindow  {

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
}
