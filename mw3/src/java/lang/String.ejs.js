var java_lang_String = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

java_lang_String.prototype.validateLength = function(length){
	var input = $('#' + mw3.createInputId(this.objectId));
	
	if(input.val().length >= length){
		return false;
	}else{
		return true;
	}
}