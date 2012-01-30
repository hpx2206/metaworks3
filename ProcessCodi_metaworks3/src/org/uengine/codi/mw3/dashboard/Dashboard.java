package org.uengine.codi.mw3.dashboard;

import org.uengine.codi.mw3.model.InstanceListPanel;

public class Dashboard {
	
	Plan plan;
	
		public Plan getPlan() {
			return plan;
		}
	
		public void setPlan(Plan plan) {
			this.plan = plan;
		}

		
	Usage usage;
	
		public Usage getUsage() {
			return usage;
		}
	
		public void setUsage(Usage usage) {
			this.usage = usage;
		}
		
	InstanceListPanel instanceListPanel;

		public InstanceListPanel getInstanceListPanel() {
			return instanceListPanel;
		}
	
		public void setInstanceListPanel(InstanceListPanel instanceListPanel) {
			this.instanceListPanel = instanceListPanel;
		}
}
