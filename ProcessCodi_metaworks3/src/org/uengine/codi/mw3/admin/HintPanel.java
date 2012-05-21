package org.uengine.codi.mw3.admin;

import org.uengine.codi.mw3.common.FacebookComments;
import org.uengine.codi.mw3.knowledge.Workflowy;
import org.uengine.codi.mw3.knowledge.WorkflowyPanel;
import org.uengine.codi.mw3.model.IUser;

public class HintPanel {
	
	public HintPanel(IUser user, String pageName) throws Exception {
		facebookComments = new FacebookComments("ide");
		facebookComments.setUrl(pageName);
		
		setWorkflowyPanel(new WorkflowyPanel(new Workflowy(user.getUserId(), "read")));
		
		//hintSearchBox = new HintSearchBox();
	}
	
	/*
	HintSearchBox hintSearchBox;
		public HintSearchBox getHintSearchBox() {
			return hintSearchBox;
		}
		public void setHintSearchBox(HintSearchBox hintSearchBox) {
			this.hintSearchBox = hintSearchBox;
		}
	*/
	
	WorkflowyPanel workflowyPanel;
		public WorkflowyPanel getWorkflowyPanel() {
			return workflowyPanel;
		}
		public void setWorkflowyPanel(WorkflowyPanel workflowyPanel) {
			this.workflowyPanel = workflowyPanel;
		}

	String pageName;	
		public String getPageName() {
			return pageName;
		}
		public void setPageName(String pageName) {
			this.pageName = pageName;
		}

	FacebookComments facebookComments;
		public FacebookComments getFacebookComments() {
			return facebookComments;
		}
		public void setFacebookComments(FacebookComments facebookComments) {
			this.facebookComments = facebookComments;
		}	

}
