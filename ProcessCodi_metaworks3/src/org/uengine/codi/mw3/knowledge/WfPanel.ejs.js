var org_uengine_codi_mw3_knowledge_WfPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	
	$('#' + this.divId).addClass('workflowy');
	
	rootNodeId = mw3.objects[objectId].rootNodeId;
	
	var object = mw3.objects[this.objectId];
	
	if(!object.first){
		setTimeout(function(){
			mw3.call(objectId, 'load');
		},1);
	}
}

org_uengine_codi_mw3_knowledge_WfPanel.prototype = {
	startLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
			mw3.getFaceHelper(this.windowObjectId).startLoading();
	},
	endLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
	},
	destroy : function(){
		this.endLoading();
	}	
}

var rootNodeId;
