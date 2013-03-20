var org_uengine_codi_mw3_ide_CloudWindow = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	this.objectDiv.css('height', '100%');
};

org_uengine_codi_mw3_ide_CloudWindow.prototype = {
	toAppend : function(object){
		this.objectDiv.find('.contentcontainer:first').append(mw3.locateObject(object, null));
	}
};