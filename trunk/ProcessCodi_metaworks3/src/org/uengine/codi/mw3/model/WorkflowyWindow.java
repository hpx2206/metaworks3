package org.uengine.codi.mw3.model;

import java.net.UnknownHostException;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

import com.mongodb.MongoException;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Workflowy", options={"hideLabels"}, values={"true"})
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

	@ServiceMethod(when=MetaworksContext.WHEN_NEW)
	public void load() throws UnknownHostException, MongoException{
		setWorkflowy(new Workflowy());		
	}
}
