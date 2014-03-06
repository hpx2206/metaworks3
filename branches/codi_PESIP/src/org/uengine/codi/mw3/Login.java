package org.uengine.codi.mw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import net.oauth.dna.OAuthBasic;
import net.oauth.dna.OAuthDB;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.EnterpriseInformation;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.PortraitImageFile;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.User;
import org.uengine.kernel.GlobalContext;

public class Login implements ContextAware {
	
	protected static Hashtable<String, HashMap<String, String>> SessionIdForCompanyMapping = new Hashtable<String, HashMap<String, String>>();
	protected static Hashtable<String, HashMap<String, String>> SessionIdForDeptMapping = new Hashtable<String, HashMap<String, String>>();
	protected static Hashtable<String, String> SessionIdForEmployeeMapping = new Hashtable<String, String>();
	
	protected static Hashtable<String, String> userIdDeviceMapping = new Hashtable<String, String>();	
	
	public Login(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		
		this.setUseSubscribe("1".equals(GlobalContext.getPropertyString("signup.use", "1")));
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

	String userId;
		@Face(options="placeholder", values="E-Mail")
		@Validator(name = ValidatorContext.VALIDATE_NOTNULL)
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String portrait;
		public String getPortrait() {
			return portrait;
		}
		public void setPortrait(String portrait) {
			this.portrait = portrait;
		}
		
	boolean isAdmin;
		public boolean isAdmin() {
			return isAdmin;
		}
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}
		
	boolean useSubscribe;
		public boolean isUseSubscribe() {
			return useSubscribe;
		}
		public void setUseSubscribe(boolean useSubscribe) {
			this.useSubscribe = useSubscribe;
		}

	String defId;	
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	boolean facebookSSO;
		public boolean isFacebookSSO() {
			return facebookSSO;
		}
		public void setFacebookSSO(boolean facebookSSO) {
			this.facebookSSO = facebookSSO;
		}

	Boolean rememberMe;
		public Boolean getRememberMe() {
			return rememberMe;
		}
		public void setRememberMe(Boolean rememberMe) {
			this.rememberMe = rememberMe;
		}
		
	String lastVisitPage;
		public String getLastVisitPage() {
			return lastVisitPage;
		}
		public void setLastVisitPage(String lastVisitPage) {
			this.lastVisitPage = lastVisitPage;
		}

	String password;
		@Face(options={"type", "placeholder"}, values={"password", "Password"})
		@Validator(name = ValidatorContext.VALIDATE_NOTNULL)
		@Hidden(when = MetaworksContext.WHEN_VIEW)
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
		
	public Session loginService() throws Exception {
		
		Session session = new Session();
		
		IEmployee emp = new Employee();
		emp.setEmpCode(getUserId());
		emp.setPassword(getPassword().trim());
		
		if(this.isFacebookSSO()){
			if (getUserId() != null){
				session.setEmployee(emp.findMe());
			}
			
		}else{
			if (getUserId() != null && getPassword() != null) {
				session.setEmployee(emp.load());
			}
		}
		
		session.fillSession();
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(emp.getMetaworksContext().getWhen());
			
			
			//try {				
/*			} catch (Exception e) {
			MetaworksContext contextWhenEdit = new MetaworksContext();
			contextWhenEdit.setWhen(MetaworksContext.WHEN_EDIT);
			emp.setMetaworksContext(contextWhenEdit);
			setErrorMessage(e.getMessage());
*/
			// FIXME Monitoring for login errors
			//throw new RuntimeException(e);//.printStackTrace();
		//}
		
		setPassword(null);
		
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
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP	)
	public Object popupSubscribe() throws Exception{
		
		this.setStatus("subscribe");
		
		ModalWindow window = new ModalWindow(this, 600, 380, "Sign Up");
		window.setId("subscribe");
		window.setResizable(false);
		
		return window;
	}
	
	@ServiceMethod(payload={"userId"}, target=ServiceMethodContext.TARGET_SELF)
	public Object subscribe() throws Exception{
		// 임시 작성
		
		Employee emp = new Employee();
		emp.getMetaworksContext().setHow("detail");
		emp.getMetaworksContext().setWhen("new");				
		emp.getMetaworksContext().setWhere("admin");
		emp.setImageFile(new PortraitImageFile());
		emp.getImageFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		emp.setEmpCode(getUserId());
		emp.setEmail(getUserId());
		
		emp.checkRegistered();
		
		String name = null;
		if(getUserId().length() != 0){
			try{
				name = getUserId().substring(0, getUserId().indexOf("@"));
			}catch(Exception e){
				throw new MetaworksException("$InvalidMailAddress");
			}
		}else if(getUserId().length() == 0){
			throw new MetaworksException("$YouMustCheckYourEmail");
		}
		
		//defaultUX, defaultMob 값 설정
		String defaultUX = "wave";
		String defaultMob = "auto";
				
		emp.setEmpName(name);	
		emp.setPassword(getPassword());
		emp.setPreferUX(defaultUX);
		emp.setPreferMob(defaultMob);
		return emp;
		
		//return null;
	}
		
	@ServiceMethod(payload={"userId"}, target=ServiceMethodContext.TARGET_NONE)
	public boolean checkAuthSocial(){
		if(this.getUserId() == null || this.getUserId().length() == 0)
			return false;
		
		Employee employee = new Employee();
		employee.setEmpCode(this.getUserId());
		
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
		
		if("1".equals(GlobalContext.getPropertyString("tadpole.use", "1"))){
			goTadpoleLogin(userId, password);
		}
		Session session = loginService();
		
		storeIntoServerSession(session);
		
		Locale locale = new Locale();
		locale.setLanguage(session.getEmployee().getLocale());
		locale.load();
		
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
*/		
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
		
		return new Object[]{new Remover(new ModalWindow(), true), new Refresh(locale), new Refresh(mainPanel, false, true)};
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
		String tenantId = session.getCompany().getComCode();
		
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

	@ServiceMethod(callByContent=true)
	public MainPanel loginSocialCoding() throws Exception {
		IUser loginUser = new User();
		
		loginUser.setName(getName());
		loginUser.setUserId(getUserId());
				
		Session session = new Session();
		session.setUser(loginUser);
		session.setDefId(getDefId());
		
		storeIntoServerSession(session);
		
		MainPanel mainPanel = new MainPanel(new Main(session));
		
		return mainPanel;
		//return new MainPanel(new Knowledge(session));
	}
	
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