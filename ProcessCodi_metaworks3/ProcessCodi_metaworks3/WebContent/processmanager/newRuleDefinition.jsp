<%@include file="../common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="../style/uengine.css" type=text/css rel=stylesheet>
<LINK rel="stylesheet" href="../style/portlet.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/portal.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/groupware.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="../style/default.css" />

<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red" leftmargin="15" topmargin="15" marginwidth="15" marginheight="15" onLoad="drawLoopLines()">
<form name="actionForm" action="saveObjectDefinition.jsp" method=POST>
<table border="0" cellpadding="0" cellspacing="0" width="100%" xmlns>
	<tr>
		<td height="28"><span class="sectiontitle">New Rule Definition</span>
		</td>
	</tr>

</table>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="5"></td>
	</tr>
</table>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr >
		<td align="center" colspan="3" class="formheadline"></td>
	</tr>			
	
	<tr height="30" >
		<td width="150"  class="formtitle">Name
		</td>
		<td width="10"></td>
		<td width="*" align=left>
			<input name="definitionName">			
		</td>
	</tr>
	<tr >
		<td align="center" colspan="3" class="formline" height="1"></td>
	</tr>    
			
	<tr height="30" >
		<td width="150"  class="formtitle">Description
		</td>
		<td width="10"></td>
		<td width="*" align=left>
			<input name=description size=80>
		</td>
	</tr>
	<tr >
		<td align="center" colspan="3" class="formline" height="1"></td>
	</tr> 
    		<tr height="20" >
		<td width="150"  class="formtitle">Rule Definition
		</td>
		<td width="10"></td>
		<td width="*" align=left>
<textarea name="definition" rows=17 cols=80 style="margin:10px 0 10px 0;"></textarea>

		</td>		
	</tr>
	<tr >
		<td align="center" colspan="3" class="formheadline"></td>
	</tr>	
</table>		
			

	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="10"></td>
	</tr>
</table>



<input name="version" value="1" type="hidden">
<input name="objectType" value="rule" type="hidden">
<input name="folderId" value="<%=request.getParameter("folderId")%>" type="hidden" />
<input type="submit">

</form>