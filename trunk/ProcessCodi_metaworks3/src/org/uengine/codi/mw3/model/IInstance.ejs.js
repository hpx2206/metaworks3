var org_uengine_codi_mw3_model_IInstance = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);

	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	this.overTimer;
	
	$('#' + this.divId).parent().css("background","#DFE1E3")
	$('#' + this.divId).css({'margin':'0 8px 0 5px','border-left':'1px solid #ccc','border-right':'1px solid #ccc','background':'#fff'});
	
	
	$('#' + this.divId).bind('click', {objectId: this.objectId},function(){
		mw3.getFaceHelper(objectId).unBlinking();
		
		if( object && object.metaworksContext && object.metaworksContext.how != 'sns'){
			 $(".tbl_type").parent().css({'margin':'0 8px 0 5px','border-right':'1px solid #ccc','border-bottom':'none','border-radius':'0px','-moz-border-radius':'0px;','-webkit-border-radius':'0px;'});
			 $(this).css({'margin':'0px 2px 0 0','border-right':'2px solid #A1A1A1','border-bottom':'2px solid #A1A1A1','border-radius':'7px','-moz-border-radius':'7px;','-webkit-border-radius':'7px;'});
		}
	 });
	
	$('#' + this.divId).hover(function(){				
			$('#objDiv_' + objectId + ' .instanceBtn').show();
		},function(){	
			$('#objDiv_' + objectId + ' .instanceBtn').hide();
	});

	var object = mw3.objects[this.objectId];
	 if(object && object.metaworksContext && object.metaworksContext.when == 'blinking'){
		 this.blinking();
	 }
	 
	 if( object && object.metaworksContext && object.metaworksContext.how == 'sns'){
		 $('#' + this.divId).css('border-top','1px solid #E3E3E3');
		 $('.tbl_type td').css('border','none');
	 }
	 
	 $('#td_' + this.objectId).click({objectId : this.objectId}, function(event){
		 var objectId = event.data.objectId;
		 
		 mw3.call(objectId, 'detail');
	 });
	 
//	 $('#tr_' + this.objectId).mouseenter({objectId : this.objectId}, function(event){
//		 mw3.call(event.data.objectId, 'overTooltip');
//    });
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
	 
	 
};

	

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
		
		$('#' + this.divId + ' .innerNewInst').hide();
		
		/*if (this.timeout) {
			clearTimeout(this.timeout);			
			$('#' + this.divId).removeClass('blinking');
			return false;
		}	*/		
	},
	
	blinking : function(){
		
		var blinkDiv = $('#' + this.divId + ' .innerNewInst');
		
		blinkDiv.show( 'pulsate' ,  1500 );		
		
		
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
