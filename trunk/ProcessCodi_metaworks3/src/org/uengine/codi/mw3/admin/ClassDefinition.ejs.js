var tryClassDefinition = false;
var tryRunning = false;

var org_uengine_codi_mw3_admin_ClassDefinition = function(objectId, className){

	//this.objectId = objectId;
	
	var object = mw3.objects[objectId];


	if(!tryClassDefinition && object.alias == null){
		setTimeout("mw3.test(" +objectId + ", 'ClassDefinition', {guidedTour: true})", 1000);

		tryClassDefinition = true;
	}
	
	if(!tryRunning && object.alias != null){
		setTimeout("mw3.test(" +objectId + ", 'run()', {guidedTour: true})", 1000);

		tryRunning = true;
	}
}