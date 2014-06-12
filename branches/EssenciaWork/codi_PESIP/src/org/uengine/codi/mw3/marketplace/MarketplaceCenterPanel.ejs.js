var org_uengine_codi_mw3_marketplace_MarketplaceCenterPanel = function (objectId, className) {
	
	this.objectId = objectId;
	this.className = className;
	
	var slideNum = 0;
	var session = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
	if(session && session.lastSelectedItem)
		slideNum = session.lastSelectedItem;
	
	//{startingSlide: 4}
	$('#slideshow_' + this.objectId)
		.cycle({startingSlide: slideNum})
		.on('cycle-before', function(event, optionHash, outgoingSlideEl, incomingSlideEl, forwardFlag){
			var appList = mw3.getAutowiredObject('org.uengine.codi.mw3.marketplace.AppList');			
			appList.categoryId = optionHash.slideNum - 1;
			appList.load();
		});
	
};

org_uengine_codi_mw3_marketplace_MarketplaceCenterPanel.prototype = {
	destroy : function(){
		$('.mp_left_menu li').unbind('click');
	},
		
	selectCategory: function() {
		
		var object = mw3.getObject(this.objectId);
		
		var category = object.category[0];

		object.category = category;
		object.selectCategory();
	}
		
};