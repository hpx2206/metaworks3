var MobileWindow = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

MobileWindow.prototype.close = function(){	
	$("#objDiv_" + this.objectId).remove();
}