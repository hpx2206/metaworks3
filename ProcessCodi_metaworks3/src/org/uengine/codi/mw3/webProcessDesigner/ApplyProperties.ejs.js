var org_uengine_codi_mw3_webProcessDesigner_ApplyProperties = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	console.log(this.object.content);
	
	$('#' + this.object.id).data('activity', this.object.content);
};