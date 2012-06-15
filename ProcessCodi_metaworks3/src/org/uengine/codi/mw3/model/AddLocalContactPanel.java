package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;

public class AddLocalContactPanel {

	
	public AddLocalContactPanel() {
		
	}
	
	public AddLocalContactPanel(Session session) throws Exception {
		IDept dept = new Dept();
		dept.setGlobalCom(session.getEmployee().getGlobalCom());
		setDept(dept.findByGlobalCom());
		getDept().getMetaworksContext().setWhere("addLocalContactPanel");
	}

	IDept dept;
	
	public IDept getDept() {
		return dept;
	}

	public void setDept(IDept dept) {
		this.dept = dept;
	}

	@AutowiredFromClient
	public Session session;

}
