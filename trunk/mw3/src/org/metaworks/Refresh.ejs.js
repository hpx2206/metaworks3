var org_metaworks_Refresh = function(objectId, className){
	
	var object = mw3.objects[objectId];
	
	if(object){
		var objKeys = mw3._createObjectKey(object.target, true);
			
		if(objKeys && objKeys.length){
						        				
			for(var i=0; i<objKeys.length; i++){
				
				var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
	
				if(mappedObjId){
					mw3.setObject(mappedObjId, object.target);				
					break;
				}
				
			}
		}
		
		mw3.removeObject(objectId, true);	
		mw3.onLoadFaceHelperScript();
	}	
}