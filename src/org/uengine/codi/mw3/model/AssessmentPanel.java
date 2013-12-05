package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class AssessmentPanel {
	public AssessmentPanel() {
		
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String userName;
		public String getUserName() {
			return userName;
		}
	
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
	ArrayList<Assessment> assessment;
		public ArrayList<Assessment> getAssessment() {
			return assessment;
		}
	
		public void setAssessment(ArrayList<Assessment> assessment) {
			this.assessment = assessment;
		}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Remover save() throws Exception{
		for(int i = 0; i < assessment.size(); i++) {
			assessment.get(i).createDatabaseMe();
		}
		
		return new Remover(new Popup());
		
	}
}
