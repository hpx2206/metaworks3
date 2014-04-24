var org_uengine_codi_mw3_webProcessDesigner_ProcessSelectPanel = function(objectId, className){
    
    this.objectId = objectId;
    this.className = className;
    
};

org_uengine_codi_mw3_webProcessDesigner_ProcessSelectPanel.prototype = {
    toOpener : function(target){
        var object = mw3.objects[this.objectId];
        object.definitionId = target;
    }
};