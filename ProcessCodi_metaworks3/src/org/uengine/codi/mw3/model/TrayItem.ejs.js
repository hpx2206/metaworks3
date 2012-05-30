var org_uengine_codi_mw3_model_TrayItem = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
	this.divId = '#objDiv_' + this.objectId;
	
	
}

org_uengine_codi_mw3_model_TrayItem.prototype = {
	openChat : function(){
		$(this.divId + ' .topcontentcontainer').show().width(500);
		
		mw3.call(this.objectId, 'open');
	}
}
