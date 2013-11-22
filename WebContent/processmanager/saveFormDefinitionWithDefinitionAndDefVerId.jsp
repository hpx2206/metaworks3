<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
%>
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
<%!
/*	public String decode(String source) throws Exception{
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
			System.out.println(line);
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
	
	String definition 		= decode(request.getParameter("definition"));
	String definitionVerId	= decode(request.getParameter("defVerId"));
	
	try{
		//TODO:New File Generate (Write.jsp, View.jsp)
		
		Source source=new Source(new String(definition));
		source.setLogWriter(new OutputStreamWriter(System.err)); // send log messages to stderr
		FormFields formFields=source.findFormFields();
		System.out.println("The document contains "+formFields.size()+" form fields:\n");
		
		StringBuffer sb = new StringBuffer();
		for (Iterator i=formFields.iterator(); i.hasNext();) {
			FormField formField=(FormField)i.next();
			System.out.println(formField.getName());
			System.out.println(formField.getFormControl().getFormControlType().toString());
			System.out.println(formField.getDebugInfo());
			
			//sb.append("<" + "%=" + formField.getName() + "%" + ">");
			formFields.addValue(formField.getName(),sb.toString());
			sb.setLength(0);
		}
		
		ProcessDefinitionRemote pdr = pm.getProcessDefinitionRemote(definitionVerId);
		ProcessDefinitionRemote[] findLastVersion = pm.findAllVersions(pdr.getBelongingDefinitionId());
		
		OutputDocument outputDocument=new OutputDocument(source);
		outputDocument.replace(formFields);
		
		// *.form save
		String defVerId = pm.addProcessDefinition(pdr.getName().toString(), pdr.getVersion() , pdr.getDescription().toString(), false, definition, pdr.getParentFolder(), pdr.getBelongingDefinitionId(), pdr.getAlias(), "form");
		//String defVerId = pm.addProcessDefinition(definitionName, Integer.parseInt(version), description, false, definition, savingFolder, belongingDefinitionId, definitionAlias, objectType);
		String DEFINITION_ROOT = GlobalContext.getPropertyString(
				"server.definition.path",
				"./uengine/definition/"
			);
		
		
		String defverid = defVerId.substring(0, defVerId.lastIndexOf("@"));
		String [] defVerIdArr = defVerId.split("@");
		//String defverid = defVerIdArr[0];
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
		
		
		
	}catch(Exception e){
		pm.cancelChanges();
		e.printStackTrace(response.getWriter());
		e.printStackTrace();
		throw e;
	}finally{
		pm.remove();
	}
%>