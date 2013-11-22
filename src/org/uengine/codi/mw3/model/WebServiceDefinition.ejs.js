var org_uengine_codi_mw3_model_WebServiceDefinition = function(objectId, className) {
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	mw3.afterCall = function(method, result){
		  if(method == "generateAdapter"){
		   
		    var importValue;
		 
		  	for(var i=0;i<object.clsList.length;i++) {
		  		importValue +=  'import ' + object.clsList[i].packageName + '.' + object.clsList[i].className + ';\n';
		  	}
		  	
		  	 var codeAssist = mw3.getAutowiredObject("org.metaworks.example.ide.CodeAssist");
		  	 codeAssist.extendImportValue = importValue;
		  	 codeAssist.ExtendImport();
		  	 
		  }
		  
	}
    
}