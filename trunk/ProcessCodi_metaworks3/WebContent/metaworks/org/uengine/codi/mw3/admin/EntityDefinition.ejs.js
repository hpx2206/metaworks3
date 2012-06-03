var org_uengine_codi_mw3_admin_EntityDefinition = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = 'objDiv_' + this.objectId;
	
	var object = mw3.objects[this.objectId];
	
	if(object.entityName != null){
		var windowObjId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
		
		if(windowObjId){
			mw3.getFaceHelper(windowObjId).setTitle(object.className + '.java');
		}
	}
	
	// overrides the function
	object.generateDDL = function() {
		console.debug(object);
		if(object.entityName == null || object.entityName.trim() == ""){
			alert("'Entity Name' 을 먼저 입력하세요.");
			
			return false;
		}

		if(object.entityFields == null){
			alert("'Entity Field' 을 먼저 추가하세요.");
			
			return false;
		}

		$("#objDiv_" + objectId).parentsUntil("mw3_tab").tabs("select",1);
		
		mw3.call(objectId, 'generateDDL');
	}

	object.generateDao = function() {
		
		if(object.parentFolder == null){
			object.parentFolder = "1";
		}
		
		mw3.call(objectId, 'generateDao');
		
		var importValue = 'import ' + object.packageName + '.' + object.entityName + ';';
		importValue = importValue + '\n' + 'import ' + object.packageName + '.I' + object.entityName + ';';
		
		var codeAssist = mw3.getAutowiredObject("org.metaworks.example.ide.CodeAssist");
		codeAssist.extendImportValue = importValue;
		codeAssist.ExtendImport();
		
	}	
}