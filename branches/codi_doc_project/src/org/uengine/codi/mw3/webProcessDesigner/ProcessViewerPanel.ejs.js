var org_uengine_codi_mw3_webProcessDesigner_ProcessViewerPanel = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	$(this.divId).css({"position":"relative","height":"100%"});
	
};

org_uengine_codi_mw3_webProcessDesigner_ProcessViewerPanel.prototype = {
		
};