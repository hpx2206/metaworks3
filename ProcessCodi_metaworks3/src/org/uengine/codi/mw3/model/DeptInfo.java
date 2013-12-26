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
		this.setIsJoinME();
	}

	@Override
	public void followersLoad() throws Exception {
		followers = new DeptFollowers();
		followers.session = session;
		followers.load();
	}
	

	@Override
	public Object[] subscribe() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmpCode(session.getEmployee().getEmpCode());
		employee.copyFrom(employee.databaseMe());
		
		if(employee.getPartCode()!=null && this.getId().equals(employee.getPartCode())){
			throw new Exception("$part.already.subscribe");
		}
		
		employee.setPartCode(this.getId());
		employee.syncToDatabaseMe();
		employee.flushDatabaseMe();
		
		return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
		
	}
	
	@Override
	public Object[] unSubscribe() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmpCode(session.getEmployee().getEmpCode());
		employee.copyFrom(employee.databaseMe());
		
		if(!this.getId().equals(employee.getPartCode())){
			throw new Exception("$part.still.UnSubscribe");
		}
		
		employee.setPartCode("");
		employee.syncToDatabaseMe();
		employee.flushDatabaseMe();
		
		return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
		
	}
	
	
	@Override
	public void setIsJoinME() throws Exception {

		Employee emp = new Employee();
		emp.setEmpCode(session.getEmployee().getEmpCode());
		emp.setPartCode(this.getId());
		IEmployee findEmp = emp.findMe();
		
		if(this.getId().equals(findEmp.getPartCode()))
			this.setIsJoined(true);
		else
			this.setIsJoined(false);
		
				
	}
}
