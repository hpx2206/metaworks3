var org_metaworks_website_MetaworksFile = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var value = mw3.getObject(this.objectId);
	
	if(value && value.mimeType && value.mimeType.indexOf('image') > -1){
		var image = value.downloadImage(); //this cause recursive call
		$('#image_' + this.objectId).html("<img src='" + image + "'>");
	}
}

org_metaworks_website_MetaworksFile.prototype.download = function(){
	var metaworksFile = mw3.getObject(this.objectId);
	metaworksFile.download();
	
	dwr.engine.openInDownload(metaworksFile.fileTransfer);
}