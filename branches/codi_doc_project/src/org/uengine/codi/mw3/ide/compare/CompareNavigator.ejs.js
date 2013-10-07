var org_uengine_codi_mw3_ide_compare_CompareNavigator = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = 'objDiv_' + this.objectId;
	this.object = mw3.objects[this.objectId];
	
	var object = this.object;
	
	if( this.object.nodeSelect != null ){
		var selectNodeObjectId = this.object.nodeSelect.__objectId;
		$('#select_' + selectNodeObjectId).change(function(){
			object.changeFileNavigator();
		});
	}
	
};