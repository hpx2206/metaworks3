var org_uengine_codi_mw3_ide_editor_metadata_ProcessXmlEditor = function(objectId, className){
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

	if(mw3.importScript('scripts/ace/build/src/ace.js')){
		mw3.importScript('scripts/ace/build/src/theme-eclipse.js');
		mw3.importScript('scripts/ace/build/src/mode-javascript.js');
		mw3.importScript('scripts/ace/build/src/mode-java.js');
		mw3.importScript('scripts/ace/build/src/mode-xml.js');
		mw3.importScript('scripts/ace/build/src/mode-html.js', function(){mw3.getFaceHelper(objectId).load();});

	}else{
		var faceHelper = this;

		faceHelper.load();
	}
};

org_uengine_codi_mw3_ide_editor_metadata_ProcessXmlEditor.prototype = {
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
		addProperty : function(xml){
			
			var search = this.editor.$search;
			
			search.set({needle:'</MetadataProperty>' , backwards:true});
			var range = search.find(this.editor.getSession());
			
			if(range != null){
				var pos = {row:range.end.row ,column:range.end.column};
				this.editor.getSession().doc.insertNewLine(pos);
				var posNext = {row:range.end.row+1 ,column:range.end.column};
				this.editor.getSession().doc.insert(posNext,xml); 
			}
			
		},
		removeProperty : function(xml, name){
			
			var object = mw3.objects[this.objectId];
			
			var search = this.editor.$search;
			search.set({needle:xml.toString(), wholeWord:true});
			
			var content = object.content.toString();
			var range = search.find(this.editor.getSession());
			var replace = content.replaceAll(/xml.toString()/g, " ");
			
		},
		addDefinition : function(){		
		},
		addRule : function(){		
		},
		
		load : function(){		
			var faceHelper = this;
			var objectId = faceHelper.objectId;
			var object = mw3.objects[objectId];

			faceHelper.editor = ace.edit(faceHelper.objectDivId);
			faceHelper.editor.setTheme("ace/theme/eclipse");
			var XMLMode = require("ace/mode/xml").Mode;
			faceHelper.editor.getSession().setMode(new XMLMode());

			if(!faceHelper.object.loaded){
				faceHelper.object.content = mw3.call(objectId, 'load');
			}

			faceHelper.editor.getSession().setValue(faceHelper.object.content);
		},

		resize : function(e){
			this.editor.resize();	
		}	
};
