var org_uengine_codi_mw3_model_OverlayCommentWorkItem = function(objectId, className){
	
	this.objectId = objectId;
	var workItem = mw3.objects[objectId];
	var parentWorkItem = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IWorkItem@"+ workItem.overlayCommentOption.parentTaskId);
	
	var div = $("#objDiv_" + parentWorkItem.__objectId);
	//var div = $("#objDiv_" + parentWorkItem.__objectId).find('.view_box3:first');
	
		
	$('.overlay_comment').hover(
			function(){$(this).css({"z-index":"101",'opacity':'1'})},
			function(){$(this).css({"z-index":"100",'opacity':'0.5'})}
	);
	$('.overlay_comment_line').hover(
			function(){$(this).parent().find('.overlay_comment').css({'z-index':'101','opacity':'1'})},
			function(){$(this).parent().find('.overlay_comment').css({'z-index':'100','opacity':'0.5'})}
	);
	
	if(workItem.metaworksContext && (workItem.metaworksContext.when == mw3.WHEN_NEW || workItem.metaworksContext.when == mw3.WHEN_EDIT)){
	//	workItem.overlayCommentOption.x = mw3.mouseX - div.offset().left - 30; /*  (mw3.mouseX - div.offset().left - 30) / (div.parent().width()/100) = 백분율 좌표 */
	//	workItem.overlayCommentOption.y = mw3.mouseY - div.offset().top - 34;
		
		if(workItem.taskId==null){
			workItem.overlayCommentOption.x = (mw3.mouseX - div.offset().left -22) / (div.width()/100);
			workItem.overlayCommentOption.y = (mw3.mouseY - div.offset().top -34) / (div.height()/100);
		}		

		var title = (workItem.metaworksContext.when == mw3.WHEN_EDIT) ? workItem.title :'';
		var comment = prompt('설명을 입력해주세요 : ', title);
		
		if(comment){
			workItem.rootInstId = parentWorkItem.rootInstId;
			workItem.title = comment;
			workItem.add();		
		}				
		
	}
};