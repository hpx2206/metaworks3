var org_uengine_codi_mw3_webProcessDesigner_ConditionPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	//this.mw3Obj = mw3.getObject(objectId);
	$("#cdContainer").bind(
			'mouseup', {objectId : objectId}, function(event){
		    	var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
		 		var clipboardNode = session.clipboard;
		 		if( clipboardNode != null ){
		 			var object = mw3.getObject(event.data.objectId);
			 		// And노드를 캔버스에 떨구었을때 
			 		if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.And"){
			 			object.dragClassName = "And";
			 			object.drawCondition();
			 		}		
			 		// Or노드를 캔버스에 떨구었을때 
			 		else if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.Or"){
			 			object.dragClassName = "Or";
			 			object.drawCondition();
			 		}		
			 		// Evaluate노드를 캔버스에 떨구었을때 
			 		else if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.Evaluate"){
			 			object.dragClassName = "evaluate";
			 			object.drawCondition();
			 		}		
		 		}
	 		session.clipboard = null;
    });
	
};

