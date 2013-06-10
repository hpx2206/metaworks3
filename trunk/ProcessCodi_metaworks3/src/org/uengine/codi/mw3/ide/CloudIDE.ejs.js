var org_uengine_codi_mw3_ide_CloudIDE = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];
	if(this.object == null)
		return true;
	
};

org_uengine_codi_mw3_ide_CloudIDE.prototype = {
	getValue : function(){
		var tabDivObj = $('.org_uengine_codi_mw3_ide_CloudTab.focus').children(':first');
		if(tabDivObj){
			var tabObjectId = tabDivObj.attr('objectId');
			var tabObject = mw3.getObject(tabObjectId);
			
			var tabMetadata = mw3.getMetadata(tabObject.__className);
			
			if(tabMetadata.keyFieldDescriptor){
				tabId = tabObject[tabMetadata.keyFieldDescriptor.name];
			}
			
			if(tabId){
				this.object.currentEditorId = tabId;
				
				var editor = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.Editor@' + tabId);
				if(editor)
					this.object.currentProjectId = editor.resourceNode.projectId;
			}
		}
		
		return this.object;
	}
};