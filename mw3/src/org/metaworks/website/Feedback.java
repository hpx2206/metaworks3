package org.metaworks.website;

import java.util.Date;

import org.metaworks.MetaworksObject;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;

public class Feedback extends MetaworksObject<IFeedback> implements IFeedback {

	int feedbackId;
		public int getFeedbackId() {
			return feedbackId;
		}
		public void setFeedbackId(int feedbackId) {
			this.feedbackId = feedbackId;
		}
	
	int menuId;
		public int getMenuId() {
			return menuId;
		}
		public void setMenuId(int menuId) {
			this.menuId = menuId;
		}

	Date postdate;
	String text;

		
	String writerId;
		public String getWriterId() {
			return writerId;
		}
		public void setWriterId(String writerId) {
			this.writerId = writerId;
		}

	String writerName;
		public String getWriterName() {
			return writerName;
		}
		public void setWriterName(String writerName) {
			this.writerName = writerName;
		}

	IFacebookLoginUser writer;
		public IFacebookLoginUser getWriter() {
			return writer;
		}
		public void setWriter(IFacebookLoginUser writer) {
			this.writer = writer;
		}
	
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public static IFeedback loadFeedback(IMenu menu) throws Exception{
		
		IFeedback homeFeedback = (IFeedback) Database.sql(IFeedback.class, "select * from feedback where menuId = ?menuId");
		homeFeedback.setMenuId(menu.getMenuId()); //TODO: later we will just set setMenu(IMenu menu) insteadof setMenuId
		homeFeedback.select();
		
		return homeFeedback;
		
	}
	
	public FeedbackPanel post() throws Exception {
		
		setWriter(loginUser);
		
		try{
			IDAO id = Database.sql(IDAO.class, "select max(feedbackId) 'feedbackId' from feedback");
			id.select();
			id.next();

			setFeedbackId(id.getInt("feedbackId") + 1);
		}catch(Exception e){
			
		}
		
		createDatabaseMe();
		syncToDatabaseMe();
		flushDatabaseMe();
		
		FeedbackPanel panel = new FeedbackPanel();
		panel.loginUser = loginUser; //TODO: it's annoying and error-prone too.
		panel.load(contentPanel.getMenu());
		
		return panel;
	}
	
	public FeedbackPanel delete() throws Exception{
		deleteDatabaseMe();
		//flushDatabaseMe();
		
		FeedbackPanel panel = new FeedbackPanel();
		panel.load(contentPanel.getMenu());
		
		return panel;		
	}

	
	@AutowiredFromClient
	public ContentPanel contentPanel;
	
	@AutowiredFromClient
	public IFacebookLoginUser loginUser;

}
