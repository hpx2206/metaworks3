var org_uengine_codi_mw3_model_Session = function(objectId, className){

	var session = mw3.objects[objectId];

	var jobId = setInterval(function(){
		
		if(!mw3.objects[session.__objectId]){
			clearInterval(jobId);
			
			return;
		}
		
		var messageFromServer = session.heartbeat();
	
		if(messageFromServer){
			
			mw3.locateObject(messageFromServer, null, "body");
			
		}
		
	}, 10000);

}