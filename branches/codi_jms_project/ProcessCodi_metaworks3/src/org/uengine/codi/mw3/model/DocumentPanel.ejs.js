var org_uengine_codi_mw3_model_DocumentPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);	
};

org_uengine_codi_mw3_model_DocumentPanel.prototype = {
	toAppend : function(appendobject){		
		//var html = mw3.locateObject(object.target, null)
		
		var ul = $('#' + this.divId + ' ul');
		$('<li>').appendTo(ul).html(mw3.locateObject(appendobject, null));
		console.log($('<li>').appendTo(ul).html());		
		
		//html = html.replace('<div', '<li');
		//html = html.replace('div>', 'li>');	
		
		//ul.append(html);
	}
};