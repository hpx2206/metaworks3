var org_uengine_codi_mw3_model_NotificationBadge = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = 'objDiv_' + this.objectId;
	
	$('.badgeRed').show( 'pulsate' ,  500 );	
	
	var badge = mw3.objects[objectId];
	
	if(badge && badge.newItemCount == -1)
		mw3.objects[objectId].refresh();
	
	window.document.title = (badge.newItemCount > 0 ? "("+ badge.newItemCount + ")":"") + "Process Codi"; 

}