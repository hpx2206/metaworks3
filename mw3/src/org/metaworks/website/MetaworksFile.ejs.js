var org_metaworks_website_MetaworksFile = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.fileChangeListeners = [];

	var faceHelper = this;
	var object = mw3.objects[this.objectId];
	
	$("#objDiv_" + this.objectId).attr("objectId", this.objectId);

	this.reset();
	
	if(object && object.uploadedPath){
		$("#filebtnadd_" + this.objectId).css('display', 'none');
		
		if(object.filename)
			faceHelper.setFilename(faceHelper.extraFilename(object.filename));
		else
			faceHelper.setFilename(faceHelper.extraFilename(object.uploadedPath));
		
		if(object.metaworksContext.when == 'image'){
			var image = object.downloadImage(false);
			var width = 0;
			var height = 0;
			var parentwidth = $("#image_" + this.objectId).parent().width();

			
			if(object.__descriptor){
				width = object.__descriptor.getOptionValue('imageWidth');
				height = object.__descriptor.getOptionValue('imageHeight');
			}
			$("#image_" + this.objectId).html('<img style=\"display:none\" ' + (width?'width='+width+' ':' ') + (height?'height='+height+' ':' ') + 'src=\'' + image + '\'>').attr("href","javascript:mw3.objects[" + objectId + "].download()");
			
			
			$("#image_" + this.objectId).find('img').load(function(){
				if($(this).width() > parentwidth){
					$(this).width(parentwidth);
				}
				
				$(this).show();
			})
		}
			
	}
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

	var object = mw3.objects[this.objectId];
	
	object.filename = filename;
	
	if(filename == null){
		filename = '선택된 파일이 없습니다';
		$("#filebtnadd_" + this.objectId).css('display', 'block');
		$("#filebtndel_" + this.objectId).css('display', 'none');
	} else {
		$("#filebtnadd_" + this.objectId).css('display', 'none');
		$("#filebtndel_" + this.objectId).css('display', 'block');				
	}
	
	$("#filename_" + this.objectId).html(filename);
	
	for(var i=0; i<this.fileChangeListeners.length; i++){
		var listner = this.fileChangeListeners[i];
		listner(object);
	}
	
}	

org_metaworks_website_MetaworksFile.prototype.addFileChangeListener = function(listener){
	this.fileChangeListeners[this.fileChangeListeners.length] = listener;
}


org_metaworks_website_MetaworksFile.prototype.reset = function(){
	
	var fileTransfer = mw3.getInputElement(this.objectId, 'fileTransfer');
	$(fileTransfer).unbind('change');

	if(fileTransfer)
		fileTransfer.parentNode.innerHTML = fileTransfer.parentNode.innerHTML;
	
	fileTransfer = mw3.getInputElement(this.objectId, 'fileTransfer');
	$(fileTransfer).bind('change', {objectId: this.objectId}, function(event){
		var object = mw3.objects[event.data.objectId];
		var faceHelper = mw3.getFaceHelper(event.data.objectId);
		
		if(object.auto)
			object.upload();
		else
			faceHelper.setFilename(faceHelper.extraFilename(this.value));
			
	});		
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
			$("#filebtndel_" + this.objectId).css('display', 'none');
			$("#filebtnadd_" + this.objectId).css('display', 'block');
			
			this.reset();
			this.setFilename(null);
		}
	}else{
		this.reset();
		this.setFilename(null);

	}
}

org_metaworks_website_MetaworksFile.prototype.download = function(){		
	mw3.call(this.objectId, 'download');
}