var org_uengine_codi_mw3_model_OrganizationPerspective = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);	
}

org_uengine_codi_mw3_model_OrganizationPerspective.prototype = {
	toAppend : function(html){
		$('#' + this.divId + ' ul').append($('<li>')).append( html );
	}
}