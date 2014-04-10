package org.uengine.webservice;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.directwebremoting.extend.Replies;
import org.directwebremoting.extend.Reply;
import org.metaworks.MetaworksException;
import org.metaworks.dao.TransactionContext;
import org.metaworks.spring.SpringConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.uengine.codi.mw3.CodiClassLoader;

public class WebServiceServletContainer extends org.glassfish.jersey.servlet.ServletContainer{
	
	@Autowired
	public SpringConnectionFactory connectionFactory;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	@Override
	public void service(javax.servlet.http.HttpServletRequest request,  javax.servlet.http.HttpServletResponse response)     
			throws javax.servlet.ServletException, IOException{
		
		TransactionContext tx = new TransactionContext(); //once a TransactionContext is created, it would be cached by ThreadLocal.set, so, we need to remove this after the request processing. 
        
		tx.setManagedTransaction(false);
		tx.setAutoCloseConnection(true);
		
		tx.setRequest(request);
		tx.setResponse(response);

		if(connectionFactory!=null)
			tx.setConnectionFactory(connectionFactory);
		
		CodiClassLoader clForSession = CodiClassLoader.createClassLoader(null, "uengine", false);
		Thread.currentThread().setContextClassLoader(clForSession);
		try{
			super.service(request, response);
			try{
		        
	        	tx.commit();
	        }catch(Exception exAtCommit){
	        	
	        	//TODO: [before production] replacing the reply (by reply.setThrowable(exAtCommit)) to shout out the right error messages to the client.
	        	Replies replies = (Replies) tx.getSharedContext("replies");
	//        	replies.getReply(0).
	        	replies.addReply(new Reply(replies.getCalls().getCall(0).getCallId(), null, exAtCommit));
	        	
	        	throw exAtCommit;
	        }			
		}catch(Throwable e){
			try {
				tx.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(!(e instanceof MetaworksException))				
				e.printStackTrace();
			
			//in DWR, we never stops the application
			//throw new ServletException(e);
			
		}finally{
			try {
				tx.releaseResources();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
            
}
