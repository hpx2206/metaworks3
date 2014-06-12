<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String logoName = UEngineUtil.isNotEmpty(loggedUserGlobalCom) ? loggedUserGlobalCom : "bpmass";
String notifiedTaskIdStr = (String)session.getAttribute("notifiedTaskId");
String notifiedTaskId = "";
if(notifiedTaskId!=null){
	notifiedTaskId = notifiedTaskIdStr;
}

%>
<style type="text/css">
#notification{ position:absolute; right:10px; bottom:0px; background-color:white; border:1px solid #9ca3af; border-bottom:none; width:244px; display:none; z-index:99999;}
#notification #slide_head{ height:26px; background:url(/uengine-web/images/topmenu/slideup_title.gif) no-repeat; padding:5px; color:#484e58; position:relative; z-index:100000;}
#notification #slide_head #slide_toggle{position:absolute; top:0px; right:25px; width:22px; height:15px;  background:url(/uengine-web/images/topmenu/slideup_title_bt.gif) no-repeat; cursor:pointer;z-index:100001;}
#notification #slide_head #slide_close{position:absolute; top:0px; right:5px; width:22px; height:15px;  background:url(/uengine-web/images/topmenu/slideup_title_bt02.gif) no-repeat; cursor:pointer;z-index:100001;}
#notification #slide_body{ display:block;  padding:5px; color:#484e58;}
</style>
<script type="text/javascript">
	function viewTaskInfo(taskid, instanceId, tracingTag) {
		var screenWidth = screen.width;
		var screenHeight = screen.Height;
		var windowWidth = 950;
		var windowHeight = 650;
		var window_left = (screenWidth - windowWidth) / 2;
		var window_top = (screenHeight - windowHeight) / 2;
	
		var option = "left=" + window_left 
				+ ", top=" + window_top 
				+ ", width="+ windowWidth 
				+ ", height=" + windowHeight
				+ " ,scrollbars=yes,resizable=yes";
		var url = "/uengine-web/processparticipant/worklist/workitemHandler.jsp?taskId=" + taskid 
				+"&instanceId=" + instanceId 
				+"&tracingTag=" + tracingTag;
		var openedWih = window.open(url, "processView", option);
		openedWih.onclose = refresh;
	}
</script>
<script type="text/javascript">
	var USE_NOTIFICATION = true;
	var COOKIE_KEY_NEW_TASKID = "uEngine.Standalone.newTaskId";
	var COOKIE_KEY_MSG_VIEW = "uEngine.Standalone.msgView";
	var historyTaskId = 0;
	var historyMsgView = true;
	var notificationMessage = null;
	
	function initNotificationMessage(){
		historyTaskId = $.cookie(COOKIE_KEY_NEW_TASKID);
		if(historyTaskId!=null){
			historyTaskId = parseInt(historyTaskId);
		}
		historyMsgView = $.cookie(COOKIE_KEY_MSG_VIEW);
		if(historyMsgView==null){
			historyMsgView = true;
		}else{
			historyMsgView = (historyMsgView == "true")?true:false;
		}
		
		$('#notification').find("div[id='slide_head']").find("div[id='slide_toggle']").click(function(){
			var parent = $(this).parent().parent();
			var bodyheight = parent.height() - $(this).parent().height();
			var bottom = parent.css("bottom");
			if(parseInt(bottom) < 0){
				parent.animate({"bottom": "+="+bodyheight+"px"}, "slow");
			}else{
				parent.animate({"bottom": "-="+bodyheight+"px"}, "slow");
			}
		});
		
		$('#notification').find("div[id='slide_head']").find("div[id='slide_close']").click(function(){
			var parent = $(this).parent().parent();
			if(!parent.is('hidden')){
				parent.hide();
			}
			saveHistoryMsgView(false);
		});
		
		var jsonObj = loadNotificationMessage();
		
		if(jsonObj){
		
			var newTaskId = parseInt(jsonObj.TASKID);
			
			if((historyTaskId != null && newTaskId == historyTaskId) && historyMsgView == true){
				saveHistoryTaskId(newTaskId);
				var contentData = makeNotificationMessage(jsonObj);
				updateNotificationMessage(contentData);
				showNotificationMessage();
				/*
				var notiHead = $('#notification').find("div[id='head']");
				var bodyheight = notiHead.parent().height() - notiHead.height();
				notiHead.parent().css({"bottom": "-"+bodyheight+"px"});
				*/
			}
			
			setTimeout('reloadNotificationMessage()',3000);
		}
	}
	
	function saveHistoryTaskId(taskId){
		if($.cookie(COOKIE_KEY_NEW_TASKID)==null){
			$.cookie(COOKIE_KEY_NEW_TASKID, taskId, {'expires':7,'path':"/"});
		}else{
			$.cookie(COOKIE_KEY_NEW_TASKID, null);
			$.cookie(COOKIE_KEY_NEW_TASKID, taskId, {'expires':7,'path':"/"});
		}
		saveHistoryMsgView(true);
		historyTaskId = taskId;
	}
	
	function saveHistoryMsgView(bool){
		if($.cookie(COOKIE_KEY_MSG_VIEW)==null){
			$.cookie(COOKIE_KEY_MSG_VIEW, bool, {'expires':7,'path':"/"});
		}else{
			$.cookie(COOKIE_KEY_MSG_VIEW, null);
			$.cookie(COOKIE_KEY_MSG_VIEW, bool, {'expires':7,'path':"/"});
		}
	}
	
	function updateNotificationMessage(htmlData){
		notificationMessage = htmlData;
		$('#notification').find("div[id='slide_body']").html(notificationMessage);
	}

	function showNotificationMessage(){
		if($('#notification').is('hidden')){
			$('#notification').fadeIn(100);
		}else{
			$('#notification').hide();
			$('#notification').fadeIn(100);
		}
	}
	
	function hideNotificationMessage(){
		if(!$('#notification').is('hidden')){
			$('#notification').fadeOut(100);
		}
	}
	
	function loadNotificationMessage(){
		var bodyContent = $.ajax({
		      url: "/uengine-web/processparticipant/worklist/wl2_workList_notification.jsp",
		      global: false,
		      type: "POST",
		      dataType: "json",
		      async:false,
		      success: function(msg){
		         alert(msg);
		      }
		   }
		).responseText;
		
		return eval("("+bodyContent+")");
	}
	
	function makeNotificationMessage(jsonObj){
		var contentData = "새로운 업무가 도착하였습니다. <br/><strong>"
			+jsonObj.NAME+"-"+jsonObj.TITLE+"</strong><br/> 시작하려면 "
			+"<a href=javascript:viewTaskInfo('"+jsonObj.TASKID+"','"+jsonObj.INSTID+"','"+jsonObj.TRCTAG+"','NEW');>"
			+"여기</a>를 클릭하세요."
			
		return contentData;
	}
	
	function reloadNotificationMessage(){
		//#notification
		setTimeout('reloadNotificationMessage()', 60 * 1000);
		
		var jsonObj = loadNotificationMessage();
		var contentData = makeNotificationMessage(jsonObj);
		
		if(jsonObj.RESULT=="TRUE"){
			var newTaskId = parseInt(jsonObj.TASKID);

			if(historyTaskId < newTaskId){
				saveHistoryTaskId(newTaskId);
				updateNotificationMessage(contentData);
				showNotificationMessage();
			} else if(historyTaskId > newTaskId){
				hideNotificationMessage();
			}
		}else{
			hideNotificationMessage();
		}
	}
	jQuery(document).ready(function(){
		initNotificationMessage();
	});
</script>
<div class="loadframe">
	<div class="loadinner">
		<div class="loadbar">
			<img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/i_dash_load.gif">
		</div>
    </div>
</div>
<%@ include file="topMenuList.jsp" %>
<div id="header" dojoType="dijit.layout.ContentPane" region="top" style="height:74px; border:none; background:url(<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/topbg_default.gif);">
	<div id="logo">
		<a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/index.jsp"><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/portrait/groups/<%=logoName %>_logo.gif" width="197" height="74"></a>
	</div>
	<form id="titleform" name="titleform" action="<%=GlobalContext.WEB_CONTEXT_ROOT%>/logout.jsp" target="_top" method="post">
		<div id="topline">
	        <div class="userbtn"><a href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/organizationmanager/userInfoByself.jsp" target="innerworkarea" class="rollover"><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/B_EditInfo_<%=loggedUserLocale%>.gif" /><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/B_EditInfo_on_<%=loggedUserLocale%>.gif" class="over" /></a>&nbsp;<a href="javascript:titleform.submit();" class="rollover"><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/B_LogoOut_<%=loggedUserLocale%>.gif" /><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/B_LogoOut_on_<%=loggedUserLocale%>.gif" class="over"/></a>
	        </div>
	        <div class="userinfo">
	        	<span class="fontbold"><%=loggedUserComName%> - <%=loggedUserFullName%></span>
	        </div>
	    </div>
	</form>
</div>
<!-- Notification Message Div -->
<div id="notification">
	<div id="slide_head"><div id="slide_toggle"></div><div id="slide_close"></div><div>알립니다.</div></div>
	<div id="slide_body"></div>
</div>