<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>


<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.uengine.util.dao.DefaultConnectionFactory"%><LINK href="../style/uengine.css" type=text/css rel=stylesheet>

<jsp:useBean id="processManagerFactory" scope="application"
	class="org.uengine.processmanager.ProcessManagerFactoryBean" />

<%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	InitialContext context = new InitialContext();
	
	String fn = request.getParameter("folderName");
	String parent = request.getParameter("parentFolder");
	//System.out.println("fn :"+fn);
	//System.out.println("parent :"+parent);
	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);

	try {
		if (tx != null) tx.begin();
	
		String defID = pm.addFolder(fn, parent);
		pm.applyChanges();
		
		if (tx != null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.commit();
		
		
		AclManager acl = AclManager.getInstance();
		acl.setPermission(Integer.parseInt(defID), acl.ACL_FIELD_EMP, loggedUserId, new String[]{AclManager.PERMISSION_MANAGEMENT + ""}); 
		acl.setPermission(Integer.parseInt(defID), acl.ACL_FIELD_COM, loggedUserGlobalCom, new String[]{AclManager.PERMISSION_VIEW + ""});
		
	
		String sql = "UPDATE bpm_procdef SET comcode = ? WHERE defid = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = DefaultConnectionFactory.create().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loggedUserGlobalCom);
			pstmt.setLong(2, new Long(defID));
			
			pstmt.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (pstmt != null) try { pstmt.close(); } catch (Exception e) { }
			if (conn != null) try { conn.close(); } catch (Exception e) { }
		}
	
%>
Folder '<%=fn%>' has been successfully created.
<p>
<script type="text/javascript">
window.top.location.href = "index.jsp";
</script> 
<%
	} catch(Exception e) {
		e.printStackTrace();
	
		if (tx != null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.rollback();
		throw e;
	}

%>
