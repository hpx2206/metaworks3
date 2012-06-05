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
		$('body').prepend('<div id=\"mw3_progress\" style=\"position:absolute; z-index:99999; width:70px; height:70px; background:url(images/waveStyle/loadingBg.png) no-repeat; left:45%; top:45%;\"><img style=\"margin:10px 0 0 17px;\" src=\"images/waveStyle/load.gif\" /></div>');
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