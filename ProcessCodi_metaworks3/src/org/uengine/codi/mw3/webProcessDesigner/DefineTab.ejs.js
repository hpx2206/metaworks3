var org_uengine_codi_mw3_webProcessDesigner_DefineTab = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	
	$( "#roleTab" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
    $( "#roleTab li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
};
