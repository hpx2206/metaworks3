var org_metaworks_ToEvent = function(objectId, className){

	var object = mw3.objects[objectId];
	if(object){
		mw3.removeObject(objectId);
			
		var triggerObjId = null;
		
		if(typeof object.target == 'string'){
			if(object.target == 'opener')
				triggerObjId = mw3.recentOpenerObjectId[mw3.recentOpenerObjectId.length - 1];
			if(object.target == 'self')
				triggerObjId = mw3.recentCallObjectId;
					
		}else{
			var objKeys = mw3._createObjectKey(object.target, true);
				
			if(objKeys && objKeys.length){
				for(var i=0; i<objKeys.length; i++){
					var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
		
					if(mappedObjId){
						triggerObjId = mappedObjId;
						
						break;
					}	
				}
			}
		}
		
		if(triggerObjId){
			$('#' + mw3._getObjectDivId(triggerObjId)).trigger(object.event);
			
			mw3.onLoadFaceHelperScript();
		}
	}
};