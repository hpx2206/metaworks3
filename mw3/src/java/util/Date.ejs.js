var java_util_Date = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.inputId = mw3.createInputId(objectId);
	
	var when = $('#' + this.inputId).attr('when');
	
	$('#' + this.inputId).datepicker({
		dateFormat: 'yy-mm-dd',
		showOn: 'button',
		buttonImageOnly: true,
		buttonImage :'images/waveStyle/calendar.gif'
	});
	
	$('#' + this.inputId).datepicker("setDate", mw3.objects[objectId]);
	
	if(when != 'edit' && when != 'new')
		$('#objDiv_' + objectId + ' .ui-datepicker-trigger').remove();	
}

java_util_Date.prototype.getValue = function(){
	var selDate = new Date($("#" + this.inputId).datepicker("getDate"));
	
	return selDate.getTime();	
}