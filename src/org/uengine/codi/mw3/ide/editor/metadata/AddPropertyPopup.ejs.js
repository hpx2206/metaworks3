var org_uengine_codi_mw3_ide_editor_metadata_AddPropertyPopup = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.divObj = $("#objDiv_" + this.objectId).parent("#objDiv_");
	
};

org_uengine_codi_mw3_ide_editor_metadata_AddPropertyPopup.prototype = {
		saveProperty : function(){
			
			var object = mw3.getObject(this.objectId);
			
			
			var autowiredXml = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataXmlEditor' + '@'+object.filePath, true);
			if(autowiredXml){
				var metadataProperty = {
						__className : 'org.uengine.codi.mw3.ide.editor.metadata.MetadataPropertyImpl',
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
				var popup =	{
							__className	:'org.uengine.codi.mw3.model.Popup'
						};
					
				
				var remove = mw3.locateObject(
						{
							__className	:'org.metaworks.Remover',
								target	: popup,
								match   : true
						}, null, 'body'
					);
			}
			
			
			mw3.endLoading(object);
			this.divObj.remove();
			
		}
};
