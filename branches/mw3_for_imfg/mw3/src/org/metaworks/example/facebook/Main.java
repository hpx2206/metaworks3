package org.metaworks.example.facebook;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public class Main {

	IPosting newsfeed;
		public IPosting getNewsfeed() {
			return newsfeed;
		}
		public void setNewsfeed(IPosting newsfeed) {
			this.newsfeed = newsfeed;
		}
		
	IPosting newPosting;
		public IPosting getNewPosting() {
			return newPosting;
		}
		public void setNewPosting(IPosting newPosting) {
			this.newPosting = newPosting;
		}

	IPerson loginUser;
		public IPerson getLoginUser() {
			return loginUser;
		}
		public void setLoginUser(IPerson loginUser) {
			this.loginUser = loginUser;
		}

	ILogin loginInfo;
		@Id
		public ILogin getLoginInfo() {
			return loginInfo;
		}
		public void setLoginInfo(ILogin loginInfo) {
			this.loginInfo = loginInfo;
		}
		
		
	@ServiceMethod
	public ILogin logout() throws Exception{
		
		MetaworksContext context = new MetaworksContext();
		context.setWhen(IDAO.WHEN_EDIT);
		
		loginInfo.setMetaworksContext(context);
		
		return loginInfo;
	}
	
	@ServiceMethod
	public Main refresh() throws Exception{
		return new Main(getLoginInfo());
	}
	
	
	public Main(){}
	
	protected Main(ILogin loginInfo) throws Exception{
		
		this.loginInfo = loginInfo;
		loginInfo.setPassword("");

		//set login user
		loginUser = new Person();
		loginUser.setName(loginInfo.getUserId());
		loginUser = loginUser.fill();
		
		//set the new posting default. 
		newPosting = new Posting();
		newPosting.setWriter(getLoginUser());
		
		// lets the new posting can be new entry form
		MetaworksContext context = new MetaworksContext();
		context.setWhen(Posting.WHEN_NEW);
		
		newPosting.setMetaworksContext(context);
		
//		setLoginUser(loginUser);
		
		//set newsfeed
		Posting newsFeed = new Posting();
		newsFeed.setWriter(loginUser);
		
		setNewsfeed(newsFeed.getNewsFeed());
	}
}
