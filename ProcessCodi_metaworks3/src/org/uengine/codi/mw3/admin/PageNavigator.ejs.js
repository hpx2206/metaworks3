var org_uengine_codi_mw3_admin_PageNavigator = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;

	$('#objDiv_' + objectId).addClass('pageflip');	
	
	//Page Flip on hover
	$(".pageflip").hover(function() {
		$(".pageflip img , .msg_block").stop()
			.animate({
				width: '307px', 
				height: '319px'
			}, 700); 
		} , function() {
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
	
}

org_uengine_codi_mw3_admin_PageNavigator.prototype = {
	startLoading : function(){
		$('.logo').after('<div id=\"mw3_progress\" style=\"width:128px; height:15px; float:left; margin-top:20px;"><img src=\"images/waveStyle/ajax-loader_t.gif\" /></div>');
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