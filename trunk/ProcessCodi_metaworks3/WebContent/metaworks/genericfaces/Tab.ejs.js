var Tab = function(objectId, className){
	console.debug("Tab()");
	
	this.objectId = objectId;
	this.className = className;
		
	$("#objDiv_" + objectId).tabs();
}