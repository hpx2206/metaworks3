var org_uengine_kernel_ProcessVariable = function(objectId, className){
    // default setting
    this.objectId = objectId;
    this.className = className;
    
    $('#' + mw3._getObjectDivId(this.objectId) ).find('select').bind('change', {objectId : this.objectId},function(event){
		mw3.call(objectId, 'changeType');
	});
    
};
