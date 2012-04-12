var org_metaworks_widget_ModalWindow = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	this.divId = '#objDiv_' + objectId;
	
	var object = mw3.objects[this.objectId];
	
	
	var title = $('#title_' + objectId).val();
		
	//$(this.divId).css('height','100%');
	
	var options = {};
	
	options['title'] = title;
	options['modal'] = true;
	
	if(object.width)
		options['width'] = object.width;
	
	if(object.height)
		options['height'] = object.height;
	
	
	$( "#dialog:ui-dialog" ).dialog( "destroy" );	
	$(this.divId).dialog(options);
	
/*	this.divId = '#objDiv_' + objectId;
	
	$('#' + this.divId).parent().find('#info_' + objectId).remove();*/
}

org_metaworks_widget_ModalWindow.prototype.close = function(){
	
    $('#objDiv_' + this.objectId).next('div').hide();
	$('#objDiv_' + this.objectId).hide('clip',100);
	$('#objDiv_' + this.objectId).parent().remove();
}

org_metaworks_widget_ModalWindow.prototype.startLoading = function(){
	$('#loader_' + this.objectId).show();
}

org_metaworks_widget_ModalWindow.prototype.endLoading = function(){
	$('#loader_' + this.objectId).hide();

}