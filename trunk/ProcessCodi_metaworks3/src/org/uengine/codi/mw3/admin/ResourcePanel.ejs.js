var org_uengine_codi_mw3_admin_ResourcePanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = "#objDiv_" + objectId;
	
	$(this.divId).attr('objectId', objectId);
};