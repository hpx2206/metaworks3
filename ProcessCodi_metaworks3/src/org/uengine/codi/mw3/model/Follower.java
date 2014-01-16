package org.uengine.codi.mw3.model;

import org.metaworks.EventContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.filter.AllSessionFilter;
import org.uengine.codi.mw3.filter.OtherSessionFilter;
import org.uengine.kernel.Role;

public class Follower extends Database<IFollower> implements IFollower {	
	
	@AutowiredFromClient
	public Session session;
	
	String parentType;
		public String getParentType() {
			return parentType;
		}
		public void setParentType(String parentType) {
			this.parentType = parentType;
		}

	String parentId;
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

	String endpoint;
		public String getEndpoint() {
			return endpoint;
		}
		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}

	String resName;
		public String getResName() {
			return resName;
		}
		public void setResName(String resName) {
			this.resName = resName;
		}

	int assigntype;
		public int getAssigntype() {
			return assigntype;
		}
		public void setAssigntype(int assigntype) {
			this.assigntype = assigntype;
		}

	IUser user;
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
		}
	
	IDept dept;
		public IDept getDept() {
			return dept;
		}
		public void setDept(IDept dept) {
			this.dept = dept;
		}
	
	public IFollower find() throws Exception {
		throw new Exception("not defined exist method");
	}

	public void put() throws Exception {
		throw new Exception("not defined put method");
	}
	
	public void put(IUser user) throws Exception {
		this.setUser(user);
		this.setAssigntype(Role.ASSIGNTYPE_USER);
		this.put();
	}
	
	public void delegate() throws Exception {
		throw new Exception("not defined delegate method");
	}
	
	public void delegate(IUser user) throws Exception {
		this.setUser(user);
		this.setAssigntype(Role.ASSIGNTYPE_USER);
		this.delegate();
	}
	
	public IFollower findFollowers() throws Exception {
		throw new Exception("not defined findFollowers method");
	}

	public IContact findContacts(String keyword) throws Exception {
		throw new Exception("not defined findContacts(String keyword) method");
	}

	@Override
	public Object[] detail() throws Exception {
		session.setClipboard(this);
		
		if(Role.ASSIGNTYPE_USER == this.getAssigntype()){
			User convertUser = new User();
			convertUser.copyFrom(this.getUser());
			convertUser.session =  session;
			
			return new Object[]{new Refresh(session), convertUser.detail()};
		}else
			return null;
	}
	
	@Override
	public Object[] addFollower() throws Exception {
		this.put();
		
		return new Object[]{ new ToEvent(ServiceMethodContext.TARGET_OPENER, EventContext.EVENT_CHANGE), new Remover(ServiceMethodContext.TARGET_SELF)};	
	}
	
	@Override
	public Object[] removeFollower() throws Exception {
		this.delegate();
		
		return new Object[]{ new ToEvent(ServiceMethodContext.TARGET_OPENER, EventContext.EVENT_CHANGE) };
	}

	@Override
	public Object[] popupAddFollower() throws Exception {
		Popup popup = new Popup(400,400);
		popup.setWidth(400);
		popup.setHeight(400);

		// copy to follower for addFollower
		session.setClipboard(this);

		AddFollowerPanel addFollowerPanel = new AddFollowerPanel(session, this);
		popup.setPanel(addFollowerPanel);

		return new Object[]{ new Refresh(session), popup }; 
	}
	
	public void push() throws Exception {
		// 본인 이외에 다른 사용자에게 push
		MetaworksRemoteService.pushClientObjectsFiltered(
				new AllSessionFilter(Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom())),
				new Object[]{this});
	}
	
}
