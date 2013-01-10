var org_uengine_codi_mw3_model_NewInstancePanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = 'objDiv_' + this.objectId;
	
	$('#openmind_btn').click(function(){
		$('#openmind_box').show();		
	});
	
	$('#openmind_box li').click(function(){
		$('#openmind_box').hide();		
	});

};