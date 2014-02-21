var org_uengine_codi_mw3_webProcessDesigner_VariableSelectBox = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
	
	$('#select_'+this.objectId).bind('change', {objectId : this.objectId},function(event){
		var object = mw3.objects[objectId];
		if( object.id == 'makeKey' || object.id == 'makeSecondKey' ){
            mw3.call(objectId, 'makeValiableChoice');
		}else{
			if( object.childSelectBox != null ){
				mw3.call(objectId, 'makeValiableChoiceChild');
			}
		}
	});
};

org_uengine_codi_mw3_webProcessDesigner_VariableSelectBox.prototype = {
    getValue : function(){
        var object = mw3.objects[this.objectId];
        
        var combo = $('#select_' + this.objectId);
        
        if(combo.length > 0){
            var change = combo.find('option:selected');
            
            object.selected = change.val();
            object.selectedText = change.text();                
        }
        return object;
    }
};