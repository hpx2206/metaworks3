var org_uengine_codi_mw3_marketplace_AppMapPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.objectDiv.parent().css({'background':'url(images/marketPlace/bg.jpg) center bottom no-repeat #fff'})
	
};
