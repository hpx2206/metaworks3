var org_uengine_codi_mw3_webProcessDesigner_ProcessViewWindow = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
		
	$(this.divId).parent().css({"position":"relative","height":"100%"});
	
};
