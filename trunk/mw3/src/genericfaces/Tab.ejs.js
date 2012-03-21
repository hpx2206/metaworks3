var Tab = function(objectId, className){
	console.debug("Tab");
	
	this.objectId = objectId;
	this.className = className;
		
	var objectMetadata = mw3.getMetadata(className);
		
	if(objectMetadata.faceOptions['tabsBottom'] == 'true'){
		$('#objDiv_' + objectId).addClass('tabs-bottom');
	}	
	
	$('#objDiv_' + objectId).addClass('mw3_tab').css('height', '100%');
	$('#objDiv_' + objectId).tabs();
	$('#objDiv_' + objectId).removeClass( "ui-corner-all");
	
	$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" ).removeClass( "ui-corner-all ui-corner-top" ).addClass( "ui-corner-bottom" );
}