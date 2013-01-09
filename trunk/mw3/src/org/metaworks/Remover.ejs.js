var org_metaworks_Remover = function(objectId, className){
	var object = mw3.objects[objectId];
	
	var objKeys = mw3._createObjectKey(object.target, true);
		
	if(objKeys && objKeys.length){
					        				
		for(var i=0; i<objKeys.length; i++){
			mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];

			if(mappedObjId){
				var faceHelper = mw3.getFaceHelper(mappedObjId);
				
				if(faceHelper && faceHelper.remover)
					faceHelper.remover(object.target);
				else	
					mw3.removeObject(mappedObjId);
				
				break;
			}	
			
/*			var mappedObjdivId = "objDiv_" + mappedObjId;
			
			if(mappedObjId && document.getElementById(divId)){ //if there's mappedObjId exists, replace that div part.
				
				if(serviceMethodContext.target=="append"){
					mw3.locateObject(result_, null, "#"+mappedObjdivId);
				}else if(serviceMethodContext.target=="prepend"){
					mw3.locateObject(result_, null, "#"+mappedObjdivId, {prepend: true});
				}else{
					mw3.setObject(mappedObjId, result_);
				}
				
				neverShowed = false;
			}
			*/
		}
	}
	
	this.loaded = function(){
		if(mw3.objects[objectId] != null)
			mw3.removeObject(objectId);		
	};

};

