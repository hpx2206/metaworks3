var org_uengine_codi_mw3_admin_WebEditor = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.editorId = 'ckEditor_' + this.objectId;

	
	this.editor = CKEDITOR.replace(this.editorId, {
		fullPage : true,
		extraPlugins : 'docprops',
		htmlEncodeOutput : false,
		entities : false
	});
}

org_uengine_codi_mw3_admin_WebEditor.prototype.getValue = function() {
	
	if(this.editor) {
		var editorId = $('[name="EDITOR"]').attr('id');
		
		var editor = {
			__className : 'org.uengine.codi.mw3.admin.WebEditor',
			contents : this.editor.getData()
		}
		return editor;
	}
	
}