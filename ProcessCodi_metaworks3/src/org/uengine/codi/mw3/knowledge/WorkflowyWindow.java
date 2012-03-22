package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/WindowTab.ejs", 
      displayName="Workflowy", 
      options={"hideLabels"}, 
      values={"true"})
public class WorkflowyWindow {
	
	public WorkflowyWindow() throws Exception {	
		setWorkflowy(new Workflowy());
	}
	
	Workflowy workflowy;
		public Workflowy getWorkflowy() {
			return workflowy;
		}
		public void setWorkflowy(Workflowy workflowy) {
			this.workflowy = workflowy;
		}
}
