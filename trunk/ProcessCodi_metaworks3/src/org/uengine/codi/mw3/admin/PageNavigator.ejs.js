var org_uengine_codi_mw3_admin_PageNavigator= function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;	
	
	$('#objDiv_' + objectId).addClass('pageflip');	
	
	//Page Flip on hover
	$(".pageflip").hover(
		function() {
			$(".pageflip img , .msg_block").stop()
				.animate({
					width: '307px', 
					height: '319px'
				}, 700); 
		}, 
		function() {
			$(".pageflip img").stop() 
				.animate({
					width: '42px', 
					height: '42px'
				}, 700);
			$(".msg_block").stop() 
				.animate({
					width: '42px', 
					height: '42px'
				}, 700);
		});	
	/*
	$('.goProcess').hover(
			function(){$('.msg_block').css("background","url(images/waveStyle/pageNavigation_Process.png)");},
			function(){$('.msg_block').css("background","url(images/waveStyle/pageNavigation.png)")}	
	)
	$('.goKnowledge').hover(
			function(){$('.msg_block').css("background","url(images/waveStyle/pageNavigation_Knowledge.png)");},
			function(){$('.msg_block').css("background","url(images/waveStyle/pageNavigation.png)")}	
	)
	$('.goIDE').hover(
			function(){$('.msg_block').css("background","url(images/waveStyle/pageNavigation_IDE.png)");},
			function(){$('.msg_block').css("background","url(images/waveStyle/pageNavigation.png)")}	
	)
	*/
	
}

org_uengine_codi_mw3_admin_PageNavigator.prototype = {
	startLoading : function(){
		$('.logo').after('<div id=\"mw3_progress\" style=\"width:30px; height:15px; float:left; margin-top:20px;  margin-right:20px;"><img src=\"images/waveStyle/ajax-loader_t.gif\" /></div>');
	},
	endLoading : function(){
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 500);		
	},
	destroy : function(){
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 500);		
		
	},
	showStatus : function(){
		
	}
}