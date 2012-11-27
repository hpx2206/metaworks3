var org_uengine_codi_mw3_model_InstanceView = function(objectId, className){
	this.objectId = objectId;
	this.className = className;	
	this.divId = "#objDiv_" + objectId;
	
	var object = mw3.objects[this.objectId];
	if(object){
		$(this.divId).addClass('mw3_layout').attr('objectId', this.objectId);

		var faceHelper = this;
		this.windowObjId = $(this.divId).closest('.mw3_window').attr('objectId');
		
		if(typeof this.windowObjId == 'undefined'){
			faceHelper.load();					
		}else{
			var windowFaceHelper = mw3.getFaceHelper(this.windowObjId);
			
			if(windowFaceHelper && windowFaceHelper.layoutDiv && windowFaceHelper.layoutDiv.css('display') != 'none')
				faceHelper.load();					
		}
	}
 	$('#objDiv_' + objectId + ' .ui-layout-content').mCustomScrollbar();
	
	
//		var posting = [];
//		
//		var postIds = object.crowdSourcer.postIds;	
//		
//		if(postIds != null){
//			var cnt = 0;
//			
//			for(var i=0; i<postIds.length; i++){
//				var num = 20;
//				 
//				FB.api(postIds[i], {
//					limit : num
//				}, function(response) {
//					if (!response || response.error) {
//						//console.debug(response);
//						
//						if(typeof response.error != "undefined")
//							alert("error : " + response.error.message);
//						
//					} else {
//						for(var j=0; j<response.comments.count; j++){
//							var comment = response.comments.data[j];
//							var user = {
//									userId : comment.from.id,
//									name : comment.from.name,
//									__className : "org.uengine.codi.mw3.model.IUser"
//							};
//							
//							posting[cnt] = {    type : "postings",
//									           title : comment.message,
//									        endpoint : comment.from.id,
//									          writer : user,
//									     __className : "org.uengine.codi.mw3.model.PostingsWorkItem"
//									  
//							  };
//							
//							cnt++;					
//						}
//						
//						if(i == postIds.length){
//							mw3.setObject(object.threadPosting.__objectId, posting);
//						}
//					}
//				});	
//			}
//		}
//	}

}

org_uengine_codi_mw3_model_InstanceView.prototype = {
	load : function(){
		var object = mw3.objects[this.objectId];
		var options = {
				togglerLength_open:	0, 
				spacing_open:		0, 
				spacing_closed:		0,
				center__onresize:	'mw3.getFaceHelper('+this.objectId+').resizeChild()'
		}
		
		this.layout = $(this.divId).layout(options);
	},
	destroy : function(){
		if(this.layout)
			$(this.divId).layout().destroy();
	},
	resize : function(){
		var windowFaceHelper = mw3.getFaceHelper(this.windowObjId);

		if(windowFaceHelper && windowFaceHelper.layoutDiv && windowFaceHelper.layoutDiv.css('display') != 'none' && typeof windowFaceHelper.layout != 'undefined' && typeof this.layout == 'undefined')
			this.load();		
		
		if(this.layout){
			this.layout.resizeAll();
			
			this.resizeChild();
		}
	},
	resizeChild : function(){
		$(this.divId).find('.mw3_layout, .mw3_resize').each(function(index, value){
			var layoutId = value.getAttribute('objectId');
			
			if(layoutId)
				mw3.getFaceHelper(layoutId).resize();
		});
	}
}