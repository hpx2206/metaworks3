package org.metaworks.dwr;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.extend.Replies;
import org.directwebremoting.extend.Reply;
import org.directwebremoting.servlet.DwrServlet;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.JDBCConnectionFactory;
import org.metaworks.dao.TransactionContext;

public class TransactionalDwrServlet extends DwrServlet{


	public static boolean useSpring = false;
	
	public static ConnectionFactory connectionFactory;
	
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(servletConfig);
		
		if("true".equals(servletConfig.getInitParameter("useSpring"))){
			useSpring = true;
		}

		String connectionString = getServletConfig().getInitParameter("connectionString");
		
		if(connectionString!=null){
			String driverClass = getServletConfig().getInitParameter("driverClass");
			String userId = getServletConfig().getInitParameter("userId");
			String password = getServletConfig().getInitParameter("password");
			
			JDBCConnectionFactory cf = new JDBCConnectionFactory();
			cf.setConnectionString(connectionString);
			cf.setDriverClass(driverClass);
			cf.setUserId(userId);
			cf.setPassword(password);
	
			connectionFactory = cf;
		}

	}




	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		
		
		//TODO: It's debugging option 
		//MetaworksRemoteService.metadataStorage = new Hashtable<String, WebObjectType>();
		
		TransactionContext tx = TransactionContext.getThreadLocalInstance();
		tx.setManagedTransaction(false);
		tx.setAutoCloseConnection(true);
		
		tx.setRequest(request);
		tx.setResponse(response);
		
//		String defaultDS = getServletConfig().getInitParameter("defaultDS");
//		
//		DataSourceConnectionFactory dscf = new DataSourceConnectionFactory();
//		dscf.setDataSourceJndiName(defaultDS);

		if(connectionFactory!=null)
			tx.setConnectionFactory(connectionFactory);
		

		try{
			super.doPost(request, response);
			
			Replies replies = (Replies) tx.getSharedContext("replies");
			
			if(replies!=null)
			for(Reply reply : replies){
				if(reply.getThrowable()!=null){
					throw reply.getThrowable();
				}
			}
			
			tx.commit();
		}catch(Throwable e){
			try {
				tx.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
