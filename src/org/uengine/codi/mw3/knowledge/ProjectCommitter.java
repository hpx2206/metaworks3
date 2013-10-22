package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Contact;
import org.uengine.codi.mw3.model.IContact;
import org.uengine.codi.mw3.model.IDept;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TopicFollowers;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;

public class ProjectCommitter {
	@AutowiredFromClient
	transient public Session session;
	
	String projectName;
		public String getProjectName() {
			return projectName;
		}
		
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
	
	String account;
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
	
	String password;
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
	
	String managerAccount;
		public String getManagerAccount() {
			return managerAccount;
		}
	
		public void setManagerAccount(String managerAccount) {
			this.managerAccount = managerAccount;
		}
	String userId;
		public String getUserId() {
			return userId;
		}
	
		public void setUserId(String userId) {
			this.userId = userId;
		}

	String topicId;
		public String getTopicId() {
			return topicId;
		}
	
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}

	IContact contact;
		public IContact getContact() {
			return contact;
		}
	
		public void setContact(IContact contact) {
			this.contact = contact;
		}



	ArrayList<SvnUser> svnUserList;
		public ArrayList<SvnUser> getSvnUserList() {
			return svnUserList;
		}
		public void setSvnUserList(ArrayList<SvnUser> svnUserList) {
			this.svnUserList = svnUserList;
		}
		

	public ProjectCommitter(){
		svnUserList = new ArrayList<SvnUser>();
	}
	
	public void load() throws Exception{
		
		// TODO: (svn  연동)load svn user
		  String host = GlobalContext.getPropertyString("vm.manager.ip");
		  String userId = GlobalContext.getPropertyString("vm.manager.user");
		  String passwd = GlobalContext.getPropertyString("vm.manager.password");
		  String command = null;
		
		  JschCommand jschServerBehaviour = new JschCommand();
		  jschServerBehaviour.sessionLogin(host, userId, passwd);
			
		command = GlobalContext.getPropertyString("vm.svn.userInfo") + " repos";
	
		String tmp = jschServerBehaviour.runCommand(command);
		String svnUser = null;
		Contact contact = new Contact();
		contact.setUserId(session.getUser().getUserId());
//		String code = tmp;
//		int codeLength = code.indexOf("# harry = harryssecret# sally = sallyssecret");
//		if(codeLength>=0){
//			tmp = code.substring(codeLength+44);
//			
//		}
//		System.out.println(tmp);
		
		int count = 0;
		char[] c = tmp.toCharArray();
		for(int i = 0; i < c.length; i++){
			if(c[i] == ','){
				count++;
			}
		}
		
		System.out.println(",의 갯수 " + count);
		
		if(count > 0){
			StringTokenizer st = new StringTokenizer(tmp,",");
			while(st.hasMoreTokens()){
				svnUser = st.nextToken();
				System.out.println(svnUser);
				SvnUser SvnUser = new SvnUser(svnUser);
				SvnUser.getMetaworksContext().setHow("svnUser");
				SvnUser.setIsJoined(true);
				svnUserList.add(SvnUser);
			}
		}
		System.out.println(svnUserList.size());
		IContact friends = contact.loadFriendsForCommitter(tmp);
		
//		IContact friends = contact.loadContacts(true);
		friends.getMetaworksContext().setHow("comitter");
		
		setContact(friends);
	}
	
		
	@Face(displayName=">>>")
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_APPEND)
	public Object addCommitter() throws Exception{
		
		Object svn = null;
		contact.beforeFirst();
		int size = svnUserList.size();
		System.out.println(""+getContact().size());
		while(contact.next()){
			if(contact.isChecked()){
				for(int i=0; i<size; i++){
					// TODO: add svn user 
//					String tempId = contact.getFriendId();
//					StringTokenizer st = new StringTokenizer(tempId,",");
//					String friendId = st.nextToken();
				
					if(svnUserList.get(i).getCommittor().equals(contact.getFriendId())){
						break;
					}
					if(i == size-1){
					String host = GlobalContext.getPropertyString("vm.manager.ip");
					  String userId = GlobalContext.getPropertyString("vm.manager.user");
					  String passwd = GlobalContext.getPropertyString("vm.manager.password");
					  String command = null;
					  this.setAccount(contact.getFriendId());
						  
				    JschCommand jschServerBehaviour = new JschCommand();
				    jschServerBehaviour.sessionLogin(host, userId, passwd);
					String password = "1111";
					setPassword(password);
		//		 	command = GlobalContext.getPropertyString("vm.svn.createUser") + " " + this.getProjectName() + " @" + this.getAccount() + "@ " + getPassword() + " ";
				 	command = GlobalContext.getPropertyString("vm.svn.createUser") + " " + "repos" + " " + this.getAccount() + " " + getPassword() + " ";
				 	jschServerBehaviour.runCommand(command);
					
					SvnUser su = new SvnUser(getAccount());
					su.setIsJoined(true);
					su.setIsChecked(true);
					
					TopicFollowers tf = new TopicFollowers();
					TopicMapping tm = new TopicMapping();
					tm.setTopicId(this.getTopicId());
					tm.setUserId(this.getUserId());

					if(!tm.findByUser().next()){
						tm.setUserName(this.getAccount());
						tm.saveMe();
						tm.flushDatabaseMe();
					}
					tf.session = session;
					tf.load();
					
					 svnUserList.add(su);
					}
				}
			}
		}
//		this.load();
		return this;
	
	}
	@Face(displayName="<<<")
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_APPEND)
	public Object removeCommitter() throws Exception{
		
		CopyOnWriteArrayList newUserList = new CopyOnWriteArrayList();
		newUserList.addAll(svnUserList);
		int size = newUserList.size();
		int i = 0;
		SvnUser su = null;
		Iterator<SvnUser> iterator = newUserList.iterator();
		while(iterator.hasNext()){
			i++;
			su = iterator.next();
			if(su.getIsChecked()){
			  String host = GlobalContext.getPropertyString("vm.manager.ip");
			  String userId = GlobalContext.getPropertyString("vm.manager.user");
			  String passwd = GlobalContext.getPropertyString("vm.manager.password");
			  String command = null;
				
			  JschCommand jschServerBehaviour = new JschCommand();
			  jschServerBehaviour.sessionLogin(host, userId, passwd);
	//		  command = GlobalContext.getPropertyString("vm.svn.userDelete") + " " + this.getProjectName() + " " + this.getAccount() + " " + this.getPassword();
			  command = GlobalContext.getPropertyString("vm.svn.userDelete") + " " + "repos" + " " + su.getCommittor() + " " + "1111";
			  String list = jschServerBehaviour.runCommand(command);
			  newUserList.remove(i-1);
			}
		}
		
		this.load();
		return new Refresh(this);
	}
	@Face(displayName="수정")
	@ServiceMethod(callByContent=true)
	public void modify() throws Exception{
		if(this.getAccount().equals(this.getManagerAccount())){
			
		}else{
			new Exception("관리자가 아닙니다");
		}
//		return new 
	}
	@Face(displayName="취소")
	@ServiceMethod(callByContent = true)
	public Object cancel() throws Exception{
		return new Remover(this);
	}
	
	
}
