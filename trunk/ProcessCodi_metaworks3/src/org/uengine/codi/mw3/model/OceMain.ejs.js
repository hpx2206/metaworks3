var org_uengine_codi_mw3_model_OceMain = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];

 	$('#' + mw3._getObjectDivId(this.objectId)).on('loaded_app', {appId: this.object.appId}, function(event, eventAppId, eventDivId){
	  	if(event.data.appId == eventAppId){
	   		$('#' + eventDivId + ' li').effect('bounce', 250);
	  	}
	});
 	
};