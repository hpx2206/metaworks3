<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<%@ page import="org.uengine.util.dao.DefaultConnectionFactory"%>
<%@ page import="java.sql.*,javax.sql.*,javax.naming.*" %>


<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />

<%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();

	String procDefAlias = decode(request.getParameter("alias"));
	
    String defVerId="";
	if(org.uengine.util.UEngineUtil.isNotEmpty(procDefAlias)){
		defVerId = pm.getProcessDefinitionProductionVersionByAlias(procDefAlias);
	}

	Connection 	    conn	= null;
	Statement 		stmt 	= null;
	ResultSet 		rs 		= null;


	try{
			HashMap reqCdMap = new HashMap();
			String sql="";

			conn = DefaultConnectionFactory.create().getConnection();
			stmt = conn.createStatement();

//////////////////////////// 종료해야 하는 req_cd 리스트 생성 ////////////////////////////////////////
			sql = "select ..... ";

			rs = stmt.executeQuery(sql);

			while (rs.next()){
				String req_cd=rs.getString("req_cd");
				reqCdMap.put(req_cd,req_cd);
			}
			rs.close();

/////////////////////////////// req_cd와 매핑되는 인스턴스를 종료한다 ///////////////////////////////////
			sql = "select instid from bpm_procinst where defverid='"+defVerId+"' and status='Running' ";

			rs = stmt.executeQuery(sql);

			while (rs.next()){
					String instanceId =""+ rs.getInt("instid");
					ProcessInstance pi = pm.getProcessInstance(instanceId);
					String req_cd = ""+pi.get("","req_cd"); //프로세스 변수에 req_cd가 있음.

					if(org.uengine.util.UEngineUtil.isNotEmpty(req_cd) && reqCdMap.containsKey(req_cd)){
						pm.stopProcessInstance(instanceId);
						%>Process has been stopped with instance id [<%=instanceId%>]<br><%
					}
			}
			rs.close();

			pm.applyChanges();

	}catch(Exception e){
		try{
			pm.cancelChanges();
		}catch(Exception ex){
		}
		
		throw e;
	}finally{
		try{
			pm.remove();
		}catch(Exception ex){
		}

		if ( stmt != null ) try { stmt.close(); } catch (Exception e) {}
		if ( conn != null ) try { conn.close(); } catch (Exception e) {}
	}
%>
