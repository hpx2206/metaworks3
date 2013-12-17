var org_metaworks_metadata_FileProperty = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	
	this.accordionList = $("#sp_accordion_list_" + this.objectId);
	
	this.accordionList.click(function(){
		mw3.call(objectId, 'showPropertyDetail');
	});
	
	this.accordionList.hover(function(){
			$(this).css('cursor','pointer');
		}, function(){			
			$(this).css('cursor','auto');
	});
	
	
	this.editBtn = $("#sp_editBtn_" + this.objectId);
	
	this.editBtn.hover(function(){
			$(this).css('cursor','pointer');
		}, function(){			
			$(this).css('cursor','auto');
	});
	
	/*
	var isFile = false;
	var isResource = false;

	var checkFileDivId = $("#sp_checkFile_" + this.objectId).children()[0].id;
	var checkFildInputId = $('#' + checkFileDivId).children()[0].id;

	var checkResourceDivId = $("#sp_checkResource_" + this.objectId).children()[0].id;
	var checkResourceInputId = $('#' + checkResourceDivId).children()[0].id;

	$('#' + checkFildInputId).click(function(){
		if($('#' + checkFildInputId).is(":checked") == true){
			isFile = true;
			if($('#' + checkResourceInputId).is(":checked") == true){
				isResource = false;
				$('#' + checkResourceInputId).attr("checked", false);
			}
			this.isFile = isFile;
			this.isResource = isResource;
		}else {
			isFile = false;
			if($('#' + checkResourceInputId).is(":checked") == true){
				isResource = true;
			}
			this.isFile = isFile;
			this.isResource = isResource;
		}
	});
	
	$('#' + checkResourceInputId).click(function(){
		if($('#' + checkResourceInputId).is(":checked") == true){
			isResource = true;
			if($('#' + checkFildInputId).is(":checked") == true){
				isFile = false;
				$('#' + checkFildInputId).attr("checked", false);
			}
			checkFile = isFile;
			checkResource = isResource;
		}else {
			isResource = false;
			if($('#' + checkFildInputId).is(":checked") == true){
				isFile = true;
			}
			checkFile = isFile;
			checkResource = isResource;
		}
	});
	*/
};

org_metaworks_metadata_FileProperty.prototype.saveProperty = function() {
	
};