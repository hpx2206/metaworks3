var org_uengine_codi_mw3_model_Tray = function(objectId, className){

	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	var thisWidth = $(this.divId).width()-550;
	var trayObject = $(this.divId).find('#sm01');
	
	
	trayObject.width(thisWidth/trayObject.length);
	
	if(trayObject.width() > 160){
		trayObject.width(160)
	}

}


org_uengine_codi_mw3_model_Tray.prototype.addTray = function(title, instId){
	
	var tray = mw3.getObject(this.objectId);
	tray.targetItem = {
			title: title,
			instId: instId
	};
	
	tray.addTrayItem();
	
}