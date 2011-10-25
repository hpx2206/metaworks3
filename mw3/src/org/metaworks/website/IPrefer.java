package org.metaworks.website;

import org.metaworks.dao.IDAO;

public interface IPrefer extends IDAO{
	
	public int getUserId();
	public void setUserId(int userId);
	
	public int getFeedbackId();
	public void setFeedbackId(int feedbackId);
	
}
