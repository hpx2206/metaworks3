var org_uengine_codi_mw3_marketplace_Marketplace = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;	

	setCookie("codi.lastVisit", "appMapArea", 10, "/", "", "");
};