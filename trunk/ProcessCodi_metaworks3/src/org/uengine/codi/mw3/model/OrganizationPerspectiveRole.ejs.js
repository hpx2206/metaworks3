var org_uengine_codi_mw3_model_OrganizationPerspectiveRole = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	
	this.object = mw3.objects[this.objectId];
	
};

org_uengine_codi_mw3_model_OrganizationPerspectiveRole.prototype = {
	toAppend : function(html){
		$('#' + this.divId + ' ul').append($('<li>')).append( html );
	}
};

org_uengine_codi_mw3_model_OrganizationPerspectiveRole.prototype.loaded = function(){
	$('#' + this.divId + ' a').click(function(){
		$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
		$(this).parent().addClass('selected_navi');
	});
};
