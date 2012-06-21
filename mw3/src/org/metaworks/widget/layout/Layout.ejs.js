var org_metaworks_widget_layout_Layout = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
	this.divId = '#objDiv_' + objectId;
	this.div = $(this.divId);	
	
	var object = mw3.objects[this.objectId];
	
	if(object){
		var faceHelper = this;
		
		this.size = this.div.children().length;
		this.hidden = 0;
		
		//this.change = false;
		
		this.div.addClass('mw3_layout').attr('objectId', objectId);
			
		this.list = {};	
		if(object.center)
			this.list['center'] = this.div.children('.ui-layout-center');
		if(object.east)
			this.list['east'] = this.div.children('.ui-layout-east');
		if(object.west)
			this.list['west'] = this.div.children('.ui-layout-west');
		if(object.north)
			this.list['north'] = this.div.children('.ui-layout-north');
		if(object.south)
			this.list['south'] = this.div.children('.ui-layout-south');
		
		this.parent = this.div.parent().closest('.mw3_layout');
		if(this.parent.length == 0){
			this.parent = null;
			this.name = null;
		}else{
			var layout =this.div.parent();
			
			if(!layout.hasClass('pane')){
				layout = layout.parent();
			}
			
			if(layout.hasClass('pane'))
				this.name = layout.attr('layoutName');		
		}
		
		
		faceHelper.load();
	}
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
	if(this.layout)
		this.layout.destroy();
}

org_metaworks_widget_layout_Layout.prototype.show = function(target){
	
	var pane = this.div.parent();	
	
	if(pane.css('display') == 'none'){
		var parent = pane.closest('.mw3_layout');
		var parentId = parent.attr('objectId');
		
		mw3.getFaceHelper(parentId).show(this.name);
/*	}else{
		//this.change = true;
		
		$(this.divId + ' .mw3_layout').each(function(index, value){
			mw3.getFaceHelper(value.getAttribute('objectId')).change = true;
		});
*/	}
	
	if(this.list[target].css('display') == 'none')
		this.hidden--;
	
	if(target != 'center'){
		this.layout.open(target);
				
		this.list[target].children('.mw3_window').each(function(){
			var objectId = $(this).attr('objectId');
			var faceHelper = mw3.getFaceHelper(objectId);
			
			if(faceHelper.hideSmallWindow)
				faceHelper.hideSmallWindow();
		});
	}
	this.list[target].show();
	
	this.resize();
	
}

org_metaworks_widget_layout_Layout.prototype.hide = function(target){
	
	//this.change = true;
	//if(this.list[target].attr('layoutFixed'))
	//	return false;

	if(this.list[target].find('.mw3_window').length == 0)
		return false;
			
	
	if(this.list[target].css('display') != 'none')
		this.hidden++;

	if(target != 'center'){
		if(this.list['center'].css('display') == 'none')
			this.list['center'].hide();		
		//var centerDisplay = this.list['center'].css('display');
				
		this.layout.close(target);
		
		this.list[target].find('.mw3_window').each(function(){
			mw3.getFaceHelper($(this).attr('objectId')).showSmallWindow();			
		});

/*		if(hideWindow.length > 0){
			
			mw3.getFaceHelper(hideWindow.attr('objectId')).showSmallWindow();
		}
*/				
	}
	
	this.list[target].hide();	
	this.resize();
	
	/*
	if(hidden == this.size-1){
		var parent = this.div.parent().closest('.mw3_layout');					
		var parentId = parent.attr('objectId');
		
		mw3.getFaceHelper(parentId).hide(this.name);
	}else{

		$(this.divId + ' .mw3_layout').each(function(index, value){
			mw3.getFaceHelper(value.getAttribute('objectId')).change = true;
		});
		
		this.resize();	
	}*/
	
}

org_metaworks_widget_layout_Layout.prototype.maximize = function(target){
	
	/*
	for(var pane in this.list){
		if(target != pane){
			this.hide(pane);
		}
	}	
	
	if(this.parent != null){
		var parentId = this.parent.attr('objectId');
			
		mw3.getFaceHelper(parentId).maximize(target);
	}*/
	if(this.parent != null){
		var parentId = this.parent.attr('objectId');
			
		mw3.getFaceHelper(parentId).maximize(target);
	}else{
		this.div.find('.mw3_window').each(function(){			
			var objectId = $(this).attr('objectId');
			if(objectId && objectId != target){
				var faceHelper = mw3.getFaceHelper(objectId);
				
				if(faceHelper.minimize){
					faceHelper.minimize();
				}
			}			
			
			
	/*		console.debug(this);
			
			var objectId = $(this).attr('objectId');
			
			if(objectId || objectId != target){
				var faceHelper = mw3.getFaceHelper(objectId);
				
				if(faceHelper.minimize){
					faceHelper.minimize();
				}
			}*/
		});
	}	
	
	
	
}

org_metaworks_widget_layout_Layout.prototype.resume = function(){
	
	/*
	for(var pane in this.list){
		//if(pane != 'center')
			this.show(pane);
	}
	*/
	
	if(this.parent != null){
		var parentId = this.parent.attr('objectId');
		
		mw3.getFaceHelper(parentId).resume();		
	}else{
		this.div.find('.mw3_window').each(function(){
			var objectId = $(this).attr('objectId');
			var faceHelper = mw3.getFaceHelper(objectId);
			
			if(faceHelper.resume){
				faceHelper.resume();
			}
		});
	}
	
	
}

org_metaworks_widget_layout_Layout.prototype.resize = function(){
	
	if(this.layout){
		// this.change = false;		
		this.layout.resizeAll();
			
		if(this.name != null){
			if(this.hidden == this.size){
				var pane = this.parent.children('.ui-layout-' + this.name);
				
				if(pane.css('display') == 'block'){
					var parentId = this.parent.attr('objectId');
					
					mw3.getFaceHelper(parentId).hide(this.name);
				}
				
			}else{
				if(this.list['center'].css('display') == 'none'){
					
					if(this.list['north'].length || this.list['south'].length){
						var resizer = this.div.children('.ui-layout-resizer');
						var resizerHeight = 0;
						
						if(resizer.length > 0){				
							resizerHeight = resizer.height();
							resizer.hide();
						}
						
						if(this.list['north'].length){
							var height = this.list['center'].height() + this.list['north'].height() + resizerHeight;
							 
							this.list['north'].height(height);
						}else{
							var height = this.list['center'].height() + this.list['south'].height() + resizerHeight;
							
							this.list['south'].height(height);
						}
					}
				}			
			}
		}
		//if(this.name == 'center')
		this.resizeChild();
		
	}
}

org_metaworks_widget_layout_Layout.prototype.resizeChild = function(){
	this.div.find('.mw3_layout, .mw3_resize').each(function(index, value){
		var layoutId = value.getAttribute('objectId');
		
		mw3.getFaceHelper(layoutId).resize();
	});
}