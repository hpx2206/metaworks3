var org_uengine_codi_mw3_model_InstanceNameChanger = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	this.instanceName = object.instanceName;
	
	$('#instanceName_' + this.objectId).bind('focus', function(){
		$(this).css("border","solid 1px #ccc");
	})
	
	$('#instanceName_' + this.objectId).bind('blur', function(){
		mw3.getFaceHelper(objectId).change();
		$(this).css("border","none");

	})

}

org_uengine_codi_mw3_model_InstanceNameChanger.prototype = {
	getValue : function(){
		var object = mw3.objects[this.objectId];
		
		object.instanceName = $('#instanceName_' + this.objectId).val();
	},
	change : function(){
		var instanceName = $('#instanceName_' + this.objectId).val();
		
		if(this.instanceName != instanceName){
			mw3.call(this.objectId, 'change');			
		}
	}
}

