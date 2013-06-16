var org_metaworks_metadata_FileProperty = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	console.log(value);
	
	var listDiv = $("#sp_accordion_list_" + this.objectId);
	
	$("#sp_accordion_list_" + this.objectId).click(function(){
		console.log("fileProperty");
	});
};