
var org_metaworks_ToAppend = function(objectId, className){

	var object = mw3.objects[objectId];
	
	if(object){
		mw3.removeObject(objectId);
		
		var objKeys = mw3._createObjectKey(object.parent, true);
			
		if(objKeys && objKeys.length){
						        				
			for(var i=0; i<objKeys.length; i++){
				
				var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
				
				if(mappedObjId){
					
					var html = mw3.locateObject(object.target, null);//, "#"+mappedObjdivId);					
					var faceHelper = mw3.getFaceHelper(mappedObjId);
					
					if(faceHelper && faceHelper.toAppend){
						faceHelper.toAppend(html, object.target);
					}else{
						$("#objDiv_" + mappedObjId).append(html);	
					}					
					
					break;
				}	
			}
		}
				
		mw3.onLoadFaceHelperScript();
	}

}