<%@page import="org.uengine.kernel.GlobalContext"%>
<%@page import="org.metaworks.dao.TransactionContext"%>
<%@ page contentType="text/html;charset=utf-8" import="java.sql.*" %>

<%
	String access_token = request.getParameter("access_token");
	String user_id = request.getParameter("user_id");
	
	try{			
		  
		Connection conn = TransactionContext.getThreadLocalInstance().getConnection();
		  
		String query = "select * from oauth_token where access_token='" + access_token + "' and user_id='" + user_id + "'";
		  
		PreparedStatement pstmt = conn.prepareStatement(query);			
		ResultSet rs = pstmt.executeQuery(query);
		
		if(rs.next()) {
			id = rs.getString(1);  
		} 

	}
	catch(Exception e){		
		e.printStackTrace();
		
		String message = e.getMessage();			
		throw new Exception(message);
	}
	finally{
		
		try{
			if(conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException sqle){			
		}
		
		try {
			if(stmt != null){
				stmt.close();
				stmt = null;
			}
		} catch (SQLException sqle){						
		}
		
		try {
			if(pstmt != null){
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException sqle){						
		}	
		
	}
%>
out.println(id);