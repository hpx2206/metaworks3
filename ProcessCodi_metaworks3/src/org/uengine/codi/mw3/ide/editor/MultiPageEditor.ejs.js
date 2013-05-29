var org_uengine_codi_mw3_ide_editor_MultiPageEditor = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];
	this.objectMetadata = mw3.getMetadata(this.className);

	if(this.objectMetadata.faceOptions && this.objectMetadata.faceOptions.tabClass)
		this.tabClass = this.objectMetadata.faceOptions.tabClass;
	else
		this.tabClass = 'org.uengine.codi.mw3.ide.CloudTab';
	
	this.divClass = this.tabClass.split('.').join('_');
	
	if(this.object == null)
		return true;
	
	//		'height': '100%',
	//'position': 'relative',
	this.objectDiv.css({
		'padding-bottom': '35px'
	}).addClass('mw3_layout');
	
	this.lastIndex = this.object.pagePanel.length;
};

org_uengine_codi_mw3_ide_editor_MultiPageEditor.prototype = {
		loaded : function(){
			this.bind();
			
			this.objectDiv.find('.' + this.divClass + ':first').trigger('click');
		},
		bind : function(){
			var objectId = this.objectId;
			
			this.objectDiv.find('.' + this.divClass).unbind();
			this.objectDiv.find('.' + this.divClass).bind('click selecttab', function(event){
				var id = $(this).attr('id');

				mw3.getFaceHelper(objectId).select(id.substring(id.indexOf('_')+1));
			});
		},
		
		select : function(id){
			$('.' + this.divClass).removeClass('focus');		
			
			$('#top_' + id).siblings().removeClass('selected').end().addClass('selected').addClass('focus');
			$('#bottom_' + id).siblings().hide().end().show();
			
			var object = mw3.objects[this.objectId];
			if( object.callServiceFunction && object.callSelectFunctionName != null){
				mw3.call(this.objectId ,object.callSelectFunctionName);
			}
		}
};
