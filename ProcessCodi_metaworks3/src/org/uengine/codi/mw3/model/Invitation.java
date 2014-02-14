package org.uengine.codi.mw3.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import javax.validation.constraints.Pattern;

import org.metaworks.ContextAware;
import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.util.CodiStringUtil;
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
	
	String invitedMessage;
		@Available(how={"afterinvite"})
		public String getInvitedMessage() {
			return invitedMessage;
		}
		public void setInvitedMessage(String invitedMessage) {
			this.invitedMessage = invitedMessage;
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
	
	
	@ServiceMethod(callByContent=true, target="popup", validate=true)
	@Face(displayName="$Invite")
	public Object[] invite() throws Exception{
		
		String authKey = UUID.randomUUID().toString();
		
		Employee emp = new Employee();
		emp.setEmail(this.getEmail());
		IEmployee findEmp = emp.findByEmail();
		
		// 1. 코디 사용자가 아닐때
		if(findEmp==null){
			String empName = this.getEmail().substring(0, this.getEmail().indexOf("@"));
			Employee newUser = new Employee();
			newUser.setEmail(getEmail());
			newUser.setEmpName(empName);
			newUser.setEmpCode(newUser.createNewId());
			newUser.setAuthKey(authKey);
			newUser.setIsDeleted("0");
			newUser.setApproved(false);
			newUser.setInviteUser(session.getEmployee().getEmpCode());
			
			String comAlias = Employee.extractTenantName(this.getEmail());
			
			Company company = new Company();
			company.setAlias(comAlias);
			
			ICompany findCompany = company.findByAlias();
			if(findCompany == null){
				
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
			newUser.setGlobalCom(tenantId);
			
			try {
				newUser.createDatabaseMe();
				newUser.flushDatabaseMe();
			} catch (Exception e) {
				e.printStackTrace();
				throw new MetaworksException(e.getMessage());
			}
			sendMailToNoUser(authKey);
			addContactEachother();
			this.setInvitedMessage("친구초대 메일을 보냈습니다.");
			this.getMetaworksContext().setHow("afterinvite");
			
			return new Object[]{new ToEvent(new ContactPerspective(), EventContext.EVENT_CHANGE), new Refresh(this)};
		}
		
		
		Contact contact = new Contact();
		IContact findContact = contact.findContactsWithFriendId(this.session.getUser(), findEmp.getEmpCode());
		
		// 2. 코디 사용자 이고, 이미 친구일 때
		if(findContact != null){
			// 3. 초대는 받았지만 아직 가입을 하지 않은 유저이고, 친구 일때
			if(!findEmp.isApproved()){
				sendMailToNoUser(authKey);
				this.setInvitedMessage("친구초대 메일을 보냈습니다.");
				this.getMetaworksContext().setHow("afterinvite");
				
				return new Object[]{new Refresh(this)};
			}
			
			this.setInvitedMessage("이미 등록된 친구입니다.");
			this.getMetaworksContext().setHow("afterinvite");
			
			return new Object[]{new Refresh(this)};
		}
	 
	// 4. 초대는 받았지만 아직 가입을 하지 않은 유저이고, 친구가 아닐때
		if(!findEmp.isApproved()){
			
			sendMailToNoUser(authKey);
			findEmp.setAuthKey(authKey);
			
			Employee saveEmp = new Employee();
			saveEmp.copyFrom(findEmp);
			saveEmp.syncToDatabaseMe();
			
			sendMailToUser(authKey);
			
			this.setInvitedMessage("친구초대 메일을 보냈습니다.");
			this.getMetaworksContext().setHow("afterinvite");
			
			return new Object[]{new Refresh(this)};
		}
		
		// 5. 코디 사용자이고, 친구가 아니고 다른회사 사람일때
		if(!session.getEmployee().getGlobalCom().equals(findEmp.getGlobalCom())){
			
			Employee saveEmp = new Employee();
			saveEmp.copyFrom(findEmp);
			saveEmp.setInviteUser(session.getEmployee().getEmpCode());
			saveEmp.setAuthKey(authKey);
			saveEmp.syncToDatabaseMe();
			addContactEachother();
			
			sendMailToUser(authKey);
			
			this.setInvitedMessage("친구가 등록 되었습니다.");
			this.getMetaworksContext().setHow("afterinvite");
			return new Object[]{new ToEvent(new ContactPerspective(), EventContext.EVENT_CHANGE), new Refresh(this)};
			
		
		}else{
		
			//6. 코디 사용자이고, 친구가 아니고 같은회사 사람일때
			Contact newContact = new Contact();
			newContact.setUserId(session.getUser().getUserId());
			
			IUser user = new User();
			user.setName(findEmp.getEmpName());
			user.setUserId(findEmp.getEmpCode());
			user.setNetwork("local");
			newContact.setFriendId(user.getUserId());
			newContact.setFriend(user);
			newContact.createDatabaseMe();
			newContact.flushDatabaseMe();

			this.setInvitedMessage("친구가 등록 되었습니다.");
			this.getMetaworksContext().setHow("afterinvite");
			return new Object[]{new ToEvent(new ContactPerspective(), EventContext.EVENT_CHANGE), new Refresh(this)};
		}
	}
	
	public void addContactEachother() throws Exception{
		Employee newUser = new Employee();
		newUser.setEmail(this.getEmail());
		newUser.copyFrom(newUser.findByEmail());
		
		Contact newContact = new Contact();
		newContact.setUserId(session.getUser().getUserId());
		
		IUser newUser_ = new User();
		newUser_.setName(newUser.getEmpName());
		newUser_.setUserId(newUser.getEmpCode());
		newUser_.setNetwork("local");
		newContact.setFriendId(newUser.getEmpCode());
		newContact.setFriend(newUser_);
		newContact.createDatabaseMe();
		newContact.flushDatabaseMe();
		
		newContact = new Contact();
		newContact.setUserId(newUser_.getUserId());
		
		IUser me_ = new User();
		me_.setName(session.getUser().getName());
		me_.setUserId(session.getUser().getUserId());
		me_.setNetwork("local");
		newContact.setFriendId(me_.getUserId());
		newContact.setFriend(me_);
		newContact.createDatabaseMe();
		newContact.flushDatabaseMe();

	}
	
	public void sendMailToUser(String authKey) throws Exception {
		
		String from = GlobalContext.getPropertyString("codi.mail.support", "support@processcodi.com");
		String beforeName = "user.name";
		String afterName = session.getEmployee().getEmpName(); //초대 하는사람
		String beforeCompany = "user.company";
		String baseLinkUrl = "base.url";
		String afterCompany =  Employee.extractTenantName(session.getEmployee().getEmail());
		
		String baseUrl = TenantContext.getURL(Employee.extractTenantName(this.getEmail()));
		String url = "";
		url += baseUrl + "/invite.html?key=" + authKey;
		
		String signUpURL = "signup.url";
		String tenantId = Employee.extractTenantName(this.getEmail());
		String beforeFaceIcon = "face.icon";
		String afterFaceIcon = session.getEmployee().getEmpCode();
		
		String content;
		String tempContent = "";
		
		String resourcePath = GlobalContext.getPropertyString("resource.path", "resource");
		String path = resourcePath + File.separatorChar+"mail"+File.separatorChar+"invitationMail.html";
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			int data;
			while((data = br.read()) != -1){
				tempContent += (char)data;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String title = afterCompany+" 의 "+ afterName + " 님이  당신을  코디에 초대 하였습니다";
		
		Login login = new Login();
		content = login.replaceString(tempContent,beforeName,afterName);
		content = login.replaceString(content, beforeCompany, afterCompany);
		content = login.replaceString(content, baseLinkUrl, baseUrl);
		content = login.replaceString(content, signUpURL, url);
		content = login.replaceString(content, beforeFaceIcon, afterFaceIcon);
		System.out.println(content);
		
		try{
			(new EMailServerSoapBindingImpl()).sendMail(from, getEmail(), title, content);
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
		
	}
	
	public void sendMailToNoUser(String authKey) throws Exception {
		
		String from = GlobalContext.getPropertyString("codi.mail.support", "support@processcodi.com");
		String beforeName = "user.name";
		String afterName = session.getEmployee().getEmpName(); //초대 하는사람 이름
		String beforeCompany = "user.company";
		String afterCompany =  Employee.extractTenantName(session.getEmployee().getEmail()); //초대 하는사람
		String baseUrl = CodiStringUtil.lastLastFileSeparatorChar(TenantContext.getURL());
		String url = "";
		url += baseUrl + "activate.html?key=" + authKey;
		String beforeFaceIcon = "face.icon";
		String afterFaceIcon = session.getEmployee().getEmpCode();
		String baseLinkUrl = "base.url";
		String signUpURL = "signup.url";
		
		String content;
		String tempContent = "";
		
		String resourcePath = GlobalContext.getPropertyString("resource.path", "resource");
		String path = resourcePath + File.separatorChar+"mail"+File.separatorChar+"invitationMail.html";
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			int data;
			while((data = br.read()) != -1){
				tempContent += (char)data;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String title = afterCompany+" 의 "+ afterName + " 님이  당신을  코디에 초대 하였습니다";
		
		
		Login login = new Login();
		content = login.replaceString(tempContent,beforeName,afterName);
		content = login.replaceString(content, beforeCompany, afterCompany);
		content = login.replaceString(content, baseLinkUrl, baseUrl);
		content = login.replaceString(content, signUpURL, url);
		content = login.replaceString(content, beforeFaceIcon, afterFaceIcon);
		System.out.println(content);
		
		try{
			(new EMailServerSoapBindingImpl()).sendMail(from, getEmail(), title, content);
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
	}

}
