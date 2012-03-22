var Window = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;

	this.divId = "objDiv_" + objectId;
	this.smallDivId = "sm_" + objectId;
	
	this.width = $("#" + this.divId).width();
	this.height = $("#" + this.divId).height();
	
	$("#" + this.divId).parent().css("padding-bottom", "35px").attr('height-margin', '35');
	
	$("#" + this.divId).addClass("mw3_window").css("height","100%");	
	$("#" + this.divId).parent().find("#info_" + objectId).remove();
}

Window.prototype.minimize = function(layoutName){

	// 최초 입력을 위해 입력여부 확인
	if($(".mw3_windowpanel").find("#" + this.smallDivId).length == 0)
		$("#" + this.smallDivId).appendTo(".mw3_windowpanel");
	
	$("#" + this.smallDivId).show();
		
	var layoutId = $("#objDiv_" + this.objectId).closest(".mw3_layout").attr("objectId");
	if(layoutId)
		mw3.getFaceHelper(layoutId).hide(layoutName);
}


Window.prototype.resume = function(layoutName){
	
	$("#" + this.smallDivId).hide();
	
	var layoutId = $("#objDiv_" + this.objectId).closest(".mw3_layout").attr("objectId");
	if(layoutId)
		mw3.getFaceHelper(layoutId).show(layoutName);
}

Window.prototype.startLoading = function(){
	$("#loader_" + this.objectId).show();
}

Window.prototype.endLoading = function(){
	$("#loader_" + this.objectId).hide();

}
