package org.metaworks.website;

import java.util.Collection;
import java.util.Date;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;

public class Feedback extends Database<IFeedback> implements IFeedback {

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
	
	String text;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	Date postdate;
		public Date getPostdate() {
			return postdate;
		}
		public void setPostdate(Date postdate) {
			this.postdate = postdate;
		}
	
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
	


	public static IFeedback loadFeedback(IMenu menu) throws Exception{
		
		IFeedback homeFeedback = (IFeedback) Database.sql(IFeedback.class, "select * from feedback where menuId = ?menuId");
		homeFeedback.setMenuId(menu.getMenuId()); //TODO: later we will just set setMenu(IMenu menu) instead of setMenuId
		homeFeedback.select();
		
		return homeFeedback;
		
	}
	
	@ServiceMethod(callByContent=true)
	public FeedbackList post() throws Exception {
		
		setWriter(session.loginUser);
		
		try{
			IDAO id = sql("select max(feedbackId) 'feedbackId' from feedback"); //cohesion is right this!
			id.select();
			id.next();

			setFeedbackId(id.getInt("feedbackId") + 1);
		}catch(Exception e){
			
		}
		
		createDatabaseMe();
		syncToDatabaseMe();
		flushDatabaseMe();
		
		FeedbackList feebackList = new FeedbackList();
		feebackList.session = session; //TODO: it's annoying and error-prone too.
		feebackList.load(session.getMenu());
		
		WebContext wctx = WebContextFactory.get();
		String currentPage = wctx.getCurrentPage();

		   Collection sessions = wctx.getScriptSessionsByPage(currentPage);

		   Util utilAll = new Util(sessions);
		   utilAll.addFunctionCall("mw3.getAutowiredObject('org.metaworks.website.FeedbackList').refresh");
	
		
		return feebackList;
	}
	
	public FeedbackList delete() throws Exception{
		deleteDatabaseMe();
		
		FeedbackList feebackList = new FeedbackList();
		feebackList.session = session;
		feebackList.load(session.getMenu());
		
		return feebackList;		
	}

	@AutowiredFromClient
	public Session session;

}
