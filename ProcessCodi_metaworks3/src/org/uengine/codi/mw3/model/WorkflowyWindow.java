package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Workflowy")
public class WorkflowyWindow {
	
	public WorkflowyWindow(){		
	}
	
	Workflowy workflowy;
		public Workflowy getWorkflowy() {
			return workflowy;
		}
		public void setWorkflowy(Workflowy workflowy) {
			this.workflowy = workflowy;
		}


	@ServiceMethod
	public void load(){
		setWorkflowy(new Workflowy());		
	}
}
