var org_uengine_codi_mw3_model_ContactPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;

	this.divId = "objDiv_" + objectId;	
	$("#" + this.divId).css("height","100%");
	
	var layout = $("#" + this.divId).closest(".mw3_window").parent();
	
	var height = layout.css("padding-bottom");
	if(height.length){
		height = height.replace('px', '');
		
		height = Number(height);
		
		layout.css("padding-bottom", String(height + 100) + 'px');
		
	}
	
}