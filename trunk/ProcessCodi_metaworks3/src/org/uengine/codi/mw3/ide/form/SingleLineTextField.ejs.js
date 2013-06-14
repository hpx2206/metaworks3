var RadioButton = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.objectDivId = '#' + mw3._getObjectDivId(this.objectId);
};
