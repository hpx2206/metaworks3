var org_uengine_codi_mw3_model__ChoiceComboPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
}

org_forx_model_common_ChoiceComboPanel.prototype.getValue = function() {
	
	var object = mw3.objects[this.objectId];
	
	if($('#selection_' + this.objectId).length > 0){
	
		var choice = {
			__className : '',
			optionValues : object.optionValues,
			optionNames : object.optionNames,
			selected : $('#selection_'+this.objectId +' option:selected').val(),
			selectedText : $('#selection_'+this.objectId +' option:selected').text()
		}
	
		return choice;
	}else{
		return object;
	}
}