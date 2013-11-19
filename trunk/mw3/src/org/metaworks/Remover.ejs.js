var org_metaworks_Remover = function(objectId, className){
	this.object = mw3.objects[objectId];	
	var object = this.object;
	
	if(this.object == null)
		return true;	
	
	if(typeof object.target == 'string'){
		if(object.target == 'opener')
			triggerObjId = mw3.recentOpenerObjectId[mw3.recentOpenerObjectId.length - 1];
		if(object.target == 'self')
			triggerObjId = mw3.recentCallObjectId;
				
	}else{
		var objKeys = [];
		if(this.object.match)
			objKeys.push(mw3._createObjectKey(this.object.target));
		else
			objKeys = mw3._createObjectKey(this.object.target, true);
		
		if(objKeys && objKeys.length){
			for(var i=0; i<objKeys.length; i++){			
				mappedObjId = mw3.objectId_KeyMapping[objKeys[i]];
	
				if(mappedObjId){
					triggerObjId = mappedObjId;
					break;
				}	
			}
		}
	}
	
	if(triggerObjId){
		var faceHelper = mw3.getFaceHelper(mappedObjId);
				
		if(faceHelper && faceHelper.remover)
			faceHelper.remover(this.object.target);
		else	
			mw3.removeObject(mappedObjId);
	}
	
	this.loaded = function(){
		if(mw3.objects[objectId] != null)
			mw3.removeObject(objectId);		
	};

};

