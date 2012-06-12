var org_uengine_codi_mw3_model_UnstructuredProcessInstanceStarter = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	if(object && object.friend && object.friend.network == 'fb'){
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				$('#chat_' + objectId).show();
				
				mw3.getInputElement(objectId, 'title').focus();
			}else{
				FB.login(function(response) {
					if (response.status === 'connected') {
						$('#chat_' + objectId).show();
						
						mw3.getInputElement(objectId, 'title').focus();
					}else{
						$('#objDiv_' + objectId).closest('.mw3_popup').remove();
						
						mw3.removeObject(objectId);
					}
					
				}, {scope:'email,user_checkins,publish_stream,user_likes,export_stream'});
			}
		});
	}else{
		mw3.getInputElement(this.objectId, 'title').focus();
	}
}