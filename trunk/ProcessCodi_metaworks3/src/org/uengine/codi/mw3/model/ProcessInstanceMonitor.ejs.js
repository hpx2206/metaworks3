var org_uengine_codi_mw3_model_ProcessInstanceMonitor = function(objectId, className) {
	
	var object = mw3.objects[objectId];	
	
	this.objectId = objectId;
	this.className = className;
	
	var faceHelper = this;
	
	cleanAll();
	drawAll();
	
}

org_uengine_codi_mw3_model_ProcessInstanceMonitor.prototype.destroy = function() {
	var object = mw3.getObject(this.objectId);
	
	//alert("destroy" + this.objectId);
	$("#divDrawAreainstance" + object.instanceId).remove();
	
}