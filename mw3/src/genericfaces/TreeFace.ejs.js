var TreeFace = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;

	this.objectDiv.addClass('mw3_tree');
	
	this.objectDiv.css('margin-left', '10px');
}