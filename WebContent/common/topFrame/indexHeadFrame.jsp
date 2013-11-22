
<%@page import="org.uengine.security.AclManager"%>

<%@include file="../header.jsp"%>
<%@include file="../getLoggedUser.jsp"%>
<%@include file="../../lib/jquery/importJquery.jsp"%>

<%
	String sUserLevel = null;
	if (loggedUserIsMaster)
	{
		sUserLevel = "platformmanager";
	}
	else if (loggedUserIsAdmin)
	{
		sUserLevel = "companymanager";
	}
	else
	{
		sUserLevel = "default";
	}

%>

<title>Welcome to uEngine BPM</title>

<link rel="stylesheet" type="text/css" href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/dojo/dijit/tests/css/dijitTests.css" />
<link rel="stylesheet" type="text/css" href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/dojo/dojox/layout/tests/_expando.css" />
<link rel="stylesheet" type="text/css" href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/style/default.css" />
<link rel="stylesheet" type="text/css" href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/style/topmenu/topmenu.css" />

<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/common.js"></script>
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/dojo/dojo/dojo.js" djConfig="parseOnLoad:true, isDebug:false"></script>
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/dojo/dijit/tests/_testCommon.js"></script>
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/dojo/dojox/layout/ExpandoPane.js"></script>
<%--
<script type="text/javascript" language="javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/jquery/jquery.dropdownPlain.js"></script>
 --%>
<script type="text/javascript">
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dijit.Tree");
	dojo.require("dijit.layout.AccordionContainer");
	dojo.require("dijit.layout.BorderContainer");
    dojo.require("dijit.Declaration");
    dojo.require("dojo.data.ItemFileWriteStore");
	dojo.require("dijit.Menu");
	dojo.require("dojo.parser");	// scan page for widgets and instantiate them
	dojo.require("dijit.layout.LayoutContainer");   
    dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.TextBox");
        
	var global_tn = '';

	var req = null;
	var WEB_CONTEXT_ROOT = "<%=GlobalContext.WEB_CONTEXT_ROOT%>";
	var WEB_ROOT = "<%=GlobalContext.WEB_CONTEXT_ROOT%>";

	var getAuthorityList = function() {
		var url = WEB_CONTEXT_ROOT + "/checkWork?userId=<%=codiSession.getAttribute("loggedUserId")%>&todo=newWork";
		submitAjaxServlet(url, "Get", alertNewWork)
	}
	var urlNotice = "http://uengine.org/notice_<%=loggedUserLocale%>.jsp";


	function resizeFrame(iframe)
	{
		var titleBarHeight = 0;
		if(isIE)
		{
			iframe.height = "100%"
		}
		else
		{
			titleBarHeight = iframe.parentNode.offsetHeight;
			iframe.style.height = (titleBarHeight - 33) + "px";
		}
	}
	
</script>
