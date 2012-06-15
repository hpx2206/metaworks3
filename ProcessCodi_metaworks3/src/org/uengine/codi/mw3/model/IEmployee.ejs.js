var org_uengine_codi_mw3_model_IEmployee = function(objectId, className){
	var object = mw3.objects[objectId];	
	
	this.objectId = objectId;
	this.className = className;

	var theLoginHelper = this;
	
	$('#objDiv_' + this.objectId).parent().parent().css({'border':'none'});
};

org_uengine_codi_mw3_model_IEmployee.prototype = {
	getValue : function(){
		var object = mw3.objects[this.objectId];
		
		object.preferUX = $('#uxStyle_'+this.objectId +':checked').val();
		
		return object;
	},
	startLoading : function(){
		$('.logo').after('<div id=\"mw3_progress\" style=\"width:128px; height:15px; float:left; margin-top:20px;  margin-right:20px;"><img src=\"images/waveStyle/ajax-loader_t.gif\" /></div>');
	},
	endLoading : function(){
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 500);		
	},
	destroy : function(){
		setTimeout(function(){
			$('#mw3_progress').remove();
		}, 500);		
		
	},
	showStatus : function(){
		
	}		
}


//org_forx_model_codi_IEmployee.prototype.editInfo = function(){
//	var object = mw3.objects[this.objectId];
//	//object.metaworksContext.when = mw3.WHEN_EDIT;
//	mw3.editObject(this.objectId);
//};