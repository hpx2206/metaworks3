var org_uengine_codi_mw3_model_TodoBadge = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
};

org_uengine_codi_mw3_model_TodoBadge.prototype.prototype = {
	startLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
			mw3.getFaceHelper(this.windowObjectId).startLoading();
		else
			mw3.startLoading(this.objectId);
	},
	endLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
		else
			mw3.endLoading(this.objectId);
	},
	status : function(message){
		
	}
};