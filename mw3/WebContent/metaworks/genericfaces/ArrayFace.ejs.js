var ArrayFace = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

ArrayFace.prototype.addNew = function(){
	var arrayObj = mw3.getObject(this.objectId);
	var newOne = {__className: this.className};
	arrayObj[arrayObj.length] = newOne;
	
	var divId = "#objDiv_" + this.objectId; 
	
	mw3.locateObject(newOne, this.className, divId);
	
}