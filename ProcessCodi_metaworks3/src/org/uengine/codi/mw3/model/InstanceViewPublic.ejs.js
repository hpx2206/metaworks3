var org_uengine_codi_mw3_model_InstanceViewPublic = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	   
    window.fbAsyncInit = function() {
    	try{
	        FB.init({
	          appId      : '119159701538006',// 296566710367686   119159701538006
	          status     : true, 
	          cookie     : true,
	          xfbml      : true
	        });
    	}catch(e){}
        
    	FB.login(function(response) {
		   if (response.authResponse) {
		    	FB.api('/me', function(response) {
		    			var userInfo = {
		    					userId: response.id,
		    					name: response.name,
		    					__className: "org.uengine.codi.mw3.model.Login"
		    			}
		    			
		    			var session = {
		    					login: userInfo,
		    					__className: "org.uengine.codi.mw3.model.Session"
		    			}			
		    			
		    			object.session = session;
		    			
		    			object.load();
		    		});
		   } else {
			   if(window.console)
				   	console.log('User cancelled login or did not fully authorize.');
		   }
		 }, {scope: 'email'});
	
    	
    	

      };
      
      
      (function(d){
          var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
          js = d.createElement('script'); js.id = id; js.async = true;
          js.src = "//connect.facebook.net/en_US/all.js";
          d.getElementsByTagName('head')[0].appendChild(js);
        }(document));
             
}