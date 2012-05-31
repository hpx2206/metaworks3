var org_uengine_codi_mw3_model_IProcessMap = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	var object = mw3.objects[this.objectId];
	$(this.divId).addClass(object.iconColor.value);
	
	$( ".process_map2" ).sortable({
		connectWith: "ul",
	   cancel: ".sptitle",
	   stop:function(){
		   alert("stop");
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



			