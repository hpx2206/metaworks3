package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpSession;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.Login;

	
public class Session implements ContextAware{
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
		if(getEmployee() != null) {
			login.setUserId(getEmployee().getEmpCode());
		}
		login.setMetaworksContext(new MetaworksContext());
		login.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		login.getMetaworksContext().setWhere("user");
		return login;
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

}
