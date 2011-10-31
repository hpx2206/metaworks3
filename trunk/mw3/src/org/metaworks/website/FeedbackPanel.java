package org.metaworks.website;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"}
)
public class FeedbackPanel {

	IFeedback feedback;
		public IFeedback getFeedback() {
			return feedback;
		}
		public void setFeedback(IFeedback feedback) {
			this.feedback = feedback;
		}
		
	IFeedback newFeedback;
		public IFeedback getNewFeedback() {
			return newFeedback;
		}
		public void setNewFeedback(IFeedback newFeedback) {
			this.newFeedback = newFeedback;
		}

	public void load(IMenu menu) throws Exception{
		setFeedback(Feedback.loadFeedback(menu));
		
		newFeedback = new Feedback();
		newFeedback.setMenuId(menu.getMenuId());
		newFeedback.setWriter(loginUser);
		newFeedback.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);

	}
	
	@AutowiredFromClient
	public IFacebookLoginUser loginUser;
	
}
