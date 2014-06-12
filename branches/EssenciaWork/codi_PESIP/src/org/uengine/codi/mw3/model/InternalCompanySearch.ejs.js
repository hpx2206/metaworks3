var org_uengine_codi_mw3_model_InternalCompanySearch = function(objectId, className){
    this.objectId = objectId;
    this.className = className; 
    this.divId = '#objDiv_' + objectId;
    this.div = $(this.divId);   
    this.object = mw3.objects[this.objectId];
    
    $(this.divId).css({
		'margin-left' : '70px'
		})
    
};
