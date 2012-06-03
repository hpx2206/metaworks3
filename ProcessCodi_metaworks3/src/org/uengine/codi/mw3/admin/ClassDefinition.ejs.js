var tryClassDefinition = false;
var tryRunning = false;

var org_uengine_codi_mw3_admin_ClassDefinition = function(objectId, className){
	this.objectId = objectId;
	this.classname = className;
	this.divId = 'objDiv_' + this.objectId;
	
	var object = mw3.objects[objectId];
	
	$('#' + this.divId).css("height", "100%");
	
	if(object.className != null){
		var windowObjId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
		
		if(windowObjId){
			mw3.getFaceHelper(windowObjId).setTitle(object.className + '.java');
		}
	}
	

	/*
	if(!tryClassDefinition && object.alias == null){
		setTimeout("mw3.test(" +objectId + ", 'ClassDefinition', {guidedTour: true})", 1000);

		tryClassDefinition = true;
	}
	
	if(!tryRunning && object.alias != null){
		setTimeout("mw3.test(" +objectId + ", 'run()', {guidedTour: true})", 1000);

		tryRunning = true;
	}
	*/
}