

var org_uengine_codi_platform_Console = function(objectId, className){
	this.objectId = objectId;
}

org_uengine_codi_platform_Console.prototype.addLog = function(log){
	$('#log_' + this.objectId).append(log + "<br>");
	
	return function(){} //for reverse AJAX callback from DWR
}

org_uengine_codi_platform_Console.prototype.clear = function(){
	$('#log_' + this.objectId).html("");
	
	return function(){} //for reverse AJAX callback from DWR
}