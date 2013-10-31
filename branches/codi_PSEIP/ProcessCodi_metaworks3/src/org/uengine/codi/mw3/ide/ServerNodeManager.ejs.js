var org_uengine_codi_mw3_ide_ServerNodeManager = function(objectId, className){

	var object = mw3.objects[objectId];
	
	if(object){
		mw3.removeObject(objectId);
		
		if(object.target && object.parent){
			var objKeys = mw3._createObjectKey(object.parent, true);
			
			if(objKeys && objKeys.length){
							        				
				for(var i=0; i<objKeys.length; i++){
					var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
					
					if(mappedObjId){
						var node = mw3.getObject(mappedObjId);
						var nodeFaceHelper = node.getFaceHelper();
						
						if(nodeFaceHelper.changeName){
							nodeFaceHelper.changeName(node.id + ' [' + object.target + ', Synchronized]');
						}
						
						setTimeout(function(){
							nodeFaceHelper.changeName(node.id + ' [Started]');
						}, 5000);
						
						break;
					}	
				}
			}
		}
	}
};