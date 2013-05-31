
var org_metaworks_ToAppend = function(objectId, className){
	
	var object = mw3.objects[objectId];

	if(object){
		mw3.removeObject(objectId);
		
		if(object.target && object.parent){
			var objKeys = [];
			
			if(object.match)
				objKeys.push(mw3._createObjectKey(object.parent));
			else
				objKeys = mw3._createObjectKey(object.parent, true);

			if(objKeys && objKeys.length){
							        				
				for(var i=0; i<objKeys.length; i++){			
					var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
					
					if(mappedObjId){
						var faceHelper = mw3.getFaceHelper(mappedObjId);
						
						if(faceHelper && faceHelper.toAppend){
							faceHelper.toAppend(object.target);
						}else{
							var html = mw3.locateObject(object.target, null);//, "#"+mappedObjdivId);
							
							$("#objDiv_" + mappedObjId).append(html);	
						}					
						
						break;
					}	
				}
			}
		}
				
		mw3.onLoadFaceHelperScript();
	}
}