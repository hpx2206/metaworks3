var org_uengine_codi_mw3_webProcessDesigner_ConditionTreeNodeView = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	if(object != null && object.expressionType != null){
		$("input:radio[name=expression_"+this.objectId+"][value="+object.expressionType+"]").attr("checked", "checked");
	}
	
};

org_uengine_codi_mw3_webProcessDesigner_ConditionTreeNodeView.prototype = {
		getValue : function(){
			var object = mw3.objects[this.objectId];
			var expType = $("input:radio[name=expression_"+this.objectId+"]:checked").val();
			
			object.expressionType = expType;
			
			return object;
		}
};