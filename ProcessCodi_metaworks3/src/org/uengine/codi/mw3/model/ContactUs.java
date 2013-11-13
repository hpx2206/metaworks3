package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksException;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.webservices.emailserver.impl.EMailServerSoapBindingImpl;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
options={"fieldOrder"}, values={"title,contents"})
public class ContactUs {
	@AutowiredFromClient
	public Session session;
	
	String title;
		@Face(displayName="$ContactUs.title")
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
	
	WebEditor contents;
		@Face(displayName="$ContactUs.content")
		public WebEditor getContents() {
			return contents;
		}
		
		public void setContents(WebEditor contents) {
			this.contents = contents;
		}
		
	
	public ContactUs(){
		setContents(new WebEditor());
	}
	


	@ServiceMethod(callByContent=true)
	@Face(displayName="$sendContactUs")
	public Object sendFeedback() throws Exception{
		String contents = this.getContents().getContents();
		
		
		EMailServerSoapBindingImpl emailServerSoapBindingImpl = new EMailServerSoapBindingImpl();
		Company company = new Company();
		company.setComCode(session.getEmployee().getGlobalCom());
		ICompany iCompany;
		try {
			iCompany = company.findByCode();
			String mailto = iCompany.getRepMail();
			emailServerSoapBindingImpl.sendMail(session.getEmployee().getEmail(), //송신자
					session.getUser().getUserId(),
					mailto, //수신자
					this.getTitle(), 
					contents,
					null,
					null,
					"UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MetaworksException("$FailedToSendInvitationMail");
		}
	
		
		return new Remover(new ModalWindow());
		
	}
}
