var org_uengine_kernel_ProcessVariable = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];	
};

org_uengine_kernel_ProcessVariable.prototype = {
	getValue : function(){
		if(!this.object)
			this.object = {__className : this.className};			
		
		this.object['name'] = this.objectDiv.find('select').val();
		
		return this.object;
	}
};