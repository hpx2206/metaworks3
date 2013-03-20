var org_metaworks_component_Tree = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;

	this.objectDiv.addClass('filemgr-tree').addClass('filemgr-treeFocus').addClass(this.object.align);	
	this.objectDiv.css({'border-width': '0px',
						'left': '0px', 
						'top': '0px',
						'right': '0px',
						'bottom': '0px',
						'min-width': '0px',
						'min-height': '0px',
						'max-width': '10000px',
						'max-height': '10000px'});

	this.objectDiv.attr('objectId', this.objectId);

	this.objectDiv.bind('loadedNode', {objectId: this.objectId}, function(event, nodeId, objectId){
		var faceHelper = mw3.getFaceHelper(event.data.objectId);
		if(faceHelper && faceHelper.loadedNode)
			faceHelper.loadedNode(nodeId, objectId);
	});
	
	if(this.object && this.object.showCheckBox){
		if(this.object.checkNodes){
			for(var i=0; i<this.object.checkNodes.length; i++){
				var node = this.object.checkNodes[i];

				$(this.objectDiv.find('#' + node.id)).bind('loaded', function(event, nodeId, objectId){
					var faceHelper = mw3.getFaceHelper(objectId);

					faceHelper.check();
				});
			}
		}
	}
};

org_metaworks_component_Tree.prototype = {
	getValue : function(){
		// get check nodes
		var checkNodes = [];

		if(this.object && this.object.showCheckBox){
			this.objectDiv.find('input[type=checkbox]:checked').each(function(){
				var node = mw3.objects[$(this).attr('objectId')];
				
				checkNodes.push(node);
			});
		}
		this.object.checkNodes = checkNodes;
		
		
		// get select node
		var selectNode = null;		
		this.objectDiv.find('.item-fix.selected').each(function(){
			selectNode = mw3.objects[$(this).attr('objectId')];
		});
		this.object.selectNode = selectNode;
		
		return this.object;
	},
	
	loaded : function(){
		this.objectDiv.trigger('loaded');
	},
	
	destroy : function(){
		this.objectDiv.unbind();
	},
	
	loadedNode : function(nodeId, objectId){
		if(this.object.showCheckBox){
			var node = mw3.objects[objectId];
			
			if(!this.object.hiddenCheckBoxFolder || (node && !node.folder))
				this.objectDiv.find('#' + nodeId + ' .tree-checkbox').css('display', 'inline-block');
		}
	},
	getClosedParentNodes : function(objectId){
		var object = mw3.objects[objectId];
		if(object.root)
			return null;
		
		var parentObjectId = this.objectDiv.find('.item-fix[id='+ object.parentId+']').attr('objectId');
		var parentObject = mw3.objects[parentObjectId];
		if( parentObject.expanded ){
			return this.getClosedParentNodes(parentObject.__objectId);
		}else{
			return parentObject.__objectId;
		}
		
		/*
		var parentNode = $('.item-fix[nodeId='+ object.parentId+']');
		var parentObjectId = $(parentNode).attr('objectId');
		var parentObject = mw3.objects[parentObjectId];
		this.parentNodes.push(parentObject);
		if( parentObject.parentId ){
			this.getParentNodes(parentObject.parentId);
		}
		*/
	},
	
	getSelectedNode : function(){
		var node = this.objectDiv.find('.selected');		
		
		return node;
	},
	
	toAppend : function(appendobject){
		if(appendobject != null && appendobject.length > 0){
			var html = mw3.locateObject(appendobject, null);
			var appendDiv = $('<u></u>').addClass('last').css({'display': 'block', 'height': 'auto', 'overflow': 'visible'});
			if(this.object.root)
				appendDiv.addClass('root');
			
			// 넘어온 arrayList 의 첫번째 값의 parentId를 구한다 (모두 같기 때문에)
			var parenNodtId = appendobject[0].parentId;
			var targetDiv = this.objectDiv.find("#"+parenNodtId).parent();
			targetDiv.append(appendDiv);
			targetDiv.children('u').append(html);
			
			this.objectDiv.trigger('toAppended');
		}
	}
};
