<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="org.uengine.util.dao.DefaultConnectionFactory" %>
<%@ page import="java.sql.*" %>

<%
	response.addHeader("Access-Control-Allow-Origin","*");
	response.addHeader("Access-Control-Allow-Credentials", "true");
	
	String access_token = request.getParameter("access_token");
	String user_id = request.getParameter("user_id");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String query = "";
	String ret_id = "";
	
	if (access_token != null && access_token.length() > 0 && user_id != null && user_id.length() > 0) {
		
		try{			
			conn = DefaultConnectionFactory.create().getConnection();
			  
			query = "select * from oauth_token where access_token='" + access_token + "' and user_id='" + user_id + "'";
			  
			pstmt = conn.prepareStatement(query);			
			rs = pstmt.executeQuery(query);
			
			if(rs.next()) {
				ret_id = rs.getString(1);  
			} 

			if(rs != null)
				rs.close();	
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
				if(pstmt != null){
					pstmt.close();
					pstmt = null;
				}
			} catch (SQLException sqle){						
			}	
			
		}
		
	}
	
	if(ret_id != null && ret_id.length() > 0) {
		out.println(ret_id);
	}
%>