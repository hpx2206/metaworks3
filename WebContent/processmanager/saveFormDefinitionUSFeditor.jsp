<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8"%>

<%@page contentType="text/html; charset=UTF-8" 
        import="java.math.BigDecimal,
                java.io.*,
                java.util.*,
                javax.naming.Context,
                javax.naming.InitialContext,
                javax.naming.NamingException,
                javax.rmi.PortableRemoteObject,
                org.uengine.kernel.*,
                org.uengine.processmanager.*,
                org.uengine.formmanager.trans.*,
                au.id.jericho.lib.html.*"%>
<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<%!
/*
	public String decode(String source) throws Exception{
			if(source==null)
				return null;
			return new String(source.getBytes("8859_1"), "UTF-8");
	}
*/
	public void replaceMacro(String HTML_PATH) throws Exception {
		File htmlFile = new File(HTML_PATH);
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(htmlFile), "UTF-8"));
		StringBuffer daoHeader = new StringBuffer();
		StringBuffer contents = new StringBuffer();
		String line="";
		
		while ( (line=in.readLine()) != null  ) {
			if (line.indexOf("<form") > -1) {
				line = deleteTag(line, "<form");
			}
			if (line.indexOf("</form>") > -1) {
				line = deleteTag(line, "</form>");
			}			
			//System.out.println(line);
			contents.append(line);
			contents.append("\r\n");
		}
		UEngineUtil.saveContents(htmlFile.getAbsolutePath(), daoHeader.toString()+contents.toString());
		
	//	MhtmlEncoder gen = new MhtmlEncoder();
	//	gen.setHtmlFile(HTML_PATH);
	//	gen.setMhtmlFile(MHTML_PATH);
	//	gen.generate();
	}
	
	private String deleteTag(String src, String key) throws Exception {
		String retHtml = ""; //반환값

		int beg = 0;
		int end = 0;
		int keysize = key.length();
		boolean bcontinue = true;

		if (key.equals("<form")) {
			end = src.indexOf(key);

//			System.out.println("end:" + String.valueOf(end));
			retHtml = src.substring(beg, end);

//			System.out.println("여기" + retHtml);
			beg = src.indexOf(">", end) + 1;
			retHtml = " " + retHtml + " " + src.substring(beg);

//			System.out.println("여기2" + retHtml);
		} else if (key.equals("</form>")) {
			end = src.indexOf(key);
			retHtml = src.substring(beg, end);
			retHtml = " " + retHtml + src.substring(end + 7);
		}

		return retHtml;
	}

%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	String definition 		= request.getParameter("CKeditor1");
	String definition_edit  = request.getParameter("CKeditor1_edit"); // 2011.08.04
	String version 			= request.getParameter("version");
	boolean isNew 			= "1".equals(request.getParameter("isNew"));
	String definitionName 	= request.getParameter("definitionName");
	String definitionAlias 	= request.getParameter("definitionAlias");
	String savingFolder		= request.getParameter("folderId");
	String description 		= request.getParameter("description");
	String objectType 		= "form";
	String mimeContents     = decode(request.getParameter("mimeContents"));
	String belongingDefinitionId = request.getParameter("defId");
	
	//System.out.println("map : " + request.getParameterMap());
	//System.out.println("folderId : " + savingFolder);
    
	Map map = request.getParameterMap();
	/*
	for(Iterator iter=map.keySet().iterator(); iter.hasNext(); ) {
		String key = (String)iter.next();
		String value = (String)map.get(key) ;
		System.out.println("Paramater [" + key + "]='" + value+"'");
	}
	*/
	
	
	try{
		//TODO:New File Generate (Write.jsp, View.jsp)
		
		Source source=new Source(new String(definition));
		source.setLogWriter(new OutputStreamWriter(System.err)); // send log messages to stderr
		FormFields formFields=source.findFormFields();
		System.out.println("The document contains "+formFields.size()+" form fields:\n");
        
		StringBuffer sb = new StringBuffer();
		for (Iterator i=formFields.iterator(); i.hasNext();) {
			FormField formField=(FormField)i.next();
			//System.out.println(formField.getName());
			//System.out.println(formField.getFormControl().getFormControlType().toString());
			//System.out.println("===================================");
			//System.out.println(formField.getValues());
			//System.out.println(formField.getDebugInfo());
			
			formFields.addValue(formField.getValues().toString(), sb.toString());
			
			//sb.append("<" + "%=" + formField.getName() + "%" + ">");
			//formFields.addValue(formField.getName(),sb.toString());
			sb.setLength(0);
		}
        
		// 2011.08.11
        Source source_edit = new Source(new String(definition_edit));
        source_edit.setLogWriter(new OutputStreamWriter(System.err)); // send log messages to stderr
        FormFields formFields_edit = source_edit.findFormFields();
        
        // 2011.08.11
		sb = new StringBuffer();
        for (Iterator i=formFields_edit.iterator(); i.hasNext();) {
            FormField formField_edit = (FormField)i.next();
            
            formFields_edit.addValue(formField_edit.getValues().toString(), sb.toString());
            sb.setLength(0);
        }
		
		OutputDocument outputDocument=new OutputDocument(source);
		outputDocument.replace(formFields);
        
        // 2011.08.11
		OutputDocument outputDocument_edit = new OutputDocument(source_edit);
		outputDocument_edit.replace(formFields_edit);
		
		// when user opens form editor directly from the flow chart and the activity box
		if(belongingDefinitionId!=null && belongingDefinitionId.startsWith("[")){
			String alias = belongingDefinitionId.substring(1, belongingDefinitionId.indexOf("]"));
			belongingDefinitionId = pm.getProcessDefinitionIdByAlias(alias);
		}
		
		// *.form save
		String defVerId = pm.addProcessDefinition(definitionName, Integer.parseInt(version), description, false, definition, savingFolder, belongingDefinitionId, definitionAlias, objectType);
		String DEFINITION_ROOT = GlobalContext.getPropertyString(
				"server.definition.path",
				"./uengine/definition/"
			);
		
		//String defverid = defVerId.substring(0, defVerId.lastIndexOf("@"));
		String [] defVerIdArr = defVerId.split("@");
		String defverid = defVerIdArr[1];
		String defID = defVerIdArr[0];
		String processDefinitionVersionID = defVerIdArr[1];


		// *.html save
		String HTML_PATH = DEFINITION_ROOT + defverid +".html";
		OutputStreamWriter bw = null;
		try{
			bw = new OutputStreamWriter(new FileOutputStream(HTML_PATH), "UTF-8");
			bw.write(outputDocument.toString());
			bw.close();
		}catch(Exception e){
			throw e;
		}finally{
			if(bw!=null)
				try{bw.close();}catch(Exception e){};
		}
        
        // 2011.08.11
		String HTML_PATH_EDIT = DEFINITION_ROOT + defverid +"_edit.form";
        try{
            bw = new OutputStreamWriter(new FileOutputStream(HTML_PATH_EDIT), "UTF-8");
            bw.write(outputDocument_edit.toString());
            bw.close();
        }catch(Exception e){
            throw e;
        }finally{
            if(bw!=null)
                try{bw.close();}catch(Exception e){};
        }
		
		// *.mhtml save
		String MHTML_PATH = DEFINITION_ROOT + defverid +".mhtml";
		OutputStreamWriter MhtmlWriter = null;
		
		/*
		try{
			MhtmlWriter = new OutputStreamWriter(new FileOutputStream(MHTML_PATH), "UTF-8");
			MhtmlWriter.write(mimeContents);
			MhtmlWriter.close();
		}catch(Exception e){
			throw e;
		}finally{
			if(MhtmlWriter!=null)
				try{MhtmlWriter.close();}catch(Exception e){};
		}
		*/
		
		int index = HTML_PATH.lastIndexOf(File.separatorChar);
		String IMAGE_PATH = HTML_PATH.substring(index+1, HTML_PATH.length());
		replaceMacro(HTML_PATH);
		File htmlFile = new File(HTML_PATH);
		Html2Write html2write = new Html2Write();
		html2write.transformation(htmlFile, new BigDecimal(defverid));
		
		
		Html2FormView html2formview = new Html2FormView();
		html2formview.transformation(htmlFile, new BigDecimal(defverid));	
		
		
		pm.applyChanges();
		if (isNew) {
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
		}
%><%--Object has been successfully saved with id [<%=defVerId%>]--%>


<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.uengine.util.dao.DefaultConnectionFactory"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="basic.css" rel="stylesheet" type="text/css">
<script type/text="javascript">
	function sendRedirectPage() {
		setTimeout("sendUrl()", 2000);
	}

	function sendUrl() {
		//location.href = "viewFormDefinition.jsp?objectDefinitionId=<%=processDefinitionVersionID%>&folder=&processDefinitionVersionID=<%=defverid%>";
		//2011.08.05
		//location.href = "viewFormDefinition.jsp?objectDefinitionId=<%=defID%>&folder=&processDefinitionVersionID=<%=defverid%>";
		location.href = "viewFormDefinitionUSFeditor.jsp?objectDefinitionId=<%=defID%>&folder=&processDefinitionVersionID=<%=defverid%>";
	}
</script>
<body>
<div id="center">
	<div class="boxtext">
Object has been successfully saved with id [<%=defVerId%>]
	</div>
</div>
</body>
</html>

<%
	}catch(Exception e){
		pm.cancelChanges();
		e.printStackTrace(response.getWriter());
		e.printStackTrace();
		throw e;
	}
%>
<script type/text="javascript">
sendRedirectPage();
</script>