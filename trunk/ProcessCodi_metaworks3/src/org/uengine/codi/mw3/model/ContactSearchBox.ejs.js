var org_uengine_codi_mw3_model_ContactSearchBox = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.timeout;
	
	var object = mw3.objects[this.objectId];
	if(object && object.keyword)
		this.keyword = object.keyword;
	
}

org_uengine_codi_mw3_model_ContactSearchBox.prototype = {
	getValue : function(){
		var object = mw3.objects[this.objectId];

		object.keyword = $("#search_" + this.objectId).val();

		return object;
		
	},
	keyUp : function(element){
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
	}
}