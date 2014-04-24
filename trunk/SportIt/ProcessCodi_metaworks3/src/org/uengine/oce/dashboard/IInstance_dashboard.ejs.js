var org_uengine_oce_dashboard_IInstance_dashboard = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];
	
	$('.tx_box_obj').hover(function(){
		$(this).css('cursor','pointer');
	}, function(){			
		$(this).css('cursor','auto');
});
	
};

org_uengine_oce_dashboard_IInstance_dashboard.prototype = {
	startLoading : function(){

	},
	endLoading : function(){
	
	},
	showStatus : function(message){

	}
};