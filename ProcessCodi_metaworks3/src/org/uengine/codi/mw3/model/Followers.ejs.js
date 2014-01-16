var org_uengine_codi_mw3_model_Followers = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
}

org_uengine_codi_mw3_model_Followers.prototype = {
	toAppend : function(appendObject) {
		$('<span>').css('float', 'left').appendTo(this.objectDiv.find('.usersAdded span:first').parent()).html(mw3.locateObject(appendObject, appendObject.__className, null));
	},
	startLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
			mw3.getFaceHelper(this.windowObjectId).startLoading();
	},
	endLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
	},
	showStatus : function(message){
		
	}
}
