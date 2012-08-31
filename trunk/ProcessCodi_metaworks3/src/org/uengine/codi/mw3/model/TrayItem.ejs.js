var org_uengine_codi_mw3_model_TrayItem = function(objectId, className){

	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	
	//alert($(this.divId).closest('#trayDiv').width());
	
	// $(this.divId+' #sm01').width();
	var object = mw3.objects[this.objectId];
	
	if(object && object.metaworksContext && object.metaworksContext.how == 'down'){
		$(this.divId + ' .topcontentcontainer').show().width(500);
		
		var layoutId = $(this.divId).closest('.mw3_layout').attr('objectId');
		
		mw3.getFaceHelper(layoutId).layout.allowOverflow('north');		
	}
}