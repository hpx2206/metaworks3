package org.metaworks.example;

public interface IFaceBook {
	public IPosting post(Posting posting) throws Exception;
	
	public IPosting getNewsFeed() throws Exception;
	
	public IPosting getPosting(int postingId) throws Exception;
	
	public IPerson addFriend(IPerson person) throws Exception;
	
}
