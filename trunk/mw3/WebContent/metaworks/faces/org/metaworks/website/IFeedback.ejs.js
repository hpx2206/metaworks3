var org_metaworks_website_IFeedback = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

org_metaworks_website_IFeedback.prototype.getValue =  function(){	
	console.debug("getValue()");
	
	var object = mw3.objects[this.objectId];
		
	object.text = $("#post_" + this.objectId).val();	
	
	return object;
}

function keyEventProcess(objectId){
	var e = window.event;
	
	if (e.keyCode == 13) {
		var value = mw3.getObject(objectId);
		value.post();
    }	
}