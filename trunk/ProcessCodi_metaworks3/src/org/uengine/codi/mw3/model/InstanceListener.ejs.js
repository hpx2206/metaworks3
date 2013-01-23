var org_uengine_codi_mw3_model_InstanceListener = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.object = mw3.objects[this.objectId];

	if(this.object){
		var faceHelper = this;
		
		mw3.removeObject(this.objectId);
		
		var value = this.object.applyItem;
		value.instanceViewThreadPanel = null;
		
		var preferUx = mw3.fn.getPreferUx();		
		var instId = value.instId;
				
		value.metaworksContext.how = preferUx;
		
		if(this.object.command == 'refresh'){
			// sns 모드일 경우 instance 가 열려있지 않을 경우에만 action
			if( 'sns' != preferUx || !faceHelper.isOpenInstance(instId) )
				faceHelper.changeInstance(instId, value);
										
		}else{
			// sns 모드일 경우 instance 가 열려있지 않을 경우에만 action
			if( 'sns' != preferUx || !faceHelper.isOpenInstance(instId) ){
				faceHelper.removeInstance(instId);
				faceHelper.appendInstance(value);	
			}
		}
			
		mw3.onLoadFaceHelperScript();
	}
};

org_uengine_codi_mw3_model_InstanceListener.prototype = {		
	isOpenInstance : function(instId){
		var object = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceViewThreadPanel@" + instId, true);
		if(object != null && typeof object != 'undefined')
			return true;
		
	},
	removeInstance : function(instId){
		var object = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IInstance@" + instId, true);
		if(object != null && typeof object != 'undefined')
			mw3.removeObject(object.__objectId);
		
	},
	appendInstance : function(value){
		// @? 부분은 id값을 부여하여 정확한 객체를 찾기 위함
		var object = mw3.getAutowiredObject("org.uengine.codi.mw3.model.InstanceList@1", true);
		if(object != null && typeof object != 'undefined' && object.getFaceHelper() && object.getFaceHelper().toPrepend)
			object.getFaceHelper().toPrepend(value);
		
		
	},
	changeInstance : function(instId, value){
		var object = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IInstance@" + instId, true);
		if(object != null && typeof object != 'undefined')
			mw3.setObject(object.__objectId, value);
	}
}