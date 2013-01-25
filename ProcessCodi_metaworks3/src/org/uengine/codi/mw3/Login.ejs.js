
var org_uengine_codi_mw3_Login = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.object = mw3.objects[this.objectId];
	
	
	var lastVisitPage = getCookie("codi.lastVisit");
	if(lastVisitPage) 
		this.object.lastVisitPage = lastVisitPage;

	if(this.object && this.object.metaworksContext && this.object.metaworksContext.how == 'logout'){
		delCookie("codi.id", "/","");
		delCookie("codi.password", "/","");
		delCookie("codi.rememberMe", "/","");
	}else{
		var rememberMe = getCookie("codi.rememberMe");
		
		if(rememberMe!=null && rememberMe){
			var id = getCookie("codi.id");
			var password = getCookie("codi.password");
			
			mw3.getInputElement(objectId, "userId").value = id;			
			mw3.getInputElement(objectId, "password").value = password;
			mw3.getInputElement(objectId, "rememberMe").checked = true;
		}		
	}

	
	/*
	// facebook login status
	FB.getLoginStatus(function(response) {
		if (response.status == 'connected'){
		    var uid = response.authResponse.userID;
			
			FB.api('/' + uid, function(response) {
				if(!mw3.getInputElement(objectId, "userId").value)
					mw3.getInputElement(objectId, "userId").value = response.email;
			});

		}	
		
	}, true);
	*/
	
//	$('#method_facebook_' + objectId).show();
	
	mw3.getInputElement(this.objectId, 'userId').focus();
 
	$('#' + this.divId).bind('keydown', function(event){
		mw3.getFaceHelper(objectId).keydown(event);
	});
};

org_uengine_codi_mw3_Login.prototype = {
	loaded : function(){
		var login = mw3.getObjectFromUI(this.objectId);
		
		if(login.userId && login.password && login.rememberMe){
			login.login();
		}
	},
	keydown : function(e){
		if(e.keyCode == 13){
			window.event.returnValue = false;
			
			mw3.call(this.objectId, 'login');
		}
	},
	getValue : function(){
		var login = mw3.getObjectFromUI(this.objectId);
		
		if (login.rememberMe){
			setCookie("codi.id", login.userId, 10, "/", "", "");
			setCookie("codi.password", login.password, 10, "/", "", "");			
			setCookie("codi.rememberMe", true, 10, "/", "", "");
		}else{
			delCookie("codi.id", "/","");
			delCookie("codi.password", "/","");
			delCookie("codi.rememberMe", "/","");
		}

		var lastVisitPage = getCookie("codi.lastVisit");
		if(lastVisitPage) 
			login.lastVisitPage = lastVisitPage;

		return login;
	},
	startLoading : function(){
		$('body').prepend('<div id=\"mw3_progress\" style=\"position:absolute; z-index:99999; width:70px; height:70px; background:url(images/waveStyle/loadingBg.png) no-repeat; left:50%; top:45%;\"><img style=\"margin:10px 0 0 17px;\" src=\"images/waveStyle/load.gif\" /></div>');
	},
	endLoading : function(){	
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 100);		
	},
	destroy : function(){
		$('#' + this.divId).unbind('keydown');
		
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 100);		
		
	},
	showStatus : function(){
		//alert('status');
	},
	
	loginFacebook : function(){
		var objectId = this.objectId;
		
		/*
		FB.login(function(response) {
			if (response.status === 'connected') {
				$('#method_facebook_' + objectId).hide();
				
				var object = mw3.getObject(objectId);
				
			    var uid = response.authResponse.userID;
				
				FB.api('/' + uid, function(response) {

					if(!object.userId && response.email){
						mw3.getInputElement(objectId, "userId").value = response.email;
						object.userId = response.email;							
					}
					
					object.subscribe();

				});

				
			}			
		}, {scope:'email,user_checkins,publish_stream,user_likes,export_stream'});
		*/	
	}
}