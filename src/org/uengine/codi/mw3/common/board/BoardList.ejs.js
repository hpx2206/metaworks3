var org_uengine_codi_mw3_common_board_BoardList = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#' + mw3._getObjectDivId(this.objectId);
	
	$(".tbl_type2 tr").click(function(){
		$(".tbl_type2 tr").css("background","#fff");
		$(this).css("background","#E7F1EB");
	});
}