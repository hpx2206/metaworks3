var org_uengine_codi_mw3_model_IInstance = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.windowObjectId = $('#' + this.divId).closest('.mw3_window').attr('objectId');
	
	 $('#' + this.divId).bind('click', {objectId: this.objectId},function(){
		 mw3.getFaceHelper(objectId).unBlinking();
		 
		 $(".tbl_type").parent().css("background","none");
		 $(this).css("background","#FFF5C1");
	 });
	 
	 var object = mw3.objects[this.objectId];
	 if(object && object.metaworksContext && object.metaworksContext.how == 'blinking'){
		 this.blinking();
	 }
}

	
org_uengine_codi_mw3_model_IInstance.prototype = {
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
		if (this.timeout) {
			clearTimeout(this.timeout);
			
			$('#' + this.divId).removeClass('blinking');
		}			
	},
	blinking : function(){
			if($('#' + this.divId).hasClass('blinking')){
				$('#' + this.divId).removeClass('blinking');
				
				$('#' + this.divId).animate({
					backgroundColor: "#FED5A3"
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
			}, 1000);
		
	}
}
