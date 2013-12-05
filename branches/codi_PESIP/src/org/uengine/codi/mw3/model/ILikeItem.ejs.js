var org_uengine_codi_mw3_model_ILikeItem = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    this.object = mw3.objects[this.objectId];
    
    loaded = this.object.loaded;
    objectInstId = this.object.instId;
    
   
    
}

org_uengine_codi_mw3_model_ILikeItem.prototype = {
    loaded : function(){
        if(loaded == false) {
            mw3.call(this.objectId, 'findLikeCount');
        }
    }
}
