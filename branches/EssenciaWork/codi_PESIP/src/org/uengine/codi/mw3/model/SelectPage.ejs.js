var org_uengine_codi_mw3_model_SelectPage = function(objectId, className){
    this.objectId = objectId;
    this.className = className; 
    this.divId = '#objDiv_' + objectId;
    this.div = $(this.divId);   
    this.object = mw3.objects[this.objectId];
    
    
    var object = this.object;
    for(var i = 0; i < object.pageLength; i++) {
        $('#'+object.pageId+'_page_'+(i+1)+'').click(function(){
                    
            
            var value = $(this).attr('value');
            object.selectedPage = value;
            
            if(object.pageId == 'view') {
                object.loadView();
                
            } else if(object.pageId == 'select_view') {
                object.loadSelectView();
                
            } else {
                object.loadListView();
                
            }
            
        });
    }
};

