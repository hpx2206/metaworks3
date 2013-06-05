var org_uengine_codi_mw3_ide_editor_metadata_MetadataProperty = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
};


org_uengine_codi_mw3_ide_editor_metadata_MetadataProperty.prototype = {
	
	removeProperty: function(){
		
		var object = mw3.objects[this.objectId];
		var metadataXML =  mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataXML');
		var autowiredXml = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataXmlEditor' + '@'+metadataXML.filePath, true);

		if(autowiredXml){
			var metadataProperty = {
					__className : 'org.uengine.codi.mw3.ide.editor.metadata.MetadataProperty',
					name : object.name,
					value : object.value,
					type: object.type
			};
			
			var propertyObject = mw3.locateObject(metadataProperty, null, 'body');
			var xml = mw3.call(propertyObject.targetObjectId, 'toXmlXStream');
			
			xmlObjectId = autowiredXml.__objectId;
			xmlFaceHelper = mw3.getFaceHelper(xmlObjectId);
			xmlFaceHelper.removeProperty(xml, object.name);
		}
		
//		var metadataProperty =  mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataProperty');
//		var metadataEditor =  mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor');
//		mw3.call(metadataEditor.__objectId, 'remove');
		
	}

};