var org_uengine_codi_mw3_processexplorer_ProcessExplorerPerspectivePanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	$('#navigator .depth2 a').click(function(){
		$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
		$('.idept').removeClass('selected_navi2');
		$('.iemployee').removeClass('selected_navi2');
		$(this).parent().addClass('selected_navi');
		
	});

	
};