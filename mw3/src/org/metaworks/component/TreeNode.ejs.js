var org_metaworks_component_TreeNode = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;	
	
	this.nodeDiv = $('#' + this.objectDivId).children('div');
	
	if(this.object.root)
		this.nodeDiv.addClass('root');

	if('folder' == this.object.type){
		// calc status
		var status = '';
		if(this.object.expanded)
			status = 'min';
		else
			status = 'plus';
		
		if(this.object.loaded)
			status += 'last';
		
		this.nodeDiv.addClass(status);
		
		var faceHelper = this;
		if(!faceHelper.isExpand())
			faceHelper.collapse();
		
		$(this.nodeDiv).children().children('span').bind('click', {objectId : this.objectId}, function(event){
			mw3.getFaceHelper(event.data.objectId).action();
		});		
	}
	
	// add event mouse click
	$(this.nodeDiv).bind('click', {objectId : this.objectId}, function(event){
		mw3.getFaceHelper(event.data.objectId).select();
	});
	
	$(this.nodeDiv).bind('dblclick', {objectId : this.objectId}, function(event){
		mw3.getFaceHelper(event.data.objectId).action();
	});
};

org_metaworks_component_TreeNode.prototype = {
	destroy : function() {
		$(this.nodeDiv).unbind('click').unbind('dblclick');
	},
	startLoading : function(){
		
	},
	
	endLoading : function(){
		this.nodeDiv.removeClass('loading');		
	},
	
	showStatus : function(message){
		if('expand DONE.' == message){
			this.nodeDiv.addClass('minlast');
			this.nodeDiv.removeClass('min');
			
			this.object.loaded = true;
		}
	},
	
	toAppend : function(html, appendobject){
		var appendDiv = $('<u></u>').addClass('last').css({'display': 'block', 'height': 'auto', 'overflow': 'visible'});
		
		if(this.object.root)
			appendDiv.addClass('root');
		
		this.objectDiv.append(appendDiv);
		this.objectDiv.children('u').append(html);
		
		this.push(appendobject);
	},
	
	push : function(item){
		if(this.object == null || typeof this.object == 'undefined')
			return true;
		
		if(this.object.child == null || typeof this.object.child == 'undefined'){
			this.object.tabs = [];
		}
		
		this.object.child.push(item);
	},
	
	isExpand : function(){
		return (this.nodeDiv.hasClass('min') || this.nodeDiv.hasClass('minlast'));
	},
	
	isCollapse : function(){
		return (this.nodeDiv.hasClass('plus') || this.nodeDiv.hasClass('pluslast'));
	},

	select : function(){
		if(event.stopPropagation){
			event.stopPropagation();
		}else if(window.event){
			window.event.cancelBubble = true;
		}
		
		var tree = this.objectDiv.parentsUntil('.filemgr-tree').parent('.filemgr-tree');
		
		if(!this.nodeDiv.hasClass('selected'))
			tree.trigger('change', [this.objectId]);
		
		
		$('.filemgr-tree .item-fix.selected').removeClass('selected');
		this.nodeDiv.addClass('selected');
	},
	
	action : function(){
		if(event.stopPropagation){
			event.stopPropagation();
		}else if(window.event){
			window.event.cancelBubble = true;
		}
		
		this.select();
		
		if(this.object.type == 'folder'){
			if(this.isExpand())
				this.collapse();
			else if(this.isCollapse())
				this.expand();
		}else{
			mw3.call(this.objectId, 'action');
		}
	},
	expand : function(){
		if(this.nodeDiv.hasClass('pluslast')){
			this.nodeDiv.removeClass('pluslast');
			this.nodeDiv.addClass('minlast');
		}else if(this.nodeDiv.hasClass('plus')){
			this.nodeDiv.removeClass('plus');
			this.nodeDiv.addClass('min');
		}
		
		if(this.object.loaded){
			this.objectDiv.children('u').show();
		}else{
			this.nodeDiv.addClass('loading');
			
			mw3.call(this.objectId, 'expand');
		}
		
		var tree = this.objectDiv.closest('.filemgr-tree');
		
		tree.trigger('expanded');
		
		// tree 잡아서
		
	},
	
	collapse : function(){
		if(this.nodeDiv.hasClass('minlast')){
			this.nodeDiv.removeClass('minlast');
			this.nodeDiv.addClass('pluslast');
		}else if(this.nodeDiv.hasClass('min')){
			this.nodeDiv.removeClass('min');
			this.nodeDiv.addClass('plus');
		}
		
		this.objectDiv.children('u').hide();
		
		var tree = this.objectDiv.parentsUntil('.filemgr-tree').parent('.filemgr-tree');
		
		tree.trigger('collapsed', [this.objectId]);
	}		
};