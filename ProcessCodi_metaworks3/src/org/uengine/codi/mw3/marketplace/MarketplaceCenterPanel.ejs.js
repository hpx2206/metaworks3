var org_uengine_codi_mw3_marketplace_MarketplaceCenterPanel = function (objectId, className) {
	
	this.objectId = objectId;
	this.className = className;
	
};


org_uengine_codi_mw3_marketplace_MarketplaceCenterPanel.prototype = {
		
		
	selectCategory: function() {
		
		var object = mw3.getObject(this.objectId);
		
		var category = object.category[0];

		object.category = category;
		object.selectCategory();
	}
		
};