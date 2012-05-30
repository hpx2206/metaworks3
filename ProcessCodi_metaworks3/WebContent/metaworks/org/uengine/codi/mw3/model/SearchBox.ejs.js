//var timeout;

var org_uengine_codi_mw3_model_SearchBox = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.timeout;
	this.keyword = '';
	
	$("#search_" + this.objectId).focus();
}

org_uengine_codi_mw3_model_SearchBox.prototype.getValue = function() {
	var object = mw3.objects[this.objectId];

	object.keyword = $("#search_" + this.objectId).val();

	return object;
}

org_uengine_codi_mw3_model_SearchBox.prototype.keyup = function(element) {
	var keyword = $("#search_" + this.objectId).val();
		
	if(this.keyword == keyword)
		return false;
	
	this.keyword = keyword;
	
	var objectId = this.objectId;
	
	if (this.timeout) {
		clearTimeout(this.timeout);
	}
	
	this.timeout = setTimeout(function() {
		mw3.call(objectId, 'search');
	}, 500);
	
}

org_uengine_codi_mw3_model_SearchBox.prototype.newInstance = function(){
	
	var instanceListPanel = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceListPanel");
	if(instanceListPanel){
		instanceListPanel.newInstance();
	}
	
}