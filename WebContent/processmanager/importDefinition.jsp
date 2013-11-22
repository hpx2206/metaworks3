<%@include file="../common/header.jsp"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="net.sf.jazzlib.*"%>
<%@page import="java.io.*"%>
<%@page import="org.uengine.util.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.io.*"%>
<%@page import="org.uengine.util.export.UEngineArchive"%>
<%@page import="org.uengine.util.export.DefinitionArchive"%>

<link rel="stylesheet" type="text/css" href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/style/bbs.css">
  <script language="JavaScript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/bbs.js"></script>
<LINK href="../style/uengine.css" type=text/css rel=stylesheet>
<link rel="stylesheet" type="text/css" href="../style/default.css" />
<style TYPE="TEXT/CSS">
BODY {background:url(../../processmanager/images/empty.gif) #ffffff }
</style>
	
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />

<%
	ProcessManagerRemote pm = null;
	try{
		pm = processManagerFactory.getProcessManagerForReadOnly();
	}finally{
		if(pm!=null) try{pm.remove();}catch(Exception e){}
	}
	
	String defId = null;
	
	String TEMP_DIRECTORY = GlobalContext.getPropertyString(
			"server.definition.path",
			"." + File.separatorChar + "uengine" + File.separatorChar + "definition" + File.separatorChar
		);
		
		if(!TEMP_DIRECTORY.endsWith("/") && !TEMP_DIRECTORY.endsWith("\\")){
			TEMP_DIRECTORY = TEMP_DIRECTORY + "/";
		}
		TEMP_DIRECTORY = TEMP_DIRECTORY + "temp" + File.separatorChar;
	
	String path = TEMP_DIRECTORY + "upload";
	String tempPath = path + File.separatorChar + "temp";
	String fileName = null;
	
	try{	
		Map parameterMap = new HashMap();
		
		DiskFileUpload upload = new DiskFileUpload();
		
		File f = new File(tempPath);
		if(!f.exists())
			f.mkdirs();
			
		upload.setSizeMax(10*1024*1024); //File upload Max size 
		upload.setSizeThreshold(4096);//Max memory size 		
		upload.setRepositoryPath(tempPath); //temp repository	 
		 
		List items = upload.parseRequest(request); 
		Iterator iter = items.iterator(); 
		
		while (iter.hasNext()) { 
			FileItem item = (FileItem)iter.next();
			if(item.isFormField()){ //input type != "file"	 
			    String name = item.getFieldName();// field name 
			    String value = item.getString();// field value
			    parameterMap.put(name, value);
			}else{ //input type == "file" 		 
				String absolutePath = item.getName();//field name 
			    fileName = absolutePath.substring(absolutePath.lastIndexOf("\\")+1);//get only filename (exclude path)
			    long fileSize = item.getSize();//file size
			   
			    File file = new File(path + File.separatorChar + fileName);
			    item.write(file);
			    parameterMap.put(item.getFieldName(), path + File.separatorChar + fileName);
			}
		} 	    
		String currentDir = FormActivity.FILE_SYSTEM_DIR+File.separatorChar;		
		
		InputStream is = new BufferedInputStream(new FileInputStream((String)parameterMap.get("importDefPath")));
		//Vector productionList = pm.importProcessDefinition(is, (String)parameterMap.get("definitionId"));
		
		Hashtable processDefinitionList = pm.importProcessAliasCheck(is);
		boolean[] duplication = (boolean[])processDefinitionList.get("duplicationProcessList");
		UEngineArchive ua = (UEngineArchive)processDefinitionList.get("processDefinitionArchive");
		
%>
<script type="text/javascript">
	var data_name				= new Array();
	var data_alias				= new Array();
	var data_type				= new Array();
	var data_duplication		= new Array();
	var data_archiveFileName	= new Array();
	
	function init() {
		var frm = document.insert_form;

		for (i=0; i<<%=ua.getDefinitionList().size()%>; i++) {
			data_name[i]			= frm.data_name[i].value;
			data_alias[i]			= frm.data_alias[i].value;
			data_type[i]			= frm.data_type[i].value;
			data_duplication[i]		= frm.data_duplication[i].value;
			data_archiveFileName[i]	= frm.data_archiveFileName[i].value;
		}
	}

	function checkDuplicationAlias() {
		var frm = document.insert_form;
		var count = 0;
		var datalength = frm.data_name.length;

		var isError = false;

		for (i=0; i<datalength; i++) {
			if (frm.data_duplication[i].value == "Alias Duplication" && frm.isupdate[i].checked == true) {
				//update
				frm.command[i].value = "update";
			}
			else if (frm.data_duplication[i].value != "Alias Duplication" && frm.isupdate[i].checked == false) {
				//new
				frm.command[i].value = "new";
			}
			else if (frm.data_duplication[i].value != "Alias Duplication" && frm.isupdate[i].checked == true) {
				//new
				frm.command[i].value = "new";
			}
			else if (frm.data_duplication[i].value == "Alias Duplication" && frm.isupdate[i].checked == false) {
				//error
				isError = true;
				break;
			}
		}

		if (isError == true ) {
			alert("Alias Duplication!");
		} else {
			frm.submit();
		}

		/*
	
		for (i=0; i<datalength; i++) {
			if ( (frm.data_alias[i].value==data_alias[i]) && (data_duplication[i]=="Alias Duplication") ) {
				frm.data_duplication[i].value = "Alias Duplication";
				count++;
			}
			else {
				frm.data_duplication[i].value = "";
			}
		}

		if (count != 0) {
			alert("Alias Duplication!");
		} else {
			frm.submit();
		}

		*/
	}

	var xmlHttp;
	var tempArchiveFileName;

	function createXMLHttpRequest() {
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} 
		else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();                
		}
	}

	function changeNameAlias(type) {
		var frm = document.insert_form;
		var change_text;
		var count = frm.data_name.length;

		var setString = "";

		if (type != "init") {
			for (i=0; i<count; i++) {
				if (type == "name") {
					change_text = frm.change_name.value;
					frm.data_name[i].value = frm.data_name[i].value+change_text;
				} else if (type == "alias") {
					if (frm.data_type[i].value != "folder" && frm.data_type[i].value != "root folder") {
						change_text = frm.change_alias.value;
						frm.data_alias[i].value = frm.data_alias[i].value+change_text;
					}
				}
			}
		}

		if (type == "name")
			frm.change_name.value = "";
		else if (type =="alias")
			frm.change_alias.value = "";

		if (type == "alias" || type == "init") {
			for (i=0; i<count; i++) {
				setString += frm.data_alias[i].value+"|";
			}
			setString = setString.substring(0, setString.length-1);
	
			createXMLHttpRequest();
			var url = "importValidateDefinition.jsp?mode=multi&paras=" + escape(setString);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = callbackMulti;
			xmlHttp.send(null);
		}
	}

	function callbackMulti() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var xmlDoc = xmlHttp.responseXML;
				var valList = xmlDoc.getElementsByTagName("val");
				setMessageMulti(valList);
			}
		}
	}

	function setMessageMulti(valList) {
		var frm = document.insert_form;
		
		for (i=0; i<valList.length; i++) {
			var val = valList.item(i).firstChild.nodeValue;

			if (frm.data_type[i].value != "folder" &&  frm.data_type[i].value != "root folder") {	
				if (val == "false") {
					frm.data_duplication[i].value = "";
					frm.isupdate[i].checked = "";
				} else {
					frm.data_duplication[i].value = "Alias Duplication";
					frm.isupdate[i].checked = "checked";
				}
			}
		}

	}
</script>

<body onLoad="init();">
		<form name="insert_form" action="importDefinitionArchive.jsp" method="post">
		<div style="margin-bottom: 20px">
			<table cellpadding="0" cellspacing="0" width="100%">
            	<tr>
                    <td colspan="3" class="formheadline" height="2"></td>
                </tr>
				<tr>
					<td class="formtitle" width="150">Name : </td>
					<td class="formcon"><input name="change_name" type="text"> <input type="button" value="변경" onClick="changeNameAlias('name');"></td>
					<td rowspan="3" class="formcon" width="120"><input type="button" value="초기화" onClick="reset();" style="width:150; height:30px;"></td>
				</tr>
                <tr>
                    <td class="formline" height="1"></td>
                    <td class="formline" height="1"></td>
                </tr>
				<tr>
					<td class="formtitle" width="150">Alias : </td>
					<td class="formcon"><input name="change_alias" type="text"> <input type="button" value="변경" onClick="changeNameAlias('alias');"></td>
				</tr>
                <tr>
                    <td colspan="3" class="formheadline" height="2"></td>
                </tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
</div>
		
		<center> 
		<table cellpadding="1" cellspacing="1" width="100%" class="tableborder" bgcolor="#E0E7F0">
			<tr class="tabletitle"  align="center" style="font-weight:bold">
				<td>Name</td>
				<td>Alias</td>
				<td>Type</td>
				<td>Status</td>
				<td>Update</td>
			</tr>
<%
		for (int i=0; i<ua.getDefinitionList().size(); i++) {
			DefinitionArchive da = (DefinitionArchive) ua.getDefinitionList().get(i);
%>
			<tr bgcolor="#ffffff" align="center" height="25">
				<td><input type="text" name="data_name" value="<%=da.getName() %>"></td>
				<td>
				<% 
					if (da.getObjectType().equals("folder"))
						out.print("<input name=\"data_alias\" type=\"text\" readonly=\"readonly\" value=\" \" onchange=\"changeNameAlias('init');\">");
					else
						out.print("<input name=\"data_alias\" type=\"text\" value=\""+da.getAlias()+"\" onchange=\"changeNameAlias('init');\">");
				%>
				</td>
				<%
					String typeValue = null;
					if ( da.getParentFolder().equals("-1") ) {
						typeValue = "root folder";
					} else if ( !da.getParentFolder().equals("-1") ) {
						int lastOfSeparator = da.getArchiveFileName().indexOf(ZipEntryMapper.ENTRY_SEPARATOR);
						if ( lastOfSeparator > -1 )
							typeValue = da.getArchiveFileName().substring(0, lastOfSeparator);
						
						if ( typeValue != null && typeValue.equals("sub") ) {
							typeValue = "sub "+da.getObjectType();
						} else {
							typeValue = da.getObjectType();
						}
					}
				%>
				<td><input type="text" value="<%=typeValue %>" readonly="readonly" style="border: 0;"></td>
				<input type="hidden" name="data_type" value="<%=da.getParentFolder().equals("-1") ? "root folder" : da.getObjectType() %>" readonly="readonly" style="border: 0;">
				<td><input type="text" name="data_duplication" value="<%=duplication[i]==true ? "Alias Duplication" : " " %>" readonly="readonly" style="border: 0;color: red;"></td>
					
				<%	if ( "folder".equals(da.getObjectType()) || "root folder".equals(da.getObjectType()) ) {
						out.print("<td><input type=\"checkbox\" name=\"isupdate\" style=\"display: none\" value=\""+i+"\"></td>");
					} else {
						if (duplication[i]==true)
							out.print("<td><input type=\"checkbox\" name=\"isupdate\" checked=\"checked\" value=\""+i+"\"></td>");
						else
							out.print("<td><input type=\"checkbox\" name=\"isupdate\" value=\""+i+"\"></td>");
					} 
					%>
					
				<input type="hidden" name="data_archiveFileName" value="<%=da.getArchiveFileName() %>">
				<input type="hidden" name="command" value="">
			</tr>
<%
		}
		out.print("<input type=\"hidden\" name=\"fileName\" value=\""+fileName+"\">");
		out.print("<input type=\"hidden\" name=\"definitionId\" value=\""+(String)parameterMap.get("definitionId")+"\">");
		out.print("</table></center><br><input type=\"button\" value=\"Submit\" onclick=\"checkDuplicationAlias();\"></form>");
		
		is.close();
		if(f.exists()) {
			f.deleteOnExit();
		}
	}catch(Exception e){
%>
Import Definition Error
<script type="text/javascript">
	sendRedirectPage();
	
	function sendRedirectPage() {
		setTimeout("top.location.reload()", 3000);
	}
</script>
<%
	    e.printStackTrace();
	}finally{
	  	try{
			pm.remove();
		}catch(Exception ex){}
	}
%>

</body>
