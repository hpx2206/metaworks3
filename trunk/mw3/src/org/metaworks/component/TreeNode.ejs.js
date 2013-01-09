var org_metaworks_component_TreeNode = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;	
	
	this.nodeDiv = $('#' + this.objectDivId).children('div');
	this.nodeDiv.attr('objectId', this.objectId);
	this.treeDiv = this.nodeDiv.parentsUntil('.filemgr-tree').parent('.filemgr-tree');
	
	var faceHelper = this;
	
	if(this.object.folder){
		// calc status
		var status = '';
		if(this.object.expanded)
			status = 'min';
		else
			status = 'plus';
		
		if(this.object.loaded)
			status += 'last';
		
		this.nodeDiv.addClass(status);
		
		
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
	loaded : function(){
		if(this.object != null){
			this.nodeDiv.triggerHandler('loaded', [this.object.id, this.objectId]);
			this.treeDiv.triggerHandler('loadedNode', [this.object.id, this.objectId]);
		}
	},
	destroy : function() {
		if(this.object != null)
			this.nodeDiv.unbind();
	},
	startLoading : function(){
		
	},
	
	endLoading : function(){
		this.nodeDiv.removeClass('loading');		
	},
	
	showStatus : function(message){
		if('expand DONE.' == message){
			if(this.nodeDiv.find('.item-fix').length > 0){
				this.nodeDiv.addClass('minlast');
				this.nodeDiv.removeClass('min');
			}else{
				this.nodeDiv.removeClass('min');
			}
			
			this.object.loaded = true;
		}
	},
	
	toAppend : function(appendobject){
		var html = mw3.locateObject(appendobject, null);
		
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

	check : function(){
		this.nodeDiv.find('input[type=checkbox]:visible').each(function(){
			if($(this).is(':checked'))
				$(this).prop('checked', false);
			else
				$(this).prop('checked', true);
		});
	},
	select : function(){
		if(event.stopPropagation){
			event.stopPropagation();
		}else if(window.event){
			window.event.cancelBubble = true;
		}
		
		if(!this.nodeDiv.hasClass('selected')){
			this.treeDiv.find('.item-fix.selected').removeClass('selected');
			this.nodeDiv.addClass('selected');

			this.treeDiv.trigger('change', [this.objectId]);			
		}else{
			this.treeDiv.find('.item-fix.selected').removeClass('selected');
			this.nodeDiv.addClass('selected');			
		}
		
		this.check();
	},
	
	action : function(){
		if(event.stopPropagation){
			event.stopPropagation();
		}else if(window.event){
			window.event.cancelBubble = true;
		}
		
		this.select();
		
		if(this.object.folder){
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
			this.object.expanded = true;
			this.objectDiv.children('u').show();
		}else{
			this.nodeDiv.addClass('loading');
			
			mw3.call(this.objectId, 'expand');
		}
		
		this.treeDiv.trigger('expanded');
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
		this.object.expanded = false;
		
		this.treeDiv.trigger('collapsed', [this.objectId]);
	}
};