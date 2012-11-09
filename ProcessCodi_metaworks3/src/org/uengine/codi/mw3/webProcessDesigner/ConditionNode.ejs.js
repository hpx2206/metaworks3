var org_uengine_codi_mw3_webProcessDesigner_ConditionNode = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.mw3Obj = mw3.getObject(objectId);
//	$('#condiNode_' + this.objectId).bind(
//			'mouseup', {objectId : objectId}, function(event){
//		    	var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
//		 		var clipboardNode = session.clipboard;
//		 		if( clipboardNode){
//		 			var object = mw3.getObject(event.data.objectId);
//			 		// conditionNode 를 conditionNode에 떨구었을때
//			 		if(clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.ConditionNode"){
//			 			object.nameNext = clipboardNode.conditionName;
//			 			object.typeNext = "Node";
//			 			object.addChildNode();
//			 		}		
//			 		// And노드를 conditionNode에 떨구었을때 
//			 		else if(clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.And"){
//			 			object.nameNext = "And";
//			 			object.typeNext = "And";
//			 			object.addChildNode();
//			 		}		
//			 		// Or노드를 conditionNode에 떨구었을때 
//			 		else if(clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.Or"){
//			 			object.nameNext = "Or";
//			 			object.typeNext = "Or";
//			 			object.addChildNode();
//			 		}		
//			 		// Evaluate노드를 conditionNode에 떨구었을때 
//			 		else if(clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.Evaluate"){
//			 			object.nameNext = "Eval";
//			 			object.typeNext = "Eval";
//			 			object.addChildNode();
//			 		}		
//		 		}
//	 		session.clipboard = null;	// 이거로 셋팅을 해 놓으면 drop() 메서드에서 null 로 받아버림
//    });
//	
//	this.mw3Obj.id = objectId;
};

