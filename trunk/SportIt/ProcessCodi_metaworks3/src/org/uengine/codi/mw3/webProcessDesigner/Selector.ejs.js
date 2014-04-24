var org_uengine_codi_mw3_webProcessDesigner_Selector = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;

	var childId = mw3.getChildObjectId(this.objectId, 'target');
	var childDivId = mw3._getObjectDivId(childId);
	
};

org_uengine_codi_mw3_webProcessDesigner_Selector.prototype = {
	toOpener : function(target){
		var object = mw3.objects[this.objectId];
        object.target = target;
	}
};