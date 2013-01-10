var org_uengine_codi_mw3_model_NewInstancePanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = 'objDiv_' + this.objectId;
	this.object = mw3.objects[this.objectId];
	
	var securityLevel = this.object.securityLevel.selected;
	
	var securityBox = $('#openmind_box');
	
	$('#openmind_btn').click(function(){
		securityBox.toggle();
		
		if(securityBox.is(':visible')){
			securityBox.hover(
				// mouse in
				function() {
					$(this).attr('focus', 'in');
				},
				// mouse out
				function() {
					$(this).attr('focus', 'out');
				}
			).attr('focus', 'out');
			
			$('body').bind('mousedown', function(){
				if('in' !=  securityBox.attr('focus'))
					$('#openmind_btn').trigger('click');
			});		
		}else{
			securityBox.unbind('hover');
			
			$('body').unbind('mousedown');
		}
	});
	
	

	
	$('#openmind_box li').bind('mousedown', function(){
		$(this).siblings().removeClass('selected').end().addClass('selected');
		
		$('#openmind_btn').trigger('click');
	}).filter(function(){
		if(securityLevel == $(this).attr('value'))
			return true;
		
	}).addClass('selected');
};

org_uengine_codi_mw3_model_NewInstancePanel.prototype = {
	getValue : function(){
		var securityLevel = $('#openmind_box li.selected').attr('value');
		
		this.object.securityLevel.selected = securityLevel;
		
		return this.object;
	}
};