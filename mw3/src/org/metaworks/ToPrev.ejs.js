
var org_metaworks_ToPrev = function(objectId, className){

	var object = mw3.objects[objectId];
	
	if(object){
		mw3.removeObject(objectId);
		
		var objKeys = mw3._createObjectKey(object.next, true);
			
		if(objKeys && objKeys.length){
						        				
			for(var i=0; i<objKeys.length; i++){
				
				var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
				if(mappedObjId){
					
					var html = mw3.locateObject(object.target, null);//, "#"+mappedObjdivId);
	
					$(html).insertBefore("#objDiv_" + mappedObjId);
	
					break;
				}	
			}
		}
		
		mw3.onLoadFaceHelperScript();
	}

}