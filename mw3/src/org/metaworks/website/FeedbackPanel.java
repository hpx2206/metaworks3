package org.metaworks.website;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"}
)
public class FeedbackPanel {

	FeedbackList feedbackList;
		
		public FeedbackList getFeedbackList() {
			return feedbackList;
		}
		public void setFeedbackList(FeedbackList feedbackList) {
			this.feedbackList = feedbackList;
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
		newFeedback.setWriter(session.loginUser);
		newFeedback.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);

	}
	
	@ServiceMethod
	public void refresh() throws Exception{
		setFeedback(Feedback.loadFeedback(session.menu));
		
		newFeedback = new Feedback();
		newFeedback.setMenuId(session.menu.getMenuId());
		newFeedback.setWriter(session.loginUser);
		newFeedback.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);

	}
	
	
	
	@AutowiredFromClient
	public Session session;
	
}
