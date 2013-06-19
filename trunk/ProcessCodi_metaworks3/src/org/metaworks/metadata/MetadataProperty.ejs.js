var org_metaworks_metadata_MetadataProperty = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
};


org_metaworks_metadata_MetadataProperty.prototype = {
	
	removeProperty: function(){
		
		
		var object = mw3.objects[this.objectId];
		var metadataXML =  mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataXMLImpl');

/*		var properties = metadataXML.properties;
		var array = new Array();
		
		for(var index=0; index < (properties.length) -1; index++){
			if(object.index!=properties[index].index){
				array[index] = properties[index];
			}
		}
		
		metadataXML.properties = array;*/
		
			console.log(metadataXML);
			
			var xmlData = mw3.call(metadataXML.__objectId, 'toXmlXStream');
			
//			console.log(xmlData);
			/*xmlObjectId = autowiredXml.__objectId;
			xmlFaceHelper = mw3.getFaceHelper(xmlObjectId);
			xmlFaceHelper.removeProperty(xml, object.name);*/
		
		//xml 파일저장
/*		var metadataEditor =  mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor');
		mw3.call(metadataEditor.__objectId, 'save');*/
		
		//designer reload
/*		var metadataContentDesigner = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataContentDesigner');
		mw3.call(metadataContentDesigner.__objectId, 'load');*/
//		var metadataProperty =  mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataProperty');
//		var metadataEditor =  mw3.getAutowiredObject('org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor');
//		mw3.call(metadataEditor.__objectId, 'remove');
		
	}

};