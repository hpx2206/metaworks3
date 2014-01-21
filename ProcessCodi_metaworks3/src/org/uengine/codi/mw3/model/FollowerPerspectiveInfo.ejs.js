var org_uengine_codi_mw3_model_FollowerPerspectiveInfo = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.object = mw3.objects[this.objectId];
	
	this.objectDiv.find('.toolbar li').not('.right').bind('click', function(){
		$(this).siblings().removeClass('toolbar_current').end().addClass('toolbar_current');
	});
	
	this.objectDiv.unbind('contextmenu');
	
	this.divId = mw3._getObjectDivId(this.objectId);
	
	// set window title
	this.windowObjectId = this.objectDiv.closest('.mw3_window').attr('objectId');
	
	var session = mw3.fn.getSession();
	if(this.object && this.object.title || session.windowTitle){
		var title = session.windowTitle;
		console.log(this.object);
		if(this.object && this.object.title)
			title = this.object.title;
		
		if(this.windowObjectId)
			mw3.getFaceHelper(this.windowObjectId).setTitle(mw3.localize(title));
	}
}