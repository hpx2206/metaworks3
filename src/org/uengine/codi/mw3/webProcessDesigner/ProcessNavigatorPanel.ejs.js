var org_uengine_codi_mw3_webProcessDesigner_ProcessNavigatorPanel = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
};

org_uengine_codi_mw3_webProcessDesigner_ProcessNavigatorPanel.prototype = {
	clickNavigator : function(index){
		var object = mw3.objects[this.objectId];
		var historyItem = object.historyList[index];
				
		object.defId = historyItem.defId;		
		object.alias = historyItem.alias;
		object.index = index;
		object.changeViewPanel();
	}
};