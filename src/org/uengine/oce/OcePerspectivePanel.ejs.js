var org_uengine_oce_OcePerspectivePanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	
	this.object = mw3.objects[this.objectId];
	
	$(this.divId).parent().css({"background":"none","width":"100%","overflow":"hidden"});
	$(this.divId).parent().hover(
			function(){
				$(this).css({"overflow":"auto","margin-right":"0px"});
			},
			function(){
				$(this).css({"overflow":"hidden"});
			}
	);
	
	$('#navigator .depth1').click(function(){
		$('#navigator .depth1').removeClass('selected');
		$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
		$(this).addClass('selected');
	});
	
};