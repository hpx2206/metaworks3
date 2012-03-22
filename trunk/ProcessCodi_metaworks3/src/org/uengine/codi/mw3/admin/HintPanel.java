package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.common.Facebook;

public class HintPanel {
	public HintPanel(String pageName){
		facebookComments = new Facebook();
		facebookComments.setUrl(pageName);
		facebookLike = new Facebook();		
	}
	
	String pageName;	
		public String getPageName() {
			return pageName;
		}
		public void setPageName(String pageName) {
			this.pageName = pageName;
		}

	Facebook facebookComments;
		@Face(ejsPath="org/uengine/codi/mw3/common/FacebookComments.ejs")
		public Facebook getFacebookComments() {
			return facebookComments;
		}
		public void setFacebookComments(Facebook facebookComments) {
			this.facebookComments = facebookComments;
		}	
		
	Facebook facebookLike;
		@Face(ejsPath="org/uengine/codi/mw3/common/FacebookLike.ejs")
		public Facebook getFacebookLike() {
			return facebookLike;
		}
		public void setFacebookLike(Facebook facebookLike) {
			this.facebookLike = facebookLike;
		}	
}
