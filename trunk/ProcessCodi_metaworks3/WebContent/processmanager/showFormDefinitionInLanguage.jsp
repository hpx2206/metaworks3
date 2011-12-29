<%@page contentType="text/xml; charset=UTF-8" 
	    import="java.lang.reflect.Method,
	            java.io.*,
	            java.util.*,
	            org.uengine.kernel.*,
	            org.uengine.codi.mw3.admin.*,
	            org.uengine.processmanager.*"
                %>
<%
	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%! 
	private FormDefinition readForm(String id, ProcessManagerRemote pm) throws Exception {
		String prodVerId = pm.getProcessDefinitionProductionVersion(id);
		String strFormDef = pm.getResource(prodVerId);

		FormDefinition formDefinition = (FormDefinition)GlobalContext.deserialize(strFormDef, FormDefinition.class);
		return formDefinition;
    }
%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	//load up the formfield
	String formDefId = request.getParameter("formDefId");
	//System.out.println("formDefId =["+formDefId+"]");
	
	ProcessManagerRemote pm = null;
	try{
		pm = processManagerFactory.getProcessManagerForReadOnly();
		FormDefinition formDefinition = readForm(formDefId, pm);
		ArrayList<FormField> formFields = formDefinition.getFormFields();
	
		//System.out.println("The document contains "+formFields.size()+" form fields:\n");
		
		ArrayList array = new ArrayList();
		for (Iterator i=formFields.iterator(); i.hasNext();) {
			FormField formField=(FormField)i.next();
			if(formField.getFieldName()!=null)
				array.add(formField.getFieldName());
		}
		
		out.println(GlobalContext.serialize(array,ArrayList.class));
		
	}finally{
		if(pm != null) try{ pm.remove(); } catch(Exception e){}
	}
%>
