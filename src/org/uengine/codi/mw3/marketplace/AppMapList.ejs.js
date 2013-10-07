var org_uengine_codi_mw3_marketplace_AppMapList = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
$('.process_map3').width(($('.process_map3 li').length-1) * 174);	
	
	if($('.process_map3').width() > $('.contentcontainer').width()){
		$('.process_map3').width($('.contentcontainer').width());
	}else{

	}
	
	
   $('.process_map3').css({"margin-top":$('.contentcontainer').height()/2 - $('.process_map3').height()/2 + 30});	
   
   $('.process_map3 li').show("bounce", 500);
  
	
};
