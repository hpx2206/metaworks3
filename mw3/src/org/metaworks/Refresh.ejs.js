var org_metaworks_Refresh = function(objectId, className){
	
	var object = mw3.objects[objectId];
	
	if(object){
		mw3.removeObject(objectId);
		
		var objKeys = mw3._createObjectKey(object.target, true);
			
		if(objKeys && objKeys.length){
						        				
			for(var i=0; i<objKeys.length; i++){
				
				var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
	
				if(mappedObjId){
					var faceHelper = mw3.getFaceHelper(mappedObjId);
					
					if(faceHelper && faceHelper.refresh){
						var html = mw3.locateObject(object.target, null);		
						
						faceHelper.refresh(html, object.target);
					}else{
						mw3.setObject(mappedObjId, object.target);	
					}							
								
					break;
				}
				
			}
		}
		mw3.onLoadFaceHelperScript();
	}	
};