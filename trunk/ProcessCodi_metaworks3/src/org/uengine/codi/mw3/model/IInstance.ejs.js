var org_uengine_codi_mw3_model_IInstance = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	this.overTimer;
	
	
	
		$('#' + this.divId).bind('click', {objectId: this.objectId},function(){
			if( object && object.metaworksContext && object.metaworksContext.where != 'sns'){
				 $(".tbl_type").parent().css("background","none");
				 $(".tbl_type").parent().css("box-shadow","none");
				 $(this).css("background","#C9E2FC");
				 $(this).css("box-shadow","0px 0px 5px 2px rgba(0, 0, 0, 0.1) inset");
			}
		 });
		
	

	 var object = mw3.objects[this.objectId];
	 if(object && object.metaworksContext && object.metaworksContext.how == 'blinking'){
		 this.blinking();
	 }
	 if( object && object.metaworksContext && object.metaworksContext.where == 'sns'){
		 $('#' + this.divId).css('border-top','1px solid #E3E3E3');
		 $('#tr_' + this.objectId).click({objectId : this.objectId}, function(event){
			var objectId = event.data.objectId;
				
				 mw3.call(objectId, 'over');
		 });
	}else{
		 $('#tr_' + this.objectId).click({objectId : this.objectId}, function(event){
				var objectId = event.data.objectId;
					
				mw3.call(objectId, 'detail');
			 });
	}
	 
	 $('#tr_' + this.objectId).draggable({
	        start   : function () {
	        	var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
	        	session.clipboard = object;
	        },
	        cursorAt: { top: -1, left: -1 },
	        helper  : 'clone',
	        distance: 30,
	        opacity: 0.8
	    });
	// 
	 /*
	$('#' + this.divId).mouseleave({objectId : this.objectId}, function(event){
		 var objectId = event.data.objectId;
		 
		 var faceHelper = mw3.getFaceHelper(objectId);
		 
		 clearTimeout(faceHelper.overTimer);
		 
		 
		 /*
		 var object = mw3.getObject(objectId);
		 
		 if(object.instanceViewThreadPanel && object.instanceViewThreadPanel.instanceId) 
			 mw3.call(objectId, 'over');
			*/ /*
	});
	
	$('#' + this.divId).mouseenter({objectId : this.objectId}, function(event){		
		var objectId = event.data.objectId;
		
		var object = mw3.getObject(objectId);
		 
		if(object.instanceViewThreadPanel && object.instanceViewThreadPanel.instanceId){
			
		}else{
			var faceHelper = mw3.getFaceHelper(objectId);
			
			faceHelper.overTimer = setTimeout(function(){
				 mw3.call(objectId, 'over');
			}, 2000);			
		}
		
	});
	*/
}

	

org_uengine_codi_mw3_model_IInstance.prototype = {
	destroy : function(){
		//$('#' + this.divId).unbind('mouseenter').unbind('mouseleave');
		$('#' + this.divId).unbind('click');
	},
	startLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).startLoading)
			mw3.getFaceHelper(this.windowObjectId).startLoading();
	},
	endLoading : function(){
		if(this.windowObjectId && mw3.getFaceHelper(this.windowObjectId) && mw3.getFaceHelper(this.windowObjectId).endLoading)
			mw3.getFaceHelper(this.windowObjectId).endLoading();
	},
	showStatus : function(message){
		
	},
	unBlinking : function(){
		
		$('#' + this.divId + ' .innerNewInst').css("background","none");
		
		/*if (this.timeout) {
			clearTimeout(this.timeout);			
			$('#' + this.divId).removeClass('blinking');
			return false;
		}	*/		
	},
	
	blinking : function(){
		
		
		var blinkDiv = $('#' + this.divId + ' .innerNewInst');
		
		
		blinkDiv.animate({
			backgroundColor: "#FEE5A3"
		}, 500 );
		
		setTimeout(function(){
			blinkDiv.animate({
				backgroundColor: "#ffffff"
			}, 500 );
		},500)
		
		setTimeout(function(){
			blinkDiv.animate({
				backgroundColor: "#FEE5A3"
			}, 500 );
		},1000)
			
		setTimeout(function(){
			blinkDiv.animate({
				backgroundColor: "#ffffff"
			}, 500 );
		},1500)
		
		setTimeout(function(){
			blinkDiv.animate({
				backgroundColor: "#FEE5A3"
			}, 500 );
		},2000)
		
			/*if($('#' + this.divId).hasClass('blinking')){
				$('#' + this.divId).removeClass('blinking');
				
				$('#' + this.divId).animate({
					backgroundColor: "#FEE5A3"
				}, 1000 );
				
				// 꺼짐
			}else{
				$('#' + this.divId).addClass('blinking');
				$('#' + this.divId).animate({
					backgroundColor: "#ffffff"
				}, 1000 );
				// 켜짐
			}
			
		
			var objectId = this.objectId
			this.timeout = setTimeout(function() {
				mw3.getFaceHelper(objectId).blinking();
			}, 1000);*/
		
	}
}
