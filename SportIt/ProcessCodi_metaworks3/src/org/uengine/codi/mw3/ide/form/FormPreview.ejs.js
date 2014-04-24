var org_uengine_codi_mw3_ide_form_FormPreview = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	if($('#' + this.objectDivId).find('.formpreview_inner').height() > $('#' + this.objectDivId).parent().height()){
		
	}else{
		this.objectDiv.css("height","100%");
	}
	
	
	
};
