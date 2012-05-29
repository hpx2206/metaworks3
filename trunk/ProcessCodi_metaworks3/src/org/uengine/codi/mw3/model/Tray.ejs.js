var org_uengine_codi_mw3_model_Tray = function(objectId, className){

	this.objectId = objectId;

}


org_uengine_codi_mw3_model_Tray.prototype.addTray = function(title, instId){
	
	var tray = mw3.getObject(this.objectId);
	tray.targetItem = {
			title: title,
			instId: instId
	};
	
	tray.addTrayItem();
	
}