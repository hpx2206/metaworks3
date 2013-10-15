package org.uengine.codi.mw3.model;

import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Properties;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Face(options={"hideLabel","placeholder"}, values={"true", "$Email"})
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}

	String name;
	@Face(options={"hideLabel","placeholder"}, values={"true", "$Name"})
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		
	boolean guest;
		@Face(displayName="Guest 여부")
		public boolean isGuest() {
			return guest;
		}
		public void setGuest(boolean guest) {
			this.guest = guest;
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
	
	
	@ServiceMethod(callByContent=true, target="popup")
	@Face(displayName="$Invite")
	public Object invite() throws Exception{
		
		IEmployee alreadyExistChecker = (IEmployee) Database.sql(IEmployee.class, "select * from emptable where empCode=?email");
		alreadyExistChecker.setEmail(getEmail());
		alreadyExistChecker.select();
		
		if(alreadyExistChecker.next()){
			throw new Exception("$AlreadyExistingUser");
		}
				
		String defaultUX = "wave";
		String defaultMob = "auto";
		
		Employee newUser = new Employee();
		newUser.setEmail(getEmail());
		newUser.setEmpCode(getEmail());
		newUser.setEmpName(getName());
		newUser.setPreferUX(defaultUX);
		newUser.setPreferMob(defaultMob);
		newUser.setPassword(randomPassword());
		newUser.setIsDeleted("0");
		newUser.setGlobalCom(session.getCompany().getComCode());
		newUser.setLocale(session.getEmployee().getLocale());
		newUser.setApproved(true);
		newUser.setGuest(this.isGuest());
		newUser.createDatabaseMe();
		
		locale = new Locale(); 
		Properties prop = locale.webMessageBundles.get(session.getEmployee().getLocale()); 
		String EmailInviteMsgTitle = prop.getProperty("EmailInviteMsgTitle"); 
		String EmailInviteMsg = prop.getProperty("EmailInviteMsg");
		String EmailTempPw = prop.getProperty("EmailTempPw");
		String EmailChangePw = prop.getProperty("EmailChangePw");
		
		
		String requestedURL = TransactionContext.getThreadLocalInstance().getRequest().getRequestURL().toString(); 
        String base = requestedURL.substring( 0, requestedURL.lastIndexOf( "/" ) );
        
        URL urlURL = new java.net.URL(base);
       	String host = urlURL.getHost();
       	int port = urlURL.getPort();
       	String protocol = urlURL.getProtocol();

		String url = protocol + "://" + host + (port == 80 ? "" : ":"+port) + TransactionContext.getThreadLocalInstance().getRequest().getContextPath();

		try{
		    url = url + "?codi.id=" + getEmail() + "&codi.password=" + newUser.getPassword();
			
			(new EMailServerSoapBindingImpl()).sendMail(session.getEmployee().getEmail(), 
						session.getUser().getName(),
						getEmail(), 
						EmailInviteMsgTitle + session.getUser().getName(), 
						"<p><a href='" + url + "'>"+EmailInviteMsg+"</a><br/>"
						+EmailTempPw + newUser.getPassword()+"<br/>"
						+EmailChangePw, 
						null, 
						null,
						"UTF-8"
			);
		}catch(Exception e){
			throw new Exception("$FailedToSendInvitationMail");
		}
		
		
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
		
	}

}
