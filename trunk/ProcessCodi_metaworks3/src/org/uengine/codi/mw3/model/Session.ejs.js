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

mw3.fn.getPreferUx = function(){
	var session = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
	
	if(session == null || typeof session == 'undefined')
		return null;
	
	if(session.employee == null || typeof session.employee == 'undefined')
		return null;
	
	return session.employee.preferUX;
};

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