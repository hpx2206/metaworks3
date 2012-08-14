var org_uengine_codi_mw3_model_IWorkItem_edit = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.commandTrigger = null;
	this.commandActivityAppAlias = null;
	this.commandParameters = null;
	this.type = null;

	$("#post_" + this.objectId).focus();
	//$("#post_" + this.objectId).keydown()
	
	$("#post_" + this.objectId).live("keyup keydown",function(){
		var h=$(this);
		h.height(21).height(h[0].scrollHeight);//where 60 is minimum height of textarea
		});
	
	
	var value = mw3.objects[objectId];

	if(value.type=="file"){
		setTimeout(function(){
			var fileUplodaerObjectId = mw3.getChildObjectId(objectId, "file");
			var fileUploaderFaceHelper = mw3.getFaceHelper(fileUplodaerObjectId);
			fileUploaderFaceHelper.addFileChangeListener(function(file){
				
				var existingText = $("#post_" + objectId).val();
				
				if(existingText==null || existingText.trim().length==0){
					//value.title = file.filename;
					//var titleObjId = mw3.getChildObjectId(objectId, "title");
					$("#post_" + objectId).val(file.filename);
				}
			});
		}, 1000);
	}
	

	
	this.sending = false;
	
	this.userAddCommands = {};

}

org_uengine_codi_mw3_model_IWorkItem_edit.prototype.getValue =  function(){	
	//console.debug("getValue()");
	
	var object = mw3.objects[this.objectId];
		
	
	if(this.commandTrigger!=null){
		object.title = this.commandTrigger;
		object.activityAppAlias = this.commandActivityAppAlias;
		
		for(var i=0; i<this.commandParameters.length; i++){
			var parameterValue = this.commandParameters[i];
			parameterValue.valueObject = mw3.getObject(parameterValue.objectId);
		}
		
		object.parameters = this.commandParameters;
	}else{
		var text = $("#post_" + this.objectId).val();
		if(text)
			object.title = text;
	}
	
	var initialFollowers = [];
	for(var idx in this.userAddCommands){
		initialFollowers[initialFollowers.length] = idx;
	}
	
	if(initialFollowers.length>0){
		object.initialFollowers = initialFollowers;
	}
	
	return object;
}

var localTaskId = -2;

org_uengine_codi_mw3_model_IWorkItem_edit.prototype.send = function(){
	var thisFaceHelper = this;
	var value = mw3.getObject(this.objectId);
	
	this.type = value.type;
	
	if(!this.sending){
		
		this.sending = true;

		var instanceViewThreadPanel = mw3.getAutowiredObject('org.uengine.codi.mw3.model.InstanceViewThreadPanel');
		
		
		if(value.type=='comment'){
			
			if(instanceViewThreadPanel){
				var newComment = JSON.parse(JSON.stringify(value));
				newComment.metaworksContext.when = 'view';
				newComment.taskId = (localTaskId--);
				newComment.__objectId = null;
				
				var toAppend = mw3.locateObject(
					{
						__className	:'org.metaworks.ToAppend',
							target	: newComment,
							parent	: instanceViewThreadPanel
					}, null, 'body'
				);
				
				//may problematic. TODO: 'toAppend.target' should point to the newly acquired object with the __objectId exists.
				var newCommentObjectId = toAppend.targetObjectId + 1;
				
				mw3.onLoadFaceHelperScript();
	
				newComment = mw3.objects[newCommentObjectId];
				
	
				try{
					newComment.add();
				}catch(e){
					//handle when fail
				}
	
				value.title = "";
				mw3.setObject(value.__objectId, value);
				
				$("#post_" + this.objectId).focus();
			}
			
			

		}else{
			value.add();
		}

	}
	
	setTimeout(function(){thisFaceHelper.sending=false;}, 1000);
}

org_uengine_codi_mw3_model_IWorkItem_edit.prototype.showCommandForm = function(processDef){
	var thisFaceHelper = this;

	var text = $("#post_" + this.objectId).val();
	var fullText = text;
	
	if(fullText==null  ||  fullText.trim().length == 0){
		fullText = processDef.cmTrgr;
	}
	
	
	var divId = "#commandDiv_" + this.objectId;


	$(divId).html("<b>" + fullText + ": </b>");
	$(divId).keydown(function(e){
		if(e.keyCode==13){
	
			thisFaceHelper.send();
	
		}
	
	});
			
	
	
	
	//processMap.processMapList[i].initiate();
	
	//break;
	
	
	//alert(processMap.processMapList[i].cmPhrase);
	//

	var entryAndPhrases = processDef.cmPhrase.split("$");
	
	this.commandTrigger=processDef.cmPhrase;
	this.commandActivityAppAlias=processDef.defId;
	
	mw3.setWhen('edit');
	
	this.commandParameters = [];//{__className: 'org.uengine.codi.mw3.model.ParameterValue[]'};

	var parameterIndex = 0;

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
				objectId: valueObject  
		};
		
		this.commandParameters[parameterIndex]['objectId'] = 0+(object.object ? object.object.__objectId : object.__objectId);
		
		parameterIndex++;
		
		$(divId).append(connector);
	}
	
	mw3.onLoadFaceHelperScript();
	mw3.setWhen('view');
	
}



org_uengine_codi_mw3_model_IWorkItem_edit.prototype.press = function(){
	var e = window.event;
	
	if (e.keyCode == 13 && !e.shiftKey) {
		this.send();
    }else{
    	
    	
    	var text = $("#post_" + this.objectId).val();
    	var fullText = text;
    	
    	var tokens  = text.split(" ");
    	if(tokens.length>1)
    		text = tokens[tokens.length-1];
    	
    	
    	//////// assists about process initiation /////////
    	
    	var processMap = mw3.getAutowiredObject("org.uengine.codi.mw3.model.ProcessMapList");
    	
    	//var divIdWithoutShaf = "commandDiv_" + this.objectId; 
		var recommendDivId = "#commandRecommendDiv_" + this.objectId;
		var recommendFirst = true;

//console.log('1');

		if(text && text.length>0 && processMap)
    	for(var i=0; i<processMap.processMapList.length; i++){
    		var commandTrigger = processMap.processMapList[i].cmTrgr+":";

    		
//    		console.log('keychar:' + e.charCode)
 //   		console.log('2:' +text + ' and commandTrigger=' + commandTrigger);

    		if(processMap.processMapList[i].name.indexOf(text)==0){
    			var theAppDiv = "#objDiv_" + processMap.processMapList[i].__objectId;

    			if(!theAppDiv['__inEffect']){
	    			theAppDiv['__inEffect'] = true;
	    			$(theAppDiv).effect("bounce", {times: 3}, 300);
    			}

    		}
    		
    		if(commandTrigger && commandTrigger.indexOf(text)==0){
        		
        		var commandPhrase = processMap.processMapList[i].cmPhrase;
        		
//        		console.log('text:'+text);
//        		console.log('commandTrigger:'+commandTrigger);
        		
        		var thisFaceHelper = this;
        		if(text==commandTrigger){//e.keyCode == 58 || e.charCode== 58 || e.keyCode == 59 || e.keyCode == 186) { //means ":" that user wants to command

        			this.showCommandForm(processMap.processMapList[i]);
	    			
	    			break;
	    			
        		}else{ //just recommend the command phrase;
        			
        			if(recommendFirst){
        				$(recommendDivId).html("" + mw3.localize('$RecommendedCommand') + ": \"<b>" + commandTrigger + "</b>\"");
        				recommendFirst = false;
        			}else{
        				
        				$(recommendDivId).append(", \"<b>" + commandTrigger + "</b>\"");
        				
        			}
        		}
        	}
    		
    	}
		
		
		/////// assists about user names //////
    	
		var contactListPanel = mw3.getAutowiredObject('org.uengine.codi.mw3.model.ContactListPanel');
		
		if(text && text.length>0 && contactListPanel){
			var contactList = contactListPanel.localContactList;

			var exisingFollowers = {};
			
			
			var value = mw3.objects[this.objectId];
			
			if(value.instId){
				var instanceView = mw3.getAutowiredObject('org.uengine.codi.mw3.model.InstanceView@' + value.instId);
				
				for(var i=0; i<instanceView.followers.followers.length; i++){
					exisingFollowers[instanceView.followers.followers[i].userId] = true;
				}
			}
			
			for(var i=0; i<contactList.contacts.length; i++){
				var contact = contactList.contacts[i];
				
				if(contact.friend && contact.friend.userId && contact.friend.name && text.indexOf(contact.friend.name) > -1 && !this.userAddCommands[contact.friend.userId] && !exisingFollowers[contact.friend.userId]){
    				$(recommendDivId).append("" + mw3.localize('$AddUserAsFollower') + ": \"<b>" + contact.friend.name + "</b>\"<br>");
    				this.userAddCommands[contact.friend.userId] = contact.friend.userId;
				}
			}
			
			
		}
		
		
		//////// assists about dates ////////
		
		//////// assists about url ////////
		
		

    }
	
	

}

