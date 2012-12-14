var org_metaworks_component_Tree = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	this.objectDiv.addClass('filemgr-tree').addClass('filemgr-treeFocus').addClass(this.object.align);
	this.objectDiv.css({'border-width': '0px',
						'left': '0px', 
						'top': '0px',
						'right': '0px',
						'bottom': '0px',
						'min-width': '0px',
						'min-height': '0px',
						'max-width': '10000px',
						'max-height': '10000px'});
	
	// scroll bar
	this.objectDiv.mCustomScrollbar();
	this.objectDiv.bind('change', function(event, objectId){
		var node = mw3.getObject(objectId);
	});
};

