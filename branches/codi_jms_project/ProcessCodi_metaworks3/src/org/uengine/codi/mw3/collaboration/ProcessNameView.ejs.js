var org_uengine_codi_mw3_collaboration_ProcessNameView = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	var object = mw3.objects[this.objectId];
	
	
	$('.favoriteAdded').toggle(function(){
		
		//메소드
//		object.add();
		object.remove();
		$(this).attr("src","images/checkbox_on.png");
	}, function(){
		object.remove();
		$(this).attr("src","images/checkbox_off.png");
	});
	
		
	
};