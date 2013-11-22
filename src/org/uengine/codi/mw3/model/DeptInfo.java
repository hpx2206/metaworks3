package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;

public class DeptInfo extends PerspectiveInfo{

	public final static int MODIFY_POPUP_HEIGHT = 250;
	public final static int MODIFY_POPUP_WIDTH = 500;
	
	public DeptInfo(){
		
	}
	public DeptInfo(Session session){
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
		
		
/*		if( (this.getChildren() != null && this.getChildren().size() > 0) || (this.getDeptEmployee() != null && this.getDeptEmployee().size() > 0))
			throw new Exception("�섏쐞 �몃뱶媛�議댁옱�섎㈃ ��젣�����놁뒿�덈떎.");*/
				
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
	}

	@Override
	public void followersLoad() throws Exception {
		followers = new DeptFollowers();
		followers.session = session;
		followers.load();
	}
	

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$part.Subscribe")
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
		
		this.getFollowers().session = session;
		this.getFollowers().load();
			
		return new Object[]{new Refresh(this.followers)};
		
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$part.UnSubscribe")
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
		
		this.getFollowers().session = session;
		this.getFollowers().load();
			
		return new Object[]{new Refresh(this.followers)};
		
		
	}
}
