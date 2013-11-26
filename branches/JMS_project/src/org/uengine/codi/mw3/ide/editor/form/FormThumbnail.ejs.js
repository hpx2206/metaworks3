var org_uengine_codi_mw3_ide_editor_form_FormThumbnail = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

};

org_uengine_codi_mw3_ide_editor_form_FormThumbnail.prototype = {
		loaded : function(){
			this.objectDivId = mw3._getObjectDivId(this.objectId);
			this.objectDiv = $('#' + this.objectDivId);
			var height = this.objectDiv.height();
			var html = this.objectDiv.html();
//			var html = '<html>' + this.objectDiv.html() + '</html>';
			var object = mw3.objects[this.objectId];
			object.formHtml = html;
			object.imageWidth = this.objectDiv.width();
			object.imageHeight = this.objectDiv.height();
			object.formPreView();
		}
};