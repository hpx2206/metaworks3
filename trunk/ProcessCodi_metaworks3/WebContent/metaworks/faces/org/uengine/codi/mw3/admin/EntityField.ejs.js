var org_uengine_codi_mw3_admin_EntityField = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	
	var entityFieldObjId = objectId;
	
	document.addEventListener(
			"mousedown",

	   		function(e) {
				//  mouse down - right button
				if(e.which == 3){
					console.debug("entityFieldObjId : " + entityFieldObjId);
					
					
					var entityField = mw3.getObject(entityFieldObjId);
					entityField.clientObjectId = entityFieldObjId;
					
					entityField.showMenu();
				}

				/*
				if (e && e.keyCode==190 && theEditor.textInput.getElement() == e.srcElement){
					var whereEnd = theEditor.getCursorPosition();
					var whereStart = {column: 0, row: whereEnd.row};
					
					
					var line = theEditor.getSession().doc.getTextRange({start: whereStart, end: whereEnd});
					
					//alert(line);
					
					mw3.mouseX = e.srcElement.offsetLeft;
					mw3.mouseY = e.srcElement.offsetTop - 3;
					
					var sourceCode = mw3.getObject(theSourceCodeObjId);
					
					sourceCode.lineAssistRequested = line;
					
					sourceCode.cursorPosition = {
						column: whereEnd.column,
						row: whereEnd.row,
						__className : "org.metaworks.example.ide.Position"
					};
					
					sourceCode.clientObjectId = theSourceCodeObjId;
					
					//TODO: maybe later the return value from NONE targeted value should be stored by mw3.objects[objectId]
					sourceCode = sourceCode.requestAssist();
					
					//     that means the following code must be more comprehensive than this and also the value should be persist.
					//get again from the result
					//sourceCode = mw3.getObject(theSourceCodeObjId);

					//alert(sourceCode.assistance);
				}					
*/
		    },
			
			false
	    );	
}

org_uengine_codi_mw3_admin_EntityField.prototype.getValue = function(){
	var entityField = mw3.objects[this.objectId];
	
	entityField.length = $("#length_" + this.objectId).val();
	
	return entityField;
}

org_uengine_codi_mw3_admin_EntityField.prototype.startLoading = function(){
	$("#progress_" + this.objectId).html("loading");
}
