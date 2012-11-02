var org_metaworks_example_ide_SourceCode = function(objectId, className){
	

	this.objectId = objectId;
	this.className = className;

	this.divName = "objDiv_" + objectId;	
	
	this.loadRequestAssist = false;
	this.lastCommandString = "";
	
	var object = mw3.objects[this.objectId];
	
	if(object){
		$("#" + this.divName).css("width", "100%").css("height", '100%').css("overflow", "hidden");
		$("#" + this.divName).addClass('mw3_editor').addClass('mw3_resize').attr('objectId', objectId);
		
		this.editor = ace.edit(this.divName);
	    this.editor.setTheme("ace/theme/eclipse");
	
	    var JavaScriptMode = require("ace/mode/java").Mode;
	    this.editor.getSession().setMode(new JavaScriptMode());
	    
	    this.editor.getSession().on('change', function(event){
	    	try{
	    		mw3.getFaceHelper(objectId).change(event);
	    	}catch(e){    		
	    	}
	    });
	      
	    this.event = require("pilot/event");
	    
	    this.editor.setKeyboardHandler(    		   
	    	{
	    		handleKeyboard : function(data, hashId, key, keyCode, e) {
	    			switch (e.keyCode) {
	    				case 13 :    				
			    			mw3.getFaceHelper(objectId).selectAssist(e);
			    	    	
			    	    	break;
	    				case 32 :    	
							if(e.ctrlKey){				
								var faceHelper = mw3.getFaceHelper(objectId);	
								var command = faceHelper.getCommandString();
								
								mw3.mouseX = e.srcElement.offsetLeft + 4;
								mw3.mouseY = e.srcElement.offsetTop + 18;
								
								faceHelper.lastCommandString = command;
		    					faceHelper.assistType = 'requestAssist';
		    					faceHelper.requestAssist(command);
		    					
		    					faceHelper.event.stopEvent(e);
							}
							
							break;		    	    	
	    				case 190:
	    					var faceHelper = mw3.getFaceHelper(objectId);    					
	    					var command = faceHelper.getCommandString();
	    					
	    					command += '.';
	    					
	    					mw3.mouseX = e.srcElement.offsetLeft + 4;
	    					mw3.mouseY = e.srcElement.offsetTop + 18;
	    					
	    					faceHelper.lastCommandString = command;
	    					faceHelper.assistType = 'requestAssist';
	    					faceHelper.requestAssist(command);
	    					
	    					break;
	    					
	    				case 79 : // ctrl + shift + o
	    					if(e.ctrlKey && e.shiftKey){
	    						var faceHelper = mw3.getFaceHelper(objectId);
	    						var command = faceHelper.getCommandString();
	    						
	    						mw3.mouseX = e.srcElement.offsetLeft + 4;
	    						mw3.mouseY = e.srcElement.offsetTop + 18;
	
	    						faceHelper.assistType = 'showOrganizeImports';
	    						faceHelper.lastCommandString = command;
	    						faceHelper.showOrganizeImports(command);
	    						
	    						faceHelper.stopEvent(e);
	    					}
			    	    default :
			    	    	break;
	    					
			    	    
	    			}
	    		}
	   	    }
	    );
	    
	    
		var canon = require("pilot/canon"); 
		canon.addCommand({
		    name: "gotoleft",
		    bindKey: this.bindKey("Left", "Left|Ctrl-B"),
		    exec: function(env, args, request) {
		    	mw3.getFaceHelper(objectId).closeAssist();
		    	
		    	env.editor.navigateLeft(args.times);
		    }
		});
		canon.addCommand({
		    name: "gotoleft",
		    bindKey: this.bindKey("Right", "Right|Ctrl-F"),
		    exec: function(env, args, request) {
		    	mw3.getFaceHelper(objectId).closeAssist();
		    	
		    	env.editor.navigateRight(args.times);
		    }
		});
		canon.addCommand({
		    name: "golineup",
		    bindKey: this.bindKey("Up", "Up|Ctrl-P"),
		    exec: function(env, args, request) {
		    	var assist = mw3.getAutowiredObject('org.metaworks.example.ide.CodeAssist');
				
		    	if(assist != null)
					mw3.getFaceHelper(assist.__objectId).up();
				else  	
					env.editor.navigateUp(args.times);
		    }
		});
		canon.addCommand({
		    name: "golinedown",
		    bindKey: this.bindKey("Down", "Down|Ctrl-N"),
		    exec: function(env, args, request) {
		    	var assist = mw3.getAutowiredObject('org.metaworks.example.ide.CodeAssist');
				
				if(assist != null)
					mw3.getFaceHelper(assist.__objectId).down();
				else  	
					env.editor.navigateDown(args.times);
		    }
		});
		canon.addCommand({
		    name: "esckey",
		    bindKey: this.bindKey("Esc", "Esc"),
		    exec: function(env, args, request) {
		    	mw3.getFaceHelper(objectId).closeAssist();
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
	          
	    
    	if(object.code)
    		this.editor.getSession().setValue(object.code);
    	
    	if(object.compileErrors){
    		for(var i in object.compileErrors){
    			var compileError = object.compileErrors[i];

    			this.editor.getSession().setAnnotations([{ 
                    row: compileError.line -1, 
                    column: compileError.column -1, 
                    text: compileError.message, 
                    type: "error" 
                  }]); 
    		}
    	}
	}
}

org_metaworks_example_ide_SourceCode.prototype = {
	bindKey : function(win, mac) {
	    return {
	        win: win,
	        mac: mac,
	        sender: "editor"
	    };
	},
	change : function(event){
		if(!this.loadRequestAssist){
			var objectId = this.objectId;
			
			if (this.timeout) {
				clearTimeout(this.timeout);
			}
			
			this.timeout = setTimeout(function() {
				mw3.getFaceHelper(objectId).assistEventCall();
			}, 100);			
			
		}	
	},
	startLoading : function(serviceMethodName){
		mw3.log('startLoading : ' + serviceMethodName);
		
		if('requestAssist' == serviceMethodName){
			this.loadRequestAssist = true;
		}
	},
	endLoading : function(serviceMethodName){
		mw3.log('endLoading : ' + serviceMethodName);
		
		if('requestAssist' == serviceMethodName){			
			this.loadRequestAssist = false;
			
			var assist = mw3.getAutowiredObject('org.metaworks.example.ide.CodeAssist')
			
			if(assist != null){
				if(this.assistType == 'requestAssist'){
					$('#' + mw3._getObjectDivId(assist.__objectId)).children().show();
					
					this.assistEventCall();
				}else{
					if(assist.assistances.length > 1){
						$('#' + mw3._getObjectDivId(assist.__objectId)).children().show();
						
						this.assistEventCall();
					}else if(assist.assistances.length == 1){						
						this.organizeImports(assist.__objectId);
					}
				}
			}
		}
	},
	showError : function(message, serviceMethodName){
		if('requestAssist' == serviceMethodName)
			this.loadRequestAssist = false;		
	},
	getValue : function(){
		
		var object = mw3.objects[this.objectId];
		
		if(object==null){
			object = {code: [], className: this.className};
		}else{
			object.code = this.editor.getSession().getValue();	
		}
		//object.code = this.editor.getSession().doc.getAllLines();
		
		return object;
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
	requestAssist : function(command){
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
	closeAssist : function(){
		var assist = mw3.getAutowiredObject('org.metaworks.example.ide.CodeAssist');
		if(assist != null)
			mw3.removeObject(assist.__objectId);
		
	},
	assistEventCall : function(){
		
		var assist = mw3.getAutowiredObject('org.metaworks.example.ide.CodeAssist');
		
		if(assist){
			var command = this.getCommandString();
			
    		if(command.indexOf("import ") == 0)
    			command = command.substring("import ".length);
    		
    		if(command.indexOf("@") == 0)
    			command = command.substring("@".length);
    		
    		command = command.trim();
    		if(command != this.lastCommandString){
				this.lastCommandString = command;
				
				mw3.getFaceHelper(assist.__objectId).change(command);
			}
		}
		
		
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
	selectAssist : function(e){
		var assist = mw3.getAutowiredObject('org.metaworks.example.ide.CodeAssist');
    	
    	if(assist != null){
    		this.event.stopEvent(e);    		
    		
    		if(this.assistType == 'requestAssist'){
	    		var selectedValue = mw3.getFaceHelper(assist.__objectId).select();
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
	resize : function(e){
		this.editor.resize();	
	},
		
} 