<%@page import="java.util.*,java.io.*,org.apache.commons.fileupload.servlet.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.disk.*,org.drools.decisiontable.SpreadsheetCompiler,org.drools.decisiontable.InputType"%>
<%
String ruleDefinition="";

		if (ServletFileUpload.isMultipartContent(request)) {
  
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		//factory.setRepository(new File("/tmp"));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1000000);

		List fileItems = upload.parseRequest(request);
		Iterator i = fileItems.iterator();
		FileItem fi = (FileItem) i.next();

		if (fi.isFormField()) {
			//do nothing
		} else {
			InputStream uploadedStream  = fi.getInputStream();
			SpreadsheetCompiler compiler = new SpreadsheetCompiler();
			String drl = compiler.compile(uploadedStream ,InputType.XLS);
			ruleDefinition=drl;
			System.out.println(drl);
			uploadedStream.close();
		}
	} else {
	}
%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="../style/uengine.css" type=text/css rel=stylesheet>
<LINK rel="stylesheet" href="../style/portlet.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/portal.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/groupware.css" type="text/css"/>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="1">
		<td align="center" height="1" class="bgcolor_head_upperline"></td>
	</tr>
	<tr height="5">
		<td align="center" height="5" class="bgcolor_head"></td>
	</tr>
	<tr height="1">
		<td align="center" height="1" class="bgcolor_head_underline"></td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="20" >
		<td width="150" align=left bgcolor="f4f4f4">
			&nbsp;<b>Object Definition</b>
		</td>
		<td width="10"></td>
		<td width="*" align=left>
		<textarea name="definition" rows=17 cols=80><%=ruleDefinition %></textarea>
		</td>		
	</tr>
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>
</table>	
</body>
</html>
