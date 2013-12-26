var org_uengine_codi_mw3_model_IInstance = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.objDiv = $('#' + this.divId);
	this.object = mw3.objects[this.objectId];
	
	if(this.object == null)
		return false;

	this.windowObjectId = this.objDiv.closest('.mw3_window').attr('objectId');
	this.overTimer;

	this.objDiv.parent().css("background","#DFE1E3");
	this.objDiv.css({'margin':'0 0 0 5px','border-left':'1px solid #ccc','background':'#fff'});
	this.objDiv.addClass('instance').addClass('mw3_resize');
	this.objDiv.attr('status' , this.object.status);
	
	if( this.object.status == 'Completed'){
		this.objDiv.css("background", "#f2f3f4");
	}

	this.objDiv.bind('click', {objectId: this.objectId},function(){
		mw3.getFaceHelper(objectId).unBlinking();
		
		if( object && object.metaworksContext && object.metaworksContext.how != 'sns'){
			$(".tbl_type").parent().each(function(){
				if( $(this).attr("status") == 'Completed' ){
					$(this).css({'margin':'0 0 0 5px','border-left':'1px solid #ccc','border-bottom':'none','border-radius':'0px','-moz-border-radius':'0px;','-webkit-border-radius':'0px;','background':'#f2f3f4'});
				}else{
					$(this).css({'margin':'0 0 0 5px','border-left':'1px solid #ccc','border-bottom':'none','border-radius':'0px','-moz-border-radius':'0px;','-webkit-border-radius':'0px;','background':'#fff'});
				}
			});
			$(this).css({'margin':'0px 0 0 0','border-left':'1px solid #ccc','border-bottom':'2px solid #A1A1A1','border-top-left-radius':'7px','border-bottom-left-radius':'7px','-moz-border-top-left-radius':'7px;','-moz-border-bottom-left-radius':'7px;','-webkit-border-top-left-radius':'7px;','-webkit-border-bottom-left-radius':'7px;','background':'#FFF0A5/*{bgColorActive}*/ url(scripts/jquery/jquery-ui-189custom/development-bundle/themes/base/images/ui-bg_highlight-soft_75_ffe45c_1x100.png)/*{bgImgUrlActive}*/ 50%/*{bgActiveXPos}*/ 50%/*{bgActiveYPos}*/ repeat-x/*{bgActiveRepeat}*/'});
		}
	});

	this.objDiv.hover(function(){				
		$('#objDiv_' + objectId + ' .instanceBtn').show();
	},function(){	
		$('#objDiv_' + objectId + ' .instanceBtn').hide();
	});

	var object = this.object;
	if(object && object.metaworksContext && object.metaworksContext.when == 'blinking'){
		this.blinking();
	}

	if( object && object.metaworksContext && object.metaworksContext.how == 'sns'){
		$('#' + this.divId).css('border-top','1px solid #E3E3E3');
		$('.tbl_type td').css('border','none');
	}

	$('#td_' + this.objectId).click({objectId : this.objectId}, function(event){	 	
		var objectId = event.data.objectId;

		var object = mw3.getObject(objectId);

		var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
		
		if(session && session.ux == 'oce' && session.lastPerspecteType == 'inbox'){
			
			var object = mw3.getObject(objectId);
			object.goSns();
		}else{
			object.view(true, function(){
				var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
				
				if(session && session.ux == 'phone'){
					$('.mw3_popup').each(function(){
						var objectId = $(this).attr('objectId');

						mw3.getFaceHelper(objectId).destoryPopup();
					});
				}
			});
		}
		
	});
	
	var faceHelper = this;
	
	faceHelper.resize();	



//	$('#tr_' + this.objectId).mouseenter({objectId : this.objectId}, function(event){
//	mw3.call(event.data.objectId, 'overTooltip');
//	});
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

			blinkDiv.show( 'pulsate' ,  1500 ).height(blinkDiv.parent().height());		


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
			this.timeout = setTimeout(function() {\fs
				mw3.getFaceHelper(objectId).blinking();
			}, 1000);*/

		},
		resize : function(){
			var phone_size = 300;
			
			if(phone_size <= this.objDiv.width()){
				if(this.objDiv.hasClass('phone')){
					this.objDiv.removeClass('phone');
					//this.objDiv.find('.first_td').removeClass('phone');
					//this.objDiv.find('.instanceType').removeClass('phone');				
				}				

			}else{
				if(!this.objDiv.hasClass('phone')){
					this.objDiv.addClass('phone');
					//this.objDiv.find('.first_td').addClass('phone');
					//this.objDiv.find('.instanceType').addClass('phone');				
				}
			}

		}
};
