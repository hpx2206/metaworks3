<%@page contentType="text/xml; charset=UTF-8" import="org.drools.compiler.PackageBuilderConfiguration,java.lang.reflect.Method,java.io.*,java.util.*,org.uengine.kernel.*,org.uengine.processmanager.*,org.drools.RuleBase,org.drools.RuleBaseFactory,org.drools.WorkingMemory,org.drools.compiler.PackageBuilder,org.drools.decisiontable.model.Import,org.drools.rule.Package"%>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
response.setContentType("text/xml; charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0); //prevents caching at the proxy server

ProcessManagerRemote pm = null;
	
try{
	pm = processManagerFactory.getProcessManager();

	//load up the rulebase
	String ruleDefId = request.getParameter("ruleDefId");

	List parameterList = org.uengine.kernel.DRoolsActivity.getParameterList(pm, ruleDefId);

	out.println(GlobalContext.serialize(parameterList, List.class));
}finally{
	try{
		pm.remove();
	}catch(Exception ex){}
}
%>
