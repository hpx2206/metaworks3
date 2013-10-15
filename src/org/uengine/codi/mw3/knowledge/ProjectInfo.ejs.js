var org_uengine_codi_mw3_knowledge_ProjectInfo = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
};

org_uengine_codi_mw3_knowledge_ProjectInfo.prototype = {
		getValue : function(){
			
			console.log('dddddddddddddddd');
			
			var object = mw3.objects[this.objectId];
			console.log(object);
			
			return object;
		}
}