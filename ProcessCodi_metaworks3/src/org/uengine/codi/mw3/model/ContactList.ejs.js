var org_uengine_codi_mw3_model_ContactList = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
	this.divId = '#objDiv_' + this.objectId;
	
	$(this.divId).parent().css({"background":"#5693d6"});
}

