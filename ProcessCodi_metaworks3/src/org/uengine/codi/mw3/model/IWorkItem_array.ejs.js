var org_uengine_codi_mw3_model_IWorkItem_array = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.object = mw3.objects[this.objectId];
	
	for(var i=0; i<this.object.length; i++){
		var workItem = this.object[i];
		
		if(workItem.type == null && (workItem.workItemHandler == null || workItem.workItemHandler.instanceId==null) && workItem.status == 'NEW' && workItem.tool != 'formApprovalHandler')
			workItem.detail();
		
		if(workItem){
			if(workItem.type == 'ovryCmnt'){
				this.objectDiv.one('loaded.workitem_' + workItem.overlayCommentOption.parentTaskId, {workItem: workItem}, function(event){				
					var workItem = event.data.workItem;
					
					var ovryCmntTarget = {
				    	__className : 'org.uengine.codi.mw3.model.IWorkItem',
				    	taskId : workItem.overlayCommentOption.parentTaskId
				    };
				    
					var toAppend = {
						__className : 'org.metaworks.ToAppend',
						parent : ovryCmntTarget,
						target : workItem,
						match : true
					};
					
					mw3.locateObject(toAppend, toAppend.__className, 'body');
					
					mw3.onLoadFaceHelperScript();
				});
			}
		}
	}
};