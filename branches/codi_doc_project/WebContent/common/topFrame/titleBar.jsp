<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function changeTitle(iframe)
	{
		document.getElementById("divPageTitle").innerHTML = iframe.contentWindow.document.title;
		var navi = iframe.contentWindow.document.navigation;
		
		if(navi)
		{
			var navis = navi.split(";");
			var sNavi = "";

			for(var i = 0; i < navis.length; i++)
			{
				sNavi += " <img src=\"<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/i_blue03.gif\"> " + navis[i];
			}
			
			document.getElementById("divPageNavi").innerHTML = sNavi;
		}

		resizeFrame(iframe);
	}
</script>
<div class="pagrtitlebg" id="divPageTitleBar">
	<div class="pagetitleicon"><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/lc05711.gif"></div>
	<div class="pagetitletitle" id="divPageTitle"></div>
	<div class="pagrtitlenavi" id="divPageNavi"></div>
</div>