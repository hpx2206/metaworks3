
var lastMore;

var org_uengine_codi_mw3_model_InstanceList = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.divObj = $('#' + this.divId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');

	var object = mw3.objects[objectId];
	
	if(object && object.instances == null)
		lastMore = objectId;
};

org_uengine_codi_mw3_model_InstanceList.prototype = {
	startLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
			mw3.getFaceHelper(this.windowObjectId).startLoading();
	},
	endLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
	},
	showStatus : function(message){
		
	},
	toPrepend : function(target){
		if(target.__className == 'org.uengine.codi.mw3.model.Instance' || target.__className == 'org.uengine.codi.mw3.model.IInstance'){
			var isAuth = false;

			var session = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
			var userId = session.employee.empCode;
			var partCode = session.employee.partCode;
			
			if(target.secuopt == '0' || target.secuopt == '2'){
				isAuth = true;
				
			} else if(target.secuopt == '1'){			// follower
				for(var i=0; i<target.followers.followers.length; i++){
					var user = target.followers.followers[i];
					
					if(user.userId == userId){
						isAuth = true;
						break;
					}
				}
				
				if(!isAuth){
					for(var i=0; i<target.followers.deptFollowers.length; i++){
						var dept = target.followers.deptFollowers[i];
						
						if(dept.partCode == partCode){
							isAuth = true;
							break;
						}
					}					
				}

			}else if(target.secuopt == '3'){	// topic
				
			}
			
			if(isAuth){
				var html = mw3.locateObject(target, null);
				
				$('<div>').prependTo(this.divObj).append(html);
			}
		}			
	}
};
