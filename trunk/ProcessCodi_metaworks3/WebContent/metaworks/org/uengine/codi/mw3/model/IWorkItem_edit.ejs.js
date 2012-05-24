var org_uengine_codi_mw3_model_IWorkItem_edit = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	$("#post_" + this.objectId).focus();


}

org_uengine_codi_mw3_model_IWorkItem_edit.prototype.getValue =  function(){	
	//console.debug("getValue()");
	
	var object = mw3.objects[this.objectId];
		
	var text = $("#post_" + this.objectId).val();
	if(text)
		object.title = text;
	
	return object;
}

org_uengine_codi_mw3_model_IWorkItem_edit.prototype.press = function(){
	var e = window.event;
	
	if (e.keyCode == 13) {
		var value = mw3.getObject(this.objectId);
		value.add();
    }	
}