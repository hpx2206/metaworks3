var org_uengine_codi_mw3_processexplorer_ProcessNameView = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	var object = mw3.objects[this.objectId];
	
	
	var favoriteAdded = object.favoriteAdded;
	
	$('.favoriteToggle').toggle(function(){
		if(favoriteAdded == false){
			
			object.add();
			favoriteAdded = true;
			$(this).attr("src","images/checkbox_on.png");	
		}else{
			object.remove();
			favoriteAdded = false;
			$(this).attr("src","images/checkbox_off.png");	
		}
	},function(){
		if(favoriteAdded == false){
			object.add();
			favoriteAdded = true;
			$(this).attr("src","images/checkbox_on.png");						
		}else{
			object.remove();
			favoriteAdded = false;
			$(this).attr("src","images/checkbox_off.png");	
		}
	});
			
		
		
		
		
};