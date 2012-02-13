var org_uengine_codi_mw3_admin_EntityField = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
}

org_uengine_codi_mw3_admin_EntityField.prototype.edit = function(){
	// edit mode
	mw3.editObject(this.objectId, this.className);
	
	// name focus
	$("#objDiv_" + this.objectId).find("input:text:first").focus();
}

org_uengine_codi_mw3_admin_EntityField.prototype.startLoading = function(){
	$("#progress_" + this.objectId).html("loading");
}


org_uengine_codi_mw3_admin_EntityField.prototype.showError = function(message){
	$("#progress_" + this.objectId).html(message);
}
