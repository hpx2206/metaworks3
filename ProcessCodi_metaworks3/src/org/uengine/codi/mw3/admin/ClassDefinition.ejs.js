var tryOnce = false;
var org_uengine_codi_mw3_admin_ClassDefinition = function(objectId, className){

	//this.objectId = objectId;
	
	var object = mw3.objects[objectId];


	if(!tryOnce && object.alias == null)
		setTimeout("mw3.test(" +objectId + ", 'ClassDefinition', {guidedTour: true})", 1000);

	tryOnce = true;
}