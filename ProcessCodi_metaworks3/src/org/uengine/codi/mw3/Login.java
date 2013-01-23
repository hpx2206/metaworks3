package org.uengine.codi.mw3;

import java.io.File;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

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
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.PortraitImageFile;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.User;

public class Login implements ContextAware {
	
	protected static Hashtable<String, String> userIdSessionIdMapping = new Hashtable<String, String>();
	protected static Hashtable<String, String> userIdDeviceMapping = new Hashtable<String, String>();	
	
	public Login(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
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
		emp.setPassword(getPassword());
		
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
		return userIdSessionIdMapping.get(userId.toUpperCase());
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
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP	)
	public Object popupSubscribe() throws Exception{
		Login login = new Login();
		login.setStatus("subscribe");
		
		ModalWindow window = new ModalWindow(login, 720, 450, "Sign Up");
		
		return window;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public Object subscribe() throws Exception{
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
		}
		
		emp.setEmpName(name);
		emp.setPassword(getPassword());
		
		return emp;
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
	
	@Test(scenario="first", starter=true, instruction="Welcome! If you have account, sign in please... or sign up for your new account.", next="autowiredObject.org.uengine.codi.mw3.model.InstanceListPanel.newInstance()")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)//, validate=true)
	public Object[] login() throws Exception {

		Session session = loginService();
		
		Locale locale = new Locale();
		locale.setLanguage(session.getEmployee().getLocale());
		locale.load();
		
		MainPanel mainPanel;
		PageNavigator pageNavigator = new PageNavigator();
		pageNavigator.session = session;

		if("knowledge".equals(lastVisitPage)){
			mainPanel = pageNavigator.goKnowledge();
		}else if("pinterest".equals(lastVisitPage)){
			mainPanel = pageNavigator.goPinterest();
		}else if("ide".equals(lastVisitPage)){
			mainPanel = pageNavigator.goIDE();
		}else{
			String preferUX = session.getEmployee().getPreferUX();
			if("sns".equals(preferUX) || "".equals(preferUX)){
				mainPanel = pageNavigator.goSns();
			}else{
				mainPanel = pageNavigator.goProcess();
			}
		}
		
		storeIntoServerSession();

		return new Object[]{new Remover(new ModalWindow(), true), new Refresh(locale), new Refresh(mainPanel, false, true)};
	}

	
	public void storeIntoServerSession() {
		//setting the userId into session attribute;
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		httpSession.setAttribute("userId", getUserId());
		
		WebContext wctx = WebContextFactory.get();
		
		userIdSessionIdMapping.put(getUserId().toUpperCase(), wctx.getScriptSession().getId()); //stores session id to find out with user Id
		
		String device = "desktop";
		if(Main.isPad()){
			device = "pad";
		}else if(Main.isPhone()){
			device = "phone";
		}
		
		userIdDeviceMapping.put(getUserId().toUpperCase(), device); //stores session id to find out with user Id
		
		String mySourceCodeBase = CodiClassLoader.mySourceCodeBase();
		
		if(mySourceCodeBase.endsWith("main/src/"))
			(new File(mySourceCodeBase)).mkdirs();
		
		if(mySourceCodeBase!=null && new File(mySourceCodeBase).exists()){
			httpSession.setAttribute("sourceCodeBase", mySourceCodeBase);
		}
	}

	@ServiceMethod(callByContent=true)
	public MainPanel loginSocialCoding() throws Exception {
		storeIntoServerSession();
		
		IUser loginUser = new User();
		
		loginUser.setName(getName());
		loginUser.setUserId(getUserId());
				
		Session session = new Session();
		session.setUser(loginUser);
		session.setDefId(getDefId());

		MainPanel mainPanel = new MainPanel(new Main(session));
		
		return mainPanel;
		//return new MainPanel(new Knowledge(session));
	}

}