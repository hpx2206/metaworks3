package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.dao.MetaworksDAO;

public class DeptFollowers extends Followers{
	
	static final String CONTEXT_WHERE_DEPTFOLLOWERS = "deptFollowers";
	
	public DeptFollowers(){
		setInstanceId("dept");
	}
	
	@Override
	public void load() throws Exception{
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		
		
		String partCode = session.getLastSelectedItem();
		String globalCom = session.getEmployee().getGlobalCom();
		
		Dept dept = new Dept();
		dept.setPartCode(partCode);
		dept.setGlobalCom(globalCom);
		IDept refDept = dept.findChildren();
		refDept.getMetaworksContext().setHow(CONTEXT_WHERE_DEPTFOLLOWERS);
		setDeptFollowers(refDept);
		
		
		IUser user = new User();
		user.setMetaworksContext(new MetaworksContext());
		user.getMetaworksContext().setWhen(CONTEXT_WHERE_DEPTFOLLOWERS);
		IUser users = user.findByDept(dept);
		setFollowers(users);
		
	}

}
