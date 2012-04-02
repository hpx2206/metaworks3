var org_uengine_codi_mw3_model_InstanceList = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
	this.divId = "#objDiv_" + objectId;
	
	$(this.divId).parent().unbind('scroll');
	
	var object = mw3.objects[objectId];
	
	if(object.metaworksContext.when == 'more'){
		$(this.divId).parent().bind('scroll', function(e){
			console.debug('lazy : ' + objectId);
			
			var scroll = $("#objDiv_" + objectId).parent();		
			
			if(scroll.scrollTop() + 26 > scroll.children('div').height() - scroll.height()){
				scroll.unbind('scroll');
				
				mw3.call(objectId, 'more');	
			}
		});
	}

}