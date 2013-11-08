var org_uengine_codi_mw3_model_ContactPerspective = function(objectId, className) {

	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.objectDiv.hover(
			function(){
				$(this).find('.west_more_btn').show();
			},
			function(){
				$(this).find('.west_more_btn').hide();
			}
		)
	
	
	
};
