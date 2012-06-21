var org_metaworks_ToOpener = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[objectId];

	if(object){
		mw3.removeObject(this.objectId);
		
		mw3.setObject(mw3.recentOpenerObjectId, object.target);
		mw3.onLoadFaceHelperScript();
	}
}