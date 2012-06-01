var org_uengine_codi_mw3_model_ContactListPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	
	$("#accordion_" + this.objectId).accordion({ fillSpace:	true });
}