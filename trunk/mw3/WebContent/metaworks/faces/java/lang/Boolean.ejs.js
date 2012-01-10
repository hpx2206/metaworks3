var java_lang_Boolean = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

java_lang_Boolean.prototype.getValue = function() {
	return $(":input:radio[name=" + this.objectId + "]:checked").val();
}