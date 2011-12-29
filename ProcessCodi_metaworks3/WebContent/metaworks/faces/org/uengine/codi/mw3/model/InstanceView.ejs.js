mw3.importStyle("style/waveStyle/wih.css");

var org_uengine_codi_mw3_model_InstanceView = function(objectId, className){
	this.objectId = objectId;
	
	var object = mw3.getObject(objectId);
	
	//overrides the function
	object.crowdSourcing = function(){
		FB.api( 
				'/me', 
				function(response) {
					loggedUserFacebookId = response.id;
					
				    var facebookFeed =  loggedUserFacebookId + "/feed";
				    
				    FB.api(facebookFeed, 'post', { message: 'crowd sourcing test for process instance id ' + object.instanceId }, function(response) {
				        if (!response || response.error) {
				        	alert("failed due to " + response.error.message);
				        } else {
	 			        	//node.facebookId = response.id;
	 			        	
//	 			        	setInterval("addFacebookNode( '"+node.id+"', '" + node.facebookId +"')", "10000");
	 			        	
	 			        	alert("답벼락에 게시되었습니다!");
				        }
				    });

				} 
		);				
	
	}
}
