var org_uengine_codi_mw3_ide_libraries_PerspectiveRole = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.object = mw3.objects[this.objectId];
	
};

org_uengine_codi_mw3_ide_libraries_PerspectiveRole.prototype = {
	toAppend : function(html){
		$('#' + this.divId + ' ul').append($('<li>')).append( html );
				
	}
	
};

	
