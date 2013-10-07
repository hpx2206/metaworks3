var org_uengine_codi_mw3_model_IDept = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];
	
};

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
		if(message){
			var message_split = message.split(' ');
			
			if(message_split[0] == 'drillDown'){
				$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
				$('.idept').removeClass('selected_navi2');
				$('.iemployee').removeClass('selected_navi2');
				this.objectDiv.find('a:first').addClass('selected_navi2');
			}
		}
	}
};
