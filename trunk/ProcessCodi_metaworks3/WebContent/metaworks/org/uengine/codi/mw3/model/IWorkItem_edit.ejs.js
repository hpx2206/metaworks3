var org_uengine_codi_mw3_model_IWorkItem_edit = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.commandTrigger = null;
	this.commandActivityAppAlias = null;
	this.commandParameters = null;

	$("#post_" + this.objectId).focus();
	//$("#post_" + this.objectId).keydown()

}

org_uengine_codi_mw3_model_IWorkItem_edit.prototype.getValue =  function(){	
	//console.debug("getValue()");
	
	var object = mw3.objects[this.objectId];
		
	
	if(this.commandTrigger!=null){
		object.title = this.commandTrigger;
		object.activityAppAlias = this.commandActivityAppAlias;
		
		for(var i=0; i<this.commandParameters.length; i++){
			var parameterValue = this.commandParameters[i];
			parameterValue.valueObject = mw3.getObject(parameterValue.valueObject);
		}
		
		object.parameters = this.commandParameters;
	}else{
		var text = $("#post_" + this.objectId).val();
		if(text)
			object.title = text;
	}
	
	return object;
}

org_uengine_codi_mw3_model_IWorkItem_edit.prototype.press = function(){
	var e = window.event;
	
	if (e.keyCode == 13) {
		var value = mw3.getObject(this.objectId);
		
		
		
		
		value.add();
    }else{
    	
    	
    	var text = $("#post_" + this.objectId).val();
    	var fullText = text;
    	
    	var tokens  = text.split(" ");
    	if(tokens.length>1)
    		text = tokens[tokens.length-1];
    	
    	var processMap = mw3.getAutowiredObject("org.uengine.codi.mw3.model.ProcessMapList");
    	
		var divId = "#commandDiv_" + this.objectId;
		var recommendDivId = "#commandRecommendDiv_" + this.objectId;
		var recommendFirst = true;
		var parameterIndex = 0;
		
		if(text && text.length>0)
    	for(var i=0; i<processMap.processMapList.length; i++){
    		var commandTrigger = processMap.processMapList[i].cmTrgr;

    		if(commandTrigger && commandTrigger.indexOf(text)==0){
        		
        		var commandPhrase = processMap.processMapList[i].cmPhrase;
        		
        		if(e.keyCode == 59 || e.keyCode == 186) { //means ":" that user wants to command

            		$(divId).html("<b>" + fullText + ": </b>");
            		

        		
//        		processMap.processMapList[i].initiate();
        		
//        		break;
        		
        		
//        		alert(processMap.processMapList[i].cmPhrase);
//        
        		
	        		var entryAndPhrases = commandPhrase.split("$");
	        		
	            	this.commandTrigger=commandPhrase;
	            	this.commandActivityAppAlias=processMap.processMapList[i].defId;
	        		
	    			mw3.setWhen('edit');
	    			
	    			this.commandParameters = [];//{__className: 'org.uengine.codi.mw3.model.ParameterValue[]'};
	
	    			if(entryAndPhrases.length>1)
	        		for(var entryIndex=1; entryIndex<entryAndPhrases.length; entryIndex++){
	        			var entry = entryAndPhrases[entryIndex];
	        			var entryNameAndClassName = entry.split("<");
	        			var entryName = entryNameAndClassName[0];
	        			var classNameAndConnector = entryNameAndClassName[1].split(">");
	        			var className = classNameAndConnector[0];
	        			var connector = classNameAndConnector[1];
	        			
	//        			alert(entryName);
	//        			alert(className);
	        			
	        			
	    				var object = new MetaworksObject({
	                        __className : className//,
	                        //metaworksContext: {when:'edit'}
	                    }, divId);
	    				
	
	    				var valueObject = 0+(object.object ? object.object.__objectId : object.__objectId);
	    				
	        			this.commandParameters[parameterIndex] = {
	        					__className: 'org.uengine.codi.mw3.model.ParameterValue',
	        					variableName: entryName,
	        					valueObject: valueObject  
	        			};
	        			
	        			this.commandParameters[parameterIndex].valueObject = 0+(object.object ? object.object.__objectId : object.__objectId);
	    				
	        			parameterIndex++;
	        			
	    				$(divId).append(connector);
	        		}
	    			
	    			mw3.setWhen('view');
	    			
	    			break;
	    			
        		}else{ //just recommend the command phrase;
        			
        			if(recommendFirst){
        				$(recommendDivId).html("" + mw3.localize('$RecommendedCommand') + ": \"<b>" + commandTrigger + ":</b>\"");
        				recommendFirst = false;
        			}else{
        				
        				$(recommendDivId).append(", \"<b>" + commandTrigger + ":</b>\"");
        				
        			}
        		}
        	}
    		
    	}
    	
    }
}