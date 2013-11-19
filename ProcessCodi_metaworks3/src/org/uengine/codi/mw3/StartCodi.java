package org.uengine.codi.mw3;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;

public class StartCodi {

	public final static String USE_OCE = GlobalContext.getPropertyString("oce.use", "0");
	public final static String USE_SIGNUP = GlobalContext.getPropertyString("signup.use", "1");
	public final static String USE_DASHBOARD = GlobalContext.getPropertyString("dashboard.use", "0");
	public final static String USE_MULTITENANCY = GlobalContext.getPropertyString("multitenancy.use", "0");
	public final static String USE_IAAS = GlobalContext.getPropertyString("iaas.use", "0");

	String key;
		@Hidden
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
	Session session;
		@Hidden
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
	public StartCodi(){
		this(null);
	}
	
	public StartCodi(Session session){
		this(session, null);
	}
	
	public StartCodi(Session session, String key){
		this.setSession(session);
		this.setKey(key);
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public Object load() throws Exception{
		
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();		
		String loggedUserId = (String)httpSession.getAttribute("loggedUserId");

		String comAlias = TenantContext.getThreadLocalInstance().getTenantId();
		String comCode = null;
		
		if("1".equals(USE_MULTITENANCY)){
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
			boolean isLogin = false;
			
			if("1".equals(USE_MULTITENANCY)){
				Employee employee = new Employee();
				employee.setEmpCode(loggedUserId);
				IEmployee employeeRef = employee.findMe();
				
				if(employeeRef.getGlobalCom().equals(comCode))
					isLogin = true;
			}else{
				isLogin = true;
			}
			
			if(isLogin)
				return login();
		}
		
		Login login = new Login();
		login.setMetaworksContext(new MetaworksContext());
		login.getMetaworksContext().setHow("login");
		login.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		return login;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public Object[] login() throws Exception {
		
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();		
		String loggedUserId = (String)httpSession.getAttribute("loggedUserId");

		Employee emp = new Employee();
		emp.setEmpCode(loggedUserId);
		IEmployee findEmp = emp.findMe();
		
		Session session = new Session();
		session.setEmployee(findEmp);
		
		Login login = new Login();
		
		return login.login(session);
	}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public Login logout() throws Exception {
		this.getSession().removeUserInfoFromHttpSession();
		
		Login login = new Login();
		login.getMetaworksContext().setHow("logout");
		login.fireServerSession(this.getSession());
        
		HttpServletRequest httpServletRequest = TransactionContext.getThreadLocalInstance().getRequest();
		
		String ipAddress = httpServletRequest.getRemoteAddr();
		
        CodiLog  log = new CodiLog();
        log.setId(log.createNewId());
        log.setEmpcode(this.getSession().getEmployee().getEmpCode());
        log.setComCode(this.getSession().getEmployee().getGlobalCom());
        log.setType("logout");
        log.setDate(new Date());
        log.setIp(ipAddress);
        log.createDatabaseMe();
        
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
