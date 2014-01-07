var org_uengine_codi_mw3_model_WorkItemHandler = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	// 댓글달기 버튼  
	if( object && object.parameters){
		for(var i = 0; i < object.parameters.length; i++){
			
			var valObject = object.parameters[i].valueObject;
			var metadata = mw3.getMetadata(valObject.__className);
			if(metadata.fieldDescriptors){
				for (var j=0; j<metadata.fieldDescriptors.length; j++){
					var fd = metadata.fieldDescriptors[j];
					var fieldName = fd.name;
					if(!fieldName || fieldName.length == 0)
						continue;
					
					var valiableObjectId = mw3.getChildObjectId(valObject.__objectId, fieldName);
					if( valiableObjectId ){
						var valiableObjectDivId = mw3._getObjectDivId(valiableObjectId);
						var valiableObjectDiv = $('#' + valiableObjectDivId);
						valiableObjectDiv.find('input').css('float','left');
						valiableObjectDiv.find('.form-control').css('width','200px');
						var buttonHtml = '<a id="wBtn" style="float:right" href="#" onClick="mw3.getFaceHelper('+objectId+').callReply(\''+fieldName+'\' , \''+valiableObjectId+'\')"><span style="color:#000; margin-right:0px"> ' + mw3.localize('$AddComment') + ' </span></a>';
						valiableObjectDiv.append(buttonHtml);
					}
				}
			}
		}
	}
};
	
org_uengine_codi_mw3_model_WorkItemHandler.prototype = {
		callReply : function(propName , valiableObjectId){
			var workItem = mw3.objects[this.objectId];
			var comment = prompt('Enter Comment : ');
			if(comment){
				workItem.replyFieldName = propName ;
				workItem.replyTitle = comment;
				workItem.comment();
				/*
				var replyObject = mw3.call(this.objectId, 'comment');
				var html = mw3.locateObject(replyObject, replyObject.__className);
				
				var valiableObjectDivId = mw3._getObjectDivId(valiableObjectId);
				var valiableObjectDiv = $('#' + valiableObjectDivId);
				valiableObjectDiv.append(html);
				*/
			}				
		}
};
