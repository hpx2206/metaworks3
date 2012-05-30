var org_uengine_codi_mw3_model_Main = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	$('#objDiv_' + objectId).css('height', '100%');
	
	$(document).mousedown(function(e){
		$('.mw3_popup').each(function(){
			var objectId = $(this).attr('objectId');
			
			mw3.getFaceHelper(objectId).checkPosition(e);
		});
	});
}
