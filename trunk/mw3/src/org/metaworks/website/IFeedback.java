package org.metaworks.website;

import java.util.Date;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

@Face(options={"hideAddBtn"}, values={"true"})
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
	
	
	public String getWriterId();
	public void setWriterId(String writerId);
	
	public String getWriterName();
	public void setWriterName(String writerName);
	
	@ORMapping(objectFields = {"userId", "name"}, databaseFields = {"writerId", "writerName"})
	public IFacebookLoginUser getWriter();
	public void setWriter(IFacebookLoginUser writer);

	@ServiceMethod(callByContent=true, when=WHEN_NEW)
	public FeedbackPanel post() throws Exception;
	
	@ServiceMethod(when=WHEN_VIEW)
	public FeedbackPanel delete() throws Exception;
	
}
