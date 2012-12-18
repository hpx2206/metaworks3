var org_uengine_codi_mw3_model_Navigation = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	$('#navigator .depth2 a').click(function(){
		$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
		$(this).parent().addClass('selected_navi');
			
	})
}
