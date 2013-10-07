var org_uengine_codi_mw3_webProcessDesigner_ConditionTreeNodeView = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
};

org_uengine_codi_mw3_webProcessDesigner_ConditionTreeNodeView.prototype = {
		getValue : function(){
			var object = mw3.objects[this.objectId];
			return object;
		}
};