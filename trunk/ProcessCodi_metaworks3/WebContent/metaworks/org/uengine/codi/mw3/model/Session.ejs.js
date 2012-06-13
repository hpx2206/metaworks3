var org_uengine_codi_mw3_model_Session = function(objectId, className){

	var session = mw3.objects[objectId];

	setInterval(function(){session.heartbeat();}, 10000);

}
