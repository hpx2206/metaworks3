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
	
	this.objectDiv.bind('change', function(event, objectId){
	});
};

org_metaworks_component_Tree.prototype = {
	loaded : function(){
		this.objectDiv.trigger('loaded');
	},
	getClosedParentNodes : function(objectId){
		var object = mw3.objects[objectId];
		if(object.root)
			return null;
		
		var parentObjectId = this.objectDiv.find('.item-fix[nodeId='+ object.parentId+']').attr('objectId');
		
		var parentObject = mw3.objects[parentObjectId];
		
		if( parentObject.expanded ){
			return this.getClosedParentNodes(parentObject.__objectId);
		}else{
			return parentObject.__objectId;
		}
		
		/*
		var parentNode = $('.item-fix[nodeId='+ object.parentId+']');
		var parentObjectId = $(parentNode).attr('objectId');
		var parentObject = mw3.objects[parentObjectId];
		this.parentNodes.push(parentObject);
		if( parentObject.parentId ){
			this.getParentNodes(parentObject.parentId);
		}
		*/
	}
};
