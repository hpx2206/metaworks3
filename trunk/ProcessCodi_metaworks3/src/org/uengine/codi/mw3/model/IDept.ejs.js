var org_uengine_codi_mw3_model_IDept = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

org_uengine_codi_mw3_model_IDept.prototype = {
		startLoading : function(){
			if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
				mw3.getFaceHelper(this.windowObjectId).startLoading();
		},
		endLoading : function(){
			if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
				mw3.getFaceHelper(this.windowObjectId).endLoading();
		},
		destroy : function(){
			if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
				mw3.getFaceHelper(this.windowObjectId).endLoading();
		},
		showStatus : function(message){
			
		}
	}
