var org_metaworks_website_IFacebookLoginUser = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
    window.fbAsyncInit = function() {
      FB.init({
        appId      : '175213242502173',
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

org_metaworks_website_IFacebookLoginUser.prototype.setUserInfo = function(){
	
	var faceLoginUser = mw3.getObject(this.objectId);
	if(faceLoginUser && faceLoginUser.name) return;
	
	var fluThis = this;
	
	FB.api('/me', function(response) {
		  //alert('Your name is ' + response.name);
			var convertedUserInfo = {
					userId: response.id,
					name: response.name,
					__className: "org.metaworks.website.FacebookLoginUser"
			}
			
			convertedUserInfo = mw3.setObject(fluThis.objectId, convertedUserInfo).getObject();
			
			convertedUserInfo.personalizeMain();
		});
	
}