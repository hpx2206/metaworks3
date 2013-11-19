package org.uengine.codi.mw3.model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import javax.validation.constraints.Pattern;

import org.metaworks.ContextAware;
import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.SignUpConfirm;
import org.uengine.kernel.GlobalContext;
import org.uengine.webservices.emailserver.impl.EMailServerSoapBindingImpl;



public class Invitation implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public Invitation(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		
		if(session!=null){
			String emailAddress = session.getEmployee().getEmail();
			
			int posAt = emailAddress.indexOf("@");

			if(emailAddress!=null && posAt > -1){
				String domain = emailAddress.substring(posAt + 1);
				setEmail(domain);
			}
		}
	}
	
	String email;
	//@Face(options={"placeholder"}, values={"Enter your work email address"})
	@Pattern(regexp = "/^[a-z A-Z 0-9\\-_]+@[a-z A-Z 0-9\\-]+(\\.[a-z A-Z 0-9 \\-]+)+$/", message = "이메일 형식이 잘못되었습니다.")
	@Face(options={"hideLabel","placeholder"}, values={"true", "$InputEmail"})
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}


	@AutowiredFromClient
	public Session session;
	
	@Autowired
    public Locale locale;
	
	//randomPassword()생성
	public String randomPassword(){
	    SecureRandom random = new SecureRandom();
	    return new BigInteger(40, random).toString(32);
	}
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND, validate=true)
	@Face(displayName="$Invite")
	public Object invite() throws Exception{
		
		/*
		Employee employee = new Employee();
		employee.setEmail(this.getEmail());
		IEmployee findEmployee = employee.findByEmail();
		
		if(findEmployee != null){
			throw new Exception("$AlreadyExistingUser");
		}
				
		String defaultUX = "wave";
		String defaultMob = "auto";
		
		String authKey = UUID.randomUUID().toString();
		String tenantName = Employee.extractTenantName(this.getEmail());
		
		
		Company company = new Company();
		company.setAlias(tenantName);
		ICompany findCompany = company.findByName();

	
		if(findCompany == null){
			throw new Exception("$DeferentCompany");
		}
		else if(!findCompany.getComCode().equals(session.getEmployee().getGlobalCom())){
			throw new Exception("$DeferentCompany");
		}
		
		
		Employee newUser = new Employee();
		newUser.setEmail(getEmail());
		newUser.setEmpCode(employee.createNewId());
		newUser.setPreferUX(defaultUX);
		newUser.setPreferMob(defaultMob);
		newUser.setAuthKey(authKey);
		newUser.setIsDeleted("0");
		newUser.setGlobalCom(session.getCompany().getComCode());
		newUser.setLocale(session.getEmployee().getLocale());
		newUser.setApproved(false);
		
		try {
			findEmployee = newUser.createDatabaseMe();
			newUser.flushDatabaseMe();
			
			System.out.println("memberId : " + findEmployee.getEmpCode());
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new MetaworksException(e.getMessage());
		}
		
		locale = new Locale(); 
		Properties prop = locale.webMessageBundles.get(session.getEmployee().getLocale()); 
		String EmailInviteMsgTitle = prop.getProperty("EmailInviteMsgTitle"); 
		String EmailInviteMsg = prop.getProperty("EmailInviteMsg");
		String EmailTempPw = prop.getProperty("EmailTempPw");
		String EmailChangePw = prop.getProperty("EmailChangePw");
		
		
		String requestedURL = TransactionContext.getThreadLocalInstance().getRequest().getRequestURL().toString(); 
        String base = requestedURL.substring( 0, requestedURL.lastIndexOf( "/" ) );
        
//        URL urlURL = new java.net.URL(base);
//       	String host = urlURL.getHost();
//       	int port = urlURL.getPort();
//       	String protocol = urlURL.getProtocol();

//    		String activateURL = protocol + "://" + host + (port == 80 ? "" : ":"+port) + TransactionContext.getThreadLocalInstance().getRequest().getContextPath() + "/activate.html?key=" + findEmployee.getAuthKey() + "&mid=" + findEmployee.getEmpCode() + "&tid=" + findEmployee.getGlobalCom();
           	
     	String host = GlobalContext.getPropertyString("web.server.ip");
    	String port = GlobalContext.getPropertyString("web.server.port");
    	String root = GlobalContext.getPropertyString("web.context.root");
    		
           	
    		
    	String activateURL = "http://"+ host + ":" + port + root + "/activate.html?key=" + findEmployee.getAuthKey();
		System.out.println(activateURL);		
		
		try{
		    
			(new EMailServerSoapBindingImpl()).sendMail(session.getEmployee().getEmail(), 
						getEmail(), 
						EmailInviteMsgTitle + session.getUser().getName(), 
						"<p><a href='" + activateURL + "'>"+"Click-"+activateURL+"</a><br/>"
			);
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
		*/
		
		/*
		Contact newContact = new Contact();
		newContact.setUserId(session.getUser().getUserId());
		
		IUser newUser_ = new User();
		newUser_.setName(newUser.getEmpName());
		newUser_.setUserId(newUser.getEmpCode());
		newUser_.setNetwork("local");
		newContact.setFriendId(newUser.getEmpCode());
		newContact.setFriend(newUser_);
		newContact.createDatabaseMe();
		
		newContact = new Contact();
		newContact.setUserId(newUser_.getUserId());
		
		IUser me_ = new User();
		me_.setName(session.getUser().getName());
		me_.setUserId(session.getUser().getUserId());
		me_.setNetwork("local");
		newContact.setFriendId(me_.getUserId());
		newContact.setFriend(me_);
		newContact.createDatabaseMe();
		
		ContactList cp = new ContactList();
		cp.setId(ContactList.LOCAL);
		cp.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		cp.getMetaworksContext().setWhere(ContactList.LOCAL);
		
		cp.load(session.getUser().getUserId());

		return new Refresh(cp);
		*/
		
		return new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE);
	}
	
	
	@ServiceMethod(callByContent=true, validate=true)
	public Object signUp() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmail(this.getEmail());
		IEmployee findEmployee = employee.findByEmail();

		// already s	ign up or invite user
		if(findEmployee != null){
			throw new Exception("$AlreadyExistingUser");
		}
		
		
		// not yet sign up user
		String authKey = UUID.randomUUID().toString();
		String tenantName = Employee.extractTenantName(this.getEmail());
		boolean isAdmin = false;
		
		System.out.println("authKey : " + authKey);
		System.out.println("tenantName : " + tenantName);		
		
		
		Company company = new Company();
		company.setAlias(tenantName);
		ICompany findCompany = company.findByName();

	
		if(findCompany == null){
			// not yet sign up tenant
			try {
				company.setComCode(company.createNewId());
				company.setComName(tenantName);

				findCompany = company.createDatabaseMe();
				isAdmin = true;
				employee.setIsAdmin(true);
				employee.setApproved(true);
			} catch (Exception e) {
				e.printStackTrace();
				throw new MetaworksException(e.getMessage());
			}
		}

		String tenantId = findCompany.getComCode();
		System.out.println("tenantId : " + tenantId);
		String defaultUX = "wave";
		String defaultMob = "auto";
		
		//tenantName.substring(0, tenantName.lastIndexOf("@"))
		employee.setGlobalCom(tenantId);
		employee.setAuthKey(authKey);
		employee.setIsAdmin(isAdmin);
		employee.setIsDeleted("0");
		employee.setApproved(false);
		employee.setPreferUX(defaultUX);
		employee.setPreferMob(defaultMob);
		employee.setEmpCode(employee.createNewId());
		employee.setEmpName(getEmail().substring(0, getEmail().lastIndexOf("@")));
		
		try {
			findEmployee = employee.createDatabaseMe();
			employee.flushDatabaseMe();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new MetaworksException(e.getMessage());
		}
		
		
		
	

//		String requestedURL = TransactionContext.getThreadLocalInstance().getRequest().getRequestURL().toString(); 
//        String base = requestedURL.substring( 0, requestedURL.lastIndexOf( "/" ) );
//        
//        URL urlURL = new java.net.URL(base);
//       	String host = urlURL.getHost();
//       	int port = urlURL.getPort();
//       	String protocol = urlURL.getProtocol();

       	
       	String host = GlobalContext.getPropertyString("web.server.ip");
		String port = GlobalContext.getPropertyString("web.server.port");
		String root = GlobalContext.getPropertyString("web.context.root");
		
       	
		
//		String activateURL = protocol + "://" + host + (port == 80 ? "" : ":"+port) + TransactionContext.getThreadLocalInstance().getRequest().getContextPath() + "/activate.html?key=" + findEmployee.getAuthKey() + "&mid=" + findEmployee.getEmpCode() + "&tid=" + findEmployee.getGlobalCom();
       	String activateURL = "http://"+ host + ":" + port + root + "/activate.html?key=" + findEmployee.getAuthKey();
		System.out.println(activateURL);
		

		try{
			
			(new EMailServerSoapBindingImpl()).sendMail(getEmail(), getEmail(), "Codi Account - Email Authority","<p><a href='" + activateURL + "'>"+"Click-"+activateURL+"</a><br/>");
		
		
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
		
		SignUpConfirm confirm = new SignUpConfirm();
		confirm.setEmail(this.getEmail());
		confirm.setUrl(activateURL);
		return confirm;
	}
	
	@ServiceMethod(callByContent=true, validate=true)
	public Object findPassword() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmail(this.getEmail());
		IEmployee findEmployee = employee.findByEmail();

		// already s	ign up or invite user
		if(findEmployee == null){
			throw new Exception("$NoExistedUser");
		}
		
		
		// not yet sign up user
		String authKey = UUID.randomUUID().toString();
		String tenantName = Employee.extractTenantName(this.getEmail());
		boolean isAdmin = false;
		
		System.out.println("authKey : " + authKey);
		System.out.println("tenantName : " + tenantName);		
		
		
		employee.copyFrom(findEmployee);
		employee.setApproved(false);
		try {
			employee.syncToDatabaseMe();
			employee.flushDatabaseMe();
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new MetaworksException(e.getMessage());
		}
		
		
		
		String requestedURL = TransactionContext.getThreadLocalInstance().getRequest().getRequestURL().toString(); 
        String base = requestedURL.substring( 0, requestedURL.lastIndexOf( "/" ) );
        
//        URL urlURL = new java.net.URL(base);
//       	String host = urlURL.getHost();
//       	int port = urlURL.getPort();
//       	String protocol = urlURL.getProtocol();
	
       	
//       	String findpwURL = protocol + "://" + host + (port == 80 ? "" : ":"+port) + TransactionContext.getThreadLocalInstance().getRequest().getContextPath() + "/findpw.html?key=" + findEmployee.getAuthKey() + "&mid=" + findEmployee.getEmpCode() + "&tid=" + findEmployee.getGlobalCom();
     	String host = GlobalContext.getPropertyString("web.server.ip");
    	String port = GlobalContext.getPropertyString("web.server.port");
    	String root = GlobalContext.getPropertyString("web.context.root");
    		
           	
    		
//    	String activateURL = protocol + "://" + host + (port == 80 ? "" : ":"+port) + TransactionContext.getThreadLocalInstance().getRequest().getContextPath() + "/activate.html?key=" + findEmployee.getAuthKey() + "&mid=" + findEmployee.getEmpCode() + "&tid=" + findEmployee.getGlobalCom();
        String findpwURL = "http://"+ host + ":" + port + root + "/findpw.html?key=" + findEmployee.getAuthKey();
       	
		System.out.println(findpwURL);

		try{
			
			(new EMailServerSoapBindingImpl()).sendMail(getEmail(), getEmail(), "Codi Account - Email Authority","<p><a href='" + findpwURL + "'>"+"Click-"+findpwURL+"</a><br/>");
		
		
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
		
		SignUpConfirm confirm = new SignUpConfirm();
		confirm.setEmail(this.getEmail());
		confirm.setUrl(findpwURL);
		return confirm;
	}

}
