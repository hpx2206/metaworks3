var org_metaworks_metadata_MetadataPropertyDetail = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	
	this.editBtn = $("#sp_editBtn_" + this.objectId);
	
	this.editBtn.hover(function(){
			$(this).css('cursor','pointer');
		}, function(){			
			$(this).css('cursor','auto');
	});
	
	
};