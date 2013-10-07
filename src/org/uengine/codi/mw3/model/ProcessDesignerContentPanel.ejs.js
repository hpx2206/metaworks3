var WEB_CONTEXT_ROOT = "<%=GlobalContext.WEB_CONTEXT_ROOT%>";

var org_uengine_codi_mw3_model_ProcessDesignerContentPanel = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	if(object.parentFolder!=null)
		window.frames['process_' + this.objectId].location.href = mw3.base + "/processmanager/ProcessDesigner.jnlp?folderId=" + object.parentFolder + "&userId=" + mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session').user.userId;
	else
		window.frames['process_' + this.objectId].location.href = mw3.base + "/processmanager/ProcessDesigner.jnlp?defId=" + object.defId + "&defVerId=" + object.defVerId + "&userId=" + mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session').user.userId;
	
	changeDisplayDraw(true);
}

function changeDisplayDraw(booleanValue) {
	if (booleanValue) {
		drawAll();
	} else {
		cleanAll();
	}
}


