<%@page contentType="application/x-java-jnlp-file; charset=UTF-8" import="java.util.*,java.net.*,org.uengine.kernel.*"%>

<%@include file="../common/headerMethods.jsp"%>

<%
		response.setContentType("application/x-java-jnlp-file; charset=UTF-8");
		response.setHeader("Cache-Control", "public");
		response.setHeader("Expires", "0");

        String url = request.getRequestURL().toString();
        String codebase = url.substring( 0, url.lastIndexOf( "/" ) );
        URL urlURL = new java.net.URL(codebase);
       	String host = urlURL.getHost();
       	int port = urlURL.getPort();
       	
        	
 //      	String authorInfo = GlobalContext.serialize(revInfo, RevisionInfo.class);
 //      	authorInfo = authorInfo.replace("<", "&lt;").replace(">", "&gt;");

		//String authorInfo = loggedUserId;
 %>

<?xml version="1.0" encoding="utf-8"?> 
	<!-- you should modify the ip address for 'codebase' with an actual IP address of web server -->
	<jnlp spec="1.0+" codebase="<%=codebase%>"> 
  	  <information> 
    	    <title>u|Engine Process Designer</title> 
    	    <vendor>uEngine.org</vendor> 
    	      <homepage href="http://www.uengine.org"/> 
    	      <description>u|Engine Process Designer</description> 
    	      <description kind="short">u|Engine Process Designer</description> 
    	    <offline-allowed/> 
  	  </information> 
	  <security> 
	    <all-permissions/> 
	  </security>   	  
  	  <resources> 
	    <j2se version="1.5+" initial-heap-size="500M" max-heap-size="1000M"/>
            <jar href="signeduengine.jar"/> 
            <jar href="signedmetaworks.jar"/> 
            <jar href="signedcul4xdk.jar"/> 
            <jar href="signeddocsoapxdk.jar"/> 
            <jar href="signedwsdl4j.jar"/>           
            <jar href="signedxmlapis.jar"/> 
            <jar href="signedxerces_dom.jar"/>             
            <jar href="signeddom4j.jar"/> 
            <jar href="signedjdom.jar"/> 
            <jar href="signedtwister-engine.jar"/> 
            <jar href="signedbpel.jar"/>
            <jar href="signedtinylaf.jar"/> 
            <jar href="signedaxis.jar"/> 
            <jar href="signedbsf.jar"/> 
            <jar href="signedjakarta-regexp.jar"/> 
            <jar href="signedjboss-client.jar"/> 
            <jar href="signedjboss-common-client.jar"/> 
            <jar href="signedjs.jar"/> 
            <jar href="signedjxl.jar"/> 
            <jar href="signedjaxrpc.jar"/> 
            <jar href="signedjboss-j2ee.jar"/> 
            <jar href="signedjboss-transaction-client.jar"/> 
            <jar href="signedjbossall-client.jar"/> 
            <jar href="signedjbossmq-client.jar"/> 
            <jar href="signedcommons-httpclient.jar"/> 
            <jar href="signedcommons-logging.jar"/> 
            <jar href="signedcommons-codec.jar"/> 
            <jar href="signedcommons-lang.jar"/> 
            <jar href="signedxpp3.jar"/> 
            <jar href="signedxstream.jar"/>
            <jar href="signeddrools-core.jar"/>
            <jar href="signedjanino.jar"/>
            <jar href="signedservlet.jar"/>
            <jar href="signedxml-apis-ext.jar"/>
            <jar href="signedbatik-all.jar"/>
            <jar href="signedflamingo.jar"/>
            <jar href="signedsubstance.jar"/>
            <jar href="signedsubstance-flamingo.jar"/>
            <jar href="signedswingx.jar"/>
            <jar href="signedjl.jar"/>
                     
            <jar href="signeduengine_settings.jar"/> 
            
<%
	try{
		URL jlurl = new URL(codebase + "/jarlist.xml");
		ArrayList jarList = (ArrayList)GlobalContext.deserialize(jlurl.openStream(), String.class);
		for(Iterator iter = jarList.iterator(); iter.hasNext();){
			Object item = (Object)iter.next();
			%>		<jar href="signed<%=item%>"/><%
		}		
	}catch(Exception e){
		//e.printStackTrace();
	}
%>

		<property name="bpm_host" value="<%=host%>"/>		
<%
		if(port > 0){
%>
		<property name="bpm_port" value="<%=port%>"/>		
<%
		}
%>
      </resources> 
      
<%
	String instanceId = request.getParameter("instanceId");
	String defId = request.getParameter("defId");
	String defVerId = request.getParameter("defVerId");
	String folderId = request.getParameter("folderId");
	String superDefId = request.getParameter("superDefId");
	if(folderId==null || folderId.trim().length()==0) folderId="null";
	System.out.println("--------------------------------------------------------------");
	System.out.println("defId " + defId);
	System.out.println("defVerId " + defVerId);
	System.out.println("folderId " + folderId);
	System.out.println("--------------------------------------------------------------");
	
	if(instanceId!=null){%>
      <application-desc main-class="org.uengine.processdesigner.ProcessDesigner">
      		<argument>ko</argument> 
		    <argument>@ADHOC</argument> 
		    <argument><%=instanceId%></argument> 
		    <argument><%=host%></argument> 
		    <argument><%=defId%></argument> 
	  </application-desc>		
<%	}else{%>
      <application-desc main-class="org.uengine.processdesigner.ProcessDesigner">
      		<argument>ko</argument> 
		    <argument><%=folderId%></argument> 
		    <argument><%=defId%></argument> 
		    <argument><%=defVerId%></argument> 
		    <argument>null</argument> 
		    <argument><%=superDefId%></argument>
	  </application-desc> 
<%	}%>
	  
</jnlp>