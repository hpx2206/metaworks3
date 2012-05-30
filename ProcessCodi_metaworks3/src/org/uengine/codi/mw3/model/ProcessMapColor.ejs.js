var org_uengine_codi_mw3_model_ProcessMapColor = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = '#objDiv_' + this.objectId;
}

org_uengine_codi_mw3_model_ProcessMapColor.prototype = {
	getValue : function(){
		var object = mw3.objects[this.objectId];

		object.value = $(this.divId + ' :input:radio[name=iconbg]:checked').val(); 
		
		return object;
	}
}