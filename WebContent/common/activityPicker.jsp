<%@include file="header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="../style/uengine.css" type=text/css rel=stylesheet>
<LINK rel="stylesheet" href="../style/portlet.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/portal.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/groupware.css" type="text/css"/>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />

<%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();

	String instanceId = decode(request.getParameter("instanceId"));
	
	Hashtable options = new Hashtable();
	options.put("decorated", new Boolean(true));
	options.put("nowrap", new Boolean(true));
	options.put("enableEvent_onActivityClicked", new Boolean(true));
	options.put("imagePathRoot", "../processmanager/");

	String chart = pm.viewProcessInstanceFlowChart(instanceId, options);	
%>

<script>
	function ActivitySelection() {
		var instanceId = '';
		var tracingTag = '';
		var activityName = '';
	}
	
	var activitySelection = new ActivitySelection();
	
	function onActivityClicked(defId, defVersionId, tracingTag, instanceId, propertyString){
		propertyKeyAndValues = propertyString.split(",");
		properties = new Array();
		for(i = 0; i<propertyKeyAndValues.length; i++){
			if(propertyKeyAndValues[i]!=null){
				keyAndValue = propertyKeyAndValues[i].split("=");
				properties[keyAndValue[0]] = keyAndValue[1];
			}
		}
		
		activitySelection.instanceId = instanceId;
		activitySelection.tracingTag = tracingTag;
		activitySelection.activityName = properties["activityName"];
		document.forms[0].selectedActivity.value = properties["activityName"];
	}
</script>

<center>

<%=chart%>

<form>
Selected Activity: <input name='selectedActivity'>
<input type=button value="Ok" onclick="onOk(activitySelection, inputName);window.close()">
<input type=button value="Cancel">
</form>