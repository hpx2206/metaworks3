var org_uengine_codi_mw3_admin_EntityDefinition = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	var object = mw3.objects[this.objectId];

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
		var codeAssist = mw3.getAutowiredObject("org.metaworks.example.ide.CodeAssist");

		console.debug(codeAssist);
		
		mw3.call(objectId, 'generateDao');
	}	
}