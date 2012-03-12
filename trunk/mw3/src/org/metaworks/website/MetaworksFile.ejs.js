var org_metaworks_website_MetaworksFile = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.uploadedOnce = false;

	var value = mw3.objects[this.objectId];
	
	if(value && value.mimeType && value.mimeType.indexOf('image') > -1){
		var image = value.downloadImage(false); //this cause recursive call so the 'false' option to set off it get again the object.
		$('#image_' + this.objectId).html("<img src='" + image + "'>");
	}
	
	value.fileTransfer.onchange = function(){
		mw3.getObject(value.__objectId).upload(); 
	}
}

//org_metaworks_website_MetaworksFile.prototype.getValue = function(){
//	
//	var metaworksFile = mw3.getObjectFromUI(this.objectId);
//	
//	if(!this.uploadedOnce && metaworksFile.fileTransfer && metaworksFile.fileTransfer.value!=null){
//		metaworksFile = metaworksFile.upload();
//		this.uploadedOnce = true;
//	}
//		
//	return metaworksFile;
//}

org_metaworks_website_MetaworksFile.prototype.download = function(){
	var metaworksFile = mw3.getObject(this.objectId);
	metaworksFile.download();
	
	dwr.engine.openInDownload(metaworksFile.fileTransfer);	
}