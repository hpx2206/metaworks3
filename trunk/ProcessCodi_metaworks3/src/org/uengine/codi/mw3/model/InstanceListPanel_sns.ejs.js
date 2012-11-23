var org_uengine_codi_mw3_model_InstanceListPanel_sns = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	
	this.divElement = $('#' + this.divId); 	
	this.divElement.addClass('mw3_layout').attr('objectId', objectId);
	
	var object = mw3.objects[this.objectId];
	if(object && object.title){
		if(this.windowObjectId){
			mw3.getFaceHelper(this.windowObjectId).setTitle(mw3.localize(object.title));
		}
	}	
	if(object && object.preloaded){
		var faceHelper = this;	
		faceHelper.load();	
		
		var scrollDiv = $('#objDiv_' + this.objectId + " .ui-layout-center .ui-layout-content")
		
		scrollDiv.scroll(function(e) {
			if(scrollDiv.scrollTop() == scrollDiv.find('div').eq(0).height() - scrollDiv.height() && lastMore){
				mw3.objects[lastMore].more();
			}
		});
		
		searchBarMagin = $('.newprocessbtn').width() + 20;
		$('.searchboxarea').css("margin-left",searchBarMagin);
		
		
		/*		
		 	$('#' + this.divId + ' .ui-layout-content').mCustomScrollbar({
				callbacks:{
					
					onTotalScroll:function(){
						mw3.objects[lastMore].more();
						setTimeout(function(){$("#method_37_switchToScheduleCalendar .ui-layout-content").mCustomScrollbar("update");},2000);
					}
				}
			});
		*/
		
		$('#' + mw3._getObjectDivId(mw3.getChildObjectId(this.objectId, 'topicFollowers')) + " #searchbox").css("border-bottom","#9EBFC4 1px solid ");
	}else{
		setTimeout(function(){
			mw3.call(objectId, 'load');	
		}, 1);
	}
	
}


org_uengine_codi_mw3_model_InstanceListPanel_sns.prototype = {
	load : function(){
		
		var object = mw3.objects[this.objectId];
		var options = {
				togglerLength_open:	0, 
				spacing_open:		0, 
				spacing_closed:		0,
				center__onresize:	'mw3.getFaceHelper('+this.objectId+').resizeChild()'
		}

		this.layout = this.divElement.layout(options);
	},
	destroy : function(){
		if(this.layout)
			this.layout.destroy();
	},
	resize : function(){
		if(this.layout){
			//console.debug('visible : ' + $(this.divId).visible);
			
			this.layout.resizeAll();
			
			this.resizeChild();
		}
	},
	resizeChild : function(){
		
		this.divElement.find('.mw3_layout, .mw3_resize').each(function(index, value){
			var layoutId = value.getAttribute('objectId');
			
			if(layoutId)
				mw3.getFaceHelper(layoutId).resize();
		});
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
		
	}	
		
}