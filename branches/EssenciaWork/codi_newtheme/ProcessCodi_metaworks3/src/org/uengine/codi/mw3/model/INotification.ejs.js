var org_uengine_codi_mw3_model_INotification = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	$('#see_' + this.objectId).bind('click', {objectId : this.objectId}, function(event){
		var object = mw3.getObject(event.data.objectId);
		
		object.see(true, function(){
		 	var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
		 	
		 	if(session && session.ux == 'phone'){
 				$('.mw3_popup').each(function(){
					var objectId = $(this).attr('objectId');
					
					mw3.getFaceHelper(objectId).destoryPopup();
				});
		 	}			
		});
	});
};
	
org_uengine_codi_mw3_model_INotification.prototype = {
	destroy : function(){
		$('#see_' + this.objectId).unbind();
	}
};