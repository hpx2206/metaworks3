var org_uengine_codi_mw3_model_SearchBox = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.timeout;
	
	var object = mw3.objects[this.objectId];
	if(object && object.keyword)
		this.keyword = object.keyword;

	
	$("#search_" + this.objectId).focus();
}

org_uengine_codi_mw3_model_SearchBox.prototype = {
	getValue : function() {
		var object = mw3.objects[this.objectId];

		object.keyword = $("#search_" + this.objectId).val();

		return object;
	},
	keyup : function(element) {
		var keyword = element.value;
			
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
		
	},
	newInstance : function(){
		
		var instanceListPanel = mw3.getAutowiredObject('org.uengine.codi.mw3.model.InstanceListPanel');
		if(instanceListPanel){
			instanceListPanel.newInstance();
		}
		
	}		
}
