var org_uengine_codi_mw3_model_ContactListPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = mw3._getObjectDIvId(this.objectId);
	
	$("#accordion_" + this.objectId).accordion({ fillSpace:	true });
}