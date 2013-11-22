var org_uengine_codi_mw3_model_DecisionTreeNode = function(objectId, objectType){
	this.objectId = objectId;
	this.objectType = objectType;

	var node = mw3.objects[this.objectId];
	node.nodeId = (__rule_seq ++);
	
	var selectorId = "#variableSelector_" + objectId;
	
	this.selector = $(selectorId)[0];
	
	this.refreshVariableSelector();

}; 


var __rule_seq = 0;

//org_uengine_codi_mw3_model_Node.prototype.getValue = function(){
//	
//	var node = mw3.objects[this.obnjectId];
//	node.nodeId = (__rule_seq ++);
//	alert(nodeId);
//	
//	return node;
//	
//};

org_uengine_codi_mw3_model_DecisionTreeNode.prototype.refreshVariableSelector = function(){
	var ruleVariableDefinition = mw3.getAutowiredObject("org.uengine.codi.mw3.model.RuleVariableDefinition");
	
	var selectedValue = null;
	if( this.selector.options[this.selector.selectedIndex] != null ){
		selectedValue = this.selector.options[this.selector.selectedIndex].value;
	}
	
	if(ruleVariableDefinition.classFields != null) {
		this.selector.options = null;
		for(var i=0;i<ruleVariableDefinition.classFields.length;i++) {
			var classField = ruleVariableDefinition.classFields[i];
			
			this.selector.options[i] = document.createElement('option');

			this.selector.options[i].text = classField.displayname;
			this.selector.options[i].value = classField.id;
		}
		$("#variableSelector_" + this.objectId +" > option[value="+selectedValue+"]").attr("selected", "true");
	}
};

org_uengine_codi_mw3_model_DecisionTreeNode.prototype.getValue = function(){
	var object = mw3.getObjectFromUI(this.objectId);
	object.name = this.selector.options[this.selector.selectedIndex].value;

	return object;
}

