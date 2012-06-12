var org_uengine_codi_mw3_Login = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);

	if($('#fb-root').length == 0){
		$('<div id="fb-root"></div>').insertBefore('#' + this.divId);

		// facebook 
		(function(d, s, id) {
			  var js, fjs = d.getElementsByTagName(s)[0];
			  if (d.getElementById(id)) {
			  	return;
			  }
			  
			  js = d.createElement(s); js.id = id;
			  js.src = "//connect.facebook.net/ko_KR/all.js#xfbml=1&appId=119159701538006";
			  fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));			
		
		window.fbAsyncInit = function() {
			FB.init({appId: '119159701538006', status: true, cookie: true,xfbml: true});
		}
	}
	
	var tryGuidedTour = false;
 
	var object = mw3.objects[this.objectId]; 
 
 //mw3.getInputElement(this.objectId, 'userId').value = 'test';
 //mw3.getInputElement(this.objectId, 'password').value = 'test';
 
	mw3.getInputElement(this.objectId, 'userId').focus();
 
 //object.login();
 
	if(!tryGuidedTour && object.alias == null){
		setTimeout("mw3.test(" +objectId + ", 'first', {guidedTour: true})", 1000);

		tryGuidedTour = true;
	}
	
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
		
	},
	showError : function(errorMessage, svcNameAndMethodName){
		var messageDiv = "#message_" + this.objectId;
		$(messageDiv).html(errorMessage);
		
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 100);
	}
}