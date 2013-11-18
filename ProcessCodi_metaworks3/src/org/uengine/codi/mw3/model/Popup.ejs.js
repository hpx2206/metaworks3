var org_uengine_codi_mw3_model_Popup = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = "#objDiv_" + this.objectId;
	this.divObj = $("#objDiv_" + this.objectId).closest('.target_stick,.target_popup');
	
	var modalWindow = $('.ui-dialog');
	
	var zIndex = 100;
	
	if(modalWindow && modalWindow.length > 0){
		zIndex = $(modalWindow[modalWindow.length-1]).css('z-index');
		zIndex = String(Number(zIndex)+1);
	}		
	
	this.divObj.addClass("mw3_popup").attr("objectid", objectId).addClass("clue-right-rounded").addClass("cluetip-rounded").css({position:'absolute','z-index':zIndex,display:'none'});
	
	var object = mw3.objects[this.objectId];
	
	var faceHelper = this;
	
	faceHelper.createPopup(object.width, object.height, mw3.mouseX, mw3.mouseY);
};

org_uengine_codi_mw3_model_Popup.prototype = {
	createPopup : function(w,h,x,y){

		var popLayerWidth = w;
		var popLayerHeight = h;
		
		var bodyHeight = $('body').height();
		var bodyWidth = $('body').width();
		
		var left = x;
		var top = y;
		

		console.log('mouse.x = ' + x);
		console.log('mouse.y = ' + y);

		console.log('popLayer.width = ' + popLayerWidth);
		console.log('popLayer.height = ' + popLayerHeight);

		console.log('body.width = ' + bodyWidth);
		console.log('body.height = ' + bodyHeight);

		var position = 'right';
		
		// default position
		top -= 40;
		
		if(top < -10){
			// change bottom position
			top = y;
			left -= (popLayerWidth/2);

			position = 'top'			
		}else if(bodyHeight < top + popLayerHeight + 30){
			top -= (popLayerHeight - 60);			
		
			if(bodyHeight < top + popLayerHeight + 30){
				// change top position
				top = y - popLayerHeight - 30;
				left -= (popLayerWidth/2);
				
				position = 'bottom';
			}
		}
		
		console.log('position : ' + position);
		
		if(left < 0){
			left = 0;
		}else if(bodyWidth < left + popLayerWidth){
			if(position == 'right')
				left = x - popLayerWidth - 30;
			else
				left = bodyWidth - popLayerWidth - 30;
		}
		
		this.divObj.css({left: left + 'px',top: top + 'px'});		
	
		this.divObj.show();
	},
	destoryPopup : function() {
		mw3.endLoading(this.objectId);
		this.divObj.remove();
	},
	destroy : function() {
		this.destoryPopup();
	}
};