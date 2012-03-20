var org_metaworks_example_ide_SourceCode = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	this.divName = "sourceEditor_" + objectId;	

//	$("#"+divName).width(400);
//	$("#"+divName).height(200);
	

    this.editor = ace.edit(this.divName);
    this.editor.setTheme("ace/theme/eclipse");
//    this.editor.

    var JavaScriptMode = require("ace/mode/java").Mode;
    this.editor.getSession().setMode(new JavaScriptMode());
    
    var theEditor = this.editor;
    var theSourceCodeObjId = objectId;
    
    
    var ctrlDown = false;	
	var ctrlKey = 17, spaceKey = 81;//32
	
    
    document.addEventListener(
		"keydown",
   		function(e) {			
			if (e && e.keyCode == ctrlKey && !ctrlDown && theEditor.textInput.getElement() == e.srcElement) {
				ctrlDown = true;
				console.log("ctrlDown = " + ctrlDown);
			}
			//console.log("space down : " + e.keyCode);
			if (e && ctrlDown && e.keyCode == spaceKey && theEditor.textInput.getElement() == e.srcElement) {
				//console.log("space down");
				e.returnValue = false;
			}
			
			if (e && e.keyCode==190 && theEditor.textInput.getElement() == e.srcElement) {
				var whereEnd = theEditor.getCursorPosition();
				var whereStart = {column: 0, row: whereEnd.row};
				
				
				var line = theEditor.getSession().doc.getTextRange({start: whereStart, end: whereEnd});
				
				//alert(line);
				
				mw3.mouseX = e.srcElement.offsetLeft;
				//mw3.mouseY = e.srcElement.offsetTop - 3;
				mw3.mouseY = e.srcElement.offsetTop + 15;
				
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
	    },
		
		false
    );

    document.addEventListener(
		"keyup",

   		function(e) {
			if (e && e.keyCode == ctrlKey && ctrlDown && theEditor.textInput.getElement() == e.srcElement) {				
				ctrlDown = false;	
				console.log("ctrlDown = " + ctrlDown);
			}
			
			//console.log("keyCode = " + e.keyCode + ", " + theEditor.textInput.getElement() == e.srcElement);
			
			if (ctrlDown && e.keyCode == spaceKey && theEditor.textInput.getElement() == e.srcElement) {				
				var whereEnd = theEditor.getCursorPosition();
				var whereStart = {column: 0, row: whereEnd.row};				
				var line = theEditor.getSession().doc.getTextRange({start: whereStart, end: whereEnd});
				
				var fullLine = theEditor.getSession().doc.getLine(whereEnd.row);
				
				//alert(line + " : " + fullLine);
				
				var sourceCode = mw3.getObject(theSourceCodeObjId);				
				sourceCode.lineAssistRequested = fullLine;
				sourceCode.clientObjectId = theSourceCodeObjId;
				sourceCode = sourceCode.requestAssist();
				
				
				ctrlDown = false;
				
				/*
				
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
				
				//      that means the following code must be more comprehensive than this and also the value should be persist.
				//		get again from the result
				//		sourceCode = mw3.getObject(theSourceCodeObjId);

				//alert(sourceCode.assistance);
				
				*/
			}
			
		},
		
		false
	);
    
    
   // var value = mw3.getObject(objectId);  //TODO: it's risky... may pose infinite loop. since this constructor may be called by mw3.getObject()
    var value = mw3.objects[objectId];   
    if(value!=null){
    	
    	this.editor.getSession().setValue(value.code);
    	
    	if(value.compileErrors){
    		for(var i in value.compileErrors){
    			var compileError = value.compileErrors[i];

    			this.editor.getSession().setAnnotations([{ 
                    row: compileError.line -1, 
                    column: compileError.column -1, 
                    text: compileError.message, 
                    type: "error" 
                  }]); 
//    			
//    			this.editor.getSession().setAnnotations([{ 
//    				  row: 1, 
//    				  column: 1, 
//    				  text: "Strange error", 
//    				  type: "error" // also warning and information 
//    				}]); 
//    			
    			//this.editor.getSession().addMarker(new Range(1,1,1,5), "warning");
 //   			var markerId = editor.renderer.addMarker(new Range(1, 0, 2, 0), 
  //  					"warning", "line"); 

    		}
    	}
    }
}

org_metaworks_example_ide_SourceCode.prototype.codeAssist = function(e){
	
}


org_metaworks_example_ide_SourceCode.prototype.getValue = function(){
	
	var object = mw3.objects[this.objectId]
	
	if(object==null){
		object = {code: "", className: this.className};
	}
	
	object.code = this.editor.getSession().getValue()
	
	return object;
}