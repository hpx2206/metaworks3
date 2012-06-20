var org_uengine_codi_mw3_model_IEmployee = function(objectId, className){
	var object = mw3.objects[objectId];	
	
	this.objectId = objectId;
	this.className = className;

	var theLoginHelper = this;
	
	var object = mw3.objects[this.objectId];
	
	if(object && object.metaworksContext && object.metaworksContext.when == 'new'){
		console.debug('get facebook info');
		
		// facebook login status
		FB.getLoginStatus(function(response) {
			if (response.status == 'connected'){	
			    var uid = response.authResponse.userID;
			    var accessToken = response.authResponse.accessToken;
				
				FB.api('/' + uid, function(response) {
					console.debug(response);
					
					var object = mw3.objects[objectId];
					
					object.facebookId = response.id;				
					
					object.empCode = response.email;
					object.empName = response.name;
					object.email = response.email;
					
					mw3.getInputElement(objectId, 'locale').value = response.locale.substring(0,2);
				});				
			}
		});
	}
	
	$('#objDiv_' + this.objectId).parent().parent().css({'border':'none'});
};

org_uengine_codi_mw3_model_IEmployee.prototype = {
	getValue : function(){
		var object = mw3.getObjectFromUI(this.objectId);//objects[this.objectId];
		
		var preferUX = $('#uxStyle_'+this.objectId +':checked').val();
		if(preferUX)
			object.preferUX = preferUX;
		
		return object;
	},
	startLoading : function(){
		$('.logo').after('<div id=\"mw3_progress\" style=\"width:128px; height:15px; float:left; margin-top:20px;  margin-right:20px;"><img src=\"images/waveStyle/ajax-loader_t.gif\" /></div>');
	},
	endLoading : function(){
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 500);		
	},
	destroy : function(){
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 500);		
		
	},
	showStatus : function(status){
		var arrStatus = status.split(' ');
		
		if(arrStatus[1] == 'DONE.'){
			if(arrStatus[0] == 'checkEmpCode')
				$('#checkEmpCode_' + this.objectId).html(mw3.localize('$sucessCheckEmpCode'));
		}		
		
	},
	showError : function(message, methodName){
		if(methodName == 'checkEmpCode'){
			$('#checkEmpCode_' + this.objectId).html(mw3.localize('$failCheckEmpCode'));
		}
	}
	
}


//org_forx_model_codi_IEmployee.prototype.editInfo = function(){
//	var object = mw3.objects[this.objectId];
//	//object.metaworksContext.when = mw3.WHEN_EDIT;
//	mw3.editObject(this.objectId);
//};