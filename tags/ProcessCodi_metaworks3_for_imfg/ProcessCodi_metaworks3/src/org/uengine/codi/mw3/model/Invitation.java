package org.uengine.codi.mw3.model;

import java.net.URL;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.uengine.webservices.emailserver.impl.EMailServerSoapBindingImpl;

@Face(displayName="$InviteYourFriend", options="hideLabels", values="true")
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
	@Face(options="placeholder", values="$Email")
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}

	String name;
	@Face(options="placeholder", values="$Name")
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		
	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(callByContent=true, target="popup")
	@Face(displayName="$Invite")
	public Object invite() throws Exception{
		
		IEmployee alreadyExistChecker = (IEmployee) Database.sql(IEmployee.class, "select * from emptable where email=?email");
		alreadyExistChecker.setEmail(getEmail());
		alreadyExistChecker.select();
		
		if(alreadyExistChecker.next()){
			throw new Exception("$AlreadyExistingUser");
		}
		
		Employee newUser = new Employee();
		newUser.setEmail(getEmail());
		newUser.setEmpCode(getEmail());
		newUser.setEmpName(getName());
		newUser.setPassword("generated");
		newUser.setGlobalCom(session.getCompany().getComCode());
		newUser.setLocale(session.getEmployee().getLocale());
		newUser.createDatabaseMe();
		
		
		String requestedURL = TransactionContext.getThreadLocalInstance().getRequest().getRequestURL().toString(); 
        String base = requestedURL.substring( 0, requestedURL.lastIndexOf( "/" ) );
        
        URL urlURL = new java.net.URL(base);
       	String host = urlURL.getHost();
       	int port = urlURL.getPort();
       	String protocol = urlURL.getProtocol();

		String url = protocol + "://" + host + (port == 80 ? "" : ":"+port) + TransactionContext.getThreadLocalInstance().getRequest().getContextPath();

		try{
			url = url + "?codi.id=" + getEmail() + "&codi.password=" + newUser.getPassword();
			
			(new EMailServerSoapBindingImpl()).sendMail(
				session.getEmployee().getEmail(), 
				session.getUser().getName(),
				getEmail(), 
				"[ProcessCodi] You are invited by " + session.getUser().getName(), 
				"<p><a href='" + url + "'>Connect to Process Codi to join with your team!</a>", 
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
		
		newContact.setFriend(newUser_);
		newContact.createDatabaseMe();
		
		ContactList cp = new ContactList();
		cp.setId(ContactList.LOCAL);
		cp.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		cp.getMetaworksContext().setWhere(ContactList.LOCAL);
		
		cp.load(session.getUser().getUserId());

		return new Refresh(cp);
		
	}

}
