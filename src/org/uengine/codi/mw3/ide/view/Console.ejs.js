var org_uengine_codi_mw3_ide_view_Console = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	this.objectDiv.css('height', '100%');
	
};

org_uengine_codi_mw3_ide_view_Console.prototype = {
	toAppend : function(messages){
		var items = $('#items_' + this.objectId);
		
		for(var i=0; i<messages.length; i++){
			$('<option>').html(messages[i]).appendTo(items);
		}
	}
};