

var org_uengine_codi_mw3_svn_SVNWindow = function(objectId, className){
	this.objectId = objectId;
}

org_uengine_codi_mw3_svn_SVNWindow.prototype.addLog = function(log){
	$('#log_' + this.objectId).append(log);
	
	return function(){} //for reverse AJAX callback from DWR
}