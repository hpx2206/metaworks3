var org_metaworks_ToOpener = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[objectId];

	if(object){
		var openerId = mw3.recentOpenerObjectId[mw3.recentOpenerObjectId.length - 1];
		
		mw3.recentOpenerObjectId.pop();
		
		var faceHelper = mw3.getFaceHelper(openerId);
		
		if(faceHelper.toOpener){
			faceHelper.toOpener(object.target);
		}else{
			mw3.setObject(openerId, object.target);
			
			mw3.beanExpressions[this.objectId] = null;
			
			mw3.removeObject(this.objectId);
			mw3.onLoadFaceHelperScript();
		}
		
		
	}
}