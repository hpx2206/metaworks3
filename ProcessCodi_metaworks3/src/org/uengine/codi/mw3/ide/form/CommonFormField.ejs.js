var org_uengine_codi_mw3_ide_form_CommonFormField = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.nodeDiv = this.objectDiv.children('div');
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	this.objectDiv.addClass('fb_form_box');
	
	this.objectDiv.bind('click', {objectId: this.objectId}, function(event, ui){
		mw3.getFaceHelper(event.data.objectId).select(event);
		
		mw3.call(event.data.objectId, 'modify');
	});
	
	this.startLoading = function(){
		
	};
	this.endLoading = function(){
		
	};
	this.showStatus = function(){
		
	};
	this.select = function(event){
		if(event){
			event.preventDefault();
			event.stopPropagation();
		}
				
		$('.fb_form_box').removeClass('current');
		$(this.objectDiv).addClass('current');
		
		$('.hover_div').hide();
		$(this.objectDiv).children('.hover_div').show();
	}
	
	if(this.object.selected)
		this.select();
		
};