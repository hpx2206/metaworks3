var org_uengine_codi_mw3_model_RegionPerspective = function(objectId, className){
    this.objectId = objectId;
    this.className = className; 
    this.divId = '#objDiv_' + objectId;
    this.div = $(this.divId);   
    
    this.div.hover(
        function(){
            $(this).find('.west_more_btn').show();
        },
        function(){
            $(this).find('.west_more_btn').hide();
        }
    )

};
