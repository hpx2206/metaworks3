var org_uengine_codi_platform_Console = function(objectId, className){
	this.objectId = objectId;
}

org_uengine_codi_platform_Console.prototype.addLog = function(log){
	$('#log_' + this.objectId).append(log + "<br>");
	
	return function(){} //for reverse AJAX callback from DWR
}


org_uengine_codi_platform_Console.prototype.addError = function(log){
	$('#log_' + this.objectId).append(log + "<br>");
	
	var workflowy = mw3.getAutowiredObject('org.uengine.codi.mw3.knowledge.Workflowy');
	
	if(workflowy){

		workflowy.keyword = log.split('$').join('');
		workflowy.search();
	}
	
	return function(){} //for reverse AJAX callback from DWR	
}

org_uengine_codi_platform_Console.prototype.clear = function(){
	$('#log_' + this.objectId).html("");
	
	return function(){} //for reverse AJAX callback from DWR
}