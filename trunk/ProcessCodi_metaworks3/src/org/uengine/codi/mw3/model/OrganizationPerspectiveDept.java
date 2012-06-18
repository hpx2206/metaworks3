package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class OrganizationPerspectiveDept extends Perspective {

	public OrganizationPerspectiveDept() {
		setLabel("Organization");
	}
	
	DeptList deptList;		
		public DeptList getDeptList() {
			return deptList;
		}
		public void setDeptList(DeptList deptList) {
			this.deptList = deptList;
		}
		
	@Override
	protected void loadChildren() throws Exception {
		IDept dept = new Dept();
		dept.getMetaworksContext().setWhere("navigator");
		dept.setGlobalCom(session.getEmployee().getGlobalCom());
		
		DeptList deptList = new DeptList();
		deptList.getMetaworksContext().setWhen("navigator");		
		deptList.setId("/ROOT/");		
		deptList.setDept(dept.findByGlobalCom());
		
		setDeptList(deptList);
	}

	@Override
	protected void unloadChildren() throws Exception {
		setDeptList(null);
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup addDept() {
		IDept dept = new Dept();
		dept.getMetaworksContext().setWhere("admin");
		dept.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		Popup popup = new Popup();
		popup.setPanel(dept);
		
		return popup;
	}
}
