var org_uengine_codi_mw3_ide_libraries_SearchResultPanel = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
};

org_uengine_codi_mw3_ide_libraries_SearchResultPanel.prototype = {
	clickProcess : function(index){
		var object = mw3.objects[this.objectId];
		object.index = index;
		object.showProcess();
	},
	
	clickActivity : function(index) {
		var object = mw3.objects[this.objectId];
		object.index = index;
		object.showActivity();
	},
	
	clickRole : function(index) {
		var object = mw3.objects[this.objectId];
		object.index = index;
		object.showRole();
	}
};