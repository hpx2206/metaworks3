var org_uengine_codi_mw3_model_FileWorkItem = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.object = mw3.objects[this.objectId];
	
	if(this.object == null)
		return true;	

	var imageDiv = $("#image_" + this.objectId);

	if(imageDiv.length > 0){
		var fileObjectId = mw3.getChildObjectId(objectId, 'file');
		var image = mw3.call(fileObjectId, 'downloadImage')
		
		imageDiv.html('<img style=\"display:none;\" src=\'' + image + '\'>');
				
		imageDiv.find('img').load(function(){
			$(this).width("100%");
			$("#convertProgress_" + objectId).hide();
			$(this).show();
		});
	}
}