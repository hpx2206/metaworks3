<%@page contentType="text/xml; charset=UTF-8" 
	    import="java.lang.reflect.Method,
	            java.io.*,
	            java.util.*,
	            org.uengine.kernel.*,
	            org.uengine.codi.mw3.admin.*,
	            org.uengine.codi.mw3.model.*,
	            org.uengine.processmanager.*"
                %>
<%
	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%! 
	private ArrayList readForm(String id, ProcessManagerRemote pm) throws Exception {
		String prodVerId = pm.getProcessDefinitionProductionVersion(id);
		String strFormDef = pm.getResource(prodVerId);
		
		org.uengine.codi.mw3.CodiDwrServlet.initClassLoader();

		Object definition = GlobalContext.deserialize(strFormDef, String.class);
		
		if(definition instanceof PropertyListable){
			return ((PropertyListable)definition).listProperties();	
		}
		
		return null;
    }
%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	//load up the formfield
	String formDefId = request.getParameter("formDefId");
	//System.out.println("formDefId =["+formDefId+"]");
	
	ProcessManagerRemote pm = null;
	try{
		pm = processManagerFactory.getProcessManagerForReadOnly();
		ArrayList fieldList = readForm(formDefId, pm);
		
		out.println(GlobalContext.serialize(fieldList,ArrayList.class));
		
	}finally{
		if(pm != null) try{ pm.remove(); } catch(Exception e){}
	}
%>
