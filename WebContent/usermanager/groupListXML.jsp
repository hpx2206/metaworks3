<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/xml; charset=UTF-8" language="java" import="org.uengine.util.XMLUtil,java.sql.*,javax.sql.*,org.uengine.util.dao.DefaultConnectionFactory"%><%
response.setContentType("text/xml; charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%><%@include file="../common/headerMethods.jsp"%><roles><%
Connection 	    conn	= null;
Statement 		stmt 	= null;
ResultSet 		rs 		= null;
StringBuffer 	sql 	= new StringBuffer();
int 			rows 	= 0;

try
{
	conn = DefaultConnectionFactory.create().getConnection();
	stmt = conn.createStatement();

	sql	.append(" SELECT partcode, partname FROM parttable WHERE ISDELETED = '0'");
	
	rs = stmt.executeQuery(sql.toString());

	while (rs.next())
	{
		%><role value="<%= rs.getObject("partcode") %>" name="<%=XMLUtil.encodeXMLReservedWords(rs.getString("partname")) %>"/><%
	}
	rs.close();

}
finally
{
	if ( stmt != null ) try { stmt.close(); } catch (Exception e) {}
	if ( conn != null ) try { conn.close(); } catch (Exception e) {}
}
%></roles>