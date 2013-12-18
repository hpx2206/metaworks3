var org_uengine_codi_mw3_knowledge_ProjectInfo = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
};

org_uengine_codi_mw3_knowledge_ProjectInfo.prototype = {
	callHudsonConsole : function(){
		var object = mw3.objects[this.objectId];
		object.showHudsonConsole();
	}	
};
