var org_metaworks_example_ide_SourceCode = function(objectId, className){
	this.objectId = objectId;

	this.divName = "sourceEditor_" + objectId;	

//	$("#"+divName).width(400);
//	$("#"+divName).height(200);
	

    this.editor = ace.edit(this.divName);
    this.editor.setTheme("ace/theme/eclipse");

    var JavaScriptMode = require("ace/mode/java").Mode;
    this.editor.getSession().setMode(new JavaScriptMode());
    
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

org_metaworks_example_ide_SourceCode.prototype.getValue = function(){
	var object = {
			__className: "org.metaworks.example.ide.SourceCode",
			code: this.editor.getSession().getValue()
	};
	
	return object;
}