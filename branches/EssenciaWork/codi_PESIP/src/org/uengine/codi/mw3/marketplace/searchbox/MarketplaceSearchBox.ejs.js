var org_uengine_codi_mw3_marketplace_searchbox_MarketplaceSearchBox = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;

	$(mw3.getInputElement(this.objectId, 'keyword')).bind('keyup', {objectId: this.objectId}, function(event){
		mw3.getFaceHelper(event.data.objectId).keyup(event);
	});
};

org_uengine_codi_mw3_marketplace_searchbox_MarketplaceSearchBox.prototype = {
	keyup : function(e) {
		if(e.keyCode == 13){
			window.event.returnValue = false;
			
			mw3.call(this.objectId, 'search');
		} 
	}
};