var org_metaworks_website_MetaworksFile = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	var faceHelper = this;
	var object = mw3.objects[this.objectId];
		
	$("#objDiv_" + this.objectId).attr("objectId", this.objectId);

	if(object.uploadedPath)
		faceHelper.setFilename(faceHelper.extraFilename(object.uploadedPath));

	$(mw3.getInputElement(objectId, 'fileTransfer')).bind('change', function(){
		faceHelper.setFilename(faceHelper.extraFilename(this.value));
	});
}

org_metaworks_website_MetaworksFile.prototype.extraFilename = function(filepath){
	
	var pos = filepath.lastIndexOf('\\');
	if(pos > 0)
		filepath = filepath.substring(pos + 1);
	
	pos = filepath.lastIndexOf('/');	
	if(pos > 0)
		filepath = filepath.substring(pos + 1);

	return filepath;

}

org_metaworks_website_MetaworksFile.prototype.setFilename = function(filename){

	if(filename == null){
		filename = '선택된 파일이 없습니다';
		$("#filebtndel_" + this.objectId).css('display', 'none');
	} else {
		$("#filebtndel_" + this.objectId).css('display', 'block');
	}
	
	$("#filename_" + this.objectId).html(filename);
	
}	

org_metaworks_website_MetaworksFile.prototype.add = function(){
	mw3.getInputElement(this.objectId, 'fileTransfer').click();
}

org_metaworks_website_MetaworksFile.prototype.del = function(){
	var object = mw3.objects[this.objectId];
	
	if(object.uploadedPath)
		mw3.call(this.objectId, 'delete');
	else
		this.setFilename(null);
}

org_metaworks_website_MetaworksFile.prototype.download = function(){		
	mw3.call(this.objectId, 'download');
}