var org_metaworks_website_MetaworksFile = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	var faceHelper = this;
	var object = mw3.objects[this.objectId];
	
	$("#objDiv_" + this.objectId).attr("objectId", this.objectId);

	if(object.uploadedPath){
		$("#filebtnadd_" + this.objectId).css('display', 'none');
		
		faceHelper.setFilename(faceHelper.extraFilename(object.filename));
		
		if(object.metaworksContext.when == 'image'){
			var image = object.downloadImage(false);
			var width = 0;
			var height = 0;
			
			if(object.__descriptor){
				width = object.__descriptor.getOptionValue('imageWidth');
				height = object.__descriptor.getOptionValue('imageHeight');
			}
			
			$("#image_" + this.objectId).html('<img ' + (width?'width='+width+' ':' ') + (height?'height='+height+' ':' ') + 'src=\'' + image + '\'>');
		}
			
	}
	
	$(mw3.getInputElement(objectId, 'fileTransfer')).bind('change', function(){
		var object = mw3.objects[objectId];
		
		if(object.auto)
			object.upload();
		else
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
		$("#filebtnadd_" + this.objectId).css('display', 'none');
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
	
	if(object.uploadedPath){
		object.deletedPath = object.uploadedPath;
		
		if(object.auto)
			mw3.call(this.objectId, 'remove');
		else{
			this.setFilename(null);
			$("#filebtndel_" + this.objectId).css('display', 'none');
			$("#filebtnadd_" + this.objectId).css('display', 'block');
		}
	}else{
		this.setFilename(null);
	}
}

org_metaworks_website_MetaworksFile.prototype.download = function(){		
	mw3.call(this.objectId, 'download');
}