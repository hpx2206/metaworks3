var org_uengine_codi_mw3_model_WorkflowyNode = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];

	if(object.metaworksContext.when == mw3.WHEN_EDIT){
		$('#wfnode_content_' + this.objectId).focus();
		console.debug("mw3.how : " + object.metaworksContext.how);
		console.debug("content : " + object.content);
		console.debug("contentNext : " + object.contentNext);
		
		var how = object.metaworksContext.how;
		
		if(how == "add"){
			$('#wfnode_content_' + this.objectId).selectRange(0,0);
		}
	}
	
	$('#wfnode_content_' + this.objectId).blur(function() {
		var content = $("#wfnode_content_" + object.__objectId).val();
		
		if(content)
			object.content = content;
		
		object.metaworksContext.when = mw3.WHEN_VIEW;
	});
	
	$('#wfnode_content_' + this.objectId).focus(function() {
		object.metaworksContext.when = mw3.WHEN_EDIT;
	});	
	
	$('#objDiv_' + this.objectId).addClass("workflowy_node");
}

org_uengine_codi_mw3_model_WorkflowyNode.prototype.up = function(){
	   var searchObj = $('#objDiv_' + this.objectId).prev();
	   
	   if(!searchObj.length){
		   searchObj = $('#objDiv_' + this.objectId).parent().parent().parent().parent('div');
	   }
	   
	   if(searchObj.length){
		   if(searchObj.attr('id').indexOf('info_') == 0){
			   searchObj = searchObj.prev();
			   
			   if(searchObj.find('.workflowy_node:last').length)
				   searchObj = searchObj.find('.workflowy_node:last');
		   }
		   
		   searchObj = searchObj.find('input:first');
		   
		   searchObj.focus();
		   searchObj.selectRange(0,0);
	   }
}

org_uengine_codi_mw3_model_WorkflowyNode.prototype.getValue = function(){
	
	var object = mw3.objects[this.objectId];
	
	object.content = $('#wfnode_content_' + this.objectId).val();
		
	return object;
}

org_uengine_codi_mw3_model_WorkflowyNode.prototype.down = function(){
	   var searchObj = $('#objDiv_' + this.objectId).find('.workflowy_node:first');
	   
	   if(!searchObj.length){
		   searchObj = $('#objDiv_' + this.objectId).nextUntil('.workflowy_node');
	   }
	   
	   if(searchObj.attr('id').indexOf('info_') == 0){
		   while(!searchObj.next().length)
			   searchObj = searchObj.parent().parent().parent().parent().next();
			   
		   searchObj = searchObj.next();
	   }
	   
	   if(searchObj.length){
		   searchObj = searchObj.find('input:first');
		   
		   searchObj.focus();
		   searchObj.selectRange(0,0);
	   }		   
}


org_uengine_codi_mw3_model_WorkflowyNode.prototype.press = function(inputObj){
	var e = window.event;	
	var object = mw3.objects[this.objectId];
	
	//console.debug(e.keyCode);
	switch (e.keyCode) {
	  case 37   :   // left
		   var t = inputObj.value, s = mw3.getFaceHelper(this.objectId).getSelectionStart(inputObj), e = mw3.getFaceHelper(this.objectId).getSelectionEnd(inputObj)
		   
		   if(s == 0){
			   window.event.returnValue = false;
			   
			   mw3.getFaceHelper(this.objectId).up();			   
		   }
		   
		   break;
	  case 38   :   // up
		   window.event.returnValue = false;
		   mw3.getFaceHelper(this.objectId).up();
		   		  
		   break;
	  case 39   :   // right
		   var t = inputObj.value, s = mw3.getFaceHelper(this.objectId).getSelectionStart(inputObj), e = mw3.getFaceHelper(this.objectId).getSelectionEnd(inputObj)
		  
		   if(s == t.length){
			   window.event.returnValue = false;
			   
			   mw3.getFaceHelper(this.objectId).down();
		   }
		   
		   break;
	  case 40   :   // down
		   window.event.returnValue = false;
		   mw3.getFaceHelper(this.objectId).down();
		  
		   break;
	  case 9    :	// tab
		   window.event.returnValue = false;
		   
		   if(e.shiftKey){
			   mw3.call(this.objectId, 'outdent');
		   }else{
			   mw3.call(this.objectId, 'indent');
		   }
		  	
	       break;
	  case 13    :	// enter
		   var t = inputObj.value, s = mw3.getFaceHelper(this.objectId).getSelectionStart(inputObj), e = mw3.getFaceHelper(this.objectId).getSelectionEnd(inputObj)
		   
		   if(s == t.length){
			   object.content = t;
			   object.contentNext = "";
	       }else if(s == 0){
	    	   object.content = "";
	    	   object.contentNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';		    	  			   
		   }else{
			   object.content = t.substring(0, s).replace(/ /g, '\xa0') || '\xa0';
			   object.contentNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';
		   }
		   
		   inputObj.value = object.content;
		   
		   window.event.returnValue = false;
		   		   
		   mw3.call(this.objectId, 'add');
		   
		   break;
	  case 8     :
		  var t = inputObj.value, s = mw3.getFaceHelper(this.objectId).getSelectionStart(inputObj), e = mw3.getFaceHelper(this.objectId).getSelectionEnd(inputObj)
		  
		  if(s == e && s == 0){
		      if(s == t.length){
		    	  object.content = t;
		    	  object.contentNext = "";
		      }else if(s == 0){
		    	  object.content = "";
		    	  object.contentNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';		    	  
		      }else{
		    	  object.content = t.substring(0, s).replace(/ /g, '\xa0') || '\xa0';
		    	  object.contentNext = t.substring(s).replace(/ /g, '\xa0') || '\xa0';
		      }
			  		      		      
			  inputObj.value = object.content;
	
			  window.event.returnValue = false;
	   		   
			  mw3.call(this.objectId, 'remove');
		  }
		  
		  break;
	  default    :
	        break;
	}
}

org_uengine_codi_mw3_model_WorkflowyNode.prototype.getSelectionStart = function(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveEnd('character', o.value.length)
		if (r.text == '') return o.value.length
		return o.value.lastIndexOf(r.text)
	} else return o.selectionStart
}

org_uengine_codi_mw3_model_WorkflowyNode.prototype.getSelectionEnd = function(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveStart('character', -o.value.length)
		return r.text.length
	} else return o.selectionEnd
}

org_uengine_codi_mw3_model_WorkflowyNode.prototype.startLoading = function(){
	console.debug("startLoading");
}

org_uengine_codi_mw3_model_WorkflowyNode.prototype.showStatus = function(){
	console.debug("showStatus");	
}