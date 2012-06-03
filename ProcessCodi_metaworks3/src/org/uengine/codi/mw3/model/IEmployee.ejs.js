var org_uengine_codi_mw3_model_IEmployee = function(objectId, className){
	var object = mw3.objects[objectId];	
	
	this.objectId = objectId;
	this.className = className;

	var theLoginHelper = this;
};

//org_forx_model_codi_IEmployee.prototype.editInfo = function(){
//	var object = mw3.objects[this.objectId];
//	//object.metaworksContext.when = mw3.WHEN_EDIT;
//	mw3.editObject(this.objectId);
//};