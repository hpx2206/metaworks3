var org_uengine_codi_mw3_model_Popup = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = "#objDiv_" + this.objectId;
	this.divObj = $("#objDiv_" + this.objectId).parent();
	
	this.divObj.addClass("mw3_popup").attr("objectid", objectId).addClass("clue-right-rounded").addClass("cluetip-rounded").css({position:'absolute','z-index':97,display:'none'});
	
	
	//var openerDiv = $("#objDiv_" + mw3.recentOpenerObjectId);
//	var x = openerDiv.offset().left + (openerDiv.children()[0].offsetWidth);
//	var y = openerDiv.offset().top;
	
	this.createPopup(400, 300, mw3.mouseX, mw3.mouseY);	
}

org_uengine_codi_mw3_model_Popup.prototype = {
	createPopup : function(w,h,x,y){
		var popLayerWidth = w;
		var popLayerHeight = h;
		
		var bodyHeight = $('body').height();
		var bodyWidth = $('body').width();
		
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

	},
	destoryPopup : function() {
		var divId = '#objDiv_' + this.objectId;
		
		$(divId).parent().remove();
	},
	destroy : function() {
		this.destoryPopup();
	},
	checkPosition : function(e){
		var l_position = this.divObj.offset();
		l_position.right = parseInt(l_position.left) + this.divObj.width();
		l_position.bottom = parseInt(l_position.top) + this.divObj.height();


		if ( ( l_position.left <= e.pageX && e.pageX <= l_position.right )
				&& ( l_position.top <= e.pageY && e.pageY <= l_position.bottom ) ){
			
		}else{
			this.destoryPopup();
		}
	}		
}
