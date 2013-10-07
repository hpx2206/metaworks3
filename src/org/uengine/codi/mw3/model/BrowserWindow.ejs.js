var org_uengine_codi_mw3_model_BrowserWindow = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var browserWindow = mw3.objects[objectId];
	
	window.open(mw3.base + "tester.html?className=" + browserWindow.className);
}
