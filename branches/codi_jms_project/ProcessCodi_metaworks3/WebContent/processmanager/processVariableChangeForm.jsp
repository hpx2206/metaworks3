<%@include file="../common/header.jsp"%>

<form action=processVariableChange.jsp method=POST>

<input type=hidden value='<%=decode(request.getParameter("instanceId"))%>' name=instanceId>
<input type=hidden value='<%=decode(request.getParameter("variableName"))%>' name=variableName>
<input type=hidden value='<%=decode(request.getParameter("dataClassName"))%>' name=dataClassName>

<table><tr><td bgcolor=black>
<table cellspacing=1>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />

<%
	ProcessManagerRemote pm = processManagerFactory.getProcessManagerForReadOnly();

	String clsName = decode(request.getParameter("dataClassName"));
	String instanceId = decode(request.getParameter("instanceId"));
	String variableName = decode(request.getParameter("variableName"));
	Class outputCls = org.uengine.kernel.GlobalContext.getComponentClass(clsName);

	Object existingValue = pm.getProcessVariable(instanceId, "", variableName);	
	ObjectType outputClsTable = new ObjectType(outputCls);
	FieldDescriptor[] arrayFieldDescriptors = outputClsTable.getFieldDescriptors();
		
	String[] columns = new String[arrayFieldDescriptors.length];	
	for(int i=0; i<arrayFieldDescriptors.length; i++){
		columns[i] = arrayFieldDescriptors[i].getName();
	}
	
	for(int i=0; i<columns.length; i++){
    	FieldDescriptor fieldDescriptor = outputClsTable.getFieldDescriptor(columns[i]);
        %>	<tr><td bgcolor=#aaaaff> <%=fieldDescriptor.getDisplayName()%> </td> <%
    	
    	WebInputter inputter = fieldDescriptor.getWebInputter();
    	
   		%>  <td bgcolor=white><%=inputter.getInputterHTML("", fieldDescriptor, existingValue, null)%></td>
   		</tr><%
    }
%>
</table>
</td></tr></table>
  
<input type=submit>
</form>