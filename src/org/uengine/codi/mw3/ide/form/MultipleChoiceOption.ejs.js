var org_uengine_codi_mw3_ide_form_MultipleChoiceOption = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.objectDivId = '#' + mw3._getObjectDivId(this.objectId);
};

org_uengine_codi_mw3_ide_form_MultipleChoiceOption.prototype = {
	getValue : function(){
		
		var object = mw3.objects[this.objectId];

		object.option = $("#option_" + this.objectId).val();
		object.value = $("#value_" + this.objectId).val();

		return object;
	}
};