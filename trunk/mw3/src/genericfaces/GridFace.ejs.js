var GridFace = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];
	
	if(this.object == null)
		return true;
};

GridFace.prototype = {
	getValue : function(){
	},
	
	add : function(object){
		var options = mw3.getMetadata(object.__className);
		
		options['htmlTag'] =  'tr';
		options['ejsPath'] =  'dwr/metaworks/genericfaces/GridFace_Row.ejs';
		options['parentId'] =  this.objectId;
		
		var html = mw3.locateObject(object, object.__className, null, options);
		
		this.objectDiv.children('table').append(html);
		
		mw3.onLoadFaceHelperScript();
	},
	
	remove : function(){
		var checked = this.objectDiv.find('[name=check_' + this.objectId + ']:checked');
		
		for(var i=0; i<checked.length; i++){
			var checkedObjectId = $(checked[i]).attr('objectId');
			
			for(var j=0; j<this.object.length; j++){
				if(checkedObjectId == this.object[j].__objectId){
					if(j == 0)
						this.object.shift();
					else
						this.object.splice(j,1);	
					
					break;
				}
			}
			
			mw3.removeObject(checkedObjectId);
		}
		
	},
	
	addItem : function(){
		
		var object = {
			__className : this.className,
		}		
		
		var confirmDivId = 'confirm_' + this.objectId;
		
		$('body').append("<div id='" + confirmDivId + "'></div>");
		
		var options = {when : 'edit'};
		
		mw3.locateObject(object, null, '#' + confirmDivId, options);
		
		var metadata = mw3.getMetadata(this.className);
		var confirm = mw3.localize('$Confirm');
		var cancel = mw3.localize('$Cancel');
		
		$('#' + confirmDivId).dialog({
			title : metadata.displayName + ' ' + mw3.localize('$Add'),
			
			objectId : object.__objectId,
			parentId : this.objectId,
            resizable: false,
            modal: true,
            buttons: {
            	confirm: function(event) {
                	var objectId = $(this).dialog('option', 'objectId');
                	var parentId = $(this).dialog('option', 'parentId');
                	
                	var object = mw3.getObject(objectId);
                	
                    $( this ).dialog( "close" );                    
                    
                    $(mw3.getFaceHelper(parentId).add(object));
                },
                cancel: function() {
                	$( this ).dialog( "close" );
                }
            },
            
            close: function( event, ui ) {
            	var objectId = $(this).dialog('option', 'objectId');
            	
            	mw3.removeObject(objectId);
            	
            	$( this ).dialog( "destroy" );
            	$( this ).remove();
            }
        });
	},
	
	removeItem : function(){
		var checked = this.objectDiv.find('[name=check_' + this.objectId + ']:checked');
		
		if(checked.length == 0){
			alert('Please select an item to delete.');
			return false;
		}
		
		var confirmDivId = 'confirm_' + this.objectId;
		
		var html = "<div id='" + confirmDivId + "'>총 $1개를 삭제하시겠습니까?</div>";
		html = html.replace('$1', checked.length);
		
		$('body').append(html);
		
		var metadata = mw3.getMetadata(this.className);
		var confirm = mw3.localize('$Confirm');
		var cancel = mw3.localize('$Cancel');
		
		$('#' + confirmDivId).dialog({
			title : metadata.displayName + ' ' + mw3.localize('$Remove'),			
			objectId : this.objectId,
            resizable: false,
            modal: true,
            buttons: {
            	confirm: function(event) {
            		var objectId = $(this).dialog('option', 'objectId');
            		
                    $( this ).dialog( "close" );                    
                    $( this ).remove();
                    
                    $(mw3.getFaceHelper(objectId).remove());
                },
                cancel: function() {
                    $( this ).dialog( "close" );
                    $( this ).remove();
                }
            }
        });
	},
	
	toogleCheck : function(){
		var checkYn = this.objectDiv.find('[name=checkall_' + this.objectId + ']').attr('checked');
		
		if(checkYn)
			checkYn = true;
		else
			checkYn = false;
		
		this.objectDiv.find('[name=check_' + this.objectId + ']').attr('checked', checkYn);
	}	
};