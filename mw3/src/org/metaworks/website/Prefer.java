package org.metaworks.website;

import org.metaworks.MetaworksObject;

public class Prefer extends MetaworksObject<IPrefer> implements IPrefer {
	int userId;
	int feedbackId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	

}
