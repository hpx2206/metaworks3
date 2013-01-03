var org_uengine_codi_mw3_model_Popup = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = "#objDiv_" + this.objectId;
	this.divObj = $("#objDiv_" + this.objectId).parent();
	
	var modalWindow = $('.ui-dialog');
	
	var zIndex = 100;
	
	if(modalWindow.length > 0){
		zIndex = $(modalWindow[modalWindow.length-1]).css('z-index');
		zIndex = String(Number(zIndex)+1);
	}		
	
	this.divObj.addClass("mw3_popup").attr("objectid", objectId).addClass("clue-right-rounded").addClass("cluetip-rounded").css({position:'absolute','z-index':zIndex,display:'none'});
	
	var object = mw3.objects[this.objectId];
	
	//var openerDiv = $("#objDiv_" + mw3.recentOpenerObjectId);
//	var x = openerDiv.offset().left + (openerDiv.children()[0].offsetWidth);
//	var y = openerDiv.offset().top;
	
	this.createPopup(object.width, object.height, mw3.mouseX, mw3.mouseY);	
};

org_uengine_codi_mw3_model_Popup.prototype = {
	createPopup : function(w,h,x,y){
		var popLayerWidth = w;
		var popLayerHeight = h;
		
		var bodyHeight = $('body').height();
		var bodyWidth = $('body').width();
		
		var divId = '#objDiv_' + this.objectId;
		
		var parent = $(divId).parent();
		var arrow = $(divId + ' .cluetip-arrows');
		
		parent.show();
		parent.css({top:y-28,left:x,width:popLayerWidth,height:popLayerHeight});
		parent.removeClass('clue-left-rounded');
		parent.addClass('clue-right-rounded');
		
		var innerHeight = h-39;
		
		$(divId + ' #addcontact-con').height(innerHeight);
		arrow.css({top:10});
		
		if(bodyWidth && popLayerWidth + x > bodyWidth){
			parent.css({left:x-30 - popLayerWidth});
			parent.removeClass('clue-right-rounded');
			parent.addClass('clue-left-rounded');
		};
		
		if(bodyHeight && popLayerHeight + y > bodyHeight){    	
			parent.css({top:y+28 - popLayerHeight});
			arrow.css({top:popLayerHeight-40});
		};
		
		var top = parent.css('top');
		top = top.substring(0, top.length - 2);
		
		if(top < 0){
			var arrowTop = arrow.css('top');			
			
			parent.css('top', 0);
			arrow.css({top:popLayerHeight+Number(top)-40});
		}

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
