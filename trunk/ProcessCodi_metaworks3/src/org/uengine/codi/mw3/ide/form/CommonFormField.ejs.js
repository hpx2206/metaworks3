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
	this.objectDiv.append('<div class="hover_div" style="display:none"><span><img src="images/waveStyle/copy.png" onclick="<%=methods.copy.caller()%>" /><img src="images/waveStyle/delete.png" /></span></div>');
	
	this.objectDiv.bind('click', {objectId: this.objectId}, function(event, ui){
		$('.fb_form_box').removeClass('current');
		$(this).addClass('current');
		
		$('.hover_div').hide();
		$(this).children('.hover_div').show();
		
		mw3.call(event.data.objectId, 'modify');
	});
	
	this.startLoading = function(){
		
	};
	this.endLoading = function(){
		
	};
	this.showStatus = function(){
		
	};
};