var org_uengine_codi_mw3_processexplorer_DocumentNavigatorPanel = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
};

org_uengine_codi_mw3_processexplorer_DocumentNavigatorPanel.prototype = {
	clickNavigator : function(index){
		var object = mw3.objects[this.objectId];
		var documentList = object.documentList[index];
				
		object.id = documentList.id;		
		object.parentid = documentList.parentid;
		object.name = documentList.name;
		object.index = index;
		object.changeDocumentPanel();
	}
};