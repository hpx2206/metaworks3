var org_uengine_codi_mw3_model_PersonalPerspective = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = 'objDiv_' + this.objectId;
	
	$('#navigator .depth2 a').click(function(){
		$('#navigator .depth2 a').css({"background":"none"});
		$(this).css({"background":"#C9E2FC"});		
	})

}
