package org.metaworks.example;

import java.util.Collection;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.metaworks.dao.Database;


public class PostingService{

	public IPosting post(IPosting posting) throws Exception{
		try {
			IPosting postingReturned = (IPosting) Database.create(posting.getDocument(), posting);
					
			WebContext wctx = WebContextFactory.get();
			String currentPage = wctx.getCurrentPage();

			   // For all the browsers on the current page:
			   Collection sessions = wctx.getScriptSessionsByPage(currentPage);

			   //TODO: filter other topic's postings;
			   Util utilAll = new Util(sessions);
			   utilAll.addFunctionCall("otherPeoplePosted", posting);
			   
			return postingReturned;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw e;
		} 
	}
	
	public IPosting like(Posting postingFromClient) throws Exception{
		try {
			IPosting postingInServer = (IPosting) Database.get(IPosting.class, postingFromClient.getDocument());
			postingInServer.setLikeIt(true);
			
			return postingInServer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw e;
		}
		
	}
	
}
