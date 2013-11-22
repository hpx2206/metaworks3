var org_metaworks_metadata_ImageProperty = function(objectId, className){
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
	
};