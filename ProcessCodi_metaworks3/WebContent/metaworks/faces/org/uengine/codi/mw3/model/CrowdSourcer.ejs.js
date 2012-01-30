var org_uengine_codi_mw3_model_CrowdSourcer = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	// overrides the function
	object.crowdSourcing = function() {
		var followers = object.followers.followers;

		for ( var i = 0; i < followers.length; i++) {
			console.debug(followers[i].userId);
			console.debug(followers[i].name);

			var feed = followers[i].userId + "/feed";
			var cnt = 0;
			var postIds = new Array();

			FB.api(feed, 'post', {
				message : 'crowd sourcing test for process instance id '
						+ object.instanceId
			}, function(response) {
				
				for (var i in response){
					console.debug("obj : " + i);
				}
					
				cnt++;

				if (!response || response.error) {
					console.debug("error : " + response.error.message);
				} else {
					postIds.push(response.id);
				}
								
				if (cnt == followers.length) {
					if (postIds.length > 0) {
						console.debug("postIds : " + postIds);

						object.postIds = postIds;
						mw3.call(objectId, 'crowdSourcing');
					}
				}
			});
		}	
	}
}