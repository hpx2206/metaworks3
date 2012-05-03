var WEB_CONTEXT_ROOT = "<%=GlobalContext.WEB_CONTEXT_ROOT%>";

var org_uengine_codi_mw3_model_ProcessDesignerContentPanel = function(objectId, className){
	
	
	changeDisplayDraw(true);
	
}

function changeDisplayDraw(booleanValue) {
	if (booleanValue) {
		drawAll();
	} else {
		cleanAll();
	}
}


