var Window = function(objectId, className){
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


Window.prototype.maximize = function(){
	alert(mainObjectId);


	
//	$("#" + this.divId).toggle();
	$("#" + this.smallDivId).toggle();
	
	
	
//	if(sectionContactDisable == 1){ 
//		outerLayout.toggle("west");
//		sectionWestDisable = 1;			
//	}else{
//		oriNaviHeight = $('.inner-north').height();
//		oriContactHeight = $('.inner-center').height() + 183;
//	}

//	$("#sm01").toggle();
//	sectionNaviDisable = 1;
//	
//	$('#innernorthval').val(oriNaviHeight);
//	$('#innercenterval').val(oriContactHeight);

}

Window.prototype.minimize = function(innerLayoutName, innerLayout, outerLayoutName, outerLayout){

	console.debug("minimize");
	console.debug("innerLayoutName : " + innerLayoutName);
	console.debug("innerLayout : " + innerLayout);
	console.debug("outerLayoutName : " + outerLayoutName);
	console.debug("outerLayout : " + outerLayout);
	
	$("#" + this.smallDivId).show();
	
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
	
	
/*	if(outer == "innerLayout" && inner == "north"){
		innerLayout.toggle(inner);
		navigationEnable = 0;
	
		if(contactEnable == 0){
			outerLayout.toggle("west");
		};
		
	}
	else if(outer == "innerLayout" && inner == "center"){
		
	     $('.inner-center').next('div').hide();
		 $('.inner-center').hide('clip',100);
		 contactEnable = 0
		 
		if(navigationEnable == 0){
			outerLayout.toggle("west");
		};
		 
	}
	else if(outer == "middleLayout" && inner == "west"){
		middleLayout.toggle(inner);
	}
	else if(outer == "middleLayout" && inner == "center"){
		middleLayout.toggle(inner);
	}*/
	
//	innerLayout.toggle("north");

	
	

	
//	$("#" + this.divId).closest("div").
	
//	$('.inner-north').height(oriNaviHeight + oriContactHeight + 183);
	
	

}


Window.prototype.resume = function(innerLayoutName, innerLayout, outerLayoutName, outerLayout){
	
	console.debug("resume");
	console.debug("innerLayoutName : " + innerLayoutName);
	console.debug("innerLayout : " + innerLayout);
	console.debug("outerLayoutName : " + outerLayoutName);
	console.debug("outerLayout : " + outerLayout);
	
	$("#" + this.smallDivId).hide();
	
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
	
	
/*	if(outer == "innerLayout" && inner == "north"){
		innerLayout.toggle(inner);
		navigationEnable = 1;
	
		if(contactEnable == 0){
			outerLayout.toggle("west");
		};
		
	}else if(outer == "innerLayout" && inner == "center"){
		
		 $('.inner-center').next('div').show();
		 $('.inner-center').show('clip',100);
		 contactEnable = 1;
		 
		if(navigationEnable == 0){
			outerLayout.toggle("west");
		};		 
	}else if(outer == "middleLayout" && inner == "west"){
		middleLayout.toggle(inner);
	}*/
	
	
	
}

Window.prototype.startLoading = function(){
	$("#loader_" + this.objectId).show();
}

Window.prototype.endLoading = function(){
	$("#loader_" + this.objectId).hide();

}
