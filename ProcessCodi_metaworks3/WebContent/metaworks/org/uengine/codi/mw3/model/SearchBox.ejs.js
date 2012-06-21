var org_uengine_codi_mw3_model_SearchBox = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	
	this.timeout;
	
	var object = mw3.objects[this.objectId];
	if(object && object.__descriptor){
		this.isKeyupSearch = object.__descriptor.getOptionValue("keyupSearch");
		this.isEnterSearch = object.__descriptor.getOptionValue("enterSearch");
	}
	
	if(object && object.keyword)
		this.keyword = object.keyword;

	$("#search_" + this.objectId).bind('keyup', function(event){
		mw3.getFaceHelper(objectId).keyup(event, this);
	});
	$("#search_" + this.objectId).focus();
}

org_uengine_codi_mw3_model_SearchBox.prototype = {
	getValue : function() {
		var object = mw3.objects[this.objectId];

		object.keyword = $("#search_" + this.objectId).val();

		return object;
	},
	keyup : function(e, element) {
		var keyword = element.value;
		var objectId = this.objectId;
		
		if(this.isKeyupSearch){
			if(this.keyword == keyword)
				return false;
			
			this.keyword = keyword;
			
			if (this.timeout) {
				clearTimeout(this.timeout);
			}
			
			this.timeout = setTimeout(function() {
				mw3.call(objectId, 'search');
			}, 1500);
		}else if(this.isEnterSearch){
			if(e.keyCode == 13){	// key return
				window.event.returnValue = false;
				
				mw3.call(objectId, 'search');
			} 
			
		}
	},
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
	},	
	showStatus : function(message){
		
	}	
}
