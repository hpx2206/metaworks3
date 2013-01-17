var org_metaworks_Refresh = function(objectId, className){
	
	var object = mw3.objects[objectId];
	
	if(object){
		mw3.removeObject(objectId);
		
		if(object.self){
			mw3.setObject(mw3.recentCallObjectId, object.target);			 
		}else{			
			var objKeys = mw3._createObjectKey(object.target, true);
			if(objKeys && objKeys.length){
				var matchKeyCnt = 0;
				if(object.match)
					matchKeyCnt= objKeys[0].split('@').length;
					
				for(var i=0; i<objKeys.length; i++){
					if(object.match && (matchKeyCnt > objKeys[i].split('@').length))
						break;
					
					var mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
		
					if(typeof mappedObjId != 'undefined' && mappedObjId != null){
						var faceHelper = mw3.getFaceHelper(mappedObjId);
						
						if(faceHelper && faceHelper.refresh){
							faceHelper.refresh(object.target);
						}else{
							mw3.setObject(mappedObjId, object.target);	
						}							
									
						break;
					}
				}
			}
		}
		mw3.onLoadFaceHelperScript();
	}	
};