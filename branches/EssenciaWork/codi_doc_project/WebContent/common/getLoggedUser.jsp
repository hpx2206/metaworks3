<%@page import="org.uengine.kernel.GlobalContext"%>
<%@page import="com.defaultcompany.login.LoginService"%>

<%@page import="org.uengine.codi.common.session.CodiHttpSession"%>
<%@page import="org.springframework.web.context.*,org.springframework.web.context.support.*"%>
<%@page import="org.uengine.util.UEngineUtil" %>

<%
	// mandatory
    ServletContext ctx = pageContext.getServletContext();
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ctx);

    CodiHttpSession codiSession = (CodiHttpSession) wac.getBean("codiHttpSession");
    String appKey = request.getParameter("appKey");
    if(!codiSession.isRegistedAppKey() && UEngineUtil.isNotEmpty(appKey) && !"undefined".equals(appKey)){
		codiSession.setAppKey(appKey);
		appKey = codiSession.getAppKey();
    }
    //System.out.println("appKey : " + appKey);
%>
<%
boolean isRegUser = Boolean.parseBoolean(request.getParameter("regUser"));
boolean logged = (codiSession.getAttribute("loggedUserId")!=null)?true:false;   
boolean isCookieLoggin = false;

//if(!logged && Boolean.parseBoolean(GlobalContext.getPropertyString("web.use.cookie","false"))){
if(!logged && Boolean.parseBoolean(GlobalContext.getPropertyString("web.cookie.use","false"))){
	LoginService loginService = new LoginService();
	String authenticationCondition = loginService.loginForCookie(request, codiSession);
	if("Success".equals(authenticationCondition)){
		isCookieLoggin=true;
	}
}

//System.out.println("-----------------------------------------------------");
//System.out.println("getLoggedUser.jsp - logged : "+logged);
//System.out.println("getLoggedUser.jsp - isCookieLoggin : "+isCookieLoggin);
//System.out.println("-----------------------------------------------------");

if (isRegUser) {
	codiSession.setAttribute("loggedUserId", "guest");
} else if (logged == false && isCookieLoggin == false ) {
%>
<html><meta http-equiv="refresh" content="0; url=<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>/loginForm.jsp"></meta></html>
<script type="text/javascript">
	window.top.location.href = "<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>/loginForm.jsp";
</script>
<%
	return;
}
	String  loggedUserId       		= (String) codiSession.getAttribute("loggedUserId");
	String  loggedUserFullName 		= (String) codiSession.getAttribute("loggedUserFullName");
	String  loggedUserJikName  		= (String) codiSession.getAttribute("loggedUserJikName");
	String  loggedUserEmail    		= (String) codiSession.getAttribute("loggedUserEmail");
	String  loggedUserPartCode 		= (String) codiSession.getAttribute("loggedUserPartCode");
	String  loggedUserPartName 		= (String) codiSession.getAttribute("loggedUserPartName");
	String  loggedUserMsnId 		= (String) codiSession.getAttribute("loggedUserMsnId");
	String  loggedUserNateonId 		= (String) codiSession.getAttribute("loggedUserNateonId");
	String  loggedUserLocale 		= (String) codiSession.getAttribute("loggedUserLocale");
	String  loggedUserGlobalCom		= (String) codiSession.getAttribute("loggedUserGlobalCom");
	String  loggedUserComName 		= (String) codiSession.getAttribute("loggedUserComName");
	boolean loggedUserIsAdmin  		= "1".equals((String) codiSession.getAttribute("loggedUserIsAdmin"));
	boolean loggedUserIsMaster 		= false;
	if(UEngineUtil.isNotEmpty(codiSession.getAttribute("loggedUserIsMaster"))) {
	    if(codiSession.getAttribute("loggedUserIsMaster").equals(Boolean.toString(true))){
			loggedUserIsMaster = true;
	    }
	}
	
	String loggedUserTimeZone		= null;

	org.uengine.kernel.RoleMapping loggedRoleMapping = null;
	String rmClsName = "";
	
	loggedRoleMapping = org.uengine.kernel.RoleMapping.create();
	loggedRoleMapping.setEndpoint(loggedUserId);
	loggedRoleMapping.setResourceName(loggedUserFullName);
	loggedRoleMapping.setEmailAddress(loggedUserEmail);
	loggedRoleMapping.setMale(true);
	loggedRoleMapping.setBirthday(new java.util.Date());
	loggedRoleMapping.setGroupId(loggedUserPartCode);
	loggedRoleMapping.setCompanyId(loggedUserGlobalCom);
	loggedRoleMapping.setLocale(loggedUserLocale);
	loggedRoleMapping.setTitle(loggedUserJikName);
	
	rmClsName = org.uengine.kernel.RoleMapping.USE_CLASS.getName();
%>