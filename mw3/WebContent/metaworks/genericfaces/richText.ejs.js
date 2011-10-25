var richText = function(objectId){
	this.objectId = objectId;
}

richText.prototype.getValue = function(){
	return 'value for ' + this.objectId;
}