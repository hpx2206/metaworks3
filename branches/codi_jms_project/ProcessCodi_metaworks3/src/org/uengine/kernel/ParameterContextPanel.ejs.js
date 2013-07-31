var org_uengine_kernel_ParameterContextPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	if(this.object && this.object.dataType){
		var expressionObjectId = this.object.dataType.__objectId;
		var condiInputObjectId = this.object.conditionInput.__objectId;
		$('#' + mw3._getObjectDivId(expressionObjectId) ).find('select').bind('change', {objectId : this.objectId},function(event){
			var change = $(this).find('option:selected');
			var condiInputObject = mw3.objects[condiInputObjectId];
			condiInputObject.changeType = change.val();
			mw3.call(condiInputObjectId, 'changeInput');
		});
	}
};