var org_uengine_codi_mw3_ide_editor_java_JavaCodeEditor = function(objectId, className){
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
		mw3.importScript('scripts/ace/build/src/mode-xml.js');
		mw3.importScript('scripts/ace/build/src/mode-html.js', function(){mw3.getFaceHelper(objectId).load();});
		
	}else{
	*/
	
		var faceHelper = this;
		faceHelper.load();
	//}
};

org_uengine_codi_mw3_ide_editor_java_JavaCodeEditor.prototype = {
	getValue : function(){
		var object = mw3.objects[this.objectId];	
		
		if(this.editor)
			object.content = this.editor.getSession().getValue();
		
		return object;
	},
		
	toAppend : function(appendObject){
		if(typeof appendObject == 'array'){
			var annotations = [];
			
			for(var i=0; i<appendObject.length; i++){
				annotations.push({ 
	                row: appendObject[i].lineNumber - 1,  
	                text: appendObject[i].message, 
	                type: appendObject[i].type 
	            });
			}
			
			this.editor.getSession().setAnnotations(annotations);			
		}else{
			this.editor.insert(appendObject);
		}
	},
	
	load : function(){		
		var faceHelper = this;
		var objectId = faceHelper.objectId;
		var object = mw3.objects[objectId];

		faceHelper.editor = ace.edit(faceHelper.objectDivId);
		faceHelper.editor.setTheme("ace/theme/eclipse");

		faceHelper.editor.setTheme("ace/theme/textmate");
		faceHelper.editor.getSession().setMode("ace/mode/java");		

		if(!faceHelper.object.loaded){
			faceHelper.object.content = mw3.call(objectId, 'load');
		}
		
		faceHelper.editor.getSession().setValue(faceHelper.object.content);
		
	    faceHelper.editor.getSession().on('change', function(event){
	    	try{
	    		mw3.getFaceHelper(objectId).change(event);
	    	}catch(e){    		
	    	}
	    });
	    
		/*
		 * key event handler
		 */
		 
		faceHelper.event = ace.require("ace/lib/event");
		
		faceHelper.event.addMouseWheelListener(faceHelper.editor.container, function(e){
			mw3.getFaceHelper(objectId).closeAssist(e);
		});
		
	    faceHelper.editor.setKeyboardHandler(    		   
	    	{
	    		handleKeyboard : function(data, hashId, key, keyCode, e) {
	    			switch (keyCode) {
	    				case 13 :	// enter	
			    			mw3.getFaceHelper(objectId).selectAssist(e);
			    	    	
			    	    	break;
			    	    
	    				case 83:	// s
	    					if(e.altKey && e.shiftKey){
		    					mw3.mouseX = e.srcElement.offsetLeft-2;
		    					mw3.mouseY = e.srcElement.offsetTop + 18;
		    					
		    					mw3.call(objectId, 'quickMenu');
		    					
	    						faceHelper.event.stopEvent(e);
	    					}
	    					
	    					break;
	    					
	    				case 190:	// .
	    					var command = faceHelper.getCommandString();
	    					
	    					command += '.';
	    					
	    					mw3.mouseX = e.srcElement.offsetLeft + 4;
	    					mw3.mouseY = e.srcElement.offsetTop + 18;
	    					
	    					faceHelper.lastCommandString = command;
	    					faceHelper.assistType = 'requestAssist';	    					
	    					faceHelper.requestAssist(e, command);
	    					
	    					break;
	    					
	    				case 32 :    	
							if(e.ctrlKey){				
								var command = faceHelper.getCommandString();
								
								mw3.mouseX = e.srcElement.offsetLeft + 4;
								mw3.mouseY = e.srcElement.offsetTop + 18;
								
								faceHelper.lastCommandString = command;
		    					faceHelper.assistType = 'requestAssist';
		    					faceHelper.requestAssist(e, command);
							}
							
							break;		    	    	
	    					
	    				case 79 : // ctrl + shift + o
	    					/*if(e.ctrlKey && e.shiftKey){
	    						var command = faceHelper.getCommandString();
	    						
	    						mw3.mouseX = e.srcElement.offsetLeft + 4;
	    						mw3.mouseY = e.srcElement.offsetTop + 18;
	
	    						faceHelper.assistType = 'showOrganizeImports';
	    						faceHelper.lastCommandString = command;
	    						faceHelper.showOrganizeImports(command);
	    						
	    						faceHelper.stopEvent(e);
	    					}*/
			    	    default :
			    	    	break;
	    					
			    	    
	    			}
	    		}
	   	    }
	    );
	    
		faceHelper.editor.commands.addCommand({
		    name: "gotoleft",
		    bindKey: this.bindKey("Left", "Left|Ctrl-B"),
		    exec: function(env, args, request) {
		    	faceHelper.closeAssist();
		    	
		    	env.navigateLeft(args.times);
		    }
		});
		faceHelper.editor.commands.addCommand({
		    name: "gotoleft",
		    bindKey: this.bindKey("Right", "Right|Ctrl-F"),
		    exec: function(env, args, request) {
		    	faceHelper.closeAssist();
		    	
		    	env.navigateRight(args.times);
		    }
		});
		faceHelper.editor.commands.addCommand({
		    name: "golineup",
		    bindKey: this.bindKey("Up", "Up|Ctrl-P"),
		    exec: function(env, args, request) {
		    	var assist = faceHelper.getCodeAssist();
				
		    	if(assist != null)
					mw3.getFaceHelper(assist.__objectId).up();
				else  	
					env.navigateUp(args.times);
		    }
		});
		faceHelper.editor.commands.addCommand({
		    name: "golinedown",
		    bindKey: this.bindKey("Down", "Down|Ctrl-N"),
		    exec: function(env, args, request) {
		    	var assist = faceHelper.getCodeAssist();
				
				if(assist != null)
					mw3.getFaceHelper(assist.__objectId).down();
				else  	
					env.navigateDown(args.times);
		    }
		});
		faceHelper.editor.commands.addCommand({
		    name: "esckey",
		    bindKey: this.bindKey("Esc", "Esc"),
		    exec: function(env, args, request) {
		    	faceHelper.closeAssist();
		    }
		});
		
	/*	canon.addCommand({
		    name: "run",
		    bindKey: this.bindKey("F5", "F5"),
		    exec: function(env, args, request) {
		    }
		});
	*/
		/*this.editor.addEventListener(
			"keydown",
	   		function(e) {
				mw3.log('keydown : ' + objectId);
				
				//mw3.getFaceHelper(objectId).keydown(e);
		    },
			
			false
	    );*/
	},
	bindKey : function(win, mac) {
	    return {
	        win: win,
	        mac: mac,
	        sender: "editor"
	    };
	},
	getSourcePosition: function(){
		var whereEnd = this.editor.getCursorPosition();
		
		var search = this.editor.$search;
		
		search.set({needle:'public class'});
		var range = search.find(this.editor.getSession());
		
		if(range == null || range.start.row > whereEnd.row)
			return 'import';
		else
			return range.start.row;
	},
	showOrganizeImports: function(command){
		this.requestAssist('-oi ' + command);
	},
	organizeImports: function(assistId){
		var selectedValue = mw3.getFaceHelper(assistId).select();
			  
		mw3.log(selectedValue);
		
		var position = this.getSourcePosition();
					    		
		var temp = selectedValue.split('-');		    			

		
		/*		
		this.importPackage(temp[1].trim(), temp[0].trim(), Number(position));
		mw3.removeObject(assistId);
*/	},
	getCommandString : function(){
		var whereEnd = this.editor.getCursorPosition();
		var whereStart = {column: 0, row: whereEnd.row};
						
		var pos;
		var command = this.editor.getSession().doc.getTextRange({start: whereStart, end: whereEnd});
		command = command.trim();
		
		// check last command line
		pos = command.indexOf(';');
		if(pos > -1)
			command = command.substring(pos + 1);
		
		//A-Z0-9
		var pattern = /^[A-Za-z0-9]*$/;
		var expression = '';
		for (var i = command.length - 1; i >= 0; i--){
			var charAt = command.charAt(i);			
			if(!(pattern.test(charAt) || charAt == '.')){
				break;
			}
			
			expression = charAt + expression;
		}	
		
		if(command.indexOf('import ') == 0)			
			expression = 'import ' + expression;
		if(command.indexOf('@') == 0)			
			expression = '@' + expression;

		return expression;
		
	},
	
	getPackageName: function(){
		var search = this.editor.$search;
		
		search.set({needle:'package'});
		var range = search.find(this.editor.getSession());
		var result = '';
		
		if(range != null){
			var line = this.editor.getSession().getLine(range.start.row);
			line = line.trim();
										
			if(line.indexOf(';') != -1){
				var temp = line.split(';');
				
				for(var i=0;i<temp.length;i++){
					if(temp[i].indexOf('package') != -1){
						result = temp[i];
						
						break;
					}
				}
			}else{
				result = line;
			}
		}else{
			return '';
		}
				
		result = result.trim();
		if(result.indexOf('package') != 0){
			return '';
		}
		
		return result.substring('package'.length).trim();
	},

	getCodeAssist : function(){
		return mw3.getAutowiredObject('org.metaworks.example.ide.CodeAssist');
	},
	
	importPackage : function(importName, startPosition){
		var packageName = '';
		var className = '';
		
		var pos = importName.lastIndexOf('.');
		if(pos == -1){
			className = importName;
		}else{
			packageName = importName.substring(0, pos); 
			className = importName.substring(pos + 1);
		}
		
		var importedList = [];
		var lastLine = 0;
		
		for(var i=0; i<startPosition; i++) {
			var fullLine = this.editor.getSession().doc.getLine(i);
			fullLine = fullLine.trim();
			
			if(fullLine.indexOf(';') == -1)
				fullLine += ';';
			
			var lines = fullLine.split(';');
			for(var j=0; j<lines.length; j++){
				var line = lines[j].trim();
				
				if(line.indexOf('import') != -1){
					if(fullLine.indexOf('import') == 0)
						importedList.push(line.substring('import'.length).trim());    						
					
					lastLine = i;
				}else if(fullLine.indexOf('package') == 0){
					lastLine = i;
				}    			
			}
		}
		
		var exist = false;		
		if(this.getPackageName() == packageName)
			exist = true;
		
		if(!exist){
			for(var i=0; i<importedList.length; i++){		
				if(importedList[i] == importName || importedList[i] == packageName + '.*'){
					exist = true;
					
					break;
				}
			}
		}
		
		if(!exist){
			var pos = {row:lastLine+1,column:0};
			
			this.editor.getSession().doc.insertNewLine(pos);			
			this.editor.getSession().doc.insertInLine(pos, 'import ' + packageName + '.' + className + ';'); 
		}
	},
	
	loadedCodeAssist : function(){
		this.loadRequestAssist = false;
		
		var assist = this.getCodeAssist();
		
		if(assist != null){
			var assistFaceHelper = assist.getFaceHelper();
			
			if(assist.assistMap.length > 1){
				assistFaceHelper.objectDiv.show();
				
				this.changeAssist();
			}else if(assist.assistMap.length == 1){						
				this.selectAssist();
			}else{
				this.closeAssist();
			}
		}
		
	},
	requestAssist : function(e, command){
		if(!this.loadRequestAssist){
			var assist = mw3.getAutowiredObject('org.metaworks.example.ide.CodeAssist');
			
			if(assist == null){
				this.lastCommandString = command;
				
				if(command.length > 0){
					var object = mw3.getObject(this.objectId);
								
					object.lineAssistRequested = command;
					object.clientObjectId = this.objectId;
					object.requestAssist();
				}				
			}			
		}
	},	
	
	change : function(event){
		if(!this.loadRequestAssist){
			var objectId = this.objectId;
			
			if (this.timeout) {
				clearTimeout(this.timeout);
			}
			
			this.timeout = setTimeout(function() {
				mw3.getFaceHelper(objectId).changeAssist();
			}, 100);			
			
		}	
	},
	
	changeAssist : function(){
		var assist = this.getCodeAssist();
		
		if(assist){
			var command = this.getCommandString();
    		if(command.startsWith("import "))
    			command = command.substring("import ".length);

    		if(command.startsWith("@"))
    			command = command.substring("@".length);

    		command = command.trim();
    		if(command != this.lastCommandString){
				this.lastCommandString = command;
				
				mw3.getFaceHelper(assist.__objectId).change(command);
			}
		}
	},
	
	closeAssist : function(){
		var assist = this.getCodeAssist();
		if(assist != null)
			mw3.removeObject(assist.__objectId);
	},
	
	selectAssist : function(e){
		var assist = this.getCodeAssist();
    	
    	if(assist != null){
    		if(typeof e != 'undefined')
    			this.event.stopEvent(e);    		
    		
    		if(this.assistType == 'requestAssist'){
	    		var selectedValue = mw3.getFaceHelper(assist.__objectId).getSelectedValue();
	    		var selectedValues = selectedValue.split('/');

	    		// remove assist
	    		mw3.removeObject(assist.__objectId);
	    		
	    		// check select value
	    		if(selectedValue.indexOf('/') == -1 && selectedValues.length < 2){
	    			return false;
	    		}	    		
	    		
	    		var commandString = this.getCommandString();
	    		var insertString = '';
	    		var insertPackage = '';
	    		var isImport = false;
	    		
	    		if(commandString.indexOf('@') == 0)
	    			commandString = commandString.substring('@'.length);
	    		
	    		if(commandString.indexOf("import ") == 0){
	    			isImport = true;
	    			
	    			commandString = commandString.substring("import ".length);
	    		}
	    		
	    		var whereEnd = this.editor.getCursorPosition();
	    		var whereStart = {column: whereEnd.column - commandString.length, row: whereEnd.row};
	    		
	    		if(selectedValues[2] == 'package'){
	    			insertString = selectedValues[0];
	    			
	    			if(isImport)
	    				insertString += ';';
	    		}else if(selectedValues[2] == 'class' || selectedValues[2] == 'annotation'){
	    			if(commandString.charAt(commandString.length-1) == '.'){
		    			insertString = selectedValues[1] + '.' + selectedValues[0];		    					    			
		    			insertPackage = selectedValues[1] + '.' + selectedValues[0]; 	    				
	    			}else{
		    			insertString = selectedValues[0];
		    			insertPackage = selectedValues[1] + '.' + selectedValues[0]; 
	    			}	    			
	    			
	    			if(isImport)
	    				insertString += ';';
	    		}else{
	    			var pos = this.getCommandString().lastIndexOf('.');
	    			whereStart = {column: whereEnd.column - (commandString.length - (pos + 1)), row: whereEnd.row};
	    			
	    			insertString = selectedValues[0];
	    			insertPackage = selectedValues[1];
	    			
	    			if('this' == insertPackage)
	    				isImport = true;
	    		}
	    		
	    		if(whereStart.column != whereEnd.column){
	    			var selectionRange = {start:whereStart, end:whereEnd};
	    			
	    			this.editor.getSelection().setSelectionRange(selectionRange, false);
	    			this.editor.remove('left');
	    		}
	    		this.editor.insert(insertString);
	    		
	    		if(!isImport){
		    		// import package
		    		if(insertPackage.length > 0){
		    			var position = this.getSourcePosition();
		    			
		    			if(position != 'import'){
		    				this.importPackage(insertPackage, Number(position));
		    			}
		    		}	    			
	    		}
    		}else{
    			this.organizeImports(assist.__objectId);
    		}
    	}		
	},
	
	getContentLibrary : function(){
		var cloudIDE = mw3.getAutowiredObject('org.uengine.codi.mw3.ide.CloudIDE');
		
		return cloudIDE.contentLibrary;
		
	},
	addPackage : function(packageList, command, endChar){
		var library = this.getContentLibrary();
		
		if(library){
			for(var packageName in library.packageMap){
				if(packageName.startsWith(command)){
					packageList.push(packageName + endChar + "/" + packageName + "/package/");
				}
			}
		}
	},
	
	addPackageClass : function(classList, expression){
		var contentLibrary = this.getContentLibrary();
		
		if(contentLibrary){
			var classFromPackage = contentLibrary.packageMap[expression];
			
			if(classFromPackage){
				for(var i=0; i<classFromPackage.length; i++){
					classList.push(classFromPackage[i] + "/" + expression + "/class/") ;
				}
			}
		}
	},
	
	addPackageStartWith : function(packageList, expression){
		var contentLibrary = this.getContentLibrary();
		
		if(contentLibrary){
			for(var packageName in contentLibrary.packageMap){
				if(packageName.toLowerCase().startsWith(expression))
					packageList.push(packageName + "//package/");
			}			
		}
	},
	
	addClassesStartWith : function(classList, expression){
		var contentLibrary = this.getContentLibrary();
		
		if(contentLibrary){
			for(var className in contentLibrary.classMap){
				if(className.toLowerCase().startsWith(expression)){
					var packageFromClass = contentLibrary.classMap[className];
					
					for(var i=0; i<packageFromClass.length; i++){
						classList.push(className + '/' + packageFromClass[i] + '/class/');	
					}
				}
			}
		}
	},
	
	addAnnotation : function(classList, expression){
		var contentLibrary = this.getContentLibrary();
		
		if(contentLibrary){
			for(var className in contentLibrary.annotationMap){
				if(expression.trim() == '' || className.toLowerCase().startsWith(expression)){
					var annotationPackageFromClass = contentLibrary.classMap[className];
					
					for(var i=0; i<annotationPackageFromClass.length; i++){
						classList.push(className + '/' + annotationPackageFromClass[i] + '/class/');	
					}
				}
			}
		}		
	},
	resize : function(e){
		this.editor.resize();	
	}	
};
