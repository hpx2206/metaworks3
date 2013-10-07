var org_uengine_codi_mw3_processexplorer_ProcessNameView = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	var object = mw3.objects[this.objectId];
	
	if(object){
		this.instanceName = object.instanceName;
		
		$('#processName_' + this.objectId).bind('focus', function(){
			$(this).css("border","solid 1px #ccc");
		});
		
		var autoSizing = function(){
			var h=$('#processName_' + objectId);
			
			if(h.length > 0)
				h.height(21).height(h[0].scrollHeight);//where 60 is minimum height of textarea
		};  
		
		$('#processName_' + this.objectId).live("keyup keydown", autoSizing);
	
		autoSizing();
	}
	
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
