var org_metaworks_widget_layout_Layout = function(objectId, className){

	this.objectId = objectId;
	this.className = className;

	var object = mw3.objects[this.objectId];
	var faceHelper = this;
	
	this.size = $('#objDiv_' + objectId + '> .pane').length;
	this.name = object.name;
	
	this.center = $('#objDiv_' + objectId + '>.ui-layout-center');
	this.east = $('#objDiv_' + objectId + '>.ui-layout-east');
	this.west = $('#objDiv_' + objectId + '>.ui-layout-west');
	this.north = $('#objDiv_' + objectId + '>.ui-layout-north');
	this.south = $('#objDiv_' + objectId + '>.ui-layout-south');
	
	$('#objDiv_' + objectId).addClass('mw3_layout').attr('objectId', objectId);
	
	
	if(object.load)
		faceHelper.load();
	
	if(object.loadChild){
		$('#objDiv_' + objectId).find('.mw3_layout').each(function(){
			var layoutId = $(this).attr('objectId');
			
			mw3.getFaceHelper(layoutId).load();
		});
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
	
	this.layout = $('#objDiv_' + this.objectId).layout(options);
}

org_metaworks_widget_layout_Layout.prototype.show = function(target){
	
	var pane = $('#objDiv_' + this.objectId).parent();
	
	if(pane.css('display') == 'none'){
		var parent = $('#objDiv_' + this.objectId).parent().closest('.mw3_layout');
		var parentId = parent.attr('objectId');
		
		mw3.getFaceHelper(parentId).show(this.name);
	}
	
	
	if(target == 'center'){
		this.center.show();
	}else{
		this.layout.show(target);
	}
	
	this.resize();

}

org_metaworks_widget_layout_Layout.prototype.hide = function(target){
	if(target == 'center'){
		this.center.hide();
	}else{
		this.layout.hide(target);
	}
	
	var hidden = $('#objDiv_' + this.objectId + '> .pane:hidden').length;
	if(hidden == this.size || (hidden == this.size-1 && this.center.css('display') != 'none')){
		var pane = $('#objDiv_' + this.objectId).parent();
		
		if(pane.css('display') != 'none'){
			var parent = $('#objDiv_' + this.objectId).parent().closest('.mw3_layout');					
			var parentId = parent.attr('objectId');
			
			mw3.getFaceHelper(parentId).hide(this.name);
		}
	}
	
	this.resize();
}

org_metaworks_widget_layout_Layout.prototype.toggle = function(target){
	this.layout.toggle(target);
	
	this.resizeChild();
}

org_metaworks_widget_layout_Layout.prototype.resize = function(){
	if(this.layout){
		this.layout.resizeAll();
		
		var height = 0;
		
		if(this.center.css('display') == 'none'){
			height = $('#objDiv_' + this.objectId + '>.ui-layout-resizer').height();
			$('#objDiv_' + this.objectId + '>.ui-layout-resizer').hide();
			
			if(this.north.length){
				
				height += this.center.height() + this.north.height();
				height += Number(this.center.attr('height-margin'));
				 
				console.debug(height);
				
				this.north.height(height);
				
				//console.debug($('#objDiv_' + this.objectId + '>.ui-layout-north').height(height));
			}
		}
	}
}

org_metaworks_widget_layout_Layout.prototype.resizeChild = function(){
	
	$('#objDiv_' + this.objectId).find('.mw3_layout').each(function(){
		var layoutId = $(this).attr('objectId');
		
		if(layoutId)
			mw3.getFaceHelper(layoutId).resize();
	});
}