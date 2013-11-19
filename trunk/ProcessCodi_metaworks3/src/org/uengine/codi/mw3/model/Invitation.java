package org.uengine.codi.mw3.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.SignUpConfirm;
import org.uengine.kernel.GlobalContext;
import org.uengine.util.UEngineUtil;
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
		
		Employee employee = new Employee();
		employee.setEmail(this.getEmail());
		IEmployee findEmployee = employee.findByEmail();
		
		String from = "help@opencloudengine.org";
		String beforeName = "user.name";
		String afterName = findEmployee.getEmpName();
		String beforeCompany = "user.company";
		String afterCompany =  Employee.extractTenantName(this.getEmail());
		String path = this.getClass().getResource("").getPath();
		for(int i =0; i<6 ; i++){
			path = new File(path).getParent();
		}
		path = path + File.separatorChar+"WebContent"+File.separatorChar+"resources"+File.separatorChar+"mail"+File.separatorChar+"invitationMail.html";
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(path);
			UEngineUtil.copyStream(is, bao);
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String title = afterCompany+" 의 "+ afterName + " 님이  당신을  코디에 초대 하였습니다";
		
		String tempContent = bao.toString();
		
		Login login = new Login();
		String content = login.replaceString(tempContent,beforeName,afterName);
		content = login.replaceString(content, beforeCompany, afterCompany);
		System.out.println(content);
		
		try{
			(new EMailServerSoapBindingImpl()).sendMail(from, getEmail(), title, content);
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
		
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
