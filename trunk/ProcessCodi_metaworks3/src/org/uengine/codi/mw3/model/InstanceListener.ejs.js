var org_uengine_codi_mw3_model_InstanceListener = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.object = mw3.objects[this.objectId];

	console.log('org_uengine_codi_mw3_model_InstanceListener');
	
	if(this.object){
		mw3.removeObject(this.objectId);
		
		var value = this.object.applyItem;
		value.instanceViewThreadPanel = null;
		
		// value 는 
		var session = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
		var instanceId = value.instId;
		
		// @? 부분은 id값을 부여하여 정확한 객체를 찾기 위함
		var instanceList = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceList@1");
		var instanceObject = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Instance@"+instanceId);
		
		console.log('instanceObject : ');
		console.log(instanceObject);
		
		if( session != null && 'sns' == session.employee.preferUX ){
			value.metaworksContext.how = "sns";
			instanceThreadObject = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceViewThreadPanel@"+instanceId);
			if( instanceThreadObject != null && instanceThreadObject.instanceId == instanceId){
				// InstanceViewThreadPanel 이 열려있는경우
			}else{
				mw3.removeObject(instanceObject.__objectId);
				
				instanceList.getFaceHelper().toPrepend(value);
			}
		}else{
			value.metaworksContext.how = "normal";
			mw3.removeObject(instanceObject.__objectId);
			
			instanceList.getFaceHelper().toPrepend(value);			
		}
		
		mw3.onLoadFaceHelperScript();
	}
	
	
	
//		var html = mw3.locateObject(value, null);
//		var instanceThreadObject = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceViewThreadPanel@"+instanceId);
//		if( instanceThreadObject != null ){
//			// InstanceViewThreadPanel 이 열려있는경우
//			if( type == "ovryCmnt"){
//				// 댓글의 커맨트를 다는 경우
//				var parentTaskId = value.overlayCommentOption.parentTaskId;
//				var parentWorkitemObject = mw3.getAutowiredObject("org.uengine.codi.mw3.model.WorkItem@"+parentTaskId);
//				$("#objDiv_" + parentWorkitemObject.__objectId ).prepend(html);	
//			}else{
//				 InstanceViewThreadPanel의 마지막 워크 아이템에 새로운 워크아이템을 붙인다.
//				$("#objDiv_" + instanceThreadObject.thread[instanceThreadObject.thread.length-1].__objectId ).append(html);	
//			}
//		}
			
//			console.log(value);
//		
};