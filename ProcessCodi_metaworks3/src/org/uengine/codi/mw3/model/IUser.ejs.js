var org_uengine_codi_mw3_model_IUser = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	
	var user = mw3.objects[objectId];
	if(user == null)
		return true;

	if(user.metaworksContext && user.metaworksContext.how!='picker'){
		$('#objDiv_' + this.objectId).parent().css({'border':'none'});	
	}
	
	if(user.metaworksContext && user.metaworksContext.when=='contacts'){
		
		
		var msg=$('#objDiv_' + objectId).find('.fontgray').text();
		var comp=msg.length ;
		
		
		var count=0 ;
		
		var typing = function(){ 
			if(count<=comp){ 
				
				$('#objDiv_' + objectId).find('.fontgray').text(msg.substring(0,count));
				count++ ;
				setTimeout(function(){typing()}, 100); 
				
			}
		};
		
		typing();
		
	}

	
}

org_uengine_codi_mw3_model_IUser.prototype = {
	destroy : function(){
		this.endLoading();
	},
	startLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
			mw3.getFaceHelper(this.windowObjectId).startLoading();
		else
			mw3.startLoading(this.objectId);
	},
	endLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
		else
			mw3.endLoading(this.objectId);

	},
	showStatus : function(message){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
		else
			mw3.showInfo(this.objectId, message);
	},
	
	ableContextMenu : function(methodName){
		
		var user = mw3.objects[this.objectId];
		var session = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
		var login_userId = session.user.userId;
		
		console.log(session);
		
		if(methodName == 'unsubscribe') {				
			//admin or me or inviteuser
			if (session.employee.isAdmin || user.userId==login_userId ||
					(user.inviteUser != null&&user.inviteUser==login_userId))
				return true;
		}		
		else if(methodName == 'guestToUser') {
			//admin or inviteuser
			if(user.guest==true &&
					(session.employee.isAdmin || (user.inviteUser != null&&user.inviteUser==login_userId)))
				return true;
		}
		else if(methodName == 'userToGuest') {
			//admin or inviteuser
			if(user.guest==false &&
					(session.employee.isAdmin || (user.inviteUser != null&&user.inviteUser==login_userId)))
				return true;
		}
		   
		  return false;
	}
};
