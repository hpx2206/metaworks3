var org_uengine_codi_mw3_marketplace_IListing = function(objectId, className){
	
};

org_uengine_codi_mw3_marketplace_IListing.prototype = {
		
		showDescription: function(){
			$("#tab_Vendor").css('display', 'none');
			$("#tab_Description").css('display', 'block');
		},
		
		showVendor: function(){
			$("#tab_Vendor").css('display', 'block');
			$("#tab_Description").css('display', 'none');
		}
		
};