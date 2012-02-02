var org_uengine_codi_mw3_model_InstanceListPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;

	this.divId = "objDiv_" + objectId;
	
	$("#" + this.divId).css("height","100%");
	
}