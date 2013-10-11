var org_uengine_oce_OcePerspectivePanel = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;	
	this.divId = '#objDiv_' + this.objectId;
	
	$(this.divId).parent().css({"background":"none","width":"100%","overflow":"hidden"});
	$(this.divId).parent().hover(
			function(){
				$(this).css({"overflow":"auto","margin-right":"0px"});
			},
			function(){
				$(this).css({"overflow":"hidden"});
			}
	);
};