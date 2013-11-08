var org_uengine_codi_mw3_model_ContactList = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
	this.divId = '#objDiv_' + this.objectId;
	
	$(this.divId).parent().parent().parent().parent().parent().css({"background":"none","margin-right":"0px"});
	//$(this.divId).parent().hover(function(){$(this).css({"overflow":"auto"})},function(){$(this).css({"overflow":"hidden"})})
	
}

