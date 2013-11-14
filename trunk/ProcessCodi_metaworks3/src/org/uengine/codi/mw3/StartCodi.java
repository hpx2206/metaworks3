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

		System.out.println("loggedUserId : " + loggedUserId);
		
		String tenantId = TenantContext.getThreadLocalInstance().getTenantId();
		
		//등록 없음  - 회원가입 화면.
		if(tenantId == null){
			return new SignUp();
		}
		
		// 등록되어 있는테넌트 불러오기.
		Company company = new Company();
		company.setAlias(tenantId);
		ICompany findTenant = company.findByAlias();

		/*
		 * if is not null
		 * true  : 존재하는 태넌트
		 * false : 존재하지 않으므로 경고처리
		 */
		if(findTenant != null){
			
			/*
			 * if is not null
			 * true : 로그인된 사용자 -> 바로 메인화면으로
			 * false : 로그인 안되었으므로 로그인 화면
			 */
			if(loggedUserId != null){
				Employee employee = new Employee();
				employee.setEmpCode(loggedUserId);
				employee.copyFrom(employee.findMe());
				if(employee.getGlobalCom().equals(findTenant.getComCode())){
					Login login = new Login();
					login.SessionIdForCompanyMapping = Login.SessionIdForCompanyMapping;
					login.SessionIdForDeptMapping = Login.SessionIdForDeptMapping;
					login.SessionIdForEmployeeMapping = Login.SessionIdForEmployeeMapping;
					login.userIdDeviceMapping = Login.userIdDeviceMapping;
					login.setStatus("login");
					login.setUserId(employee.getEmail());
					login.setPassword(employee.getPassword());
					
					return login.login();
					
				}
			}
			
			Login login = new Login();
			login.setStatus("login");
			
			login.setMetaworksContext(new MetaworksContext());
			login.getMetaworksContext().setHow("logout");
			login.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			return login;
			//return new Login();
		}else{
			// 잘못 입력되었을때. - ex) asdf.processcodi.com:8080/uengine-web
			//return new ErrorPage();
			return new Login();
		}		
	}
	
	@ServiceMethod(callByContent=true)
	public Object activate() throws Exception {
		Employee employee = new Employee();
		employee.setAuthKey(this.getKey());

		return employee.activate();
	}
}
