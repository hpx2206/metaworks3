var org_uengine_codi_mw3_admin_WebEditor = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.editorId = 'ckEditor_' + this.objectId;
	
	this.editor = $('#' + this.editorId);
	
	CKEDITOR.replace(this.editorId, {
		fullPage : true,
		extraPlugins : 'docprops'			
	});
}

org_uengine_codi_mw3_admin_WebEditor.prototype.getValue = function() {
	
	if($('[name="EDITOR"]').attr('id')) {
		var editorId = $('[name="EDITOR"]').attr('id');
		
		var editor = {
			__className : 'org.uengine.codi.mw3.admin.WebEditor',
			contents : eval('CKEDITOR.instances.'+editorId+'.getData()')
		}
		return editor;
	}
	
}