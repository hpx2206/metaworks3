var org_uengine_codi_mw3_model_WorkItemListener = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.object = mw3.objects[this.objectId];
	
	var value = this.object.applyItem;
	
	// value 는 새로 만들어진 workItem
	var session = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
	var instanceId = value.instId;
	var type = value.type;
	
	value.metaworksContext.when = 'view';
	
	if( session != null && 'sns' == session.employee.preferUX ){
		// @? 부분은 id값을 부여하여 정확한 객체를 찾기 위함
//		var instanceListObjectId = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceList@1").__objectId;
		value.metaworksContext.how = "sns";
//		$("#objDiv_" + instanceListObjectId).prepend(html);			
	}else{
		value.metaworksContext.how = "normal";
		
	}
	
	//
//	var instanceObject = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Instance@"+instanceId);
	var instanceThreadObject = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceViewThreadPanel@"+instanceId);
	if( instanceThreadObject != null && instanceThreadObject.instanceId == instanceId){
		// InstanceViewThreadPanel 이 열려있는경우
		if( type == "ovryCmnt"){
			var html = mw3.locateObject(value, null);
			
			// 댓글의 커맨트를 다는 경우
			var parentTaskId = value.overlayCommentOption.parentTaskId;
			var parentWorkitemObject = mw3.getAutowiredObject("org.uengine.codi.mw3.model.WorkItem@"+parentTaskId);
			$("#objDiv_" + parentWorkitemObject.__objectId ).append(html);	
		}else{
			instanceThreadObject.getFaceHelper().toAppend(value);
		}
	}
};

org_uengine_codi_mw3_model_WorkItemListener.prototype = {
	loaded : function(){
		// 로드 후 본인 제거
		mw3.removeObject(this.objectId);
	}
};