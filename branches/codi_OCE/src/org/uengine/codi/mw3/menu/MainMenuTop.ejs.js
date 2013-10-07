var org_uengine_codi_mw3_menu_MainMenuTop = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	$('#objDiv_' + objectId).addClass('mw3_mainMenu');
}

org_uengine_codi_mw3_menu_MainMenuTop.prototype.callMethod = function(element, methodName){
	
	var callMethodName = $('.mw3_subMenu').attr('methodName');
	
	if(callMethodName){
		$('.mw3_subMenu').remove();
	}
	
	if(callMethodName != methodName){
		$('#objDiv_' + this.objectId).attr('menuLeft', element.offsetLeft).attr('menuTop', $('#objDiv_' + this.objectId).height());
	
		mw3.call(this.objectId, methodName);
	}
}	