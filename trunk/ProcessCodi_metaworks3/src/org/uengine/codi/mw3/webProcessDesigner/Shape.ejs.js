var org_uengine_codi_mw3_webProcessDesigner_Shape = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var thisObject = mw3.objects[this.objectId];
	if( thisObject != null){
		if (console && console.log) console.log(thisObject.element);
	}
};