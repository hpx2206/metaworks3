var org_metaworks_Forward = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	console.log('org_metaworks_Forward');
	console.log(this.object);
	
	if(this.object.url)
		document.location.href = this.object.url;
};