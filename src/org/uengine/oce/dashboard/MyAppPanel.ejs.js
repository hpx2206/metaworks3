var org_uengine_oce_dashboard_MyAppPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
};

org_uengine_oce_dashboard_MyAppPanel.prototype = {
	startLoading : function(){
		//Do Something...
	},
	
	endLoading : function(){
		//Do Something...
	},
	
	showStatus : function(message){
		//Do Something...
	}
	
};