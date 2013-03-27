var org_uengine_codi_mw3_model_IWorkItem = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.object = mw3.objects[this.objectId];
	
	this.objectDiv.css('position', 'relative');

	var container = this.objectDiv.find('.formcontexttitle span').eq(0);
	var doc = container.html();
	
	var regURL = new RegExp("(http|https|ftp|telnet|news|irc)://([-/.a-zA-Z0-9_~#%$?&=:200-377()]+)","gi");	
	
	try{
		container.html(doc.replace(regURL,"<a href='$1://$2' target='_blank' style='text-decoration:underline'>$1://$2</a>"));
	}catch(e){
		if(window.console)
			console.log(e);
	}
	
	var workItem = mw3.objects[objectId];
	this.type = workItem.type;

	//if(workItem.type == null && (workItem.workItemHandler == null || workItem.workItemHandler.instanceId==null) && workItem.status == 'NEW' && workItem.tool != 'formApprovalHandler'){ //means we need to load workItemHandler	
	if(workItem.type == null && (workItem.workItemHandler == null || workItem.workItemHandler.instanceId==null) && workItem.tool != 'formApprovalHandler'){ //means we need to load workItemHandler
		workItem.detail();
	}else if(workItem.type == 'memo' && workItem.extFile!=null && workItem.memo.contents=="...loading..." && !workItem.contentLoaded){
		this.extFile = workItem.extFile;
		console.log("loadContent" + objectId);
		//workItem.loadContents();
		mw3.call(this.objectId, 'loadContents');
		
	}else if(workItem.type == 'src' && workItem.extFile!=null && !workItem.contentLoaded){
		workItem.loadContents();
	}
};

org_uengine_codi_mw3_model_IWorkItem.prototype = {
	loaded : function(){
		/*
		var parentList = this.objectDiv.parent('.workitem_list');
		
		if(parentList.length == 0)
			parentList = this.objectDiv.parentsUntil('.workitem_list');
		
		parentList = parentList.parent();
		
		parentList.triggerHandler('loadedItem', [this.object.taskId, this.objectId]);
		*/
	},
	openFormApprovalHandler : function(){
		var object = mw3.getObject(this.objectId);
		var location = window.location;
				
		var url = location.origin.replace('8080','7080') + '/uengine-web/processparticipant/worklist/workitemHandler.jsp?taskId='+object.taskId+'&instanceId='+object.instId+'&tracingTag='+object.trcTag;
		
		window.open(url);
	}
	/*
	,toAppend : function(value){
		if(window.console){
			var loginUserId = mw3.fn.getLoginUserId();
			
			console.log('------ overlay append log ------');
			console.log('target workitem type : ' + this.object.type);
			console.log('target workitem align : ' + ((this.object.writer.userId == loginUserId)?'right':'left'));
		}
		
		var html = mw3.locateObject(value, null);
		
		var appendDiv = $('#objDiv_' + this.objectId); //.find('.view_box3:first');		
		appendDiv.append(html);
		
	}
	*/
};

