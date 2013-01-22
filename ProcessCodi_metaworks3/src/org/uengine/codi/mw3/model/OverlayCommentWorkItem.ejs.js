var org_uengine_codi_mw3_model_OverlayCommentWorkItem = function(objectId, className){
	
	var workItem = mw3.objects[objectId];
	var parentWorkItem = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IWorkItem@"+ workItem.overlayCommentOption.parentTaskId);
	
	var div = $("#objDiv_" + parentWorkItem.__objectId).find('.view_box3:first');
	
	console.log(div);
	
	if(workItem.taskId==null){
			console.log(2222);
		//workItem.overlayCommentOption.x = mw3.mouseX - div.offset().left - 30; /*  (mw3.mouseX - div.offset().left - 30) / (div.parent().width()/100) = 백분율 좌표 */
		//workItem.overlayCommentOption.y = mw3.mouseY - div.offset().top - 34;
		workItem.overlayCommentOption.x = (mw3.mouseX - div.offset().left) / (div.width()/100);		
		workItem.overlayCommentOption.y = (mw3.mouseY - div.offset().top) / (div.height()/100);

		var comment = prompt('Enter Comment : ');
		
		workItem.title = comment;
		workItem.add();
	}
};