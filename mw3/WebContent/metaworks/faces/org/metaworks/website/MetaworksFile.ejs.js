var org_metaworks_website_MetaworksFile = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

org_metaworks_website_MetaworksFile.prototype.download = function(){
	var metaworksFile = mw3.getObject(this.objectId);
	metaworksFile.download();
	
	dwr.engine.openInDownload(metaworksFile.fileTransfer);
}