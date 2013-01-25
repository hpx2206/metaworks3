package org.metaworks.website;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;


@Face(
		options={"hideEditBtn", "hideLabels"},
		values={"true", "true"}
)

public class FeedbackList {
	IFeedback feedback;
	public IFeedback getFeedback() {
		return feedback;
	}
	public void setFeedback(IFeedback feedback) {
		this.feedback = feedback;
	}
	
	public void load(IMenu menu) throws Exception{
		setFeedback(Feedback.loadFeedback(menu));		
	}
	
	@ServiceMethod
	public void refresh() throws Exception{
		setFeedback(Feedback.loadFeedback(session.menu));
	}	
	
	
	@AutowiredFromClient
	public Session session;	
	

}
