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
        
        mw3.call(objectId, "load");
  	
      };
      (function(d){
         var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
         js = d.createElement('script'); js.id = id; js.async = true;
         js.src = "//connect.facebook.net/en_US/all.js";
         d.getElementsByTagName('head')[0].appendChild(js);
       }(document));
}