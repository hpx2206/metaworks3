<%@page pageEncoding="UTF-8" import="org.uengine.kernel.*"%>
<%@page isErrorPage="true" import="java.io.*" %>

<%response.setContentType("text/html; charset=UTF-8");%>
<%!
//	private static org.apache.log4j.Logger errorLogger = org.apache.log4j.Logger.getLogger("bpm.error");
%><%
	Throwable orgErr = exception;			// 오류 객체.
	
	int errLevel = UEngineException.ERROR;
	String userMsg = "";

	Throwable finding = orgErr;
	
	while(!(finding instanceof UEngineException) && finding!=null){
		finding = finding.getCause();
	}
	
	if (finding instanceof UEngineException){
		errLevel = ((UEngineException)finding).getErrorLevel();
		orgErr = finding;
	}
	
	String[] errIconForErrorLevels = new String[]{
			"bug.gif",
			"bug.gif",
			"bug.gif",
			"info.gif"
	};
	
	String errIcon = errIconForErrorLevels[errLevel];
	String errTitle = orgErr.getMessage();	// 오류 제목.
	String errDesc = orgErr.toString();		// 오류 설명.

	if (errLevel != 3) {
		if( exception != null && exception instanceof ServletException ){
			if( ((ServletException)exception).getRootCause() != null ){
				orgErr = ((ServletException)exception).getRootCause();
				errTitle = orgErr.getMessage();	// 오류 제목.
				errDesc = orgErr.toString();	// 오류 설명.
			}
		}
		userMsg = "An exception has occurred. Please contact administrator. (ERROR LEVEL : " + errLevel + ")<br />";
	}
	
	exception.printStackTrace();
	//errorLogger.error(errTitle, orgErr);
	// TODO : 관리자에게 메일 보내기.
%>

<html>
<head>
<title>::: ERROR :::</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT %>/scripts/crossBrowser/elementControl.js"></script>
	<script type="text/javascript" src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/common/callback/errorpage.js"></script>
	<link rel="stylesheet" href="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/common.css" type="text/css">
	<style type="text/css">
	body {
		margin: 0;
		overflow: auto;
	}
	</style>
</head>
<body bgcolor="#F7F7F7" onload="setErrorPage();">
<div id="divErrorHeader" style="display: none;">
<% if (3 == errLevel) { %>
	<strong><%=errTitle%></strong>
<% } else { %>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="center"><table id="Table_01" width="563" height="94" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td rowspan="5"><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/error01_01.jpg" width="100" height="94" alt=""></td>
                <td height="34"><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorIMG.gif" width="69" height="18"
                /><a href="javascript:openPopupLayer();"><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/detailinfo.gif" width="69" height="18"
                id="idBtnDetailinfo" border="0" /></a></td>
            </tr>
            <tr>
                <td height="19" style="font-size:12px;">
                	<%= userMsg %>
                </td>
            </tr>
            <tr>
                <td background="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorlinedot.gif"></td>
            </tr>
            <tr>
                <td height="24" style="color:#5a9aea; font-size:11px;">
                	<%=errTitle%>
				</td>
            </tr>
            <tr>
                <td height="16"></td>
            </tr>
        </table></td>
    </tr>
</table>
</div>
<div id="divOrgBugDescript" style="display: none;">
<table width="720" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" align="center">
    <tr>
        <td width="3" height="3"><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorMsgBoxMo01.gif" width="4" height="4"></td>
        <td background="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorMsgBoxLine01.gif"></td>
        <td width="3"><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorMsgBoxMo02.gif" width="4" height="4"></td>
    </tr>
    <tr>
        <td background="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorMsgBoxLine04.gif"></td>
        <td align="center" style="padding:10px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td><table width="700" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="105" height="40" rowspan="2"><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorIMG02.gif" width="86" height="37"></td>
                        <td width="448" height="20" style="font-size:12px; color:#5a9aea; font-weight:bold;"><%=errTitle%></td>
                        <td width="147" rowspan="2" align="right">
                        	<a href="javascript:" name="btnCloseMsg"><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/closeMsg.gif" width="69" height="18" border="0"></a>
                        </td>
                    </tr>
                    <tr>
                        <td height="20" style="font-size:12px;">
                        	<%=errDesc%>
                    	</td>
                    </tr>
                </table></td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
                <td bgcolor="#333333" height="1"></td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
                <td>	
                	<div style="width:100%; height:400px; font-size:11px;">
                     	<textarea name="stackTrace" style="width:100%; height:400px;">
<%
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	orgErr.printStackTrace(pw);
	out.print(sw);

	orgErr.printStackTrace();
	
	sw.close();
	pw.close();
%>
					    </textarea>
                	</div>

				</td>
            </tr>
        </table></td>
        <td background="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorMsgBoxLine02.gif"></td>
    </tr>
    <tr>
        <td height="3"><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorMsgBoxMo04.gif" width="4" height="4"></td>
        <td background="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorMsgBoxLine03.gif"></td>
        <td><img src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT %>/images/Common/errorMsgBoxMo03.gif" width="4" height="4"></td>
    </tr>
</table>
<% } %>
</div>
</body>
</html>

