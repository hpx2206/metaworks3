var Tab = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
		
	$("#objDiv_" + objectId).tabs();
	$("#objDiv_" + objectId).addClass("mw3_tab");	
}