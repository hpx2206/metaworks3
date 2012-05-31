var org_uengine_codi_mw3_model_IProcessMap = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	var object = mw3.objects[this.objectId];
	$(this.divId).addClass(object.iconColor.value).attr('objectId', this.objectId);
	
	$( ".process_map2" ).sortable({
		connectWith: "ul",
		cancel: ".sptitle",
		stop:function(){
			var changed = false;
			var children = $(this).children('li');
			
			for(var i=0; i<children.length; i++){
				var child = children[i];				
				var objectId = $(child).attr('objectId');
				
				var object = mw3.objects[objectId];
				
				if(object.no != i){
					object.no = i;
					
					changed = true;
				}
				
				console.debug(object.defId + ' : ' + object.no);
			}
			
			if(changed){
				var objectId = $(this).closest('.processMapList').attr('objectId');
				
				mw3.call(objectId, 'save');
			}
		   
	   }
	});
	
	$(".process_map2 li").hover(
		function(){
			$(this).find(".editicon").css("display","block");
			
		},
		function(){
			$(this).find(".editicon").css("display","none");
		}			
	);
}

org_uengine_codi_mw3_model_IProcessMap.prototype = {
		
}



			