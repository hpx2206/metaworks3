var org_uengine_oce_dashboard_MyProjectPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);

	$(".process_map2 li").hover(
		
		function(){
			$(this).find(".editicon3").css("display","block");
		},
		function(){
			$(this).find(".editicon3").css("display","none");
		}			
	);
};