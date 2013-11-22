var org_uengine_codi_mw3_marketplace_IAppMapList = function(objectId, className){
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
	
	if( this.object && this.object.selected ){
		$("#mp_menu_list_"+ this.objectId).addClass('current');
	}

	$('#info_' + this.objectId).remove();
	
	$("#mp_menu_list_"+ this.objectId).click(function(){
		
		var object = mw3.objects[objectId];
		
		var selfServiceControlPanel = mw3.getAutowiredObject('org.uengine.codi.mw3.selfservice.SelfServiceControlPanel');
		
		selfServiceControlPanel.appId = object.appId;
		selfServiceControlPanel.appName = object.appName;
		
		if( selfServiceControlPanel.appMapping != null ){
			for(var i=0; i < selfServiceControlPanel.appMapping.length ; i++){
				var appMap = selfServiceControlPanel.appMapping[i];
				appMap.selected = false;
				if( appMap.appName == object.appName){
					appMap.selected = true;
				}
			}
		}
		mw3.call(selfServiceControlPanel.__objectId, 'selectedApp');
		
	});
};

org_uengine_codi_mw3_marketplace_IAppMapList.prototype = {
	showStatus : function(status){
	},
	startLoading : function(status){
	},
	endLoading : function(status){
	}
};

