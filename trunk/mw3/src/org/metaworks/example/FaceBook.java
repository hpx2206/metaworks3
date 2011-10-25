package org.metaworks.example;

import org.metaworks.dao.*;

import java.util.ArrayList;
import java.util.Collection;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;

public class FaceBook implements IFaceBook{
	
	public IPosting post(Posting posting) throws Exception{
		return new PostingService().post(posting);
	}
	
	public IPosting getNewsFeed() throws Exception{
		IPosting postings =  (IPosting) Database.sql(Posting.class, "select * from Posting posting, Person person where posting.writer = person.name");
		postings.select();
		
		if(!postings.next())
			return null;
		
		return postings;
	}
	
	public IPosting getPosting(int postingId) throws Exception{
		IPosting posting = (IPosting) Database.get(Posting.class, new Integer(postingId));
		
		return posting;
	}
	
	public IPerson addFriend(IPerson personFromClient) throws Exception{
		
		IPerson personInServer = (IPerson) Database.get(IPerson.class, personFromClient.getName());
		
		personInServer.setMyFried(true);  //just leave it. it whould be collected and updated automatically.
		
		return personInServer;
	}
	
	public IPosting like(Posting postingFromClient) throws Exception{
		return new PostingService().like(postingFromClient);
		
	}
	
}
