var org_uengine_codi_mw3_model_InstanceListPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	
	this.divElement = $('#' + this.divId); 	
	this.divElement.addClass('mw3_layout').attr('objectId', objectId);
	
	var faceHelper = this;	
	faceHelper.load();		
}


org_uengine_codi_mw3_model_InstanceListPanel.prototype = {
	load : function(){
		
		var object = mw3.objects[this.objectId];
		var options = {
				togglerLength_open:	0, 
				spacing_open:		0, 
				spacing_closed:		0,
				center__onresize:	'mw3.getFaceHelper('+this.objectId+').resizeChild()'
		}

		this.layout = this.divElement.layout(options);
	},
	destroy : function(){
		if(this.layout)
			this.layout.destroy();
	},
	resize : function(){
		if(this.layout){
			//console.debug('visible : ' + $(this.divId).visible);
			
			this.layout.resizeAll();
			
			this.resizeChild();
		}
	},
	resizeChild : function(){
		
		this.divElement.find('.mw3_layout, .mw3_resize').each(function(index, value){
			var layoutId = value.getAttribute('objectId');
			
			if(layoutId)
				mw3.getFaceHelper(layoutId).resize();
		});
	},
	startLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
			mw3.getFaceHelper(this.windowObjectId).startLoading();
	},
	endLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
	},
	showStatus : function(message){
		
	}	
		
}