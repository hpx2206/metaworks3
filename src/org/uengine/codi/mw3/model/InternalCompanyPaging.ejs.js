var org_uengine_codi_mw3_model_InternalCompanyPaging = function(objectId, className){
    this.objectId = objectId;
    this.className = className; 
    this.divId = '#objDiv_' + objectId;
    this.div = $(this.divId);   
    this.object = mw3.objects[this.objectId];
    
    var object = this.object;
    var type = this.object.type;
    for(var i = 0; i <= this.object.totalPage; i++) {
    	$('#page_'+(i)+'').click(function(){
    		var value = $(this).attr('value');
    		
    		object.type = type;
    		object.currentPageNo = value;
    		object.loadCompanyByNumber();
    	});
    }
    
    
};


