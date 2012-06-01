var org_uengine_codi_mw3_model_UnstructuredProcessInstanceStarter = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	mw3.getInputElement(this.objectId, 'title').focus();	
}