var org_uengine_codi_mw3_model_IWorkItem = function(objectId, className){

	var workItem = mw3.objects[objectId];
	this.type = workItem.type;
	
	if(workItem.type == null && (workItem.workItemHandler == null || workItem.workItemHandler.instanceId==null)){ //means we need to load workItemHandler
		workItem.detail();
	}

	if(workItem.type == 'memo' && workItem.extFile!=null && workItem.memo.contents=="...loading..." && !workItem.contentLoaded){
		workItem.loadContents();
	}

	if(workItem.type == 'src' && workItem.extFile!=null && !workItem.contentLoaded){
		workItem.loadContents();
	}
}
