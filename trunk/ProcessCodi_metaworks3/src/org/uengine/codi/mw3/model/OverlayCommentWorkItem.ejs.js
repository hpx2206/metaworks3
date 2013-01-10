var org_uengine_codi_mw3_model_OverlayCommentWorkItem = function(objectId, className){
	
	var workItem = mw3.objects[objectId];
	var parentWorkItem = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IWorkItem@"+ workItem.overlayCommentOption.parentTaskId);
	
	var div = $("#objDiv_" + parentWorkItem.__objectId);
	
	if(workItem.taskId==null){
		workItem.overlayCommentOption.x = mw3.mouseX - div.offset().left;
		workItem.overlayCommentOption.y = mw3.mouseY - div.offset().top - 30;

		var comment = prompt('Enter Comment : ');
		
		workItem.title = comment;
		workItem.add();
				
	}

	
}