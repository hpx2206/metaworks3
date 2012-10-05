var org_uengine_codi_mw3_model_ProcessMapList = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = '#objDiv_' + this.objectId;
	
	$(this.divId).addClass("processMapList").attr('objectId', objectId);
	
	$(this.divId).fadeTo(0,0.5);
	
	$(this.divId).hover(
			function(){
				$(this).fadeTo(500,1);
			},function(){
				$(this).fadeTo(500,0.5);
			}
	);
	
}

org_uengine_codi_mw3_model_ProcessMapList.prototype = {
}