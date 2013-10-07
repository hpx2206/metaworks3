var org_uengine_codi_mw3_ide_OpenResource = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;	

	this.objectDiv.css({
		height: '100%',
		position: 'relative'
	});
	
	var keyword = mw3.getInputElement(this.objectId, 'keyword');
	$(keyword)
		.css({width: '100%'})
		.bind('keyup', {objectId: this.objectId}, function(e){
			var faceHelper = mw3.getFaceHelper(e.data.objectId);
			
			if(faceHelper)
				faceHelper.change(this.value, e);
		})
		.focus();
};

org_uengine_codi_mw3_ide_OpenResource.prototype = {
	getValue : function(){
		this.object.selectedResources = [];
		
		var selectedItems = $('#items_' + this.objectId + ' option[selected=\"selected\"]');
		for(var i=0; i<selectedItems.length; i++){
			
			var resourceName = $(selectedItems[i]).attr('package') + '/' + selectedItems[i].value;
			
			this.object.selectedResources.push(resourceName);
		}
		
		return this.object; 
	},
	getResourceLibrary : function(){
		var cloudIDE = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.CloudIDE');
		
		return cloudIDE.resourceLibrary;
	},

	change : function(keyword, e){
		
		switch (e.keyCode){
		case 38 : // up
			if(event.stopPropagation){
				event.stopPropagation();
			}else if(window.event){
				window.event.cancelBubble = true;
			}

			var selectedItems = $('#items_' + this.objectId + ' option[selected=\"selected\"]');
			var firstSelectedItem = selectedItems.filter(':first');
			
			if(firstSelectedItem.prev().length){
				selectedItems.removeAttr('selected');
				firstSelectedItem.prev().attr('selected', 'selected');
			}
			
			break;
		case 40 : // down
			if(event.stopPropagation){
				event.stopPropagation();
			}else if(window.event){
				window.event.cancelBubble = true;
			}

			var selectedItems = $('#items_' + this.objectId + ' option[selected=\"selected\"]');
			var lastSelectedItem = selectedItems.filter(':last');
			
			if(lastSelectedItem.next().length){
				selectedItems.removeAttr('selected');
				selectedItems.next().attr('selected', 'selected');				
			}
			
			break;
		}
		
		keyword = keyword.toLowerCase();
		
		if(keyword != this.prevKeyword){
			var items = $('#items_' + this.objectId);
			items.empty();
			
			if(keyword != ''){
				var resourceLibrary = this.getResourceLibrary();
				var first = true;
				for(var filename in resourceLibrary.resourceMap){
					if(filename.toLowerCase().startsWith(keyword)){
						var pathFromResource = resourceLibrary.resourceMap[filename];
						
						for(var i=0; i<pathFromResource.length; i++){
							var resourceName = filename;
							
							if(pathFromResource.length > 1)
								resourceName += ' - ' + pathFromResource[i];
							
							$('<option>').val(filename).html(resourceName).attr('package', pathFromResource[i]).appendTo(items);
						}
					}
				}
				
				items.children('option:first').attr('selected','selected');
			}
		}
		this.prevKeyword = keyword;
	}
};