var org_uengine_codi_mw3_model_OverlayCommentWorkItem = function(objectId, className){
	
	this.objectId = objectId;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.object = mw3.objects[this.objectId];

	var faceHelper = this;
	var context = mw3.objectContexts[this.objectId].__metaworksContext;
	
	if('view' == context.when){
		if('comment' == context.how){
			faceHelper.bindMouseOverHighlight();
		}else{
		 	if(faceHelper.ableDraw())
		 		faceHelper.draw();
			else
				faceHelper.bind();
		}
	}else{
		faceHelper.showInputDialog();
	}	
};

org_uengine_codi_mw3_model_OverlayCommentWorkItem.prototype = {
	draw : function(){
		var clone = mw3.clone(this.object);
		clone.metaworksContext.how = 'comment';

		var ovryCmntTarget = {
	    	__className : 'org.uengine.codi.mw3.model.IWorkItem',
	    	taskId : this.object.overlayCommentOption.parentTaskId
	    };
	    
		var toAppend = {
			__className : 'org.metaworks.ToAppend',
			parent : ovryCmntTarget,
			target : clone,
			match : true
		};
		
		mw3.locateObject(toAppend, toAppend.__className, 'body');
		mw3.onLoadFaceHelperScript();
	},
	
	ableDraw : function(){
		var parentWorkItem = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IWorkItem@"+ this.object.overlayCommentOption.parentTaskId, true);

		if(parentWorkItem){
			var div = $("#objDiv_" + parentWorkItem.__objectId);
			if(div.length && mw3.MESSAGE_LOADING != div.html() != parentWorkItem.more)
				return true;
		}
		return false;
	},
	
	bind : function(){
		this.windowObjectId = this.objectDiv.closest('.mw3_window').attr('objectId');
			
		$('#' + mw3._getObjectDivId(this.windowObjectId)).one(
			'loaded.workitem_' + this.object.overlayCommentOption.parentTaskId, 
			{objectId: this.objectId}, 
			function(event, ui){
				mw3.getFaceHelper(event.data.objectId).draw();
		});
	},
	
	bindMouseOverHighlight : function(){
		$('.overlay_comment').hover(
				function(){$(this).css({"z-index":"101",'opacity':'1'})},
				function(){$(this).css({"z-index":"100",'opacity':'0.5'})}
		);
		$('.overlay_comment_line').hover(
				function(){$(this).parent().find('.overlay_comment').css({'z-index':'101','opacity':'1'})},
				function(){$(this).parent().find('.overlay_comment').css({'z-index':'100','opacity':'0.5'})}
		);
	},	
	
	showInputDialog : function(){
		var parentWorkItem = mw3.getAutowiredObject("org.uengine.codi.mw3.model.IWorkItem@"+ this.object.overlayCommentOption.parentTaskId, true);
		var div = $("#objDiv_" + parentWorkItem.__objectId);
		
		if(this.object.taskId==null){
			var faceHelper = parentWorkItem.getFaceHelper();
			this.object.overlayCommentOption.x = (faceHelper.mouseX - div.offset().left -10) / (div.width()/100);
			this.object.overlayCommentOption.y = (faceHelper.mouseY - div.offset().top ) / (div.height()/100);
		}		

		var title = (this.object.metaworksContext.when == mw3.WHEN_EDIT) ? this.object.title :'';
		var comment = prompt('설명을 입력해주세요 : ', title);
		
		if(comment){
			this.object.rootInstId = parentWorkItem.rootInstId;
			this.object.title = comment;
			this.object.add();		
		}		
	}
}