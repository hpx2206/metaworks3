package org.uengine.codi.mw3;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.annotation.ValidatorSet;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.util.CodiStringUtil;
import org.uengine.kernel.GlobalContext;
import org.uengine.webservices.emailserver.impl.EMailServerSoapBindingImpl;


public class Login implements ContextAware {
	
	protected static Hashtable<String, HashMap<String, String>> SessionIdForCompanyMapping = new Hashtable<String, HashMap<String, String>>();
	protected static Hashtable<String, HashMap<String, String>> SessionIdForDeptMapping = new Hashtable<String, HashMap<String, String>>();
	protected static Hashtable<String, String> SessionIdForEmployeeMapping = new Hashtable<String, String>();
	
	protected static Hashtable<String, String> userIdDeviceMapping = new Hashtable<String, String>();	
	
	public Login(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		getMetaworksContext().setHow("login");
		
		this.setUseSubscribe("1".equals(StartCodi.USE_SIGNUP));
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String status;
		@Id
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	String email;
		@Face(displayName="$Email")
		@ValidatorSet({
			@Validator(name=ValidatorContext.VALIDATE_NOTNULL, message="이메일을 입력하세요."),
			@Validator(name=ValidatorContext.VALIDATE_REGULAREXPRESSION, options={"/^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$/"}, message="이메일 형식이 잘못되었습니다")
		})
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
	String password;
		@Face(options={"type", "placeholder"}, values={"password", "Password"})
		@Validator(name = ValidatorContext.VALIDATE_NOTNULL, condition="metaworksContext.how == 'login'", message="비밀번호를 입려하세요.")
		@Hidden(when = MetaworksContext.WHEN_VIEW)
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	Boolean rememberMe;
		public Boolean getRememberMe() {
			return rememberMe;
		}
		public void setRememberMe(Boolean rememberMe) {
			this.rememberMe = rememberMe;
		}
		
	boolean useSubscribe;
		public boolean isUseSubscribe() {
			return useSubscribe;
		}
		public void setUseSubscribe(boolean useSubscribe) {
			this.useSubscribe = useSubscribe;
		}
		
	String lastVisitPage;
		public String getLastVisitPage() {
			return lastVisitPage;
		}
		public void setLastVisitPage(String lastVisitPage) {
			this.lastVisitPage = lastVisitPage;
		}
	@AutowiredFromClient
	public Locale localeManager;
	
	public Session loginService() throws Exception {
		
		Session session = new Session();
		
		Employee emp = new Employee();
		emp.setEmail(getEmail());
		IEmployee findEmp = emp.findByEmail();
		
		if(findEmp == null)
			throw new Exception("<font color=blue>Wrong User or Password! forgot?</font>");
		
		if(!getPassword().equals(findEmp.getPassword()))
			throw new Exception("<font color=blue>Wrong User or Password! forgot?</font>");	
		
		session.setEmployee(findEmp);
		
		return session;
	}
	
	public static String getSessionIdWithUserId(String userId){
		return SessionIdForEmployeeMapping.get(userId.toUpperCase());
	}

	public static HashMap<String, String> getSessionIdWithDept(String deptId){
		deptId = deptId.toUpperCase();
		if(SessionIdForDeptMapping.containsKey(deptId)){
			HashMap<String, String> mapping = SessionIdForDeptMapping.get(deptId);
			//System.out.println(.);
			
			Iterator<String> iterator = mapping.keySet().iterator();
			
			return (HashMap<String, String>)mapping.clone();
		}else{
			return null;
		}
	}
	
	public static HashMap<String, String> getSessionIdWithCompany(String companyId){
		companyId = companyId.toUpperCase();
		if(SessionIdForCompanyMapping.containsKey(companyId)){
			HashMap<String, String> mapping = SessionIdForCompanyMapping.get(companyId);
			//System.out.println(.);
			
			Iterator<String> iterator = mapping.keySet().iterator();
			
			return (HashMap<String, String>)mapping.clone();
		}else{
			return null;
		}
	}
	
	public static String getDeviceWithUserId(String userId){
		return userIdDeviceMapping.get(userId.toUpperCase());
	}

//	private Object createMainPageByLoginType(Session session) {
//		if (getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_VIEW)) {
//			if (getMetaworksContext().getWhere().equals(LOGINTYPE_WHERE_CLIENT)) {
//				// TODO session and wih page on client
////					ClientMainPage mainPage = new ClientMainPage();
////					mainPage.setSession(session);
////					mainPage = mainPage.init();
////					return mainPage;
//				return this;
//			} else if (getMetaworksContext().getWhere().equals(
//					LOGINTYPE_WHERE_USER) || getMetaworksContext().getWhere().equals(
//							LOGINTYPE_WHERE_ADMIN)) {
//				MainPage mainPage = new MainPage(session);
//				return mainPage;
//			} else {
//				return this;
//			}
//		} else {
//			return this;
//		}
//	}
//
//	private void createContextInfoByUserType(Session session) {
//		if (session.getEmployee().getIsAdmin()) {
//			getMetaworksContext().setWhere(LOGINTYPE_WHERE_ADMIN);
//		} else {
//			if (session.getEmployee().getPartCode() != null
//					&& !session.getEmployee().getPartCode().equals(
//							IDept.CUSTOMER_DEPT_PARTCODE)) {
//				getMetaworksContext().setWhere(LOGINTYPE_WHERE_USER);
//			} else if (session.getEmployee().getPartCode() != null
//					&& session.getEmployee().getPartCode().equals(
//							IDept.CUSTOMER_DEPT_PARTCODE)) {
//				getMetaworksContext().setWhere(LOGINTYPE_WHERE_CLIENT);
//			} else {
//				getMetaworksContext().setWhere(LOGINTYPE_WHERE_OTHERS);
//			}
//		}
//		
//	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND	)
	public Object popupSubscribe1() throws Exception{
		
		this.setStatus("subscribe");
		
		ModalWindow window = new ModalWindow(this, 600, 380, "Sign Up");
		window.setId("subscribe");
		window.setResizable(false);
		
		return window;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF	)
	public Object popupSubscribe() throws Exception{
		return new SignUp();
	}
	
	@ServiceMethod(payload={"userId"}, target=ServiceMethodContext.TARGET_SELF)
	public void goLogin() throws Exception{
		this.getMetaworksContext().setHow("login");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public void goSignUp() throws Exception{
		this.setStatus("signup");
		this.getMetaworksContext().setHow("signup");
	}
	
	public void sendMailForSignUp(String signUpURL) throws Exception {
		String from = "help@opencloudengine.org";
		String beforeCompany = "company.name";
		String afterCompany = Employee.extractTenantName(this.getEmail());
		String parameterSignUpURL = "signup.url";
		String signUpBaseUrl = "signup.baseurl";
		
		String url = "";
		String tenantId = Employee.extractTenantName(this.getEmail());
		String baseUrl = TenantContext.getURL(tenantId);
		
		url += baseUrl + "/" + signUpURL;
		
	

//		FileInputStream is;
//		try {
//			is = new FileInputStream(path);
//			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
//			BufferedReader br = new BufferedReader(isr);
//			
//			
//			while(true){
//				content = br.readLine();
//				if(content == null) break;
//				System.out.println(content);
//			}
//			System.out.println();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String content;
		String tempContent = "";
		
		String resourcePath = GlobalContext.getPropertyString("resource.path", "resource");
		String path = resourcePath + File.separatorChar+"mail"+File.separatorChar+"inviteMail.html";
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			
			while(true){
				tempContent += br.readLine();
				if(br.readLine() == null) break;
//				System.out.println(tempContent);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String title = "프로세스코디 계정을 활성화시키세요.";
		
		content = this.replaceString(tempContent,beforeCompany,afterCompany);
		content = this.replaceString(content, parameterSignUpURL, url);
		content = this.replaceString(content, signUpBaseUrl, baseUrl);
		System.out.println(content);
		
	
		try{
			(new EMailServerSoapBindingImpl()).sendMail(from, getEmail(), title, content);
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
	}
	
	
	public String replaceString(String lineString, String from, String to){
		String returnValue = "";
		returnValue = lineString.replaceAll(from, to);
		return returnValue;
	}
	
	public void sendMailForForgotPassword(String forgotPasswordURL) throws Exception {
		String from = "help@opencloudengine.org";
		String beforeCompany = "company.name";
		String afterCompany = Employee.extractTenantName(this.getEmail());
		String passwordURL = "password.url";
		String signUpBaseUrl = "signup.baseurl";
		
		String url = "";
		String tenantId = Employee.extractTenantName(this.getEmail());
		String baseUrl = TenantContext.getURL(tenantId);
		
		url += baseUrl + "/" + forgotPasswordURL;
		
	
		
		String content;
		String tempContent = "";
		
		String resourcePath = GlobalContext.getPropertyString("resource.path", "resource");
		String path = resourcePath + File.separatorChar+"mail"+File.separatorChar+"passwordChangeMail.html";
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			
			while(true){
				tempContent += br.readLine();
				if(br.readLine() == null) break;
				System.out.println(tempContent);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String title = "비밀번호 변경을 요청하셨습니다";
		
		content = this.replaceString(tempContent,beforeCompany,afterCompany);
		content = this.replaceString(content, passwordURL, url);
		content = this.replaceString(content, signUpBaseUrl, baseUrl);
		System.out.println(content);
		
		try{
			(new EMailServerSoapBindingImpl()).sendMail(from, getEmail(), title, content);
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
	}
	
	@ServiceMethod(callByContent=true, payload={"email"}, validate=true, target=ServiceMethodContext.TARGET_SELF)
	public Object signUp() throws Exception {
		
		if( !CodiStringUtil.isValidEmail(this.getEmail()) ){
			throw new MetaworksException("$checkEmail");
		}
		
		Employee employee = new Employee();
		employee.setEmail(this.getEmail());
		IEmployee employeeRef = employee.findByEmail();
		
		// already sign up or invite user
		if(employeeRef != null){
			if(employeeRef.isApproved()){
				throw new Exception("$AlreadyExistingUser");	
			}else{
				sendMailForSignUp("activate.html?key=" + employeeRef.getAuthKey());
				
				this.setEmail(employeeRef.getEmail());
				this.getMetaworksContext().setHow("aftersignup");
				
			}
			
			this.getMetaworksContext().setWhere("popup");
			
			return this;
		}

		String authKey = UUID.randomUUID().toString();
		
		employee.setAuthKey(authKey);
		
		String comAlias = Employee.extractTenantName(this.getEmail());
		boolean isAdmin = false;
		
		Company company = new Company();
		company.setAlias(comAlias);
		
		ICompany findCompany = company.findByAlias();
		if(findCompany == null){
			isAdmin = true;
			
			// not yet sign up tenant
			try {
				company.setComCode(company.createNewId());
				company.setComName(comAlias);

				findCompany = company.createDatabaseMe();
			} catch (Exception e) {
				e.printStackTrace();
				throw new MetaworksException(e.getMessage());
			}
		}

		String tenantId = findCompany.getComCode();
		String defaultUX = "wave";
		String defaultMob = "auto";
		
		//tenantName.substring(0, tenantName.lastIndexOf("@"))
		employee.setGlobalCom(tenantId);
		employee.setAuthKey(authKey);
		employee.setIsAdmin(isAdmin);
		employee.setIsDeleted("0");
		employee.setPreferUX(defaultUX);
		employee.setPreferMob(defaultMob);
		employee.setEmpCode(employee.createNewId());
		employee.setLocale(localeManager.getLanguage());
		
		try {
			employee.createDatabaseMe();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new MetaworksException(e.getMessage());
		}
		
		sendMailForSignUp("activate.html?key=" + authKey);
		
		this.getMetaworksContext().setHow("aftersignup");
		/*
		SignUpConfirm confirm = new SignUpConfirm();
		confirm.setEmail(this.getEmail());
		confirm.setUrl(activateURL);
		return confirm;
		*/
		
		this.getMetaworksContext().setWhere("popup");
		
		return this;
		
	}
	
	
	@ServiceMethod(callByContent=true, payload={"email"}, validate=true, target=ServiceMethodContext.TARGET_STICK)
	public Object firstSignUp() throws Exception {
		
		if( !CodiStringUtil.isValidEmail(this.getEmail()) ){
			throw new MetaworksException("$checkEmail");
		}
		
		Employee employee = new Employee();
		employee.setEmail(this.getEmail());
		IEmployee employeeRef = employee.findByEmail();
		
		// already sign up or invite user
		if(employeeRef != null){
			if(employeeRef.isApproved()){
				throw new Exception("$AlreadyExistingUser");	
			}else{
				sendMailForSignUp("activate.html?key=" + employeeRef.getAuthKey());
				
				this.setEmail(employeeRef.getEmail());
				this.getMetaworksContext().setHow("aftersignup");
				
			}
			
			this.getMetaworksContext().setWhere("popup");
			
			return this;
		}

		String authKey = UUID.randomUUID().toString();
		
		employee.setAuthKey(authKey);
		
		String comAlias = Employee.extractTenantName(this.getEmail());
		boolean isAdmin = false;
		
		Company company = new Company();
		company.setAlias(comAlias);
		
		ICompany findCompany = company.findByAlias();
		if(findCompany == null){
			isAdmin = true;
			
			// not yet sign up tenant
			try {
				company.setComCode(company.createNewId());
				company.setComName(comAlias);

				findCompany = company.createDatabaseMe();
			} catch (Exception e) {
				e.printStackTrace();
				throw new MetaworksException(e.getMessage());
			}
		}

		String tenantId = findCompany.getComCode();
		String defaultUX = "wave";
		String defaultMob = "auto";
		
		//tenantName.substring(0, tenantName.lastIndexOf("@"))
		employee.setGlobalCom(tenantId);
		employee.setAuthKey(authKey);
		employee.setIsAdmin(isAdmin);
		employee.setIsDeleted("0");
		employee.setPreferUX(defaultUX);
		employee.setPreferMob(defaultMob);
		employee.setEmpCode(employee.createNewId());
		
		try {
			employee.createDatabaseMe();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new MetaworksException(e.getMessage());
		}
		
		sendMailForSignUp("activate.html?key=" + authKey);
		
		this.getMetaworksContext().setHow("aftersignup");
		/*
		SignUpConfirm confirm = new SignUpConfirm();
		confirm.setEmail(this.getEmail());
		confirm.setUrl(activateURL);
		return confirm;
		*/
		
		this.getMetaworksContext().setWhere("popup");
		
		return this;
		
	}
	
	@ServiceMethod(payload={"userId"}, target=ServiceMethodContext.TARGET_NONE)
	public boolean checkAuthSocial(){
		if(this.getEmail() == null || this.getEmail().length() == 0)
			return false;
		
		Employee employee = new Employee();
		employee.setEmpCode(this.getEmail());
		
		IEmployee employeeRef = null;
		
		try{
			employeeRef = employee.databaseMe();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(employeeRef != null)
			return true;	
		else
			return false;
	}
	
	@ServiceMethod(payload={"userId", "facebookSSO"}, target=ServiceMethodContext.TARGET_NONE)
	public Session makeSession() throws Exception {
		return loginService();
	}
	
	@Test(scenario="first", starter=true, instruction="Welcome! If you have account, sign in please... or sign up for your new account.", next="autowiredObject.org.uengine.codi.mw3.model.InstanceListPanel.newInstance()")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)//, validate=true)
	public Object[] login() throws Exception {

		/*
		Company company = new Company();
		company.setComCode(findEmp.getGlobalCom());
		ICompany findcompany = company.findByCode();
		*/
		
//		if(!findcompany.getAlias().equals(TenantContext.getThreadLocalInstance().getTenantId())){
//			throw new Exception("");
//		}
		
		Session session = loginService();
		
		return login(session);
		
		/*
		MainPanel mainPanel=null;/*
		PageNavigator pageNavigator = new PageNavigator();
		pageNavigator.setSession(session);
		
		if("knowledge".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("knowledge.use", "1"))){
			mainPanel = pageNavigator.goKnowledge();
		}else if("pinterest".equals(lastVisitPage)){
			mainPanel = pageNavigator.goPinterest();

		}else if("ide".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("ide.use", "1"))){
			mainPanel = pageNavigator.goIDE();
		}else if("marketplace".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("marketplace.use", "1"))){
			mainPanel = pageNavigator.goMarketplace();
		}else if("selfservice".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("selfservice.use", "1"))){
			mainPanel = pageNavigator.goSelfServicePortal();
		}else{
			String preferUX = session.getEmployee().getPreferUX();
			if("sns".equals(preferUX) || "".equals(preferUX)){
				mainPanel = pageNavigator.goSns();
			}else{
				mainPanel = pageNavigator.goProcess();
			}
		}
		PageNavigator pageNavigator = null;
		String pageNavigatorPropertyName="";
		String className = null;
		
		if("1".equals(GlobalContext.getPropertyString("oce.use", "1"))){
			pageNavigatorPropertyName = "oce.pagenavigator.class";
			session.setUx("oce");
		}
		else{
			pageNavigatorPropertyName = "codi.pagenavigator.class";
		}
		
		className = GlobalContext.getPropertyString(pageNavigatorPropertyName);
		
		Class c = Thread.currentThread().getContextClassLoader().loadClass(GlobalContext.getPropertyString(pageNavigatorPropertyName));
		Object object = c.newInstance();
		
		if(object instanceof PageNavigator){
			pageNavigator = (PageNavigator)object;
		}
		else{
			throw new Exception("pageNavigator가 잘못 지정되었습니다. uengine.properties의 pagenavigatorClassName을 수정해주세요.");
		}
		
		pageNavigator.session = session;
		
		if("oce".equals(session.getUx())){
			mainPanel = pageNavigator.goDashBoard();
		}else{
			if("knowledge".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("knowledge.use", "1"))){
				mainPanel = pageNavigator.goKnowledge();
			}else if("pinterest".equals(lastVisitPage)){
				mainPanel = pageNavigator.goPinterest();
			}else if("ide".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("ide.use", "1"))){
				mainPanel = pageNavigator.goIDE();
			}else if("marketplace".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("marketplace.use", "1"))){
				mainPanel = pageNavigator.goMarketplace();
			}else if("selfservice".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("selfservice.use", "1"))){
				mainPanel = pageNavigator.goSelfServicePortal();
			}else{
				String preferUX = session.getEmployee().getPreferUX();
				if("sns".equals(preferUX) || "".equals(preferUX)){
					mainPanel = pageNavigator.goSns();
				}else{
					mainPanel = pageNavigator.goProcess();
				}
			}
		}
		
		if("1".equals(GlobalContext.getPropertyString("sso.use", "1"))){
			//Request Token
			OAuthBasic oauth = new OAuthBasic();
			oauth.requestToken();

			// Request Access Token
			String accessToken = oauth.retrieveAccessToken("");
			System.out.println("accessToken  = " + accessToken);

			session.setAccessToken(accessToken);
			
			//user id, access token save to cubrid
			OAuthDB oauthDB = new OAuthDB(); 
			oauthDB.update(session.getUser().getUserId(), session.getAccessToken());
		}
		*/
		
		/*
		HttpServletRequest httpServletRequest = TransactionContext.getThreadLocalInstance().getRequest();
		
		String ipAddress = httpServletRequest.getRemoteAddr();
		String hostAddress = httpServletRequest.getRemoteHost();
		
        InetAddress local = InetAddress.getLocalHost();

		System.out.println("-------- client 접속 ------------------");
		System.out.println("Client 명 : "+hostAddress);
		System.out.println("Client Ip : "+ipAddress);
		System.out.println();
        System.out.println("서버 pc 명: " + local.getHostName());
        System.out.println("서버    IP: " + local.getHostAddress());
        System.out.println("---------------------------------------");
       
        
        CodiLog  log = new CodiLog();
        log.setId(log.createNewId());
        log.setEmpcode(findEmp.getEmpCode());
        log.setComCode(findEmp.getGlobalCom());
        log.setType("login");
        log.setDate(new Date());
        log.setIp(ipAddress);
        log.createDatabaseMe();
        */
		
		//new Remover(new ModalWindow(), true), 
		
	}
	
	public Object[] login(Session session) throws Exception {
		
		session.fillSession();
		session.fillUserInfoToHttpSession();
		
		storeIntoServerSession(session);

		if("1".equals(PageNavigator.USE_TADPOLE)){
			goTadpoleLogin(session.getEmployee().getEmail(), password);
		}
		
		HttpServletRequest httpServletRequest = TransactionContext.getThreadLocalInstance().getRequest();
		
		String ipAddress = httpServletRequest.getRemoteAddr();

        CodiLog  log = new CodiLog();
        log.setId(log.createNewId());
        log.setEmpcode(session.getEmployee().getEmpCode());
        log.setComCode(session.getEmployee().getGlobalCom());
        log.setType("login");
        log.setDate(new Date());
        log.setIp(ipAddress);
        log.createDatabaseMe();

        
		MainPanel mainPanel;
		
		PageNavigator pageNavigator = new PageNavigator();
		pageNavigator.session = session;
		
		if("1".equals(StartCodi.USE_OCE)){
				mainPanel =  pageNavigator.goDashBoard();
		}else{
			if("knowledge".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("knowledge.use", "1"))){
				mainPanel = pageNavigator.goKnowledge();
			}else if("pinterest".equals(lastVisitPage)){
				mainPanel = pageNavigator.goPinterest();
			}else if("ide".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("ide.use", "1"))){
				mainPanel = pageNavigator.goIDE();
			}else if("marketplace".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("marketplace.use", "1"))){
				mainPanel = pageNavigator.goMarketplace();
			}else if("selfservice".equals(lastVisitPage) && "1".equals(GlobalContext.getPropertyString("selfservice.use", "1"))){
				mainPanel = pageNavigator.goSelfServicePortal();
			}else{
				String preferUX = session.getEmployee().getPreferUX();
				if("sns".equals(preferUX) || "".equals(preferUX)){
					mainPanel = pageNavigator.goSns();
				}else{
					mainPanel = pageNavigator.goProcess();
				}
			}
		}
		
		Locale locale = new Locale();
		locale.setLanguage(session.getEmployee().getLocale());
		locale.load();

		return new Object[]{new Refresh(locale), new Refresh(mainPanel, false, true)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public void goForgotPassword(){
		this.setStatus("forgotpassword");
		this.getMetaworksContext().setHow("forgotpassword");
	}
	
	@ServiceMethod(callByContent=true, payload={"email"})
	public void forgotPassword() throws Exception{
		
		Employee employee = new Employee();
		employee.setEmail(this.getEmail());
		IEmployee employeeRef = employee.findByEmail();

		// already s	ign up or invite user
		if(employeeRef == null){
			throw new Exception("$NoExistedUser");
		}else if(!employeeRef.isApproved())
			throw new Exception("$NoExistedUser");
		
		// not yet sign up user
		String authKey = UUID.randomUUID().toString();
		
		employee.setEmpCode(employeeRef.getEmpCode());
		employee.databaseMe().setAuthKey(authKey);
		this.setEmail(employeeRef.getEmail());
		
		// send mail
		this.sendMailForForgotPassword("findpw.html?key=" + authKey);
		
		this.getMetaworksContext().setHow("afterforgotpassword");
		return;
	}
	
	public void fireServerSession(Session session) {
		String userId = session.getUser().getUserId().toUpperCase();
		
		
		if(SessionIdForEmployeeMapping.containsKey(userId)){
			String sessionId = SessionIdForEmployeeMapping.get(userId);
			
			WebContext wctx = WebContextFactory.get();
			
			if(sessionId.equals(wctx.getScriptSession().getId())){
				SessionIdForEmployeeMapping.remove(userId);
				
				if(session.getEmployee() != null){
					String partCode = session.getEmployee().getPartCode();
					String globalCom = session.getEmployee().getGlobalCom();

					if(partCode != null && partCode.length() > 0){
						partCode = partCode.toUpperCase();
						HashMap<String, String> mapping = null;
						
						if(SessionIdForDeptMapping.containsKey(partCode)){
							mapping = SessionIdForDeptMapping.get(partCode);
							mapping.remove(userId);
							SessionIdForDeptMapping.put(partCode, mapping);
						}
					}
					
					if(globalCom != null && globalCom.length() > 0){
						globalCom = globalCom.toUpperCase();
						HashMap<String, String> mapping = null;
						
						if(SessionIdForCompanyMapping.containsKey(globalCom)){
							mapping = SessionIdForCompanyMapping.get(globalCom);
							mapping.remove(userId);
							SessionIdForCompanyMapping.put(globalCom, mapping);
						}
					}
				}
			}
		}
		
		

	}
	
	public void storeIntoServerSession(Session session) {
		/*
		 * 2013/04/22 cjw
		 * 
		 * add tenant process
		 */
		String userId = session.getUser().getUserId().toUpperCase();
		String tenantId = null;
		
		if(session.getEmployee() != null)
			tenantId = session.getEmployee().getGlobalCom();
		
		//setting the userId into session attribute;
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("tenantId", tenantId);
		
		/*
		httpSession.setAttribute("tenantId", tenantId);
		
		String mySourceCodeBase = CodiClassLoader.mySourceCodeBase();
		
		//if(mySourceCodeBase.endsWith("main/src/"))
		if(mySourceCodeBase.endsWith(tenantId + "/src/"))
			(new File(mySourceCodeBase)).mkdirs();
		
		if(mySourceCodeBase!=null && new File(mySourceCodeBase).exists()){
			httpSession.setAttribute("sourceCodeBase", mySourceCodeBase);
		}
		*/
		
		
		// fire exist session
		/*
		if(SessionIdForEmployeeMapping.containsKey(userId)){
			MetaworksRemoteService.pushTargetScript(Login.getSessionIdWithUserId(getUserId()), "mw3.getAutowiredObject('" + Session.class.getName() + "').__getFaceHelper().fire", new Object[]{"2"});
		}
		*/
		fireServerSession(session);
		
		// manager sessionId
		WebContext wctx = WebContextFactory.get();
		String sessionId = wctx.getScriptSession().getId();
		
		SessionIdForEmployeeMapping.put(userId, sessionId); //stores session id to find out with user Id
		
		if(session.getEmployee() != null && session.getEmployee().isApproved()){
			String partCode = session.getEmployee().getPartCode();
			String globalCom = session.getEmployee().getGlobalCom();
			
			if(partCode != null && partCode.length() > 0){
				partCode = partCode.toUpperCase();					
				HashMap<String, String> mapping = null;
				
				if(SessionIdForDeptMapping.containsKey(partCode))
					mapping = SessionIdForDeptMapping.get(partCode);
				else
					mapping = new HashMap<String, String>();
				
				mapping.put(userId, sessionId);
				SessionIdForDeptMapping.put(partCode, mapping);
				
			}
			
			if(globalCom != null && globalCom.length() > 0){
				globalCom = globalCom.toUpperCase();
				HashMap<String, String> mapping = null;
				
				if(SessionIdForCompanyMapping.containsKey(globalCom))
					mapping = SessionIdForCompanyMapping.get(globalCom);
				else
					mapping = new HashMap<String, String>();
				
				//System.out.println("LOGIN : " + sessionId);
				mapping.put(userId, sessionId);
				SessionIdForCompanyMapping.put(globalCom, mapping);
				
			}
		}
		
		String device = "desktop";
		if(Main.isPad()){
			device = "pad";
		}else if(Main.isPhone()){
			device = "phone";
		}
		
		userIdDeviceMapping.put(userId.toUpperCase(), device); //stores session id to find out with user Id
		

	}

	/*
	@ServiceMethod(callByContent=true)
	public MainPanel loginSocialCoding() throws Exception {
		IUser loginUser = new User();
		
		loginUser.setName(getName());
		loginUser.setUserId(getEmail());
				
		Session session = new Session();
		session.setUser(loginUser);
		session.setDefId(getDefId());
		
		storeIntoServerSession(session);
		
		MainPanel mainPanel = new MainPanel(new Main(session));
		
		return mainPanel;
		//return new MainPanel(new Knowledge(session));
	}
	*/
	
	protected void goTadpoleLogin(String email, String pw){
		String ip = GlobalContext.getPropertyString("pole.call.ip");
		String port = GlobalContext.getPropertyString("pole.call.port");
		String db  = GlobalContext.getPropertyString("pole.call.db");
		
		String parameter = "?email=" + email + "&pw=" + pw;
		
		String sUrl = "http://" + ip + ":" + port + db + "/loginUser" + parameter;
		
		URL url;					//URL 주소 객체
		URLConnection connection;	//URL접속을 가지는 객체
		InputStream is;				//URL접속에서 내용을 읽기위한 Stream
		InputStreamReader isr;
		BufferedReader br;
		try{
			//URL객체를 생성하고 해당 URL로 접속한다..
			url = new URL(sUrl);
			connection = url.openConnection();
			
			//내용을 읽어오기위한 InputStream객체를 생성한다..
			is = connection.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			//내용을 읽어서 화면에 출력한다..
			String buf = null;
			
			while(true){
				buf = br.readLine();
				if(buf == null) break;
				System.out.println(buf);
			}
			
		}catch(IOException ioe){
			System.err.println("IOException " + ioe);
			ioe.printStackTrace();
		}
	}

}