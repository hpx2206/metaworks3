var org_metaworks_widget_ModalWindow = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = '#objDiv_' + objectId;

	$('.mw3_layout').attr('fixed_size', true);
	
	var object = mw3.objects[this.objectId];

	if(object){
		var title = object.title;
		
		if (title == null)
			title = $('#title_' + objectId).val();
		
		if(object.metaworksContext && object.metaworksContext.where == 'mobile'){
			var modal = $(this.divId);			
			var popup = modal.parent();
			
			var header = '<div data-role=\"header\" data-theme=\"d\"><h1>' + title + '</h1></div>';			
 			
			modal.attr('data-role', 'content');
			modal.attr('data-theme', 'c');			
			
			popup.attr('id', 'dialog_' + this.objectId);
			popup.attr('data-role', 'dialog');
			popup.prepend(header);
			
			$.mobile.changePage('#dialog_' + this.objectId);
		}else{ 
			if (object.open) {		
				var options = {};
		
				options['title'] = title;
				options['modal'] = true;
				options['close'] = function(event, ui) {
					mw3.getFaceHelper(objectId).close();
				}
		
				if (object.width)
					options['width'] = object.width;
				
				if (object.height)
					options['height'] = object.height;
				else
					options['height'] = $(window).height() - 100;
				
				$("#dialog:ui-dialog").dialog("destroy");
				$(this.divId).dialog(options);
			} else {
				$(this.divId).css('display', 'none');
			}
		}
	}
}

org_metaworks_widget_ModalWindow.prototype.destroy = function() {
	$('#objDiv_' + this.objectId).parent().empty();
	
	setTimeout(function(){
		$('.mw3_layout').attr('fixed_size', false);
	},500);
}

org_metaworks_widget_ModalWindow.prototype.close = function() {
	mw3.removeObject(this.objectId);
}

org_metaworks_widget_ModalWindow.prototype.startLoading = function() {
	$('#loader_' + this.objectId).show();
}

org_metaworks_widget_ModalWindow.prototype.endLoading = function() {
	$('#loader_' + this.objectId).hide();

}