var org_uengine_codi_mw3_model_InstanceListPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;

	this.divId = "#objDiv_" + objectId;

	
	$(this.divId).addClass('mw3_layout').attr('objectId', objectId);
	
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

		this.layout = $(this.divId).layout(options);
	},
	destroy : function(){
		if(this.layout)
			$(this.divId).layout().destroy();
	},
	resize : function(){
		if(this.layout){
			//console.debug('visible : ' + $(this.divId).visible);
			
			this.layout.resizeAll();
			
			this.resizeChild();
		}
	},
	resizeChild : function(){
		
		$(this.divId).find('.mw3_layout:visible').each(function(index, value){
			var layoutId = value.getAttribute('objectId');
			
			if(layoutId)
				mw3.getFaceHelper(layoutId).resize();
		});
	}
		
}