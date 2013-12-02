package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Contact;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.IContact;
import org.uengine.codi.mw3.model.IEmployee;
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


	IContact contact;
		public IContact getContact() {
			return contact;
		}
	
		public void setContact(IContact contact) {
			this.contact = contact;
		}

	String projectAlias;
		public String getProjectAlias() {
			return projectAlias;
		}
		public void setProjectAlias(String projectAlias) {
			this.projectAlias = projectAlias;
		}

	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
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
		
		  String host = GlobalContext.getPropertyString("vm.manager.ip");
		  String userId = GlobalContext.getPropertyString("vm.manager.user");
		  String passwd = GlobalContext.getPropertyString("vm.manager.password");
		  String command = null;
		
		  JschCommand jschServerBehaviour = new JschCommand();
		  jschServerBehaviour.sessionLogin(host, userId, passwd);
			
		command = GlobalContext.getPropertyString("vm.svn.userInfo") + " " + this.getProjectAlias();
	
		String tmp = jschServerBehaviour.runCommand(command);
		String svnUser = null;
		Employee employee = new Employee();
		IEmployee committor = new Employee();
		
		int count = 0;
		char[] c = tmp.toCharArray();
		for(int i = 0; i < c.length; i++){
			if(c[i] == ','){
				count++;
			}
		}
		
		svnUserList.clear();
		if(count > 0){
			StringTokenizer st = new StringTokenizer(tmp,",");
			while(st.hasMoreTokens()){
				svnUser = st.nextToken();
				employee.setEmail(svnUser);
				committor = employee.findByEmail();
				SvnUser SvnUser = new SvnUser(committor.getEmpCode(), committor.getEmail(), committor.getEmpName());
				SvnUser.getMetaworksContext().setHow("svnUser");
				SvnUser.setIsJoined(true);
				svnUserList.add(SvnUser);
			}
		}
		
		Contact contact = new Contact();
		contact.setUserId(this.getUserId());
		IContact friends = contact.loadContacts(true);
		friends.getMetaworksContext().setHow("comitter");
		
		setContact(friends);
	}
	
		
	@Face(displayName="▶ add")
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_SELF)
	public void addCommitter() throws Exception{
		String host = GlobalContext.getPropertyString("vm.manager.ip");
		String userId = GlobalContext.getPropertyString("vm.manager.user");
		String passwd = GlobalContext.getPropertyString("vm.manager.password");
		String command = null;
		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin(host, userId, passwd);

		if(!contact.getUserId().equals(this.getManagerAccount())){
			throw new Exception("관리자가 아닙니다");
		}else{
			Object svn = null;
			Employee employee = new Employee();
			contact.beforeFirst();
			int size = svnUserList.size();
			if(size == 0){
				//관리자 삭제시 추가
				command = GlobalContext.getPropertyString("vm.svn.createUser") + " " +  this.getProjectAlias() + " " + this.getManagerAccount() + " " + getManagerAccount() + " ";
			 	jschServerBehaviour.runCommand(command);
			}
			
			
			while(contact.next()){
				if(contact.isChecked()){
					for(int i=0; i<size; i++){
						if(svnUserList.get(i).getCommittor().equals(contact.getFriendId())){
							break;
						}
						if(i == size-1){
						this.setAccount(contact.getFriendId());
						
						employee.setEmpCode(contact.getFriendId());
						employee.copyFrom(employee.databaseMe());						
						
					 	command = GlobalContext.getPropertyString("vm.svn.createUser") + " " +  this.getProjectAlias() + " " + employee.getEmail() + " " + employee.getPassword() + " ";
					 	jschServerBehaviour.runCommand(command);
						
						SvnUser su = new SvnUser(employee.getEmpCode(), employee.getEmail(), employee.getEmpName());
						su.setIsJoined(true);
						su.setIsChecked(true);
						
						TopicFollowers tf = new TopicFollowers();
						TopicMapping tm = new TopicMapping();
						tm.setTopicId(this.getProjectId());
						tm.setUserId(this.getAccount());
						
						String tempName = this.getAccount();
						StringTokenizer st = new StringTokenizer(tempName,"@");
						String userName = st.nextToken();
						if(!tm.findByUser().next()){
							tm.setUserName(userName);
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
		}	
		this.load();
	
	}
	@Face(displayName="◀ remove")
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_SELF)
	public void removeCommitter() throws Exception{
		String host = GlobalContext.getPropertyString("vm.manager.ip");
		String userId = GlobalContext.getPropertyString("vm.manager.user");
		String passwd = GlobalContext.getPropertyString("vm.manager.password");
		String command = null;
		Employee employee = new Employee();

		if (!contact.getUserId().equals(this.getManagerAccount())) {
			throw new Exception("관리자가 아닙니다");
		} else {
			CopyOnWriteArrayList newUserList = new CopyOnWriteArrayList();
			newUserList.addAll(svnUserList);
			int size = newUserList.size();
			int i = 0;
			SvnUser su = null;
			Iterator<SvnUser> iterator = newUserList.iterator();
			while (iterator.hasNext()) {
				i++;
				su = iterator.next();
				if (su.getIsChecked()) {
					employee.setEmpCode(su.getCommittor());
					employee.copyFrom(employee.databaseMe());

					JschCommand jschServerBehaviour = new JschCommand();
					jschServerBehaviour.sessionLogin(host, userId, passwd);
					command = GlobalContext.getPropertyString("vm.svn.userDelete") + " " + this.getProjectName() + " " + employee.getEmail() + " " + employee.getPassword();
					jschServerBehaviour.runCommand(command);
					newUserList.remove(su.getCommittor());
				}
			}
			svnUserList.addAll(newUserList);
		}
		this.load();
	}
	@Face(displayName="$Close")
	@ServiceMethod(callByContent = true)
	public Object cancel() throws Exception{
		return new Remover(new ModalWindow());
	}
	
	
}
