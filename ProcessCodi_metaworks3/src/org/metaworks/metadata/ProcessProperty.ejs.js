var org_metaworks_metadata_ProcessProperty = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	
	$("#sp_accordion_list_" + this.objectId).click(function(){
		
		mw3.call(objectId, 'showPropertyDetail');
	});
	
};