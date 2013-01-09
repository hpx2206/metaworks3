var org_metaworks_widget_ModalWindow = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = '#objDiv_' + objectId;

	$('.mw3_layout').attr('fixed_size', true);
	
	this.object = mw3.objects[this.objectId];

	if(this.object){
		var title = this.object.title;
		
		if (title == null)
			title = $('#title_' + objectId).val();
		
		if(this.object.metaworksContext && this.object.metaworksContext.where == 'mobile'){
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
			if (this.object.open) {		
				var options = {};
		
				options['title'] = title;
				options['modal'] = true;
				options['close'] = function(event, ui) {
					mw3.getFaceHelper(objectId).close();
				}
		
				var width = this.object.width;
				var body_width = $('body').width();
				if(width > body_width)
					width = body_width;
				
				if (this.object.width)
					options['width'] = width;
				
				if (this.object.height)
					options['height'] = this.object.height;
				else
					options['height'] = $(window).height() - 100;
				
				$("#dialog:ui-dialog").dialog("destroy");
				$(this.divId).dialog(options);
				//$(this.divId).dialog(options).css('height', '100%');
			} else {
				$(this.divId).css('display', 'none');
			}
		}
	};
	
	this.destroy = function() {
		$('#objDiv_' + this.objectId).parent().empty();
		
		setTimeout(function(){
			$('.mw3_layout').attr('fixed_size', false);
		},500);
	};

	this.close = function() {
		mw3.removeObject(this.objectId);
	};

	/*
	this.prototype.startLoading = function() {
		$('#loader_' + this.objectId).show();
	};

	this.prototype.endLoading = function() {
		$('#loader_' + this.objectId).hide();
	};
	*/
	
}