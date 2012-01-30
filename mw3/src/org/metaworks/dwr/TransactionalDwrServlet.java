package org.metaworks.dwr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

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


	public static final String PATH_METAWORKS_FACES = "metaworks/faces";

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
		
		//check if the request is simply requesting resource and handles it.
		
		String pathInfo = request.getPathInfo();
		String requestContextPath = request.getContextPath();

		int wherePrefixStarts = pathInfo.indexOf(PATH_METAWORKS_FACES);
        if (wherePrefixStarts != -1)
        {
        	String mimeType = (pathInfo.endsWith(".js") ? "text/javascript":"text/plain");
        	
        	
        	response.setContentType(mimeType + "; charset=UTF-8");
        	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
        	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        	response.setDateHeader("Expires", 0); //prevents caching at the proxy server

//            response.setContentType("text/text");
            //response.setDateHeader(HttpConstants.HEADER_LAST_MODIFIED, lastModified);
            //response.setHeader(HttpConstants.HEADER_ETAG, "\"" + lastModified + '\"');
        	
        	pathInfo = pathInfo.substring(wherePrefixStarts + PATH_METAWORKS_FACES.length() + 1);
        	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathInfo);
        	
	        if(is!=null){
	            try {
					copyStream(is, response.getOutputStream());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					is.close();
					response.flushBuffer();
				}
	            
	            return;
	        }

        }

        
		//end check if
		
		
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
	
	
	static public void copyStream(InputStream sourceInputStream, OutputStream targetOutputStream) throws Exception{
		int length = 1024;
		byte[] bytes = new byte[length]; 
		int c; 
		int total_bytes=0;
			
		while ((c = sourceInputStream.read(bytes)) != -1) { 
				total_bytes +=c; 
				targetOutputStream.write(bytes,0,c); 
		} 
		
		if (sourceInputStream != null) try { sourceInputStream.close(); } catch (Exception e) {}
		if (targetOutputStream != null) try { targetOutputStream.close(); } catch (Exception e) {}
	}
	

}
