package org.uengine.codi.mw3.model;

import java.net.UnknownHostException;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

import com.mongodb.MongoException;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Workflowy", options={"hideLabels"}, values={"true"})
public class WorkflowyWindow {
	
	public WorkflowyWindow(){	
		setWorkflowy(new Workflowy());
	}
	
	Workflowy workflowy;
		public Workflowy getWorkflowy() {
			return workflowy;
		}
		public void setWorkflowy(Workflowy workflowy) {
			this.workflowy = workflowy;
		}

	@ServiceMethod
	public void load() throws UnknownHostException, MongoException{
				
	}
}
