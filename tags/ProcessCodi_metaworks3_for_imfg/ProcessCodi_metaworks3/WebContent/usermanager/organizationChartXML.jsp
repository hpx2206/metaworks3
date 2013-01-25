<?xml version="1.0" encoding="UTF-8"?>
<%
response.setContentType("text/xml; charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%><%@page 
	pageEncoding="KSC5601" 
	import="javax.naming.Context,
	        javax.naming.InitialContext,
	        javax.naming.NamingException,
	        javax.rmi.PortableRemoteObject,
	        org.uengine.kernel.*,
	        org.uengine.util.dao.DefaultConnectionFactory,
	        java.util.*,
	        java.sql.*,
	        javax.sql.*,
	        java.io.*,
	        org.uengine.processmanager.*"
%>

<resources>
<%	
Connection conn;

conn = DefaultConnectionFactory.create().getConnection();
Statement stmt = conn.createStatement();


try
{
	
	
	StringBuffer sql = new StringBuffer();
	sql.append(" select E.EMPCODE,                                                           ");
	sql.append("        E.EMPNAME,                                                           ");
	sql.append(" 	    E.PARTCODE,                                                          ");
	sql.append(" 	    E.EMAIL,  ");
	sql.append(" 	    E.GLobalCom,                                                         ");
	sql.append(" 	    P.PARTNAME                                                           ");
	sql.append(" from PArtTable P,                                                           ");
	sql.append("      EMPTABLE E                                                            ");
	sql.append(" where  P.PARTCODE  = E.PARTCODE                                              ");
	sql.append(" and   P.Comcode = E.GLobalCom                                             ");
	sql.append(" and   P.ISDELETED = '0'                                             ");
	sql.append(" and   E.ISDELETED = '0'                                             ");
	sql.append(" order by P.PARTCODE,E.EMPNAME                                               ");
	
	ResultSet rs = stmt.executeQuery(sql.toString());
	
	String curDivCode = "";
	String curPartCode = "";
	
	while (rs.next())
	{
		%><resource value="<%=rs.getString("EMPCODE")%>" name="<%= rs.getString("EMPNAME") %>"/><%
	}
	
}
finally
{
	if ( stmt != null ) try { stmt.close(); } catch (Exception e) {}
	if ( conn != null ) try { conn.close(); } catch (Exception e) {}
}
		

	
%></resources>