package org.metaworks.example;

import org.metaworks.MetaworksObject;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class Posting extends MetaworksObject<IPosting> implements IPosting{


	IPerson writer;
		public IPerson getWriter() {
			return writer;
		}
		public void setWriter(IPerson writer) {
			this.writer = writer;
		}
		
	String document;
		public String getDocument() {
			return document;
		}
		public void setDocument(String document) {
			this.document = document;
		}
		
	boolean likeIt;
		public boolean isLikeIt() {
			return likeIt;
		}
		public void setLikeIt(boolean likeIt) {
			this.likeIt = likeIt;
		}

		
// Services 
	public void like() throws Exception{
		databaseMe().setLikeIt(true); //will automatically synchronize the value

	}
	
	public Object post() throws Exception{
		createDatabaseMe();
		flushDatabaseMe();
		
		//check for tx
	///if(true)throw new Exception("xxx");
		
		setDocument(""); //clear after inserting new
		
		if(WHEN_NEW.equals(getMetaworksContext().getWhen())){
			//return new Object[]{this, getNewsFeed()}; //return two or more return value lets the page refreshes two or more parts.
			
			Login login = new Login();
			login.setUserId(getWriter().getName());
			return new Main(login);  //returns main page and lets whole page refresh
		}
		
		return this;
	}
	
	public void save() throws Exception{
		databaseMe().setDocument(getDocument());
	}
	
	public void delete() throws Exception{
		deleteDatabaseMe();
	}
	
	public IPosting getNewsFeed() throws Exception{
		IPosting postings =  (IPosting) Database.sql(Posting.class, "select * from Posting posting, Person person where posting.writer = person.name");
		postings.select();
		
		if(!postings.next())
			return null;
		
		return postings;
	}
	@Override
	public void postToGooglePlus() {
		// TODO Auto-generated method stub
		
	}

}
