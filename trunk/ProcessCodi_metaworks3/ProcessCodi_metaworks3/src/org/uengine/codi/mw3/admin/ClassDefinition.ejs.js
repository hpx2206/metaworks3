var tryClassDefinition = false;
var tryRunning = false;

var org_uengine_codi_mw3_admin_ClassDefinition = function(objectId, className){
	this.objectId = objectId;
	this.classname = className;
	
	var object = mw3.objects[objectId];
	
	$("#objDiv_" + objectId).css("height", "100%");
	
	if(object.className != null){
		$(".mw3_window").each(function(){
			if($(this).attr("className") == "org.uengine.codi.mw3.model.ContentWindow")
				$(this).find("#navigationTitle").html(object.className + ".java");
		});
	}
	

	/*
	if(!tryClassDefinition && object.alias == null){
		setTimeout("mw3.test(" +objectId + ", 'ClassDefinition', {guidedTour: true})", 1000);

		tryClassDefinition = true;
	}
	
	if(!tryRunning && object.alias != null){
		setTimeout("mw3.test(" +objectId + ", 'run()', {guidedTour: true})", 1000);

		tryRunning = true;
	}
	*/
}