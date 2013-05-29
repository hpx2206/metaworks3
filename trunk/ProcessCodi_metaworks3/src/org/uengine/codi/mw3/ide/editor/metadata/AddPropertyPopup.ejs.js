var org_uengine_codi_mw3_ide_editor_metadata_AddPropertyPopup = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
};

org_uengine_codi_mw3_ide_editor_metadata_AddPropertyPopup.prototype = {
		saveProperty : function(){
			var object = mw3.getObject(this.objectId);
			var autowiredXml = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataXmlEditor' + '@'+object.filePath, true);
			if(autowiredXml){
				var metadataProperty = {
						__className : 'org.uengine.codi.mw3.ide.editor.metadata.MetadataProperty',
						name : object.key	,
						value : object.value,
						type : object.type.selected,
						isKeyEditable : false
					};
				
				var propertyObject = mw3.locateObject(metadataProperty, null, 'body');
				var xml = mw3.call(propertyObject.targetObjectId, 'toXmlXStream');
				
				xmlObjectId = autowiredXml.__objectId;
				xmlFaceHelper = mw3.getFaceHelper(xmlObjectId);
				xmlFaceHelper.addProperty(xml);
			}
		}
};
