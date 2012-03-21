var org_metaworks_widget_layout_Layout = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
	$('#objDiv_' + objectId).addClass('mw3_layout').attr('objectId', objectId);
	
	var object = mw3.objects[this.objectId];
	var faceHelper = this;
	
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
	this.layout.show(target);
	
	this.resizeChild();
}

org_metaworks_widget_layout_Layout.prototype.hide = function(target){
	if(target != 'center'){
		this.layout.hide(target);
	
		this.resizeChild();
	}
}

org_metaworks_widget_layout_Layout.prototype.toggle = function(target){
	this.layout.toggle(target);
	
	this.resizeChild();
}

org_metaworks_widget_layout_Layout.prototype.resize = function(){
	if(this.layout)
		this.layout.resizeAll();	
}

org_metaworks_widget_layout_Layout.prototype.resizeChild = function(){
	
	$('#objDiv_' + this.objectId).find('.mw3_layout').each(function(){
		var layoutId = $(this).attr('objectId');
		
		if(layoutId)
			mw3.getFaceHelper(layoutId).resize();
	});	
}