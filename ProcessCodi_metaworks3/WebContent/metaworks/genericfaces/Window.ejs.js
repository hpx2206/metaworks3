var Window = function(objectId, className){
	this.objectId = objectId;
	this.className = className;



	this.divId = "objDiv_" + objectId;
	this.smallDivId = "sm_" + objectId;
	
	this.width = $("#" + this.divId).width();
	this.height = $("#" + this.divId).height();

	
	
	$("#" + this.divId).css("height","100%");
	
	$("#" + this.smallDivId).appendTo("#smline");

//	var parent = $("#" + this.divId).parent();
	
//	$("#" + this.divId).parent().append($("#" + this.divId).html());
	
//	$("#" + this.divId).parent().find("#" + this.divId).remove();
	$("#" + this.divId).parent().find("#info_" + objectId).remove();
	

}


Window.prototype.maximize = function(){
	
	
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

Window.prototype.minimize = function(outer , inner){
	
	
//	$("#" + this.smallDivId).appendTo("#smline");
	$("#" + this.smallDivId).toggle();
//	$("#" + this.divId).width(10);
//	outerLayout = $('#container').resizeAll(); 
//	$("#" + this.divId).toggle();
	
//	alert(outer +","+ inner);
	
	if(outer == "innerLayout" && inner == "north"){
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
	}
	
//	innerLayout.toggle("north");

	
	

	
//	$("#" + this.divId).closest("div").
	
//	$('.inner-north').height(oriNaviHeight + oriContactHeight + 183);
	
	

}

Window.prototype.resume = function(outer , inner){
	$("#" + this.smallDivId).toggle();
//	$("#" + this.divId).toggle();
	
	if(outer == "innerLayout" && inner == "north"){
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
	}
	
	
	
}

Window.prototype.startLoading = function(){
	$("#loader_" + this.objectId).show();
}

Window.prototype.endLoading = function(){
	$("#loader_" + this.objectId).hide();

}
