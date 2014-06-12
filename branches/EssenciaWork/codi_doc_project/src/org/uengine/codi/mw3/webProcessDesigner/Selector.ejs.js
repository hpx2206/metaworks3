var org_uengine_codi_mw3_webProcessDesigner_Selector = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;

	var childId = mw3.getChildObjectId(this.objectId, 'target');
	var childDivId = mw3._getObjectDivId(childId);
	
	if(this.object.type == 'tree'){
		$('#' + childDivId).bind('change', {objectId: this.objectId}, function(event){
			var treeId = $(this).attr('objectId');
			var node = mw3.getFaceHelper(treeId).getSelectedNode();
			var object = mw3.getObject(event.data.objectId);
			
			object.name = node.attr('name');
			object.value = node.attr('id');
		});
	};
};

org_uengine_codi_mw3_webProcessDesigner_Selector.prototype = {
	getValue : function(){
		var object = mw3.objects[this.objectId];
		
		object.target = null;
		
		return object;
	}
};