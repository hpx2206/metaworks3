<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.sql.*, javax.naming.*, org.uengine.util.dao.DefaultConnectionFactory"%><%
	Connection 	    	conn	= null;
	PreparedStatement  	ps 		= null;
	ResultSet 			rs 		= null;
	String 				sql 	= "";
	int 				rows 	= 0;
	int 				cnt 	= 0;
	
	String COMCODE = (String) session.getAttribute("loggedUserGlobalCom");
	String ALIAS = request.getParameter("alias");
	
	try
	{
		conn = DefaultConnectionFactory.create().getConnection();

		sql = "SELECT COUNT(*) AS CNT FROM BPM_PROCDEF WHERE COMCODE=? AND ALIAS=?";
	
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, COMCODE);
		ps.setString(2, ALIAS);
		
		rs = ps.executeQuery();
		
		if(rs.next()){
			cnt = rs.getInt("CNT");
		}
	}finally{
		if ( rs != null ) 	rs.close();
		if ( ps != null ) 	ps.close();
		if ( conn != null ) conn.close();
	}
%><%=cnt%>