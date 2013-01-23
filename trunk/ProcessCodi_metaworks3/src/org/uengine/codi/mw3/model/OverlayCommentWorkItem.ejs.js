var org_uengine_codi_mw3_model_OverlayCommentWorkItem = function(objectId, className){
	
	var workItem = mw3.objects[objectId];
	var parentWorkItem = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IWorkItem@"+ workItem.overlayCommentOption.parentTaskId);
	
	
	//var div = $("#objDiv_" + parentWorkItem.__objectId);
	var div = $("#objDiv_" + parentWorkItem.__objectId).find('.view_box3:first .cont img');
	
	if(workItem.taskId==null){
	//	workItem.overlayCommentOption.x = mw3.mouseX - div.offset().left - 30; /*  (mw3.mouseX - div.offset().left - 30) / (div.parent().width()/100) = 백분율 좌표 */
	//	workItem.overlayCommentOption.y = mw3.mouseY - div.offset().top - 34;
		
		console.debug(mw3.mouseX - div.offset().left)
		workItem.overlayCommentOption.x = (mw3.mouseX - div.offset().left -22) / (div.width()/100)
		workItem.overlayCommentOption.y = mw3.mouseY - div.offset().top - 34;

		var comment = prompt('Enter Comment : ');
		
		workItem.title = comment;
		workItem.add();
				
	}

	
}