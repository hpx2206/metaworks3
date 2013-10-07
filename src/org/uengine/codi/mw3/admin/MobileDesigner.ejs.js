var org_uengine_codi_mw3_admin_MobileDesigner = function(objectId, className){	
	this.objectId = objectId;
	this.className = className;

	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	var objectDiv = this.objectDiv;
	var iframe = this.objectDiv.children().load(function(){
		$('#hidBar').hide();
	});
	
};

org_uengine_codi_mw3_admin_MobileDesigner.prototype = {
	showStatus : function(message){
		if('make DONE.' == message){
			$.msg({
				bgPath : mw3.base + '/images/',
			    content : "<b>Sucess</b>"
			});
		}
	},
	make : function(){
		mw3.call(this.objectId, 'make');
	}
};