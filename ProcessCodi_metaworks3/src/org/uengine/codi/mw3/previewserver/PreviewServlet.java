package org.uengine.codi.mw3.previewserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.extend.Replies;
import org.directwebremoting.extend.Reply;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.JDBCConnectionFactory;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.model.FileWorkItem;
import org.uengine.codi.mw3.model.WorkItem;
import org.uengine.kernel.GlobalContext;
import org.uengine.util.UEngineUtil;

/**
 * Servlet implementation class PreviewServlet
 */
public class PreviewServlet extends HttpServlet {
	
	//@Autowired
	//public SpringConnectionFactory connectionFactory;
	
	public static boolean useSpring = false;
	
	public static ConnectionFactory connectionFactory;

	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TransactionContext tx = new TransactionContext();
		tx.setManagedTransaction(false);
		tx.setAutoCloseConnection(true);
		
		if(connectionFactory!=null)
			tx.setConnectionFactory(connectionFactory);
		
		try{
			String pathInfo = request.getPathInfo();
			if(pathInfo == null)
				return;

			// because pathInfo is started with a "/" character
			String previewName = pathInfo.substring(1);		
			
			String fileSystemPath = GlobalContext.getPropertyString("filesystem.path",".");
			String previewPath = fileSystemPath + "/preview";
			
			// create preview folder
			File previewDirectory = new File(previewPath);		
			if(!previewDirectory.exists() || !previewDirectory.isDirectory())
				previewDirectory.mkdirs();
			
			String srcName = previewPath + pathInfo;
	
			File srcFile = new File(srcName);
		
			if(srcFile.exists() && srcFile.isFile() && srcFile.length() > 0){
				FileInputStream is = null;
				ServletOutputStream os = null;

				try{
					String mimeType = getServletContext().getMimeType(srcName);

					is = new FileInputStream(srcFile);
					response.setContentType(mimeType);

					os = response.getOutputStream();
					UEngineUtil.copyStream(is, os);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					os.close();
					is.close();
				}
	
			}else{
				int pos = previewName.lastIndexOf(".");
				String StringTaskId = previewName.substring(0, pos);
				Long taskId = Long.parseLong(StringTaskId);
				
				try{
					WorkItem workItem =new WorkItem();
					workItem.setTaskId(taskId);
					
					String realContent = workItem.databaseMe().getContent();
					String realFilePath = fileSystemPath + "/" + realContent;

					boolean success = false;
					FileWorkItem fileWorkItem = new FileWorkItem();
					String realFileMimeType = getServletContext().getMimeType(realFilePath);
					success = fileWorkItem.createPreviewFile(taskId, realContent, realFileMimeType);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
	        try{
	        	tx.commit();
	        }catch(Exception exAtCommit){
	        	
	        	//TODO: [before production] replacing the reply (by reply.setThrowable(exAtCommit)) to shout out the right error messages to the client.
	        	Replies replies = (Replies) tx.getSharedContext("replies");
//	        	replies.getReply(0).
	        	replies.addReply(new Reply(replies.getCalls().getCall(0).getCallId(), null, exAtCommit));
	        	
	        	throw exAtCommit;
	        }	
		
		} catch(Throwable e){
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				tx.releaseResources();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
