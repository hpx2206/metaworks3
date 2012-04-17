var org_metaworks_widget_layout_Layout = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
	this.divId = '#objDiv_' + objectId;
	this.div = $(this.divId);	
	
	var object = mw3.objects[this.objectId];
	var faceHelper = this;
	
	this.size = this.div.children().length;
	this.name = object.name;
	//this.change = false;
	
	this.div.addClass('mw3_layout').attr('objectId', objectId);
	
	this.center = this.div.children('.ui-layout-center');
	this.east = this.div.children('.ui-layout-east');
	this.west = this.div.children('.ui-layout-west');
	this.north = this.div.children('.ui-layout-north');
	this.south = this.div.children('.ui-layout-south');
	
	faceHelper.load();
}

org_metaworks_widget_layout_Layout.prototype.load = function(){
	
	var object = mw3.objects[this.objectId];
	var options = {};
	
	if(object.options){
		eval('options = {' + object.options + '}');		
	}else if(object.__descriptor){
		var value = object.__descriptor.getOptionValue('options');
		
		if(value){
			eval('options = {' + value + '}');
		}		
	}
	
	if(!options['center__onresize'])
		options['center__onresize'] = 'mw3.getFaceHelper(\''+this.objectId+'\').resizeChild()';
	
	this.layout = this.div.layout(options);
}

org_metaworks_widget_layout_Layout.prototype.destroy = function(){
	console.debug('layout destroy');
	
	$(this.divId).layout().destroy();
}

org_metaworks_widget_layout_Layout.prototype.show = function(target){
	
	var pane = this.div.parent();	
	if(pane.css('display') == 'none'){
		var parent = pane.closest('.mw3_layout');
		var parentId = parent.attr('objectId');
		
		mw3.getFaceHelper(parentId).show(this.name);
	}else{
		//this.change = true;
		
		/*
		$(this.divId + ' .mw3_layout').each(function(index, value){
			mw3.getFaceHelper(value.getAttribute('objectId')).change = true;
		});
		*/		
	}
	
	if(target == 'center'){
		this.center.show();
	}else{
		this.layout.show(target);
	}
	
	this.resize();
	
}

org_metaworks_widget_layout_Layout.prototype.hide = function(target){
	
	//this.change = true;
		
	var hidden = this.div.children('.pane:hidden').length;
	
	if(target == 'center'){
		this.center.hide();
	}else{
		var centerDisplay = this.center.css('display');
		
		this.layout.hide(target);
		
		if(centerDisplay == 'none')
			this.center.hide();
	}
		
	if(hidden == this.size-1){
		var parent = this.div.parent().closest('.mw3_layout');					
		var parentId = parent.attr('objectId');
		
		mw3.getFaceHelper(parentId).hide(this.name);
	}else{
		/*
		$(this.divId + ' .mw3_layout').each(function(index, value){
			mw3.getFaceHelper(value.getAttribute('objectId')).change = true;
		});
		*/
		
		this.resize();	
	}
			
}

/*
org_metaworks_widget_layout_Layout.prototype.toggle = function(target){
	
	
	this.layout.toggle(target);
	
	this.resizeChild();
}
*/

org_metaworks_widget_layout_Layout.prototype.resize = function(){
	
	if(this.layout){
		//console.debug('resize : ' + this.objectId);
		
		// this.change = false;		
		this.layout.resizeAll();
		
		//console.debug(this.center.css('display'));
		
		if(this.center.css('display') == 'none'){
			if(this.north.length || this.south.length){
				var resizer = this.div.children('.ui-layout-resizer');
				var resizerHeight = 0;
				
				if(resizer.length > 0){				
					resizerHeight = resizer.height();
					resizer.hide();
				}
				
				if(this.north.length){				
					var height = this.center.height() + this.north.height() + resizerHeight;
					 
					this.north.height(height);
				}else{
					var height = this.center.height() + this.south.height() + resizerHeight;
					
					this.south.height(height);
				}
			}
		}
		
		this.resizeChild();
	}
}

org_metaworks_widget_layout_Layout.prototype.resizeChild = function(){
	this.div.find('.mw3_layout:visible').each(function(index, value){
		var layoutId = value.getAttribute('objectId');
		
		if(layoutId)
			mw3.getFaceHelper(layoutId).resize();
	});
}