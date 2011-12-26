mw3.importStyle("style/waveStyle/droppable_layout.css");
mw3.importScript("scripts/jquery/jquery-ui-latest.js");
mw3.importScript("scripts/jquery/jquery.layout-latest.js");

org_uengine_codi_mw3_model_Main = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	outerLayout = $('#container').layout(); 
	
//	$('#right-container').layout();

}
