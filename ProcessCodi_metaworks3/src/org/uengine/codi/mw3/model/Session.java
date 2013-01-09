package org.uengine.codi.mw3.model;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.Login;

	
public class Session implements ContextAware{
	
	static Hashtable<String, ArrayList> messagesToUsers = new Hashtable<String, ArrayList>(); 
	
	public Session() {
		MetaworksContext metaworkscontext = new MetaworksContext();
		metaworkscontext.setWhen(MetaworksContext.WHEN_VIEW);
		
		setMetaworksContext(metaworkscontext);
	}
	
	IUser user;	
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
		}
		
	String defId;	
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	String ux;
		public String getUx() {
			return ux;
		}
		public void setUx(String ux) {
			this.ux = ux;
		}

	boolean guidedTour;
		@Hidden
		public boolean isGuidedTour() {
			return guidedTour;
		}
		public void setGuidedTour(boolean guidedTour) {
			this.guidedTour = guidedTour;
		}
		
	Navigation navigation;
		public Navigation getNavigation() {
			return navigation;
		}
	
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}
		
	Object clipboard;
	@Hidden
		public Object getClipboard() {
			return clipboard;
		}
		public void setClipboard(Object clipboard) {
			this.clipboard = clipboard;
		}

	IEmployee employee;
		@Available(when = MetaworksContext.WHEN_VIEW)
		@NonEditable
		public IEmployee getEmployee() {
			if(this.employee != null) {
				this.employee.getMetaworksContext().setWhere("inSession");
			}
			return employee;
		}
	
		public void setEmployee(IEmployee employee) {
			this.employee = employee;
		}

	IDept dept;
		@Hidden
		@NonEditable
		public IDept getDept() {
			return dept;
		}
	
		public void setDept(IDept dept) {
			this.dept = dept;
		}
	
	ICompany company;
		@Hidden
		@NonEditable
		public ICompany getCompany() {
			return company;
		}
	
		public void setCompany(ICompany company) {
			this.company = company;
		}
	
	private MetaworksContext context;
		@Override
		public MetaworksContext getMetaworksContext() {
			return this.context;
		}
	
		@Override
		public void setMetaworksContext(MetaworksContext paramMetaworksContext) {
			this.context = paramMetaworksContext;
		}

	String lastPerspecteType;
		@Hidden
		public String getLastPerspecteType() {
			return lastPerspecteType;
		}
	
		public void setLastPerspecteType(String lastPerspecteType) {
			this.lastPerspecteType = lastPerspecteType;
		}

	String lastSelectedItem;
		@Hidden
		public String getLastSelectedItem() {
			return lastSelectedItem;
		}
	
		public void setLastSelectedItem(String lastSelectedItem) {
			this.lastSelectedItem = lastSelectedItem;
		}
	String windowTitle;
		@Hidden
		public String getWindowTitle() {
			return windowTitle;
		}
		public void setWindowTitle(String windowTitle) {
			this.windowTitle = windowTitle;
		}
	
	IRole role;
		@Hidden
		public IRole getRole() {
			return role;
		}
		public void setRole(IRole role) {
			this.role = role;
		}
		
	int todoListCount;
		public int getTodoListCount(){
			return todoListCount;
		}
		public void setTodoListCount(int todoListCount){
			this.todoListCount = todoListCount;
		}
		
	//disabled for merging
//	SearchKeywordBox searchKeywordBox;
//
//	@Hidden
//	public SearchKeywordBox getSearchKeywordBox() {
//		return searchKeywordBox;
//	}
//
//	public void setSearchKeywordBox(SearchKeywordBox searchKeywordBox) {
//		this.searchKeywordBox = searchKeywordBox;
//	}
	


	String searchKeyword;
		@Hidden
		public String getSearchKeyword() {
			return searchKeyword;
		}
		public void setSearchKeyword(String searchKeyword) {
			this.searchKeyword = searchKeyword;
		}
	
	@ServiceMethod
	public Login logout() {
		removeUserInfoFromHttpSession();
		
		Login login = new Login();
/*		if(getEmployee() != null) {
			login.setUserId(getEmployee().getEmpCode());
		}
*/		login.setMetaworksContext(new MetaworksContext());
		
		login.getMetaworksContext().setHow("logout");
		login.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		//login.getMetaworksContext().setWhere("user");
		return login;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_NONE)
	public Object heartbeat(){
		//nothing to do
		String sessionId = WebContextFactory.get().getScriptSession().getId();
		//System.out.println("heartbeat:" + sessionId);
		
		if(messagesToUsers.containsKey(sessionId)){
			ArrayList messages = messagesToUsers.get(sessionId);
			messagesToUsers.remove(sessionId);
			
			return messages;
		}
		
		return null;
	}
	
	public static void pushMessage(String userId, Object message){
		String sessionId = Login.getSessionIdWithUserId(userId);
		
		if(sessionId==null) return;
		
		ArrayList messages = null;
		if( !messagesToUsers.containsKey(sessionId)){
			messages = new ArrayList();
			messagesToUsers.put(sessionId, messages);
		}else{
			messages = messagesToUsers.get(sessionId);
		}
		
		messages.add(message);
	}
	
	@ServiceMethod(callByContent=true)
	public Window returnToList() throws Exception{
		return Main.createInstanceListWindow(this);
	}
	
	
	// when need HttpSession
	public void fillUserInfoToHttpSession(){
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		httpSession.setAttribute("loggedUserId", getEmployee().getEmpCode());
//			httpSession.setAttribute("loggedUserPw", session.getEmployee().getPassword());
		httpSession.setAttribute("loggedUserFullName", getEmployee().getEmpName());
		httpSession.setAttribute("loggedUserIsAdmin", getEmployee().getIsAdmin());
		httpSession.setAttribute("loggedUserJikName", getEmployee().getJikName());
		httpSession.setAttribute("loggedUserEmail", getEmployee().getEmail());
		httpSession.setAttribute("loggedUserPartCode", getEmployee().getPartCode());
		httpSession.setAttribute("loggedUserPartName", getEmployee().getPartName());
		httpSession.setAttribute("loggedUserGlobalCom", getEmployee().getGlobalCom());

		httpSession.setAttribute("loggedUserComName", getCompany().getComName());
		httpSession.setAttribute("loggedUserLocale", getEmployee().getLocale());
	}
	
	public void removeUserInfoFromHttpSession(){
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		httpSession.invalidate();
	}
	
	@ServiceMethod(callByContent=true, target="popup")
	public ModalWindow manager() throws Exception{
		return new ModalWindow(new ManagerPanel(this), 600, 500);
	}
	
	public void fillSession() throws Exception {
		if (this.getEmployee() != null
				&& this.getEmployee().getGlobalCom() != null) {
			
			IUser user = new User();			
			user.getMetaworksContext().setWhere("local");
			user.setUserId(this.getEmployee().getEmpCode());
			user.setName(this.getEmployee().getEmpName());
							
			this.setUser(user);

			
			if (this.getEmployee().getPartCode() != null) {
				try{
					IDept dept = new Dept();
					dept.setPartCode(this.getEmployee().getPartCode());
					this.setDept(dept.load());
				}catch(Throwable e){
					
				}
			}
			
			if (this.getEmployee().getGlobalCom() != null) {
				try{
					ICompany company = new Company();
					company.setComCode(this.getEmployee().getGlobalCom());
					this.setCompany(company.load());
				}catch(Throwable e){
					
				}
			}
		} else {
			throw new Exception(
					"There is no Company info in user info.");
		}
	}

}
