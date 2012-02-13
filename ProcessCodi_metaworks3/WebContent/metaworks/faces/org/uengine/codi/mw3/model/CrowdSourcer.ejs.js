var org_uengine_codi_mw3_model_CrowdSourcer = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	
	if(!object.open || object.postIds == null){
		// overrides the function
		object.crowdSourcing = function() {
			var url = window.location;//$.url();
//			var instanceUrl = url.data.attr.base + url.data.attr.directory + "instanceView.html?instanceId=" + object.instanceId;
			var instanceUrl = url.origin + url.pathname + "instanceView.html?instanceId=" + object.instanceId;
			
			var message = object.message + "\n " + instanceUrl;
			var followers = object.followers.followers;
			
			for ( var i = 0; i < followers.length; i++) {
				var feed = followers[i].userId + "/feed";
				
				//feed = "100002899287992/feed";
				
				var cnt = 0;
				var postIds = new Array();
	
				FB.api(feed, 'post', {
					message : message
				}, function(response) {				
					cnt++;
	
					if (!response || response.error) {
						alert("error : " + response.error.message);
					} else {
						postIds.push(response.id);
					}
									
					if (cnt == followers.length) {
						if (postIds.length > 0) {
							object.postIds = postIds;
							mw3.call(objectId, 'crowdSourcing');							
						}
					}
				});
			}	
		}
	}	
}