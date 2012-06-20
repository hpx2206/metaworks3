package org.uengine.codi.mw3;

import java.io.File;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Dept;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IDept;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.PortraitImageFile;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.User;

public class Login extends Database<ILogin> implements ILogin{
	
	protected static Hashtable<String, String> userIdSessionIdMapping = new Hashtable<String, String>();
	protected static Hashtable<String, String> userIdDeviceMapping = new Hashtable<String, String>();
	
	
	public Login(){
		setRememberMe(new Boolean(true));
	}
	
	@Id
	String userId;
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
		
	boolean isFacebookSSO;
		
		public boolean isFacebookSSO() {
			return isFacebookSSO;
		}
		public void setFacebookSSO(boolean isFacebookSSO) {
			this.isFacebookSSO = isFacebookSSO;
		}
		
	Boolean rememberMe;
		
	
		public Boolean getRememberMe() {
			return rememberMe;
		}
		public void setRememberMe(Boolean rememberMe) {
			this.rememberMe = rememberMe;
		}

	String password;
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}

/*	String errorMessage;
		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
*/
	public Session loginService() throws Exception {
		
		Session session = new Session();
		
		if (getUserId() != null && getPassword() != null) {
			IEmployee emp = new Employee();
			emp.setEmpCode(getUserId());
			emp.setPassword(getPassword());
			
			session.setEmployee(emp.load());
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
			
		}
		
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
	
	
	public Object subscribe(){
		Employee emp = new Employee();
		emp.getMetaworksContext().setHow("detail");
		emp.getMetaworksContext().setWhen("new");				
		emp.getMetaworksContext().setWhere("admin");
		emp.setImageFile(new PortraitImageFile());
		emp.getImageFile().getMetaworksContext().setWhen(WHEN_EDIT);
		
		emp.setEmpCode(getUserId());
		emp.setPassword(getPassword());
		
		ModalWindow window = new ModalWindow(emp, 720, 450, "Sign Up");
		
		return window;
	}
		
	public Object[] login() throws Exception {


		if(isFacebookSSO){
			
			
			IUser loginUser = new User();
			
			loginUser.setName(getName());
			loginUser.setUserId(getUserId());
			loginUser.setMetaworksContext(new MetaworksContext());
			loginUser.getMetaworksContext().setWhen("view");
			
			Session session = new Session();
			session.setUser(loginUser);

			storeIntoServerSession();
			
	/*		
	*/		
			//return new MainPanel(new Knowledge(loginUser));
//			return new MainPanel(new IDE(session));
			return new Object[]{new MainPanel(new Main(session))};
			
		}else{
			Session session = loginService();
			
			Locale locale = new Locale();
			locale.setLanguage(session.getEmployee().getLocale());
			locale.load();
			
			if (getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_VIEW)){
				
				MainPanel mainPanel = new MainPanel(new Main(session));
				//MainPanel mainPanel = new MainPanel(new IDE(session));
				//MainPanel mainPanel = new MainPanel(new Knowledge(session));
				
				
				storeIntoServerSession();

				return new Object[]{locale, mainPanel};
			} else {
				return new Object[]{this};
			}
		}
	}

	
	private void storeIntoServerSession() {
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