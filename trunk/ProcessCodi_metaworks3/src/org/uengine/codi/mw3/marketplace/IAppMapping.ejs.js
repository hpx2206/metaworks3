var org_uengine_codi_mw3_marketplace_IAppMapping = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];
	
	if(this.object == null)
		return true;
	
	this.objectDiv
		.hover(function(){
				$(this).css('cursor','pointer');
			}, function(){			
				$(this).css('cursor','auto');
		});
	
	$('#info_' + this.objectId).remove();
	
	$("#mp_menu_list_"+ this.objectId).click(function(){
		
		var object = mw3.objects[objectId];
		
		var selfServiceControlPanel = mw3.getAutowiredObject('org.uengine.codi.mw3.selfservice.SelfServiceControlPanel');
		
		selfServiceControlPanel.appId = object.appId;
		mw3.call(selfServiceControlPanel.__objectId, 'selectedApp');
	});
};

org_uengine_codi_mw3_marketplace_IAppMapping.prototype = {
	showStatus : function(status){
	},
	startLoading : function(status){
	},
	endLoading : function(status){
	}
};

