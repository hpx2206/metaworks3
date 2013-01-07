
var org_uengine_codi_mw3_Login = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);

	// facebook init
	var login = mw3.objects[objectId];
	var lastVisitPage = getCookie("codi.lastVisit");
	if(lastVisitPage) 
		login.lastVisitPage = lastVisitPage;
	

	var storedId = getCookie("codi.id");
		
	if(storedId!=null){		
		
		mw3.getInputElement(objectId, "userId").value = storedId;
		var password = getCookie("codi.password");
		mw3.getInputElement(objectId, "password").value = password 
		var object = mw3.objects[objectId];
		
		if(!mw3.autoLogged && password){
			object.login();
			mw3["autoLogged"] = true;
		}
	}else{		
			
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
	
	$('#method_facebook_' + objectId).show();
	
	var object = mw3.objects[this.objectId]; 
 
//mw3.getInputElement(this.objectId, 'userId').value = 'test';
//mw3.getInputElement(this.objectId, 'password').value = 'testtest';
 
	mw3.getInputElement(this.objectId, 'userId').focus();
 
// object.login();
 
	$('#' + this.divId).bind('keydown', function(event){
		mw3.getFaceHelper(objectId).keydown(event);
	});
}

org_uengine_codi_mw3_Login.prototype = {
	keydown : function(e){
		if(e.keyCode == 13){
			window.event.returnValue = false;
			
			mw3.call(this.objectId, 'login');
		}
	},
	getValue	:	function(){
		var login = mw3.getObjectFromUI(this.objectId);
		
		if (login.rememberMe){
			setCookie("codi.id", login.userId, 10, "/", "", "");
			setCookie("codi.password", login.password, 10, "/", "", "");			
		}else{
			deleteCookie("codi.id", "/","");
			deleteCookie("codi.password", "/","");
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
	}
}