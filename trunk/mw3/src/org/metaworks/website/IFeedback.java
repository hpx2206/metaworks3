package org.metaworks.website;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IFeedback extends IDAO{

	
	@Id
	public int getFeedbackId();
	public void setFeedbackId(int feedbackId);
	
	public int getMenuId();
	public void setMenuId(int menuId);
	
	public Date getPostdate();
	public void setPostdate(Date postdate);
	
	public String getText();
	public void setText(String text);
	
	
	public int getWriterId();
	public void setWriterId(int writerId);
	
	@ORMapping(objectFields = {"userId"}, databaseFields = {"writerId"})
	public IUser getWriter();
	public void setWriter(IUser writer);

	@ServiceMethod(callByContent=true)
	public FeedbackPanel post() throws Exception;
	
	@ServiceMethod
	public FeedbackPanel delete() throws Exception;
	
}
