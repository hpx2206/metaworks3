var org_uengine_codi_mw3_model_ContactListPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	
	$('#objDiv_' + this.objectId).css({'height':'100%','overflow':'hidden'}).addClass('mw3_resize').attr('objectId', this.objectId);
	$('#accordion_' + this.objectId).accordion({ fillSpace:	true });
}

org_uengine_codi_mw3_model_ContactListPanel.prototype = {
	resize : function(){
		$('#accordion_' + this.objectId).accordion('resize');
	}
}