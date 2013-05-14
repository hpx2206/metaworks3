var org_uengine_codi_mw3_ide_CloudWindow = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	
	//		'height': '100%',
	//'position': 'relative',
	this.objectDiv.css({
		'padding-bottom': '35px'
	}).addClass('mw3_layout');
	
	this.lastIndex = this.object.tabs.length;
};

org_uengine_codi_mw3_ide_CloudWindow.prototype = {
	loaded : function(){
		this.bind();
		
		this.objectDiv.find('.cloudTab:first').trigger('click');
	},
	bind : function(){
		var objectId = this.objectId;
		
		this.objectDiv.find('.cloudTab:first').unbind();
		this.objectDiv.find('.cloudTab').bind('removetab', function(){
			var id = $(this).attr('id');
			
			mw3.getFaceHelper(objectId).remove(id.substring(id.indexOf('_')+1));
		});
		this.objectDiv.find('.cloudTab').bind('click selecttab', function(){	
			var id = $(this).attr('id');

			mw3.getFaceHelper(objectId).select(id.substring(id.indexOf('_')+1));
		});
	},
	toAppend : function(object){
		
		var tabId = null;
		var tabName = '';

		var autowiredTab = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.CloudTab@'+object.id, true);
		
		if(autowiredTab){
			autowiredTab.getFaceHelper().select();
		}else{
			var tabMetadata = mw3.getMetadata(object.__className);
			
			if(object.name){
				tabName = object.name;
			}else{
				tabName = tabMetadata.displayName;
			}
			
			var tabOptions = {
				ejsPath : 'dwr/metaworks/org/uengine/codi/mw3/ide/CloudTab.ejs'
			};
			
			var tab = {
				__className : 'org.uengine.codi.mw3.ide.CloudTab',
				name : tabName,
				id : object.id,
				type : object.type,
				parentId : this.objectId
			};
			
			
			this.lastIndex = this.lastIndex + 1;
			var id = this.objectId + '_' + this.lastIndex;
	
			$('<div>').addClass('cloudTab').attr('id', 'top_' + id).appendTo(this.objectDiv.find('.boxtoprightTab')).html(mw3.locateObject(tab, tab.__className, null, tabOptions));
			$('<div>').attr('id', 'bottom_' + id).appendTo(this.objectDiv.find('.contentcontainer')).html(mw3.locateObject(object, object.__className, null));
			
			this.bind();
			this.select(id);
		}
	},
	
	select : function(id){
		$('#top_' + id).siblings().removeClass('selected').end().addClass('selected');
		$('#bottom_' + id).siblings().hide().end().show();
		
		var content = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.CloudContent@'+id);
		if(content){
			var contentMetadata = mw3.getMetadata(content.__className);
			
			for(var i=0; i<contentMetadata.superClasses.length; i++){
				if(contentMetadata.superClasses[i] == 'org.uengine.codi.mw3.ide.editor.Editor'){
					var cloudIDE = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.CloudIDE');
					
					cloudIDE.currentEditorId = content.id;
					
					break;
				}
			}
		}
	},
	
	remove : function(id){
		var tabDivObj = $('#top_' + id).children(':first');
		
		if(tabDivObj){
			var tabObjectId = tabDivObj.attr('objectId');
			var tabObject = mw3.getObject(tabObjectId);
			
			var tabMetadata = mw3.getMetadata(tabObject.__className);
			
			if(tabMetadata.keyFieldDescriptor){
				tabId = tabObject[tabMetadata.keyFieldDescriptor.name];
			}
			
			var tab = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.CloudTab@'+tabId, true);
			
			if(tab)
				mw3.removeObject(tab.__objectId);
			
			var content = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.CloudContent@'+tabId, true);
			if(content)
				mw3.removeObject(content.__objectId);
			
		}
	}
};