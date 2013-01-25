var org_uengine_codi_mw3_model_Main = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	$('#objDiv_' + objectId).css('height', '100%');
	
	var sessionObj = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
	
	if(sessionObj && sessionObj.guidedTour){
		setTimeout("mw3.test(" + mw3.getAutowiredObject('org.uengine.codi.mw3.model.InstanceListPanel').__objectId + ", 'first', {guidedTour: true})", 1000);
	}
	
	$(document).mousedown(function(e){
		$('.mw3_popup').each(function(){
			var objectId = $(this).attr('objectId');
			
			mw3.getFaceHelper(objectId).checkPosition(e);
		});
	});	
	
	setCookie("codi.lastVisit", "process", 10, "/", "", "");
}
