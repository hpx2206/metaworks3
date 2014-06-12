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

		var showTitle = false;
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


		if(this.divObj.hasClass('target_stick')){
			console.log('stick');
		
			var position = 'right';
			var where = 'up';
			
			// default position
			top -= 20;
			
			if(top < -10){
				// change bottom position
				top = y;
				left -= (popLayerWidth/2);
	
				position = 'top'			
			}else if(bodyHeight < top + popLayerHeight + 30){
				top -= (popLayerHeight - 20);			
				where = 'down'
				
				if(bodyHeight < top + popLayerHeight + 30){
					// change top position
					top = y - popLayerHeight - 30;
					left -= (popLayerWidth/2);
					
					position = 'bottom';
				}
			}
			
			if(left < 0){
				this.divObj.find('.cluetip-arrows').css({'left':x-12});
				
				left = 0;
			}else if(bodyWidth < left + popLayerWidth){
				if(position == 'right'){
					left = x - popLayerWidth - 30;
					
					position = 'left';
				}else{
					left = bodyWidth - popLayerWidth - 30;
				}
			}
			
			this.divObj.css({left: left + 'px',top: top + 'px'});
	
			console.log('position : ' + position);
			if(position == 'right' || position == 'left'){
				if(position == 'left')
					this.divObj.removeClass('clue-right-rounded').addClass('clue-left-rounded');
				
				if(where == 'down')
					this.divObj.find('.cluetip-arrows').css({'top':257});
					
			}else if(position == 'top' || position == 'bottom'){
				if(position == 'top')
					this.divObj.removeClass('clue-right-rounded').addClass('clue-bottom-rounded');
				else
					this.divObj.removeClass('clue-right-rounded').addClass('clue-top-rounded');
			}
		}else{
			console.log('popup');
			showTitle = true;
			left = (bodyWidth - popLayerWidth)/2;
			top = (bodyHeight - popLayerHeight)/2;
			
			this.divObj.css({left: left + 'px',top: top + 'px'});
			this.divObj.find('.cluetip-arrows').remove();
		}
		
		if(showTitle){
			this.divObj.find('.cluetip-title').show();
			this.divObj.find('.cluetip-close2').show();
		}
		
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