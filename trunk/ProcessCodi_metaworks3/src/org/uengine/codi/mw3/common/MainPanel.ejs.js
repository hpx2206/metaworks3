var org_uengine_codi_mw3_common_MainPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	
	$("#objDiv_" + objectId).css("height", "100%");
	
	$("#objDiv_" + objectId +" .top_panel_mobile_menu").click(function(){
		$("#objDiv_" + objectId + " #main_container").css({"position":"relative","left":"260px","height":"100%"});
		$("#objDiv_" + objectId + " #mobile_left_container").show().animate({width:260});
		
	});
	
	
}