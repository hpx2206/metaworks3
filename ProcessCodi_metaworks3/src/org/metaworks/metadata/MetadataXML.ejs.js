var org_metaworks_metadata_MetadataXML = function(objectId, className){
	
	this.objectId = objectId;
	this.claassName = className;
};



org_metaworks_metadata_MetadataXML.prototype = {
		
	saveProperty : function(){
		
		var object = mw3.getObject(this.objectId);
		
		var newMetadataProperty = object.newMetadataProperty;
		
		var type = newMetadataProperty.selectedType.selected;
		var value ='';
		
		
		if(type =='string'){
			value = newMetadataProperty.value;
		
		}else if(type == 'img'){
			var filePath = object.filePath;
			value = filePath.substring(0, filePath.lastIndexOf('\\'));
			value += "\\" + newMetadataProperty.file.filename;
			
			newMetadataProperty.file.upload();
			
		}else if(type == 'file'){
			var filePath = object.filePath;
			value = filePath.substring(0, filePath.lastIndexOf('\\'));
			value += "\\" + newMetadataProperty.file.filename;
			
		}else if(type == 'process'){
			
		}

		
		var autowiredXml = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataXmlEditor' + '@'+object.filePath, true);
		if(autowiredXml){
			var metadataProperty = {
					__className : 'org.metaworks.metadata.MetadataProperty',
					name : newMetadataProperty.name	,
					value : value,
					type : type,
					isKeyEditable : false
			};
			
			
			
			var propertyObject = mw3.locateObject(metadataProperty, null, 'body');
			
			var xml = mw3.call(propertyObject.targetObjectId, 'toXmlXStream');

			xmlObjectId = autowiredXml.__objectId;
			xmlFaceHelper = mw3.getFaceHelper(xmlObjectId);
			xmlFaceHelper.addProperty(xml);
		}

		
		//xml 파일저장
		var metadataEditor =  mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor');
		mw3.call(metadataEditor.__objectId, 'save');
		
		//designer reload
		var metadataContentDesigner = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataContentDesigner');
		mw3.call(metadataContentDesigner.__objectId, 'load');
		
	}
};