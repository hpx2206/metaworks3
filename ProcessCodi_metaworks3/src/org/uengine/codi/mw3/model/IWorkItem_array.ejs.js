var org_uengine_codi_mw3_model_IWorkItem_array = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.object = mw3.objects[this.objectId];
	
	this.objectDiv.bind('loadedItem', {objectId : this.objectId}, function(event, taskId, itemObjectId){
		var itemObject = mw3.objects[itemObjectId];
		
		if('ovryCmnt' != itemObject.type)
				mw3.getFaceHelper(objectId).appendOverlayComment(taskId, itemObjectId);
	});
};

org_uengine_codi_mw3_model_IWorkItem_array.prototype = {
	destroy : function(){
		this.objectDiv.unbind();	
	},
	appendOverlayComment : function(taskId, itemObjectId){
		
		for(var i=0; i<this.object.length; i++){
			if('ovryCmnt' == this.object[i].type && taskId == this.object[i].overlayCommentOption.parentTaskId){
				mw3.objects[itemObjectId].getFaceHelper().toAppend(this.object[i]);
				
				break;
			}
		}					
	}
};