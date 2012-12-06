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

	this.jobId = jobId;
}

org_uengine_codi_mw3_model_Session.prototype = {
	destroy : function(){
		clearInterval(this.jobId);
	}
}

/*
function loadTest(){
	for(var i=0; i<10; i++){
		var newInst = window.open(window.location, 'popup'+i, "");
		
		var jobId = setInterval(function(){
			try{newInst.test(); clearInterval(jobId);}catch(e){}
		}, 2000)
	}
}

function test(){
	mw3.startTest('testScenario');
}
*/