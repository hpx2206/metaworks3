package org.uengine.codi.cron;

//import com.sun.mail.pop3.POP3SSLStore;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
//import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
//import javax.mail.URLName;
import javax.mail.internet.ContentType;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.ParseException;
import javax.mail.search.FlagTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.Invitation;
import org.uengine.codi.mw3.model.MemoWorkItem;
import org.uengine.codi.mw3.model.UnstructuredProcessInstanceStarter;
import org.uengine.codi.mw3.model.User;

public class EMailReader {
    
    public EMailReader() {
        
    }
  
    
    public static void dumpPart(Part p, StringBuffer sb, Properties mailInfo) throws Exception {
        if (p instanceof Message)
            dumpEnvelope((Message)p, sb, mailInfo);
       
//        String ct = p.getContentType();
//        try {
//        	sb.append("CONTENT-TYPE: " + (new ContentType(ct)).toString());
//        } catch (ParseException pex) {
//        	sb.append("BAD CONTENT-TYPE: " + ct);
//        }
        
        /*
         * Using isMimeType to determine the content type avoids
         * fetching the actual content data until we need it.
         */
        if (p.isMimeType("text/plain")) {
//        	sb.append("This is plain text");
//        	sb.append("---------------------------");
        	sb.append((String)p.getContent());        
        }  else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                dumpPart(mp.getBodyPart(i), sb, mailInfo);
            }
        }
    }
    
    public static void dumpEnvelope(Message m, StringBuffer sb, Properties mailInfo) throws Exception {        
        
        // SUBJECT
        sb.append("SUBJECT: " + m.getSubject() +"<br>");
        
        // DATE
        Date d = m.getSentDate();
        sb.append("SendDate: " +
                (d != null ? d.toString() : "UNKNOWN")+"<p>");
        

    }

    
    @Autowired
    MemoWorkItem memoWorkItem;

    public void read() throws Exception{
        
    		Properties props = System.getProperties();
    		props.setProperty("mail.store.protocol", "imaps");
    				
				ICompany company = new Company().auto();
				company.setIsDeleted("0");
				company.select();
				
				while(company.next()){
				
					if(company.getRepMlHst()==null || company.getRepMail()==null || company.getRepMlPwd()==null)
						continue;
					
	    			try {
	    				
    					org.uengine.codi.mw3.model.Session codiSession = new org.uengine.codi.mw3.model.Session();
    					codiSession.setCompany(company);
	    				
	    				try{
	    					Invitation invitation = new Invitation();
	    					invitation.setEmail(company.getRepMail());
	    					invitation.session = codiSession;
	    					invitation.invite();
	    				}catch(Exception e){}

	    				Employee representiveMailEmp = new Employee();
	    				representiveMailEmp.setEmpCode(company.getRepMail());
	    				IEmployee repMailEmp = representiveMailEmp.databaseMe();
	    				
	    				Session session = Session.getDefaultInstance(props, null);
	    				Store store = session.getStore("imaps");
	    				store.connect(company.getRepMlHst(), company.getRepMail(), company.getRepMlPwd());
	    				//"imap.gmail.com", "jinyoungj3@gmail.com", "29036jyjang");
	    				System.out.println(store);
	
	    				Folder inbox = store.getFolder("Inbox");
	    				inbox.open(Folder.READ_WRITE);
	    				
	    		    	Flags seen = new Flags(Flags.Flag.SEEN);
	    		        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
	    		        
	    		        Message[] messages = inbox.search(unseenFlagTerm);
	    		        
	    		        
	    		        FetchProfile fp = new FetchProfile();
	    		        fp.add(FetchProfile.Item.ENVELOPE);        
	    		        inbox.fetch(messages, fp);
	    		  
	    		        StringBuffer sb = new StringBuffer();
	
	    				//Message messages[] = inbox.search()
	    				for(Message message:messages) {
	    					//System.out.println(message);
	    					Properties mailInfo = new Properties();
	    					
	    					
	    			        Address[] from;
	    			        // FROM
	    			        from = message.getFrom();
	    			        
	    			        
	    			        Address[] to;// TO
	    			        to = message.getRecipients(Message.RecipientType.TO);
	    			        
	
	    					
	    					dumpPart(message, sb, mailInfo);
	    					
	    					ArrayList<User> fromUsers = new ArrayList<User>();
//	    					ArrayList<User> fromUsers = new ArrayList<User>();
	    					for(Address theFrom : from){
	        					IEmployee fromUserIsEmployee = new Employee().auto();
	        					String theFromString = theFrom.toString();
	        					String theFromName = theFromString;
	        					
	        					int whereMailAddress = theFromString.indexOf("<");
								if(whereMailAddress>-1){
	            					theFromName = theFromString.substring(0, whereMailAddress);
	            					theFromString = theFromString.substring(whereMailAddress+1, theFromString.length()-1);
	        					}
	        					
	        					fromUserIsEmployee.setEmail(theFromString);
	        					fromUserIsEmployee.select();
	        					
	        					if(fromUserIsEmployee.next()){
	        						User fromUser = new User();
	        						fromUser.setName(fromUserIsEmployee.getEmpName());
	        						fromUser.setUserId(fromUserIsEmployee.getEmpCode());
	        						fromUser.setNetwork("local");
	        						fromUsers.add(fromUser);
	        					}else{
	        						//TODO tries facebook, twitter, google+ by email searching
	        						User fromUser = new User();
	        						fromUser.setName(theFromName);
	        						fromUser.setUserId(theFromString);
	        						fromUser.setNetwork("ext");
	        						fromUsers.add(fromUser);					
	        					}
	    					}
	    					
	    					memoWorkItem.setMemo(new WebEditor());
	    					memoWorkItem.getMemo().setContents(sb.toString());
	    					memoWorkItem.setInstantiation(true);
	    					
	    					User theFirstWriter;
	    					if(fromUsers.size() > 0){
	    						theFirstWriter = fromUsers.get(0);
	    					}else{
	    						theFirstWriter = new User();
	    						theFirstWriter.setName("unknown");
	    						theFirstWriter.setUserId("unknown");
	    						theFirstWriter.setNetwork("ext");
	    					}
	    					
	    					memoWorkItem.setWriter(theFirstWriter);
	    					memoWorkItem.setTitle(message.getSubject());
	    					memoWorkItem.setStartDate(message.getReceivedDate());
	    					
	    					User receiver = new User();
	    					receiver.setUserId(repMailEmp.getEmpCode());
	    					receiver.setName(repMailEmp.getEmpName());
	    					receiver.setNetwork("local");
	    					codiSession.setUser(receiver);
	    					codiSession.setEmployee(repMailEmp);
	    					codiSession.setCompany(company);
	    					
	    					memoWorkItem.session = codiSession;
	    					memoWorkItem.instanceViewContent.instanceView.session = codiSession;
	    					
	    					memoWorkItem.add();
	    					
	    					message.setFlag(Flags.Flag.SEEN, true);
	    					//message.saveChanges();
	
	    				}
	    				
	    			} catch (NoSuchProviderException e) {
	        			
	        			throw new Exception("No Provider", e);
	        		} catch (MessagingException e) {
	        			throw new Exception("Messaging Error", e);
	        		} catch (Exception e) {
	        			throw new Exception("Error while getting email", e);
	    			}
    			}
    		

    	
    }
    
}

