var org_uengine_codi_mw3_model_InstanceListPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	this.objectDiv
		.css({
			position: 'relative',
			height:   '100%'
		});
	
	this.divId = mw3._getObjectDivId(this.objectId);
	
	// window 의 title 설정
	this.windowObjectId = this.objectDiv.closest('.mw3_window').attr('objectId');
	
	if(this.object && this.object.title){
		if(this.windowObjectId){
			mw3.getFaceHelper(this.windowObjectId).setTitle(mw3.localize(this.object.title));
		}
	}
	
	var browser = mw3.browserCheck();
	if(browser == "MSIE 7"){
		$('.searchboxarea').css("margin-left","20");
	}else{
		searchBarMagin = $('.newprocessbtn').width() + 20;
		$('.searchboxarea').css("margin-left",searchBarMagin);
	}
	
//	$("#project_Info").click(function(){
//		if($(this).hasClass('current')){
//			$('.projectInfo').slideUp(250);
//			$(this).removeClass('current').text("Project info. ▼");
//		}else{
//			$('.projectInfo').slideDown(250);
//			$(this).addClass('current').text("Project info. ▲");
//		}
//	})
	
	if(this.object && this.object.preloaded){
		
		var scrollDiv = $('#objDiv_' + this.objectId + " .bottom");
		
		scrollDiv.scroll(function(e) {
			if(scrollDiv.scrollTop() > scrollDiv.find('div').eq(0).height() - scrollDiv.height()-200){
				mw3.objects[lastMore].more();
			}
		});
		
		/*
	 	$('#' + this.divId + ' .ui-layout-content').mCustomScrollbar({
			callbacks:{
				
				onTotalScroll:function(){
					mw3.objects[lastMore].more();
					//setTimeout(function(){$("#method_37_switchToScheduleCalendar .ui-layout-content").mCustomScrollbar("update");},2000);
				}
			}
		});*/
		
		
		$('#' + mw3._getObjectDivId(mw3.getChildObjectId(this.objectId, 'topicFollowers')) + " #searchbox").css("border-bottom","#9EBFC4 1px solid ");
		$('#' + mw3._getObjectDivId(mw3.getChildObjectId(this.objectId, 'documentFollowers')) + " #searchbox").css("border-bottom","#9EBFC4 1px solid ");
	}else{
		setTimeout(function(){
			mw3.call(objectId, 'load');	
		}, 1);
	}
};

org_uengine_codi_mw3_model_InstanceListPanel.prototype = {
	startLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
			mw3.getFaceHelper(this.windowObjectId).startLoading();
	},
	endLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
	},
	showStatus : function(message){
		
	}	
};