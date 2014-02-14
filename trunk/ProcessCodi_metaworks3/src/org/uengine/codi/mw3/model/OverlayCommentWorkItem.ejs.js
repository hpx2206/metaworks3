var org_uengine_codi_mw3_model_OverlayCommentWorkItem = function(objectId, className){
	
	this.objectId = objectId;

	var workItem = mw3.objects[this.objectId];
	var parentWorkItem = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IWorkItem@"+ workItem.overlayCommentOption.parentTaskId);
	
	if('view' == mw3.objectContexts[this.objectId].__metaworksContext.when){
		if('comment' == workItem.metaworksContext.how){
			$('.overlay_comment').hover(
					function(){$(this).css({"z-index":"101",'opacity':'1'})},
					function(){$(this).css({"z-index":"100",'opacity':'0.5'})}
			);
			$('.overlay_comment_line').hover(
					function(){$(this).parent().find('.overlay_comment').css({'z-index':'101','opacity':'1'})},
					function(){$(this).parent().find('.overlay_comment').css({'z-index':'100','opacity':'0.5'})}
			);
		}else{
			var clone = mw3.clone(workItem);
			clone.metaworksContext.how = 'comment';
	
			mw3.removeObject(this.objectId);
			
			var ovryCmntTarget = {
		    	__className : 'org.uengine.codi.mw3.model.IWorkItem',
		    	taskId : workItem.overlayCommentOption.parentTaskId
		    };
		    
			var toAppend = {
				__className : 'org.metaworks.ToAppend',
				parent : ovryCmntTarget,
				target : clone,
				match : true
			};
			
			mw3.locateObject(toAppend, toAppend.__className, 'body');
		}
	}else{
		var div = $("#objDiv_" + parentWorkItem.__objectId);
	
		if(workItem.taskId==null){
			workItem.overlayCommentOption.x = (mw3.mouseX - div.offset().left -22) / (div.width()/100);
			workItem.overlayCommentOption.y = (mw3.mouseY - div.offset().top -34) / (div.height()/100);
		}		

		var title = (workItem.metaworksContext.when == mw3.WHEN_EDIT) ? workItem.title :'';
		var comment = prompt('설명을 입력해주세요 : ', title);
		
		if(comment){
			workItem.rootInstId = parentWorkItem.rootInstId;
			workItem.title = comment;
			workItem.add();		
		}			
	}	

};
