var org_uengine_codi_mw3_model_IWorkItem = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var container = $('#objDiv_' + this.objectId).find('.formcontexttitle span').eq(0);
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
	
	if(workItem.type == null && (workItem.workItemHandler == null || workItem.workItemHandler.instanceId==null) && workItem.status == 'NEW' && workItem.tool != 'formApprovalHandler'){ //means we need to load workItemHandler
		workItem.detail();
	}

	if(workItem.type == 'memo' && workItem.extFile!=null && workItem.memo.contents=="...loading..." && !workItem.contentLoaded){
		workItem.loadContents();
	}

	if(workItem.type == 'src' && workItem.extFile!=null && !workItem.contentLoaded){
		workItem.loadContents();
	};
}

org_uengine_codi_mw3_model_IWorkItem.prototype = {
	openFormApprovalHandler : function(){
		var object = mw3.getObject(this.objectId);
		var location = window.location;
				
		var url = location.origin.replace('8080','7080') + '/uengine-web/processparticipant/worklist/workitemHandler.jsp?taskId='+object.taskId+'&instanceId='+object.instId+'&tracingTag='+object.trcTag;
		
		window.open(url);
	}
};

