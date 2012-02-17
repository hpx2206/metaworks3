var org_uengine_codi_mw3_model_LoginAdmin = function(objectId, className){

	this.objectId = objectId;
	this.className = className;
	
	var theLoginHelper = this;
	
    window.fbAsyncInit = function() {
      FB.init({
        appId      : '119159701538006',// 296566710367686   119159701538006
        status     : true, 
        cookie     : true,
        xfbml      : true
      });
      
  	FB.login(function(response) {
		   if (response.authResponse) {
			   theLoginHelper.setUserInfo();
		   } else {
		     console.log('User cancelled login or did not fully authorize.');
		   }
		 }, {scope: 'email,user_checkins,publish_stream,user_likes,export_stream'});
	
    };
    (function(d){
       var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
       js = d.createElement('script'); js.id = id; js.async = true;
       js.src = "//connect.facebook.net/en_US/all.js";
       d.getElementsByTagName('head')[0].appendChild(js);
     }(document));
    
   
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

org_uengine_codi_mw3_model_LoginAdmin.prototype.setUserInfo = function(){
	
	var faceLoginUser = mw3.getObject(this.objectId);
	if(faceLoginUser && faceLoginUser.name) return;
	
	var fluThis = this;
	
	FB.api('/me', function(response) {
			var object = mw3.getObject(fluThis.objectId);
			
			object.userId = response.id;
			object.name = response.name;
			object.defId = $("#defId").val();
			
			object.login();
		});
	
}