var org_uengine_codi_mw3_model_RoleList = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);	
}

org_uengine_codi_mw3_model_RoleList.prototype = {
	toAppend : function(html){		
		html = html.replace('<div', '<li');
		html = html.replace('div>', 'li>');
		
		var ul = $('#' + this.divId + ' ul');
		ul.append(html);
	}
}