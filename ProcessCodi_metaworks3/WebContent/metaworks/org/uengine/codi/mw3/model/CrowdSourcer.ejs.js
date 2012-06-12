var org_uengine_codi_mw3_model_CrowdSourcer = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
}

org_uengine_codi_mw3_model_CrowdSourcer.prototype = {
	posting : function(){
		var object = mw3.objects[this.objectId];
		
		var url = window.location;
		var instanceUrl = url.origin + url.pathname + "index.html?instanceId=" + object.instanceId;		
		var message = object.message + "\n " + instanceUrl;
		
		var followers = object.followers.followers;
		var facebookFriends = [];
		
		for ( var i=0; i < followers.length; i++) {			
			if(followers[i].network == 'fb')
				facebookFriends.push(followers[i]);
		}
		
		if(facebookFriends.length > 0 ){
				
			for ( var i=0; i < facebookFriends.length; i++) {
					var feed = facebookFriends[i].userId + "/feed";
					
					console.debug(feed);
					
					var cnt = 0;
					var postIds = new Array();
		
					FB.api(feed, 
						   'post', 
						   {message : message}, 
						   function(response) {				
								cnt++;	
				
								if (!response || response.error) {
									if(window.console)
										console.log("error : " + response.error.message);
								} else {
									postIds.push(response.id);
								}
												
								if (cnt == facebookFriends.length) {
									if (postIds.length > 0) {
										object.postIds = postIds;
										
										mw3.call(object.__objectId, 'crowdSourcing');							
									}
								}
						   });
			}	
		}else{
			mw3.call(this.objectId, 'crowdSourcing');	
		}
			
	}
}