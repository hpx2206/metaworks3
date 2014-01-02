package org.uengine.codi.mw3.admin;

import org.uengine.codi.mw3.common.FacebookComments;
import org.uengine.codi.mw3.knowledge.WfPanel;
import org.uengine.codi.mw3.model.Session;

public class HintPanel {
	
	public HintPanel() throws Exception {
	}
	
	
	
	public void load(Session session, String pageName) throws Exception {
		facebookComments = new FacebookComments(pageName);
		facebookComments.setUrl(pageName);
		
		WfPanel wfPanel = new WfPanel();
		wfPanel.session = session;
		wfPanel.load(session.getCompany().getComCode(), "read");
		
		setWfPanel(wfPanel);		
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
	
	WfPanel wfPanel;
		public WfPanel getWfPanel() {
			return wfPanel;
		}
		public void setWfPanel(WfPanel wfPanel) {
			this.wfPanel = wfPanel;
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
