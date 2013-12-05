var org_uengine_codi_mw3_ide_form_Form = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	this.objectDiv.children('.fb_form_box').bind('click', {objectId: this.objectId}, function(event, ui){
		$('.fb_form_box').removeClass('current');
		$(this).addClass('current');
		
		mw3.call(event.data.objectId, 'modify');
	});

	this.startLoading = function(){
		
	};
	this.endLoading = function(){
		
	};
	this.showStatus = function(){
		
	};
};