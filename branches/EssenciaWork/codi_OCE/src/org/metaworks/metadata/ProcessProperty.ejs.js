var org_metaworks_metadata_ProcessProperty = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	$('#' + mw3._getObjectDivId(this.objectId)).css({'height':'100%'});
	$('#' + mw3._getInfoDivId(this.objectId)).remove();
	
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

org_metaworks_metadata_ProcessProperty.prototype = {
};