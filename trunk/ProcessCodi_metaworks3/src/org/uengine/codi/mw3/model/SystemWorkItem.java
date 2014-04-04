package org.uengine.codi.mw3.model;

import org.uengine.kernel.GlobalContext;


public class SystemWorkItem extends CommentWorkItem{

	String systemMessage;
		public String getSystemMessage() {
			return systemMessage;
		}
		public void setSystemMessage(String systemMessage) {
			this.systemMessage = systemMessage;
		}
		
	public SystemWorkItem(){
		setType(WORKITEM_TYPE_SYSTEM);
	}

	@Override
	public Object[] add() throws Exception {
		System.out.println(this.getSystemMessage());
		Session codiSession = new Session();
		
		Instance instance = new Instance();
		instance.setInstId(this.getInstId());
		
		IEmployee emp = new Employee();
		emp.setEmpCode(GlobalContext.getPropertyString("codi.user.id", "0"));
		emp.setEmpName(GlobalContext.getPropertyString("codi.user.name", "CODI"));
		emp.setGlobalCom(instance.databaseMe().getInitComCd());
		
		codiSession.setUser(emp.getUser());
		codiSession.setEmployee(emp);
		
		this.setTitle(this.getSystemMessage());
		this.session = codiSession;
		
		super.add();
		
		return null;
	}
}
