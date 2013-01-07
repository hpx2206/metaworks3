var Tab = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
		
	
	$('#objDiv_' + objectId).addClass('mw3_tab').addClass('mw3_layout').attr('objectId', objectId);
	
	$('#tabs_' + objectId).tabs({
		show: function(event, ui){
			if(mw3.getFaceHelper(objectId))
				mw3.getFaceHelper(objectId).resize(ui);
		}
	});
	
	/*
	if(objectMetadata.faceOptions['tabsBottom'] == 'true'){
		$('#objDiv_' + objectId).addClass('tabs-bottom');
	}	
	var objectMetadata = mw3.getMetadata(className);
	$('#objDiv_' + objectId).removeClass( "ui-corner-all");
	$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" ).removeClass( "ui-corner-all ui-corner-top" ).addClass( "ui-corner-bottom" );
	*/
	
	var faceHelper = this;
	
	faceHelper.load();
}

Tab.prototype.load = function(){
	
	var object = mw3.objects[this.objectId];
	var options = {
			togglerLength_open:	0, 
			spacing_open:		0, 
			spacing_closed:		0,
			center__onresize:	'mw3.getFaceHelper('+this.objectId+').resizeChild()'
	}


	this.layout = $('#objDiv_' + this.objectId).layout(options);
}

Tab.prototype.destroy = function(){
	$('#tabs_' + this.objectId).tabs('destroy');
}


Tab.prototype.resize = function(ui){
	if(this.layout){
		this.layout.resizeAll();
		
		this.resizeChild(ui);
	}
}

Tab.prototype.resizeChild = function(ui){
	$(ui.panel).find('.mw3_resize').each(function(index, value){
		var layoutId = value.getAttribute('objectId');
		
		if(layoutId)
			mw3.getFaceHelper(layoutId).resize();
	});
	
}
	