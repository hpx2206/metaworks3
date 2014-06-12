var org_uengine_codi_mw3_model_IEmployeeSignUp = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	console.log(objectId);
	
	this.object = mw3.objects[this.objectId];
	console.log(this.object);
	if(this.object == null)
		return true;	
	console.log(this.object.metaworksContext);
	console.log(this.object.metaworksContext.where);
	if(this.object && this.object.metaworksContext && this.object.metaworksContext.where == 'step1'){
		var popupObj = $("#objDiv_" + this.objectId).closest('.target_popup,.target_stick');

		var bodyHeight = $('body').height();
		var bodyWidth = $('body').width();

		
		popupObj.css({left:(bodyWidth-popupObj.width())/2 + 'px', top: (bodyHeight-popupObj.height())/2-50 + 'px'});
	}
};