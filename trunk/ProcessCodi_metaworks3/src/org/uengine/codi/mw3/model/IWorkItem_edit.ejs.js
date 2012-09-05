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
	
	this.instanceFirst = true;
	if( value.instId != null && value.instId != 0){
		this.instanceFirst = false;
	}
	
	this.sending = false;
	// process
	this.processFirst = true;
	// user names
	this.userAddCommands = {};
	// dates
	this.initialize();
	this.dateFirst = true;
	this.dueDate = null;
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
	
	if( this.commandActivityAppAlias != null){
		object.activityAppAlias = this.commandActivityAppAlias;
	}
	var initialFollowers = [];
	for(var idx in this.userAddCommands){
		initialFollowers[initialFollowers.length] = idx;
	}
	
	if(initialFollowers.length>0){
		object.initialFollowers = initialFollowers;
	}
	if( this.dueDate != null){
		object.dueDate = this.dueDate; 
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
    	
    	
    	var recommendDivId = "#commandRecommendDiv_" + this.objectId;
    	var processDivId 		= "commandProcessDiv_" + this.objectId;
    	var followerDivId 	= "commandFollowerDiv_" + this.objectId;
    	var dateDivId 			= "commandDateDiv_" + this.objectId;
    	var recommendFirst = true;
    	//////// assists about process initiation /////////
    	
    	var processMap = mw3.getAutowiredObject("org.uengine.codi.mw3.model.ProcessMapList");
    	
    	//var divIdWithoutShaf = "commandDiv_" + this.objectId; 

//console.log('1');

		if(text && text.length>0 && processMap)
    	for(var i=0; i<processMap.processMapList.length; i++){
    		var commandTrigger = processMap.processMapList[i].cmTrgr+":";
    		var processName = processMap.processMapList[i].name;
    		if( processName == null || processName == "" ){
    			break;
    		}
//    		console.log('keychar:' + e.charCode)
 //   		console.log('2:' +text + ' and commandTrigger=' + commandTrigger);

    		if(processName.indexOf(text)==0){
    			var theAppDiv = "#objDiv_" + processMap.processMapList[i].__objectId;

    			if(!theAppDiv['__inEffect']){
	    			theAppDiv['__inEffect'] = true;
	    			$(theAppDiv).effect("bounce", {times: 3}, 300);
    			}

    		}
    		// 프로세스명을 정확히 입력시 프로세스 추천 목록이 보임
    		if( text.length>1 && this.processFirst && processName == text ){
    			var innerHtmlStr = 	"<div id=\"" + processDivId + "\" >";
	        	  innerHtmlStr		+=	" " + mw3.localize('$AddWithProcess') + ": \"<b>" ;
	        	  innerHtmlStr		+=	"<span id=\"processDivSpan\">" + processName + "</span>" ;
	        	  innerHtmlStr		+=	"</b>\"";
	        	  innerHtmlStr		+=	" | <a href=\"#\" onClick=\"mw3.getFaceHelper('"+this.objectId+"').removeDiv('"+dateDivId+"')\" style=\"cursor:pointer;\">싫어요</a> ";
	        	  innerHtmlStr		+=	"<br>";
	        	  innerHtmlStr		+=	"</div>";
	        	  
	        	  this.commandActivityAppAlias = processMap.processMapList[i].defId;
	        	  
	        	  $(recommendDivId).append(innerHtmlStr);
	        	  this.processFirst = false;
    		}else if(text.length>1 && !this.processFirst && processName == text ){
	        	  $("#processDivSpan").html(processName);
	        	  this.commandActivityAppAlias = processMap.processMapList[i].defId;
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
        				var innerHtmlStr = 	"<div>";
	  		        	  innerHtmlStr		+=	" " + mw3.localize('$RecommendedCommand') + ": \"<b>" ;
	  		        	  innerHtmlStr		+=	"<span id=\"trigerDivSpan\">" + commandTrigger + "</span>" ;
	  		        	  innerHtmlStr		+=	"</b>\"";
	  		        	  innerHtmlStr		+=	"</div>";
	  		        	  
	  		        	  $(recommendDivId).append(innerHtmlStr);
	  		        	  recommendFirst = false;
        			}else{
        				$("#trigerDivSpan").append(", \"<b>" + commandTrigger + "</b>\"");
        			}
        		}
        	}
    		
    	}
		
    	if(  this.instanceFirst ){
			//////// assists about dates ////////
			if(text && text.length>1){
				date = Date.parseHangul(text) || Date.parse(text);
		          if (date !== null && this.dateFirst ) {
		        	  var innerHtmlStr = 	"<div id=\"" + dateDivId + "\" >";
		        	  innerHtmlStr		+=	" " + mw3.localize('$AddDate') + ": \"<b>" ;
		        	  innerHtmlStr		+=	"<span id=\"dateDivSpan\">" + date.toString(Date.CultureInfo.formatPatterns.fullDateTime) + "</span>" ;
		        	  innerHtmlStr		+=	"</b>\"";
		        	  innerHtmlStr		+=	" | <a href=\"#\" onClick=\"mw3.getFaceHelper('"+this.objectId+"').removeDiv('"+dateDivId+"')\" style=\"cursor:pointer;\">싫어요</a> ";
		        	  innerHtmlStr		+=	"<br>";
		        	  innerHtmlStr		+=	"</div>";
		        	  
		        	  $(recommendDivId).append(innerHtmlStr);
		        	  this.dateFirst = false;
		        	  this.dueDate = date;
		          }else if(date !== null && !this.dateFirst ){
		        	  $("#dateDivSpan").html(date.toString(Date.CultureInfo.formatPatterns.fullDateTime));
		        	  this.dueDate = date;
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
		
		//////// assists about url ////////
		
		

    }
};
org_uengine_codi_mw3_model_IWorkItem_edit.prototype.removeDiv = function(divId){
	$("#" + divId ).remove();
	if( divId.indexOf("commandDateDiv_") == 0  ){
		this.dateFirst = true;
		this.dueDate = null;
	}else if( divId.indexOf("commandProcessDiv_") == 0  ){
		this.processFirst = true;
		this.commandActivityAppAlias = null;
	}
};
/**
 * datejs.js using method
 * @url       http://www.firejune.com/1343
 * @usage:    Date.parseHangul(value); 
 */
org_uengine_codi_mw3_model_IWorkItem_edit.prototype.initialize = function(){
	this.datePatterns = {
	        '-3':/^그끄제|그끄저께/i,'-2':/^그제|그저께/i,'+2':/^모레/i,'+3':/^글피/i,'+4':/^그글피/i,
	        '1':/^하루|일일/i,'2':/^이틀|이일/i,'3':/^사흘|삼일/i,'4':/^나흘|사일/i,'5':/^닷세|오일/i,
	        '6':/^엿세|육일/i,'7':/^이레|칠일/i,'8':/^여드레|팔일/i,'9':/^아흐레|구일/i,
	        '10':/^열흘|십일/i,'11':/^열하루|열하레|십일일/i,'12':/^열이틀|십이일/i,
	        '13':/^열사흘|열산날|십삼일/i,'14':/^열나흘|열낫날|십사일/i,'15':/^보름|열닷세|십오일/i,
	        '16':/^열엿세|십육일/i,'17':/^열이레|십칠일/i,'18':/^열여드레|십팔일/i,
	        '19':/^열아흐레|십구일/i,'20':/^스무날|이십일/i,'21':/^스무하루|스무하레|이십일일/i,
	        '22':/^스무이틀|이십이일/i,'23':/^스무사흘|이십삼일/i,'24':/^스무나흘|스무낫날|이십사일/i,
	        '25':/^스무닷세|이십오일/i,'26':/^스무엿세|이십육일/i,'27':/^스무이레|이십칠일/i,
	        '28':/^스무여드레|이십팔일/i,'29':/^스무아흐레|이십구일/i,'30':/^설흔날|서른날|삼십일/i,
	        '31':/^서른하루|삼십일일/i
	      };

	      this.regex = {
	        time: /[0-9]+시|[0-9]+분|[0-9]+초|오후|저녁|밤/g,
	        date: /[0-9]+년|[0-9]+월|[0-9]+일/g,
	        years: /[0-9]+년/i,
	        months: /[0-9]+월/i,
	        days: /[0-9]+일/i,
	        second: /^[0-9]+초/i,
	        minute: /^[0-9]+분/i,
	        hour: /^[0-9]+시(간)?/i,
	        pm: /^오후|저녁|밤/i,
	        am: /^오전|아침/i,
	        after: /(이|\s)?후/i,
	        befroe: /(이|\s)?전/i
	      };

	      Date.parseHangul = this.analyze.bind(this);
};
org_uengine_codi_mw3_model_IWorkItem_edit.prototype.analyze = function(value){
    if (!value) return null;

    var time = this.searchTime(value);
    var date = this.searchDate(value);

    if (time) {
      if (!date) {
        date = new Date();
        date = new Date(date.getFullYear(), date.getMonth() + 1, date.getDate());
      }
      date = new Date(date.getTime() + time);
    }

    return isNaN(date) ? null : date;
};
org_uengine_codi_mw3_model_IWorkItem_edit.prototype.searchTime = function(str){
	 var pattern = str.match(this.regex.time), times = 0;
     if (pattern && pattern.length > 1 || 
       (str.match(this.regex.am) || str.match(this.regex.pm)) && str.match(this.regex.time)) {
       for (var i = 0; i < pattern.length; i++) {
         if (pattern[i].match(this.regex.pm)) times += 43200000;
         if (pattern[i].match(this.regex.hour)) times += parseInt(pattern[i]) * 3600000;
         if (pattern[i].match(this.regex.minute)) times += parseInt(pattern[i]) * 60000;
         if (pattern[i].match(this.regex.second)) times += parseInt(pattern[i]) * 1000;
       }

       return times || null;
     } else return null;
};
org_uengine_codi_mw3_model_IWorkItem_edit.prototype.searchDate = function(str){
	var pattern = str.match(this.regex.date);
    if (pattern && pattern.length == 3)
      for (var i = 0; i < pattern.length; i++)
        pattern[i] = parseInt(pattern[i]);
    else {
      pattern = [];
      var years = str.match(this.regex.years),
      months = str.match(this.regex.months),
      days = str.match(this.regex.days);
      
      pattern.push(years ? parseInt(years[0]) : new Date().getFullYear());
      pattern.push(months ? parseInt(months[0]) : new Date().getMonth() + 1);
      days = days ? parseInt(days[0]) : null;

      if (!days)
        for (var regex in this.datePatterns)
          if (str.match(this.datePatterns[regex])) days = regex;

      if (!days) return null;
      else if (str.match(this.regex.befroe) || str.match(this.regex.after)) {
        if (str.match(this.regex.befroe)) days = - days;
        pattern.push(new Date().getDate() + days  * 1);
      } else {
        if (typeof days == 'string' && days.match(/\+|\-/))
          days = new Date().getDate() + days * 1;
        pattern.push(days);
      }
    }

    return new Date(pattern[0], pattern[1] - 1, pattern[2]);
};
