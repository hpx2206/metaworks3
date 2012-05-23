package org.uengine.codi.mw3.model;

public class OrganizationPerspective extends Perspective {

	public OrganizationPerspective() {
		setLabel("Organization");
	}
	
	IDept dept;

	public IDept getDept() {
		return dept;
	}

	public void setDept(IDept dept) {
		this.dept = dept;
	}

	@Override
	protected void loadChildren() throws Exception {
		IDept dept = new Dept();
		setDept(dept.findByGlobalCom(session.getEmployee().getGlobalCom()));
		getDept().getMetaworksContext().setWhere("navigator");
	}

	@Override
	protected void unloadChildren() throws Exception {
		setDept(null);
	}

}
