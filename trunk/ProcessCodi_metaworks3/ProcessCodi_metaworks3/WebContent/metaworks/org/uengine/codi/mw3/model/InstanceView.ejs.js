var org_uengine_codi_mw3_model_InstanceView = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	var posting = [];
	
	var postIds = object.crowdSourcer.postIds;	
	var cnt = 0;
	
	for(var i=0; i<postIds.length; i++){
		var num = 20;
		 
		FB.api(postIds[i], {
			limit : num
		}, function(response) {
			if (!response || response.error) {
				//console.debug(response);
				
				if(typeof response.error != "undefined")
					alert("error : " + response.error.message);
				
			} else {
				for(var j=0; j<response.comments.count; j++){
					var comment = response.comments.data[j];
					var user = {
							userId : comment.from.id,
							name : comment.from.name,
							__className : "org.uengine.codi.mw3.model.IUser"
					};
					
					posting[cnt] = {    type : "postings",
							           title : comment.message,
							        endpoint : comment.from.id,
							          writer : user,
							     __className : "org.uengine.codi.mw3.model.PostingsWorkItem"
							  
					  };
					
					cnt++;					
				}
				
				if(i == postIds.length){
					mw3.setObject(object.threadPosting.__objectId, posting);
				}
			}
		});	
	}
}
