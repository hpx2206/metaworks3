
var org_metaworks_ToPrepend = function(objectId, className){

	var object = mw3.objects[objectId];
	
	if(object){
		var objKeys = mw3._createObjectKey(object.parent, true);
			
		if(objKeys && objKeys.length){
						        				
			for(var i=0; i<objKeys.length; i++){
				
				var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
				
				if(mappedObjId){
					
					var html = mw3.locateObject(object.target, null);//, "#"+mappedObjdivId);
	
					$("#objDiv_" + mappedObjId).prepend(html);
	
					break;
				}	
			}
		}
		
		mw3.removeObject(objectId, true);
		mw3.onLoadFaceHelperScript();
	}

}