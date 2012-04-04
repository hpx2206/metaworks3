var org_metaworks_widget_ModalWindow = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	this.divId = '#objDiv_' + objectId;
	
	var title = $('#title_' + objectId).val();
	
	$(this.divId).css('height','100%');
	
	$( "#dialog:ui-dialog" ).dialog( "destroy" );	
	$(this.divId).dialog({
		title: title,
		modal: true
	});
	
/*	this.divId = '#objDiv_' + objectId;

	console.debug('ModalWindow()');
	console.debug();
	
	
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