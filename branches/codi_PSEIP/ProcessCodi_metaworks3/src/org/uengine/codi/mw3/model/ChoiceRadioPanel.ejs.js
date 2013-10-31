var org_uengine_codi_mw3_model__ChoiceRadioPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
}

org_forx_model_common_ChoiceRadioPanel.prototype.getValue = function() {
	
	var object = mw3.objects[this.objectId];
	
	var selectObj = $(':input:radio[name=radio_name_'+this.objectId +']:checked');
	
	var choice = {
		__className : '',
		optionValues : object.optionValues,
		optionNames : object.optionNames,
		selected : selectObj.val(),
		selectedText : selectObj.attr('valueText')
	}
	
	return choice;
}