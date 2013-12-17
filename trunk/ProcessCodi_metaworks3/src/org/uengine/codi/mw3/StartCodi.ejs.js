var org_uengine_codi_mw3_StartCodi = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;	
	
	if(this.object.key == 'loader'){
		var id = getCookie("codi.id");
	
		if(id)
			this.object.key = 'login';
		
		this.object.load();
	}else if(this.object.key == 'logout')
		this.object.logout();
	else if(this.object.key == 'login')
		this.object.login();
};

org_uengine_codi_mw3_StartCodi.prototype = {
	startLoading : function(){
	}
};