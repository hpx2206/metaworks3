var org_uengine_codi_mw3_model_ILogin = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	var theLoginHelper = this;
	
	window.fbAsyncInit = function() {
		FB.init({
			     appId  : '119159701538006',
			     status : true, // check login status
			     cookie : true, // enable cookies to allow the server to access the session
			     xfbml  : true  // parse XFBML
			   });	
		
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {		
			    // the user is logged in and connected to your
			    // app, and response.authResponse supplies
			    // the user's ID, a valid access token, a signed
			    // request, and the time the access token 
			    // and signed request each expire
			    var uid = response.authResponse.userID;
			    var accessToken = response.authResponse.accessToken;
			    
			    theLoginHelper.setUserInfo();
			  } else if (response.status === 'not_authorized') {
			    // the user is logged in to Facebook, 
			    //but not connected to the app
			  } else {
				  $("#fb-login").show();

			  }
		});
	};    
    
    
   
//	var tempUsr = {
//			userId: '12345',
//			name: '가상계정',
//			__className: "org.uengine.codi.mw3.model.Login"
//	}
//	
//
//    mw3.objects[objectId] = tempUsr;
//    
//    mw3.call(objectId, 'login');
  
}

org_uengine_codi_mw3_model_ILogin.prototype.setUserInfo = function(){
	
	var faceLoginUser = mw3.getObject(this.objectId);
	if(faceLoginUser && faceLoginUser.name) return;
	
	var fluThis = this;
	
	FB.api('/me', function(response) {
		  //alert('Your name is ' + response.name);
			var convertedUserInfo = {
					userId: response.id,
					name: response.name,
					__className: "org.uengine.codi.mw3.model.Login"
			}
			
			convertedUserInfo = mw3.setObject(fluThis.objectId, convertedUserInfo).getObject();
			
			convertedUserInfo.login();
		});
	
}