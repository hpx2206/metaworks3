var org_metaworks_widget_Window = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	this.divId = "objDiv_" + objectId;
	this.smallDivId = "sm_" + objectId;
	
	this.width = $("#" + this.divId).width();
	this.height = $("#" + this.divId).height();
	
	$("#" + this.divId).css("height","100%");
	$("#" + this.smallDivId).appendTo("#smline");
	$("#" + this.divId).parent().find("#info_" + objectId).remove();
}


org_metaworks_widget_Window.prototype.maximize = function(){
	$("#" + this.smallDivId).toggle();
}

org_metaworks_widget_Window.prototype.minimize = function(innerLayoutName, innerLayout, outerLayoutName, outerLayout){

	console.debug("minimize");
	console.debug("innerLayoutName : " + innerLayoutName);
	console.debug("innerLayout : " + innerLayout);
	console.debug("outerLayoutName : " + outerLayoutName);
	console.debug("outerLayout : " + outerLayout);
	
	$("#" + this.smallDivId).show();
	
	if(innerLayoutName != ""){
		var layoutObjectId = $(".mw3_layout").attr("objectid")

		if(innerLayoutName == "center"){
		     $('.' + innerLayout + '-' + innerLayoutName).next('div').hide();
			 $('.' + innerLayout + '-' + innerLayoutName).hide('clip',100);
		}else{
			var innerLayoutObj = mw3.getFaceHelper(layoutObjectId).getLayout(innerLayout);
			innerLayoutObj.toggle(innerLayoutName);		
		}
		
		var innerLayoutStatus = mw3.getFaceHelper(layoutObjectId).getLayout(innerLayout + "_status");
		
		innerLayoutStatus = innerLayoutStatus - 1;
		
		console.debug("innerLayoutStatus : " + innerLayoutStatus);
		if(innerLayoutStatus == 0){
			var outerLayoutObj = mw3.getFaceHelper(layoutObjectId).getLayout(outerLayout);
			
			outerLayoutObj.toggle(outerLayoutName);
		}
		
		mw3.getFaceHelper(layoutObjectId).putLayout(innerLayout + "_status", innerLayoutStatus);
	}else{
	     $('#objDiv_' + this.objectId).next('div').hide();
		 $('#objDiv_' + this.objectId).hide('clip',100);		
	}
}


org_metaworks_widget_Window.prototype.resume = function(innerLayoutName, innerLayout, outerLayoutName, outerLayout){
	
	console.debug("resume");
	console.debug("innerLayoutName : " + innerLayoutName);
	console.debug("innerLayout : " + innerLayout);
	console.debug("outerLayoutName : " + outerLayoutName);
	console.debug("outerLayout : " + outerLayout);
	
	$("#" + this.smallDivId).hide();
	
	if(innerLayoutName != ""){
		var layoutObjectId = $(".mw3_layout").attr("objectid")
		
		if(innerLayoutName == "center"){
		     $('.' + innerLayout + '-' + innerLayoutName).next('div').show();
			 $('.' + innerLayout + '-' + innerLayoutName).show('clip',100);
		}else{
			var innerLayoutObj = mw3.getFaceHelper(layoutObjectId).getLayout(innerLayout);
			innerLayoutObj.toggle(innerLayoutName);		
		}
	
		var innerLayoutStatus = mw3.getFaceHelper(layoutObjectId).getLayout(innerLayout + "_status");
		if(innerLayoutStatus == 0){
			var outerLayoutObj = mw3.getFaceHelper(layoutObjectId).getLayout(outerLayout);
			
			outerLayoutObj.toggle(outerLayoutName);		
		}
		
		innerLayoutStatus = innerLayoutStatus + 1;
		mw3.getFaceHelper(layoutObjectId).putLayout(innerLayout + "_status", innerLayoutStatus);
	}else{
	     $('#objDiv_' + this.objectId).next('div').show();
		 $('#objDiv_' + this.objectId).show('clip',100);		
	}

}

org_metaworks_widget_Window.prototype.close = function(){
    $('#objDiv_' + this.objectId).next('div').hide();
	$('#objDiv_' + this.objectId).hide('clip',100);
	$('#objDiv_' + this.objectId).parent().remove();
}

org_metaworks_widget_Window.prototype.startLoading = function(){
	$("#loader_" + this.objectId).show();
}

org_metaworks_widget_Window.prototype.endLoading = function(){
	$("#loader_" + this.objectId).hide();

}
