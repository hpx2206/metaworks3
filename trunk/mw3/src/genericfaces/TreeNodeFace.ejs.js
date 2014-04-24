var TreeNodeFace = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.nodeDiv = this.objectDiv.children('div');
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;

	var metadata = mw3.getMetadata(this.className);
	
	if(metadata.childrenFieldDescriptor)
		this.childFieldName = metadata.childrenFieldDescriptor.name;
	
	if(metadata.serviceMethodContexts){
		for(var i=0; i<metadata.serviceMethodContexts.length; i++){
			var servceMethodContext = metadata.serviceMethodContexts[i];
			
			if(servceMethodContext.childrenLoader){
				if(!mw3.isHiddenMethodContext(servceMethodContext, this.object))
					this.childrenLoaderMethodName = servceMethodContext.methodName;
			}
		}
	}
	
	var faceHelper = this;
	
	if(this.childrenLoaderMethodName){
		// calc status
		var status = '';
		if(this.object.expanded)
			status = 'min';
		else
			status = 'plus';
		
		if(this.object.loaded)
			status += 'last';
		
		this.nodeDiv.addClass(status);

	}
			
	// add event mouse click
	$(this.nodeDiv).bind('click', {objectId : this.objectId}, function(event){
		mw3.getFaceHelper(event.data.objectId).select();
	});
}

TreeNodeFace.prototype = {
	destroy : function() {
		$(this.nodeDiv).unbind('click').unbind('dblclick');
	},
	startLoading : function(){
		
	},
	
	endLoading : function(){
		this.nodeDiv.removeClass('loading');		
	},
	
	showStatus : function(message){
		if(this.childrenLoaderMethodName + ' DONE.' == message){
			this.nodeDiv.addClass('minlast');
			this.nodeDiv.removeClass('min');

			if(this.object[this.childFieldName] == null || this.object[this.childFieldName].length == 0)
				this.nodeDiv.removeClass('minlast');
			
			var tree = this.getTree();

			tree.trigger('expanded');
		}
	},
	
	getTree : function(){
		var tree = this.objectDiv;
		
		if(!this.objectDiv.hasClass('filemgr-tree'))
			tree = tree.parentsUntil('.filemgr-tree').parent('.filemgr-tree');
		
		return tree;
	},
	
	select : function(){
		var tree = this.getTree();
		
		if(!this.nodeDiv.hasClass('selected'))
			tree.trigger('change', [this.objectId]);
		
		
		tree.find('.item-fix.selected').removeClass('selected');
		this.nodeDiv.addClass('selected');
	},
	
	expand : function(){
		this.expanded = true;
		
		if(this.nodeDiv.hasClass('pluslast')){
			this.nodeDiv.removeClass('pluslast');
			this.nodeDiv.addClass('minlast');
			
			this.objectDiv.children('u').show();
		}else if(this.nodeDiv.hasClass('plus')){
			this.nodeDiv.removeClass('plus');
			this.nodeDiv.addClass('min');

			if(this.childrenLoaderMethodName && this.childFieldName){
				this.nodeDiv.addClass('loading');
				
				mw3.call(this.objectId, this.childrenLoaderMethodName);
			}
		}
	},
	
	collapse : function(){
		if(this.nodeDiv.hasClass('minlast')){
			this.nodeDiv.removeClass('minlast');
			this.nodeDiv.addClass('pluslast');
		}else if(this.nodeDiv.hasClass('min')){
			this.nodeDiv.removeClass('min');
			this.nodeDiv.addClass('plus');
		}
		
		this.expanded = false;
		this.objectDiv.children('u').hide();
		
		var tree = this.getTree();
		
		tree.trigger('collapsed', [this.objectId]);
	},
	
	action : function(){		
		this.select();
		
		if(this.expanded)
			this.collapse();
		else
			this.expand();
	}
}