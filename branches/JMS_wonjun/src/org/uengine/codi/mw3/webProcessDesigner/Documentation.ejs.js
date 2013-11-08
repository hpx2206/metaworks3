var org_uengine_codi_mw3_webProcessDesigner_Documentation = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.divObj = $('#' + this.divId);
	
	var object = mw3.objects[this.objectId];
	
};