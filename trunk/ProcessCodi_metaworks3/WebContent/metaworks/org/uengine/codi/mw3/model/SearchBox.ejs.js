//var timeout;

var org_uengine_codi_mw3_model_SearchBox = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.timeout;
	
	$("#search_" + this.objectId).focus();
}

org_uengine_codi_mw3_model_SearchBox.prototype.getValue = function() {
	var object = mw3.objects[this.objectId];

	object.keyword = $("#search_" + this.objectId).val();

	return object;
}

org_uengine_codi_mw3_model_SearchBox.prototype.keydown = function() {
	var objectId = this.objectId;
	
	console.debug(this.timeout);
	
	if (this.timeout) {
		clearTimeout(this.timeout);
	}
	
	this.timeout = setTimeout(function() {
		console.debug("search");
		
		mw3.call(objectId, 'search');
	}, 500);
	
}

org_uengine_codi_mw3_model_SearchBox.prototype.newInstance = function(){
	
	var instanceListPanel = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceListPanel");
	if(instanceListPanel){
		instanceListPanel.newInstance();
	}
	
}