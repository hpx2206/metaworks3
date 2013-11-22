<%@page import="org.uengine.kernel.GlobalContext"%>
<%@page import="com.defaultcompany.web.acl.*"%>

<%
	String language = (String)session.getAttribute("loggedUserLocale");
	Authority authority = new Authority();
	
	boolean  isEdition = false;
	if(session.getAttribute("loggedUserIsEdition")!=null){
		isEdition = (Boolean) session.getAttribute("loggedUserIsEdition");
	}
%>
<script type="text/javascript">
var topMenuNum = <%=iMuneNum%>;
$(document).ready(function(){
	
	if(topMenuNum == 0){
		$("#topMenuList > li.back").hide();
		$("#topMenuItem_1").removeClass("current");
	}
		
	$("#topMenuList > li").mouseover(function(){
		var targetObj = $(this);
		targetObj.addClass("current");
		
		if(topMenuNum == 0){
			$("#topMenuList > li.back").show();
		}else{
			if("topMenuItem_"+topMenuNum != targetObj.attr("id")){
				$("#topMenuItem_"+topMenuNum).removeClass("current");
			}
		}
	}).mouseout(function(){
		var targetObj = $(this);
		targetObj.removeClass("current");
		if(topMenuNum == 0){
			$("#topMenuList > li.back").hide();
		}else{
			if("topMenuItem_"+topMenuNum != targetObj.attr("id")){
				$("#topMenuItem_"+topMenuNum).addClass("current");
			}
		}
	});
});
</script>

<div id="flashArea">
	<div style="width:100%; height:1px; line-height:1px;"></div>
	<div id="menu">
	    <ul class="menu" id="topMenuList">
	    <%
			if(sUserLevel.equals("default")){
		%>
	        <li id="topMenuItem_1" class="<%=(iMuneNum==1)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processparticipant/worklist/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.worklist", loggedUserLocale)%></span></a></li>
	        <li style="width:2px; height:40px; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/topmenu/menuLine.gif) no-repeat 0 15px;"></li>
	        <li id="topMenuItem_2" class="<%=(iMuneNum==2)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processparticipant/participationProcess/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.participation", loggedUserLocale)%></span></a></li>
	        <li style="width:2px; height:40px; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/topmenu/menuLine.gif) no-repeat 0 15px;"></li>
	        <li id="topMenuItem_3" class="<%=(iMuneNum==3)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processparticipant/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.process", loggedUserLocale)%></span></a></li>
	        <%-- <li id="Attendence" class="<%= (iMuneNum==8)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/attendence/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.attendence", loggedUserLocale)%></span></a></li>--%>
	        <% if(isEdition){%>
		        <li id="topMenuItem_4" class="<%=(iMuneNum==4)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processmanager/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.processmanager", loggedUserLocale)%></span></a></li>
	        <% }
			}if(sUserLevel.equals("platformmanager")){
			%>
	        <li id="topMenuItem_1" class="<%=(iMuneNum==1)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processmanager/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.processmanager", loggedUserLocale)%></span></a></li>
	        <li style="width:2px; height:40px; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/topmenu/menuLine.gif) no-repeat 0 15px;"></li>
	        <li id="topMenuItem_2" class="<%= (iMuneNum==2)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/organizationmanager/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.organization", loggedUserLocale)%></span></a></li>
		<%
			}if(sUserLevel.equals("companymanager")){
		%>
	        <li id="topMenuItem_1" class="<%=(iMuneNum==1)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processparticipant/worklist/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.worklist", loggedUserLocale)%></span></a></li>
	        <li style="width:2px; height:40px; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/topmenu/menuLine.gif) no-repeat 0 15px;"></li>
	        <li id="topMenuItem_2" class="<%=(iMuneNum==2)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processparticipant/participationProcess/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.participation", loggedUserLocale)%></span></a></li>
	        <li style="width:2px; height:40px; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/topmenu/menuLine.gif) no-repeat 0 15px;"></li>
	        <li id="topMenuItem_3" class="<%=(iMuneNum==3)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processparticipant/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.process", loggedUserLocale)%></span></a></li>
	        <li style="width:2px; height:40px; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/topmenu/menuLine.gif) no-repeat 0 15px;"></li>
	        <li id="topMenuItem_7" class="<%=(iMuneNum==7)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/monitor/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.monitor", loggedUserLocale)%></span></a></li>
	        <li style="width:2px; height:40px; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/topmenu/menuLine.gif) no-repeat 0 15px;"></li>
	        <li id="topMenuItem_4" class="<%=(iMuneNum==4)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/processmanager/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.processmanager", loggedUserLocale)%></span></a></li>
	        <li style="width:2px; height:40px; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/topmenu/menuLine.gif) no-repeat 0 15px;"></li>
	        <li id="topMenuItem_5" class="<%=(iMuneNum==5)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/organizationmanager/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.organization", loggedUserLocale)%></span></a></li>
	        <%--<li id="Analysis" class="<%= (iMuneNum==6)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/analys/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.analysis", loggedUserLocale)%></span></a></li>
	        <li id="Attendence" class="<%= (iMuneNum==8)? "current": ""%>"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/attendence/index.jsp" class="parent"><span><%=GlobalContext.getMessageForWeb("title.attendence", loggedUserLocale)%></span></a></li> --%>
	    <%	} %>
	    </ul>
	</div>
</div>
<div id="copyright" style="display:none;">Copyright &copy; 2010 <a href="http://apycom.com/">Apycom jQuery Menus</a></div>
