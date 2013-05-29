var org_metaworks_website_OpenBrowser = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	var url = object.openUrl;

	window.open(url);
	
};
