<%@ page contentType="text/html;charset=utf-8" import="java.sql.*" %>

<%
	String access_token = request.getParameter("access_token");
	String user_id = request.getParameter("user_id");
	
	System.out.println("*************************************************");
	
	try{
		
		Class.forName("cubrid.jdbc.driver.CUBRIDDriver");
		conn = DriverManager.getConnection(
				"jdbc:cubrid:192.168.212.108:33000:uengine:::?charset=utf-8", "dba", "");
			
		  
		Statement stmt = con.createStatement();

		  
		String query = "select * from oauth_token where access_token='" + token + "' and user_id='" + user_id + "'";
		  
		ResultSet rs = stmt.executeQuery(query);
		
		if(rs.next()) {
			id = rs.getString(1);  
		}		  
		  
		System.out.println("*** id = " + id);
		  
		stmt.close();
		con.close();
	}
	catch(Exception e){}
%>
out.println(id);