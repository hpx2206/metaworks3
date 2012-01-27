var org_uengine_codi_mw3_model_IUser = function(objectId, className) {
	console.debug("org_uengine_codi_mw3_model_IUser");
	
	this.objectId = objectId;
	this.className = className;
	
	var userId = $("#userId_" + objectId).val();
	
	if (typeof userId != "undefined"){		
		$("#userPicture_" + objectId).attr("src", "http://graph.facebook.com/" + userId + "/picture");
	}
}