package org.uengine.codi.mw3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.springframework.web.util.CookieGenerator;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.User;
import org.uengine.kernel.GlobalContext;

public class StartCodi {

	public final static String USE_OCE = GlobalContext.getPropertyString("oce.use", "0");
	public final static String USE_SIGNUP = GlobalContext.getPropertyString("signup.use", "1");
	public final static String USE_DASHBOARD = GlobalContext.getPropertyString("dashboard.use", "0");
	public final static String USE_MULTITENANCY = GlobalContext.getPropertyString("multitenancy.use", "0");
	public final static String USE_IAAS = GlobalContext.getPropertyString("iaas.use", "0");
	public final static String USE_CAS = GlobalContext.getPropertyString("cas.use", "0");
	public final static String CAS_REST_URL = GlobalContext.getPropertyString("cas.rest.url", "http://localhost:8080/cas/v1/tickets");
	
	//test code
	public final static HashMap MANAGED_SESSIONS = new HashMap();
	
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
	String lastVisitPage;
		@Hidden
		public String getLastVisitPage() {
			return lastVisitPage;
		}
		public void setLastVisitPage(String lastVisitPage) {
			this.lastVisitPage = lastVisitPage;
		}	
		
	String lastVisitValue;
		@Hidden
		public String getLastVisitValue() {
			return lastVisitValue;
		}
		public void setLastVisitValue(String lastVisitValue) {
			this.lastVisitValue = lastVisitValue;
		}
	
	String ssoService;
		public String getSsoService() {
			return ssoService;
		}
		public void setSsoService(String ssoService) {
			this.ssoService = ssoService;
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
	
	@ServiceMethod(payload={"key","lastVisitPage", "lastVisitValue", "ssoService"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object load() throws Exception{
		
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();		
		if("1".equals(USE_CAS)){
			final javax.servlet.http.Cookie cookie = org.springframework.web.util.WebUtils.getCookie(
					TransactionContext.getThreadLocalInstance().getRequest(), "CASTGC");
			
			if(cookie != null){
				String ssoSt = (String)httpSession.getAttribute("SSO-ST");
				if(ssoSt == null){
					String serviceURL = "http://" +  TransactionContext.getThreadLocalInstance().getRequest().getLocalAddr().toString() + ":" 
							+ TransactionContext.getThreadLocalInstance().getRequest().getLocalPort() 
							+ TransactionContext.getThreadLocalInstance().getRequest().getContextPath();
					
					String encodedServiceURL = URLEncoder.encode("service","utf-8") +"=" + URLEncoder.encode(serviceURL, "utf-8");
					System.out.println("Service url is : " + encodedServiceURL);

					String myURL = StartCodi.CAS_REST_URL + "/"+ cookie.getValue();
					URL urlST = new URL(myURL);
					System.out.println(myURL);
					
					HttpURLConnection huc = null;
					try{
						huc = (HttpURLConnection) urlST.openConnection();
						huc.setDoInput(true);
						huc.setDoOutput(true);
						huc.setRequestMethod("POST");
						
						OutputStreamWriter outST = new OutputStreamWriter(huc.getOutputStream());
						BufferedWriter bwrST = new BufferedWriter(outST);
						bwrST.write(encodedServiceURL);
						bwrST.flush();
						bwrST.close();
						outST.close();
						
						System.out.println("Response code is:  " + huc.getResponseCode());
						
						String line;
						System.out.println( huc.getResponseCode());
						if(huc.getResponseCode() == 200){
							
							String loggedUserId = (String)httpSession.getAttribute("loggedUserId");
							
							Employee emp = new Employee();
							emp.setEmpCode(loggedUserId);
							IEmployee findEmp = emp.findMe();
							
							Session session = new Session();
							session.setEmployee(findEmp);
							
							IUser user = new User();			
							user.getMetaworksContext().setWhere("local");
							user.setUserId(session.getEmployee().getEmpCode());
							user.setName(session.getEmployee().getEmpName());
							
							session.setUser(user);
							BufferedReader isr = new BufferedReader(new InputStreamReader(huc.getInputStream()));
							while ((line = isr.readLine()) != null) {
								TransactionContext.getThreadLocalInstance().getRequest().getSession().setAttribute("SSO-ST", line);
								MANAGED_SESSIONS.put(line, session);
							}
							isr.close();
							huc.disconnect();
							return login();
						}
					}catch(Exception e){
					}finally{
						huc.disconnect();
					}
				}else{
					return login();
				}
			}
			
					
			
//			
//			if(cookie != null){
//				HttpURLConnection huc = null;
//				
//				String serviceURL = getSsoService() != null ? getSsoService() : "http://" +  TransactionContext.getThreadLocalInstance().getRequest().getLocalAddr().toString() + ":" 
//						+ TransactionContext.getThreadLocalInstance().getRequest().getLocalPort() 
//						+ TransactionContext.getThreadLocalInstance().getRequest().getContextPath();
//				
//				
//				String encodedServiceURL = URLEncoder.encode("service","utf-8") +"=" + URLEncoder.encode(serviceURL,"utf-8");
//				System.out.println("Service url is : " + encodedServiceURL);
//				
//				
//				String myURL = CAS_REST_URL + "/"+ cookie.getValue() ;
//				URL urlST = new URL(myURL);
//				System.out.println(myURL);
//	
//				huc = (HttpURLConnection) urlST.openConnection();
//				huc.setDoInput(true);
//				huc.setDoOutput(true);
//				huc.setRequestMethod("POST");
//	
//				OutputStreamWriter outST = new OutputStreamWriter(huc.getOutputStream());
//				BufferedWriter bwrST = new BufferedWriter(outST);
//				bwrST.write(encodedServiceURL);
//				bwrST.flush();
//				bwrST.close();
//				outST.close();
//				
//				System.out.println("Response code is:  " + huc.getResponseCode());
//				
//				if(huc.getResponseCode() == 200){
//					return login();
//				}
//			}
		}else{
			String loggedUserId = (String)httpSession.getAttribute("loggedUserId");

			if(loggedUserId != null)
				return login();	
		}
						

		
		if("login".equals(this.getKey()) || this.getLastVisitPage() != null || ssoService != null){
			   
			   Login login = new Login();
			   if(ssoService != null)
				   login.setSsoService(ssoService);
		
			   return new Refresh(login, false, true);
			   
		}else{
			return new Refresh(new SignUp(), false, true);
			
			/*
			String comAlias = TenantContext.getThreadLocalInstance().getTenantId();
			
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
				return new Login();
			*/
		}
		
		/*
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
		*/
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] login() throws Exception {
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();		
		String loggedUserId = (String)httpSession.getAttribute("loggedUserId");

		Employee emp = new Employee();
		emp.setEmpCode(loggedUserId);
		IEmployee findEmp = emp.findMe();
		
		Session session = new Session();
		session.setEmployee(findEmp);
		
		Login login = new Login();
		login.lastVisitPage = this.getLastVisitPage();
		login.lastVisitValue = this.getLastVisitValue();
		
		try{
			return login.login(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new Object[]{new Refresh(new SignUp(), false, true)};
	}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public SignUp logout() throws Exception {
		this.getSession().removeUserInfoFromHttpSession();
		
		//test code
		CookieGenerator cookieGenerator = new CookieGenerator();
		cookieGenerator.setCookieName("CASTGC");
		cookieGenerator.removeCookie(TransactionContext.getThreadLocalInstance().getResponse());
		
		
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
        
        return new SignUp();
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
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public Object[] invited() throws Exception {
		Employee employee = new Employee();
		employee.setAuthKey(this.getKey());
		
		employee.copyFrom(employee.findByKey());
		if(employee.getInviteUser() == null){
			String url = null;
			if("1".equals(StartCodi.USE_MULTITENANCY))
	       		url = Employee.extractTenantName(employee.getEmail());
///////////////////////////////////////////////-------------- 수정 -----------------/////////////////////////////////////////////////////			
			//return new Object[]{new Forward(TenantContext.getURL(null))};
			return new Object[]{new ErrorPage()};
		}
		Object[] returnObjects = null;
		returnObjects = employee.applyForAddContact();
		
		if(returnObjects != null){
			
			//String url = TenantContext.getURL(null);
			//return new Object[]{new Forward(url)};
			return returnObjects;
		}
		
		return new Object[]{new ErrorPage()};
 
	}
}
