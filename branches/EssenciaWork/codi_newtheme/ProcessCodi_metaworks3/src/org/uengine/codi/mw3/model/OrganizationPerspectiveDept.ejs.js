var org_uengine_codi_mw3_model_OrganizationPerspectiveDept = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);	
};

org_uengine_codi_mw3_model_OrganizationPerspectiveDept.prototype = {
	toAppend : function(html){
		$('#' + this.divId + ' ul').append($('<li>')).append( html );
	}
};



org_uengine_codi_mw3_model_OrganizationPerspectiveDept.prototype.loaded = function(){
	$('#' + this.divId + ' a').click(function(){
		$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
		$(this).parent().addClass('selected_navi');
	});
};
