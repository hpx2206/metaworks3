var Window = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.divId = "objDiv_" + objectId;
	this.smallDivId = "sm_" + objectId;
	
	this.width = $("#" + this.divId).width();
	this.height = $("#" + this.divId).height();
	
		
}

Window.prototype.maximize = function(){
	
	$("#" + this.divId).toggle();
	$("#" + this.smallDivId).toggle();
	
//	if(sectionContactDisable == 1){ 
//		outerLayout.toggle("west");
//		sectionWestDisable = 1;			
//	}else{
//		oriNaviHeight = $('.inner-north').height();
//		oriContactHeight = $('.inner-center').height() + 183;
//	}
//	innerLayout.toggle("north");	
//	$("#sm01").toggle();
//	sectionNaviDisable = 1;
//	
//	$('#innernorthval').val(oriNaviHeight);
//	$('#innercenterval').val(oriContactHeight);

}

Window.prototype.minimize = function(){
	$("#" + this.smallDivId).appendTo("#smline");
	$("#" + this.smallDivId).toggle();
	$("#" + this.divId).toggle();
	
//	$("#" + this.divId).closest("div").
	
//	$('.inner-north').height(oriNaviHeight + oriContactHeight + 183);
	
}

Window.prototype.resume = function(){
	$("#" + this.smallDivId).toggle();
	$("#" + this.divId).toggle();
	
}

Window.prototype.startLoading = function(){
	
}

Window.prototype.endLoading = function(){

}
