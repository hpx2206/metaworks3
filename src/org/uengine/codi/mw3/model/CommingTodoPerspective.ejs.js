var org_uengine_codi_mw3_model_CommingTodoPerspective = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	this.divId = mw3._getObjectDivId(this.objectId);
	this.div = $('#' + this.divId);		

	if(this.object.loader)
		mw3.call(this.objectId, 'select');
};
