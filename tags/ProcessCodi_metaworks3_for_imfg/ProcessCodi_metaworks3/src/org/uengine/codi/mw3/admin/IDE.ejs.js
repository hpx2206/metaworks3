var org_uengine_codi_mw3_admin_IDE = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	$("#objDiv_" + objectId).css("height", "100%");
	
	setCookie("codi.lastVisit", "ide", 10, "/", "", "");
}