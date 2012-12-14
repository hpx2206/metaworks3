var org_uengine_kernel_Role = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	if(typeof this.object != 'undefined' && this.object != null){
		var roleName = this.object.name; 
		
		$('#selectable_' + this.objectId + ' li').each(function(index, element){
			if(roleName == $(this).html()){
				$(this).addClass('ui-selected');
			}
		});
	}
	
	$('#selectable_' + this.objectId).selectable();
	
};

org_uengine_kernel_Role.prototype = {
	getValue : function(){
		var roleName =  $('#selectable_' + this.objectId + ' .ui-selected').html();
		
		if(typeof this.object != 'undefined' && this.object != null){
			this.object.name = $('#selectable_' + this.objectId + ' .ui-selected').html();
		}else{
			if(roleName != null){
				this.object = {
					__className : 'org.uengine.kernel.Role',
					name : $('#selectable_' + this.objectId + ' .ui-selected').html()
				};
			}
		}
		
		return this.object;
	}
}
