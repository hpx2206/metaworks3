var org_uengine_codi_mw3_model_InstanceList = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
}

$("#scroll_div").scroll(function(e) {	
	/*
	alert($("#scroll_div").scrollTop()+"//"+
	  	  $('#scroll_div>div').height()+"//"+
		  $("#scroll_div").height()
	);
	*/
	if($("#scroll_div").scrollTop() + 26 > $('#scroll_div>div').height() - $("#scroll_div").height()){
		console.log("last");
	}
});




