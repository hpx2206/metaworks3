package org.uengine.codi.mw3.model;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.ITopicMapping;
import org.uengine.codi.mw3.knowledge.TopicMapping;

public class DeptInfo extends PerspectiveInfo{

	public final static int MODIFY_POPUP_HEIGHT = 250;
	public final static int MODIFY_POPUP_WIDTH = 500;
	
	public DeptInfo(){
		
	}
	public DeptInfo(Session session) throws Exception{
		this.session = session;
		this.setId(session.getLastSelectedItem());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
	}
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] delete() throws Exception {
		Dept dept = new Dept();
		dept.setPartCode(this.getId());		
		dept.copyFrom(dept.databaseMe());
		dept.setIsDeleted("1");
				
		dept.syncToDatabaseMe();
		dept.flushDatabaseMe();
		
		return new Object[]{new Refresh(new InstanceListPanel()), new Remover(dept , true)};	
	}

	@Override
	public ModalWindow modify() throws Exception {
		
		Dept dept = new Dept();
		dept.setPartCode(this.getId());
		dept.copyFrom(dept.databaseMe());
		
		dept.getMetaworksContext().setWhere("admin");
		dept.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		dept.setLogoFile(new MetaworksFile());
		
		return new ModalWindow(dept, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "부서정보바꾸기");
	//	return popup;		
	
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$part.addNewChildDept")
	public Object addNewChildDept() throws Exception {
		IDept newDept = new Dept();
		newDept.setParent_PartCode(this.getId());
		newDept.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		newDept.getMetaworksContext().setWhere("admin");
		newDept.setLogoFile(new MetaworksFile());
		
		Popup popup = new Popup();
		popup.setPanel(newDept);
		
		return popup;
	}

	@Override
	public void load() throws Exception {
		this.followersLoad();
		Dept dept = new Dept();
		dept.setPartCode(this.getId());
		
		try {
			dept.copyFrom(dept.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setName(dept.getPartName());
		this.setDescription(dept.getDescription());
		
		MetaworksFile logoFile = new MetaworksFile();
		logoFile.setUploadedPath(dept.getUrl());
		logoFile.setFilename(dept.getThumbnail());
		this.setLogoFile(logoFile);
		this.settingJoined();
	}

	@Override
	public void followersLoad() throws Exception {
		followers = new DeptFollowers();
		followers.session = session;
		followers.load();
	}
	

	@Override
	public Object[] subscribe() throws Exception {
		
		User user = new User();
		user.setUserId(session.getEmployee().getEmpCode());
		user.setName(session.getEmployee().getEmpName());
		user.session = this.session;
		user.setMetaworksContext(new MetaworksContext());
		user.getMetaworksContext().setHow("follower");
		user.getMetaworksContext().setWhen(Followers.ADD_DEPTFOLLOWERS);
		
		followers.addFollower(user);
		
		return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
		
	}
	
	@Override
	public Object[] unSubscribe() throws Exception {
		
		User user = new User();
		user.setUserId(session.getEmployee().getEmpCode());
		user.setName(session.getEmployee().getEmpName());
		user.session = this.session;
		user.setMetaworksContext(new MetaworksContext());
		user.getMetaworksContext().setWhen("deptFollowers");
		
		followers.removeFollower(user);

		return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
		
	}
	
	
	@Override
	public void settingJoined() throws Exception {

		Employee emp = new Employee();
		emp.setEmpCode(session.getEmployee().getEmpCode());
		emp.setPartCode(this.getId());
		IEmployee findEmp = emp.findMe();
		
		if(this.getId().equals(findEmp.getPartCode()))
			this.setJoined(true);
		else
			this.setJoined(false);
		
				
	}
}
