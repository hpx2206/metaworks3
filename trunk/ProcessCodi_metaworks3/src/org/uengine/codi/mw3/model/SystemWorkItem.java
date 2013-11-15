package org.uengine.codi.mw3.model;

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
	
	Session codiSession = new Session();
	Locale localeManager = new Locale();
	
	IEmployee emp = new Employee();
	emp.setEmpCode(GlobalContext.getPropertyString("codi.user.id")  + "." + session.getEmployee().getGlobalCom() );
	emp.setGlobalCom(GlobalContext.getPropertyString("codi.user.name"));
	emp = emp.findMe();

	IUser writer = new User();
	writer.setUserId(emp.getEmpCode());
	writer.setName(emp.getEmpName());
	
	ICompany company = new Company();
	company.setComCode(emp.getGlobalCom());
	
	codiSession.setUser(writer);
	codiSession.setCompany(company);
	codiSession.setEmployee(emp);
	
	
	this.setEndpoint(emp.getEmpCode());
	this.setWriter(writer);
	this.setTitle(this.getSystemMessage());
	this.session = codiSession;
	
	super.add();
	
	return null;
}
}
