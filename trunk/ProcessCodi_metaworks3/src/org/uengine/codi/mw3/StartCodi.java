package org.uengine.codi.mw3;

import javax.servlet.http.HttpSession;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.kernel.GlobalContext;

public class StartCodi {

	String key;
	@Hidden
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public Object load() throws Exception{
		
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();		
		String loggedUserId = (String)httpSession.getAttribute("loggedUserId");

		String comAlias = TenantContext.getThreadLocalInstance().getTenantId();
		String comCode = null;
		
		String multitenancyUse = GlobalContext.getPropertyString("multitenancy.use", "0");
		
		if("1".equals(multitenancyUse)){
			if(comAlias == null){
				
				return new SignUp();
			}
			
			// 등록되어 있는테넌트 불러오기.
			Company company = new Company();
			company.setAlias(comAlias);
			ICompany findCompany = company.findByAlias();

			if(findCompany == null){
				// 잘못 입력되었을때. - ex) asdf.processcodi.com:8080/uengine-web
				//return new ErrorPage();
				return new Login();
			}else
				comCode = findCompany.getComCode();
		}
		
		if(loggedUserId != null){
			Employee employee = new Employee();
			employee.setEmpCode(loggedUserId);
			IEmployee employeeRef = employee.findMe();
			
			if("0".equals(multitenancyUse) || employeeRef.getGlobalCom().equals(comCode)){
				Login login = new Login();
				login.setEmail(employeeRef.getEmail());
				login.setPassword(employeeRef.getPassword());
				
				return login.login();
			}
		}
		
		Login login = new Login();
		login.setMetaworksContext(new MetaworksContext());
		login.getMetaworksContext().setHow("login");
		login.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		return login;
	}
	
	@ServiceMethod(callByContent=true)
	public Object activate() throws Exception {
		Employee employee = new Employee();
		employee.setAuthKey(this.getKey());

		return employee.activate();
	}
	
	@ServiceMethod(callByContent=true)
	public Object forgotPassword() throws Exception {
		Employee employee = new Employee();
		employee.setAuthKey(this.getKey());

		return employee.forgotPassword();
 
	}
}
