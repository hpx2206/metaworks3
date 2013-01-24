var org_uengine_codi_mw3_model_IWorkItem_array = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.object = mw3.objects[this.objectId];
	
	this.objectDiv.bind('loadedItem', {objectId : this.objectId}, function(event, taskId, itemObjectId){
		mw3.getFaceHelper(objectId).appendOverlayComment(taskId, itemObjectId);
	});
};

org_uengine_codi_mw3_model_IWorkItem_array.prototype = {
	appendOverlayComment : function(taskId, itemObjectId){
		
		for(var i=0; i<this.object.length; i++){
			if('ovryCmnt' == this.object[i].type && taskId == this.object[i].overlayCommentOption.parentTaskId){
			    var ovryCmntTarget = {
			    	__className : 'org.uengine.codi.mw3.model.IWorkItem',
			    	taskId : taskId
			    };
				    
				var toAppend = {
					__className : 'org.metaworks.ToAppend',
					parent : ovryCmntTarget,
					target : this.object[i]
				};
				
				mw3.locateObject(toAppend, toAppend.__className, 'body');
				mw3.onLoadFaceHelperScript();
			}
		}			
	}
};