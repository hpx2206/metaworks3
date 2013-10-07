var org_uengine_codi_mw3_marketplace_IApp = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	
	var object = mw3.objects[this.objectId];
	
	var when = object.metaworksContext.when;
	var appName = object.appName;
	
	if(when=="newApps"){
		object.appName = (appName.length < 30)? appName: appName.substring(0,30) + " ...";
		
	}
	
};

org_uengine_codi_mw3_marketplace_IApp.prototype = {
		
		showDescription: function(){
			$("#tab_Vendor").css('display', 'none');
			$("#tab_Description").css('display', 'block');
		},
		
		showVendor: function(){
			$("#tab_Vendor").css('display', 'block');
			$("#tab_Description").css('display', 'none');
		}
		
		
};