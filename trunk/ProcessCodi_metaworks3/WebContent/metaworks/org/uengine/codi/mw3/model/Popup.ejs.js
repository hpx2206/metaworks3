var org_uengine_codi_mw3_model_Popup = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
	$("#objDiv_" + this.objectId).parent().addClass("mw3_popup").attr("objectid", objectId).addClass("clue-right-rounded").addClass("cluetip-rounded").css({position:'absolute','z-index':97,display:'none'});
	
	
	//var openerDiv = $("#objDiv_" + mw3.recentOpenerObjectId);
//	var x = openerDiv.offset().left + (openerDiv.children()[0].offsetWidth);
//	var y = openerDiv.offset().top;
	
	this.createPopup(400, 300, mw3.mouseX, mw3.mouseY);	
}

org_uengine_codi_mw3_model_Popup.prototype.createPopup = function(w,h,x,y){
	var popLayerWidth = w;
	var popLayerHeight = h;
	
	var bodyHeight = $('#container').height();
	var bodyWidth = $('#container').width();
	
	var divId = '#objDiv_' + this.objectId;
	
	$(divId).parent().show();
	$(divId).parent().css({top:y,left:x,width:popLayerWidth,height:popLayerHeight});
	$(divId).parent().removeClass('clue-left-rounded');
	$(divId).parent().addClass('clue-right-rounded');
	$(divId + ' #addcontact-con').height(h-43);
	$(divId + ' .cluetip-arrows').css({top:10});
	
	if(bodyWidth && popLayerWidth + x > bodyWidth){
		$(divId).parent().css({left:x - popLayerWidth});
		$(divId).parent().removeClass('clue-right-rounded');
		$(divId).parent().addClass('clue-left-rounded');
	};
	
	if(bodyHeight && popLayerHeight + y > bodyHeight){    	
	 	$(divId).parent().css({top:y - popLayerHeight});
	 	$(divId + ' .cluetip-arrows').css({top:popLayerHeight-40});
	};

};

org_uengine_codi_mw3_model_Popup.prototype.destoryPopup = function() {
	var divId = '#objDiv_' + this.objectId;
	
	$(divId).parent().remove();
}