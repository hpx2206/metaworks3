var org_uengine_codi_mw3_model_ILogin = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
    window.fbAsyncInit = function() {
      FB.init({
        appId      : '175213242502173',// 296566710367686
        status     : true, 
        cookie     : true,
        xfbml      : true
      });
    };
    (function(d){
       var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
       js = d.createElement('script'); js.id = id; js.async = true;
       js.src = "//connect.facebook.net/en_US/all.js";
       d.getElementsByTagName('head')[0].appendChild(js);
     }(document));
  
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