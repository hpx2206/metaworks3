var org_uengine_codi_mw3_model_Popup = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = "#objDiv_" + this.objectId;
	this.divObj = $("#objDiv_" + this.objectId).closest('.target_stick,.target_popup');
	
	var modalWindow = $('.ui-dialog');
	
	var zIndex = 100;
	
	if(modalWindow && modalWindow.length > 0){
		zIndex = $(modalWindow[modalWindow.length-1]).css('z-index');
		zIndex = String(Number(zIndex)+1);
	}		
	
	this.divObj.addClass("mw3_popup").attr("objectid", objectId).addClass("clue-right-rounded").addClass("cluetip-rounded").css({position:'absolute','z-index':zIndex,display:'none'});
	
	var object = mw3.objects[this.objectId];
	
	var faceHelper = this;
	
	faceHelper.createPopup(object.width, object.height, mw3.mouseX, mw3.mouseY);
};

org_uengine_codi_mw3_model_Popup.prototype = {
	createPopup : function(w,h,x,y){
	
		

		this.divObj.show();
	},
	destoryPopup : function() {
		mw3.endLoading(this.objectId);
		this.divObj.remove();
	},
	destroy : function() {
		this.destoryPopup();
	}
};