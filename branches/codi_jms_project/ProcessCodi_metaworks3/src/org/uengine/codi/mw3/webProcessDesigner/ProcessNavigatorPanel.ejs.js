var org_uengine_codi_mw3_webProcessDesigner_ProcessNavigatorPanel = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
};

org_uengine_codi_mw3_webProcessDesigner_ProcessNavigatorPanel.prototype = {
	clickNavigator : function(defId, alias){
		
		alert(alias)

		var object = mw3.objects[this.objectId];
				
		object.defId = defId;		
		object.alias = alias;
		object.changeViewPanel();
	}
};