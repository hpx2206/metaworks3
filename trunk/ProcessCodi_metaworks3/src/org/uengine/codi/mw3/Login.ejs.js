  // Load the SDK's source Asynchronously
  // Note that the debug version is being actively developed and might 
  // contain some type checks that are overly strict. 
  // Please report such bugs using the bugs tool.
  (function(d, debug){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/ko_KR/all" + (debug ? "/debug" : "") + ".js";
     ref.parentNode.insertBefore(js, ref);
   }(document, /*debug*/ false));

var org_uengine_codi_mw3_Login = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.object = mw3.objects[this.objectId];
	
	if(this.object == null)
		return true;	
	
	// 존재하는 팝업 제거
	$('.target_popup,.target_stick').remove();	

	var lastVisitPage = getCookie("codi.lastVisit");
	if(lastVisitPage) 
		this.object.lastVisitPage = lastVisitPage;

	if(this.object && this.object.metaworksContext && this.object.metaworksContext.where == 'index')
		return true;

	if(this.object && this.object.metaworksContext && this.object.metaworksContext.how == 'logout'){
		delCookie("codi.id", "/","");
		delCookie("codi.rememberMe", "/","");
		delCookie("codi.facebookSSO", "/","");
	}

	var rememberMe = getCookie("codi.rememberMe");
	if(rememberMe!=null && rememberMe){
		var id = getCookie("codi.id");
		var password = getCookie("codi.password");
		
		var input_email = mw3.getInputElement(objectId, "email");
		var input_password = mw3.getInputElement(objectId, "password");
		
		
		console.log(input_rememberMe);
		
		if(input_email)
			input_email.value = id;			
		if(input_password)
			input_password.value = password;
	}

	var input_rememberMe = mw3.getInputElement(objectId, "rememberMe");
	if(input_rememberMe)
		input_rememberMe.checked = true;

	mw3.getInputElement(this.objectId, 'email').focus();
 
	$('#' + this.divId).bind('keydown', function(event){
		mw3.getFaceHelper(objectId).keydown(event);
	});			
};

org_uengine_codi_mw3_Login.prototype = {
	loaded : function(){
		var login = mw3.getObjectFromUI(this.objectId);
		var facebookSSO = getCookie("codi.facebookSSO");
		
		if(login.email && login.password && login.rememberMe){
			login.login();
		}else{
			var objectId = this.objectId;
			
			if(typeof FB == 'object'){
				$('#method_facebook_' + this.objectId).show();
			}else{
				window.fbAsyncInit = function() {
				    // init the FB JS SDK
				    FB.init({
				      appId      : '119159701538006', // App ID from the App Dashboard
				      //channelUrl : '//localhost:8080/uengine-web/index.html', // Channel File for x-domain communication
				      status     : true, // check the login status upon init?
				      cookie     : true, // set sessions cookies to allow your server to access the session?
				      xfbml      : true  // parse XFBML tags on this page?
				    });
	
				    $('#method_facebook_' + objectId).show();
				    
				    // Additional initialization code such as adding Event Listeners goes here
					// facebook login status
					FB.getLoginStatus(function(response) {
						if (response.status == 'connected'){
						    var uid = response.authResponse.userID;
							
							FB.api('/' + uid, function(response) {
								if(login.email && login.rememberMe && facebookSSO)
									login.getFaceHelper().facebookSSO(response);
							});
						}else if('not_authorized' == response.status){
							login.getFaceHelper().loginFacebook();
						}	
	
					}, true);
				};
			}
		}
	},
	keydown : function(e){
		if(e.keyCode == 13){
			window.event.returnValue = false;
			if( this.object && this.object.status == 'subscribe'){
				mw3.call(this.objectId, 'subscribe');
			}else{
				mw3.call(this.objectId, 'login');
			}
		}
	},
	getValue : function(){
		var login = mw3.getObjectFromUI(this.objectId);
		
		var lastVisitPage = getCookie("codi.lastVisit");
		if(lastVisitPage) 
			login.lastVisitPage = lastVisitPage;

		return login;
	},
	startLoading : function(){
		$('body').prepend('<div style=\"position:absolute; z-index:99999; width:100%; height100%;\"><div id=\"mw3_progress\" style=\" width:70px; height:70px; background:url(images/waveStyle/loadingBg.png) no-repeat; margin:23% auto;\"><img style=\"margin:10px 0 0 17px;\" src=\"images/waveStyle/load.gif\" /></div></div>');
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
	showStatus : function(message){
		if('login DONE.' == message){
			if (this.object.rememberMe){
				setCookie("codi.id", this.object.email, 10, "/", "", "");
				setCookie("codi.password", this.object.password, 10, "/", "", "");			
				setCookie("codi.rememberMe", true, 10, "/", "", "");
				setCookie("codi.facebookSSO", true, 10, "/", "", "");
				
			}else{
				delCookie("codi.id", "/","");
				delCookie("codi.password", "/","");
				delCookie("codi.rememberMe", "/","");
				delCookie("codi.facebookSSO", "/","");
			}
		}
	},
	facebookSSO : function(response){
		var objectId = this.objectId;
		
		if(response.email){
			var object = mw3.getAutowiredObject('org.uengine.codi.mw3.Login@login');
			
			mw3.getInputElement(object.__objectId, "email").value = response.email;
			
			var isAuth = object.checkAuthSocial();
			
			
			if(!isAuth){
				var employee = {
					__className : 'org.uengine.codi.mw3.model.Employee',
					metaworksContext : {when : 'new'},
					empName : response.name,						
					email   : response.email,
					facebookId   : response.id
				};
				mw3.locateObject(employee, employee.className);
				
				employee.facebookSSO();
			}
		}
		
	},
	loginFacebook : function(){		
		if(typeof FB != 'object'){
			alert('not loaded facebook api');
		}
		
		var objectId = this.objectId;
		FB.login(function(response) {
			if (response.status === 'connected') {
			    var uid = response.authResponse.userID;				
				FB.api('/' + uid, function(response) {
					mw3.getFaceHelper(objectId).facebookSSO(response);
				});
			}
			
		}, {scope:'email,user_checkins,publish_stream,user_likes,export_stream'});	
	}
};