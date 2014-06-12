var org_uengine_codi_mw3_ide_editor_Editor = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);

	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;

	this.objectDiv.css("width", "100%").css("height", '100%').css("overflow", "hidden").addClass('mw3_resize');

	this.loadRequestAssist = false;
	this.lastCommandString = "";
	this.assistType = "";

/*
	if(mw3.importScript('scripts/ace/build/src/ace.js')){
		mw3.importScript('scripts/ace/build/src/theme-eclipse.js');
		mw3.importScript('scripts/ace/build/src/mode-javascript.js');
		mw3.importScript('scripts/ace/build/src/mode-java.js');
		mw3.importScript('scripts/ace/build/src/mode-html.js', function(){mw3.getFaceHelper(objectId).load();});

	}else{
	*/
		var faceHelper = this;

		faceHelper.load();
	//}
};

org_uengine_codi_mw3_ide_editor_Editor.prototype = {
		getValue : function(){
			var beanPaths = mw3.beanExpressions[this.objectId];
			if(beanPaths)
				for(var propName in beanPaths){
					var beanPath = beanPaths[propName];
					eval("mw3.objects[this.objectId]" + beanPath.fieldName + "=mw3.getObject('" + beanPath.valueObjectId + "')");
				}
			var object = mw3.objects[this.objectId];	

			if(this.editor)
				object.content = this.editor.getSession().getValue();

			return object;
		},

		load : function(){		
			var faceHelper = this;
			var objectId = faceHelper.objectId;
			var object = mw3.objects[objectId];

			faceHelper.editor = ace.edit(faceHelper.objectDivId);

			var type = 'javascript';
			if(this.object.type == 'html')
				type = 'html';
			else if(this.object.type == 'process')
				type = 'xml';

			faceHelper.editor.getSession().setMode("ace/mode/" + type);

			if(!faceHelper.object.loaded){
				faceHelper.object.content = mw3.call(objectId, 'load');
			}

			faceHelper.editor.getSession().setValue(faceHelper.object.content);
		},

		resize : function(e){
			this.editor.resize();	
		}	
};
