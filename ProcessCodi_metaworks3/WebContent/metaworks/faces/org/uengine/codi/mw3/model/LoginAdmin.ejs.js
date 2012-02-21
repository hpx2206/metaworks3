var org_uengine_codi_mw3_model_LoginAdmin = function(objectId, className){
	
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

/*
    (function(d){
       var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
       js = d.createElement('script'); js.id = id; js.async = true;
       js.src = "//connect.facebook.net/en_US/all.js";
       d.getElementsByTagName('head')[0].appendChild(js);
     }(document));
 */


//var tempUsr = {
//userId: '12345',
//name: '가상계정',
//__className: "org.uengine.codi.mw3.model.Login"
//}


//mw3.objects[objectId] = tempUsr;

//mw3.call(objectId, 'login');

}

org_uengine_codi_mw3_model_LoginAdmin.prototype.setUserInfo = function(){
	var faceLoginUser = mw3.getObject(this.objectId);
	if(faceLoginUser && faceLoginUser.name) return;

	var fluThis = this;

	FB.api('/me', function(response) {
		var object = mw3.getObject(fluThis.objectId);

		object.userId = response.id;
		object.name = response.name;
		object.defId = $("#defId").val();

		mw3.call(fluThis.objectId, "login");
	});

}