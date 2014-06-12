var org_uengine_kernel_RoleResolutionContext = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
};

org_uengine_kernel_RoleResolutionContext.prototype = {
	toOpener : function(object){
		object['__objectId'] = this.objectId;
		
		this.objectDiv.children('#rrc_' + this.objectId).val('selected');
		
		mw3.objects[this.objectId] = object;		
	},
	
	picker : function(){
		var divId = 'picker_' + this.objectId;
		
		$('body').append("<div id='" + divId + "'></div>");
		
		var metaworksContext = {
			__className : 'org.metaworks.MetaworksContext',
			when : 'edit'
		};

		mw3.recentOpenerObjectId.push(this.objectId);
		
		var object = {
			__className : 'org.uengine.codi.mw3.webProcessDesigner.RolePicker',
			id : this.objectId
		};
		
		var window = {
			__className : 'org.metaworks.widget.ModalWindow',
			open : true,
			width : 800,
			panel : object,
			metaworksContext : metaworksContext,			
		};
		
		mw3.locateObject(window, null, '#' + divId);
		mw3.onLoadFaceHelperScript();	    		
	}
};

