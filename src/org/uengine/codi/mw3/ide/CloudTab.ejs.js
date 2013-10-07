var org_uengine_codi_mw3_ide_CloudTab = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
};

org_uengine_codi_mw3_ide_CloudTab.prototype = {
	remover : function(removerObject){
		this.remove();
	},
	
	remove : function(event){
		event.stopPropagation();
		
		this.objectDiv.trigger('removetab');
	},
	
	select : function(){
		this.objectDiv.trigger('selecttab');
	},
	
	destroy : function(){
		this.objectDiv.parent().remove();
	}
};