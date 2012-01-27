var org_uengine_codi_mw3_model_Popup = function(objectId, className) {	
	console.debug("org_uengine_codi_mw3_model_Popup");	
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	
	if(object.metaworksContext.when == mw3.WHEN_VIEW){
		$("#objDiv_" + this.objectId).parent().addClass("clue-right-rounded").addClass("cluetip-rounded").css({position:'absolute','z-index':97,display:'none'});
		popUp(this.objectId, 200, 500, object.pageX, object.pageY);
	}
}

function popUp(objectId, w,h,x,y){
	
	var popLayerWidth = w;
	var popLayerHeight = h;
	
	var bodyHeight = $('#container').height();
	var bodyWidth = $('#container').width();
	
	$('#objDiv_' + objectId).parent().show();
	$('#objDiv_' + objectId).parent().css({top:y,left:x,width:popLayerWidth,height:popLayerHeight});
	$('#objDiv_' + objectId).parent().removeClass('clue-left-rounded');
	$('#objDiv_' + objectId).parent().addClass('clue-right-rounded');
	$('#objDiv_' + objectId + ' .cluetip-arrows').css({top:10});
	
	if(popLayerWidth + x > bodyWidth){
		$('#objDiv_' + objectId).parent().css({left:x - popLayerWidth});
		$('#objDiv_' + objectId).parent().removeClass('clue-right-rounded');
		$('#objDiv_' + objectId).parent().addClass('clue-left-rounded');
	};
	
	if(popLayerHeight + y > bodyHeight){    	
	 	$('#objDiv_' + objectId).parent().css({top:y - popLayerHeight});
	 	$('#objDiv_' + objectId + ' .cluetip-arrows').css({top:popLayerHeight-40});
	};

};

function addContact(objectId, e){
	var object = mw3.getObject(objectId);	
	
	object.pageX = e.pageX;
	object.pageY = e.pageY;			

	mw3.call(objectId, "openPopup");	
}