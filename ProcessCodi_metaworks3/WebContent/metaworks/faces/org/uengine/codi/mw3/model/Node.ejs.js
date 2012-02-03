var org_uengine_codi_mw3_model_Node = function(objectId, objectType){
	this.objectId = objectId;
	this.objectType = objectType;

	var node = mw3.objects[this.obnjectId];
	node.nodeId = (__rule_seq ++);

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

